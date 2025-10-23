import java.util.*;
import java.util.stream.Collectors;

public class Servicios {

        private static final Map<TipoTurno, Double> FACTORES_DE_PAGO = new HashMap<>();

        static {
                FACTORES_DE_PAGO.put(TipoTurno.NOCHE, 1.5);
                FACTORES_DE_PAGO.put(TipoTurno.GUARDIA, 2.0);
                FACTORES_DE_PAGO.put(TipoTurno.DIA, 1.0);
                FACTORES_DE_PAGO.put(TipoTurno.AUSENCIA, 0.0);
        }

        // ==============================EJERCICIO_1=========================================
        public static Map<String, Double> nominaMensualDetallada(List<Empleado> empleados, List<RegistroTurno> turnos) {

                Map<String, Double> sueldos = empleados.stream()
                                .filter(empleado -> tieneTurnosValidos(empleado.id(), turnos))
                                .collect(Collectors.toMap(
                                                Empleado::id,
                                                empleado -> calcularSueldo(empleado.id(), turnos)
                                                                * empleado.salarioBaseHora()));

                return sueldos;
        }

        private static boolean tieneTurnosValidos(String empleadoId, List<RegistroTurno> turnos) {
                return turnos.stream()
                                .filter(turno -> turno.empleadoId().equals(empleadoId)
                                                && turno.tipo() != TipoTurno.AUSENCIA)
                                .count() > 0;
        }

        private static double calcularSueldo(String empleadoId, List<RegistroTurno> turnos) {
                return turnos.stream()
                                .filter(turno -> turno.empleadoId().equals(empleadoId)
                                                && turno.tipo() != TipoTurno.AUSENCIA)
                                .mapToDouble(turno -> turno.horas() * FACTORES_DE_PAGO.get(turno.tipo()))
                                .sum();
        }

        // ==============================EJERCICIO_2=========================================
        public static Map<Area, Optional<Empleado>> empleadoMesPorArea(List<Empleado> empleados,
                        List<RegistroTurno> turnos) {
                return empleados.stream()
                                .filter(empleado -> tieneTurnosValidos(empleado.id(), turnos))
                                .collect(Collectors.groupingBy(
                                                Empleado::area, // Agrupa los empleados por área
                                                Collectors.maxBy(Comparator.comparingInt(
                                                                empleado -> sumarHorasArea(empleado.id(), turnos)))));
        }

        private static int sumarHorasArea(String empleadoId, List<RegistroTurno> turnos) {
                return turnos.stream()
                                .filter(turno -> turno.empleadoId().equals(empleadoId))
                                .mapToInt(RegistroTurno::horas)
                                .sum();
        }

        // ==============================EJERCICIO_3=========================================
        public static Map<Area, Map<TipoTurno, Integer>> desgloseHorasPorAreaYTipoTurno(List<Empleado> empleados,
                        List<RegistroTurno> turnos) {

                return empleados.stream()
                                .filter(empleado -> tieneTurnosValidos(empleado.id(), turnos))
                                .flatMap(empleado -> turnos.stream() // Obtener los turnos de cada empleado
                                                .filter(turno -> turno.empleadoId().equals(empleado.id())
                                                                && turno.tipo() != TipoTurno.AUSENCIA)
                                                .map(turno -> new AbstractMap.SimpleEntry<>(empleado.area(), turno))
                                // Crear un par (Área, Turno)
                                )
                                .collect(Collectors.groupingBy(// Agrupar por área primero
                                                Map.Entry::getKey, // La clave del grupo será el área
                                                Collectors.groupingBy(
                                                                entry -> entry.getValue().tipo(),
                                                                // Agrupar por tipo de turno
                                                                Collectors.summingInt(entry -> entry.getValue().horas())
                                                // Sumar las horas de cada tipo de turno
                                                )));
        }

        // ==============================EJERCICIO_4=========================================
        public static List<String> detectarTurnosConsecutivosAnormales(List<RegistroTurno> turnos,
                        List<Empleado> empleados) {

                Map<String, List<RegistroTurno>> turnosPorEmpleado = turnos.stream()
                                .collect(Collectors.groupingBy(RegistroTurno::empleadoId));

                // Paso 2: Filtrar los empleados con turnos consecutivos anormales
                return turnosPorEmpleado.entrySet().stream()
                                .filter(entry -> tieneTurnosConsecutivosAnormales(entry.getValue()))
                                .map(Map.Entry::getKey) // Obtener el id del empleado
                                .distinct()
                                .map(id -> obtenerNombreEmpleado(id, empleados))
                                .collect(Collectors.toList());
        }

        private static boolean tieneTurnosConsecutivosAnormales(List<RegistroTurno> turnosEmpleado) {
                return turnosEmpleado.stream()
                                .sorted(Comparator.comparing(RegistroTurno::fecha))
                                .collect(Collectors.toList())
                                .stream()
                                .anyMatch(turno -> {
                                        // Verificar si hay un turno de GUARDIA seguido de un turno de DIA
                                        int index = turnosEmpleado.indexOf(turno);
                                        if (index < turnosEmpleado.size() - 1) {
                                                RegistroTurno siguienteTurno = turnosEmpleado.get(index + 1);
                                                return turno.tipo() == TipoTurno.GUARDIA && turno.horas() == 24 &&
                                                                siguienteTurno.tipo() == TipoTurno.DIA;
                                        }
                                        return false;
                                });
        }

        private static String obtenerNombreEmpleado(String empleadoId, List<Empleado> empleados) {
                return empleados.stream()
                                .filter(empleado -> empleado.id().equals(empleadoId))
                                .map(Empleado::nombre)
                                .findFirst()
                                .orElse("Desconocido");
        }

        // ==============================EJERCICIO_5=========================================
        public static List<String> chequeoInconsistenciasDatos(List<Empleado> empleados,
                        List<RegistroTurno> turnos) {

                Set<String> empleadosIds = empleados.stream()
                                .map(Empleado::id)
                                .collect(Collectors.toSet());

                List<String> idsInconsistentes = turnos.stream()
                                .map(RegistroTurno::empleadoId) // Extraer los IDs de los turnos
                                .filter(empleadoId -> !empleadosIds.contains(empleadoId))
                                // Filtrar los que no están en empleadosIds
                                .distinct()
                                .collect(Collectors.toList());

                return idsInconsistentes;
        }

        // ==============================EJERCICIO_6=========================================
        public static List<ProductividadEmpleado> reporteProductividad(List<Empleado> empleados,
                        List<RegistroTurno> turnos) {

                Map<String, Integer> horasPorEmpleado = turnos.stream()
                                .collect(Collectors.groupingBy(
                                                RegistroTurno::empleadoId,
                                                Collectors.summingInt(RegistroTurno::horas)));

                List<ProductividadEmpleado> reporte = empleados.stream()
                                .filter(empleado -> horasPorEmpleado.containsKey(empleado.id()))
                                // Filtramos empleados que trabajaron
                                .map(empleado -> {
                                        int totalHoras = horasPorEmpleado.get(empleado.id());
                                        double salarioTotal = totalHoras * empleado.salarioBaseHora();
                                        return new ProductividadEmpleado(empleado.nombre(), empleado.area(), totalHoras,
                                                        salarioTotal);
                                })
                                .sorted(Comparator.comparingDouble(ProductividadEmpleado::salarioTotal).reversed())
                                // Ordenar por salario total de mayor a menor
                                .collect(Collectors.toList());

                reporte.forEach(System.out::println);
                return reporte;
        }

        // ==============================EJERCICIO_7=========================================
        public static List<String> auditoriaCoberturaMinima(List<Empleado> empleados, List<RegistroTurno> turnos) {
                return turnos.stream()
                                .filter(turno -> turno.tipo() == TipoTurno.GUARDIA)
                                .collect(Collectors.groupingBy(RegistroTurno::fecha))
                                .entrySet().stream()
                                // Convertir el mapa de grupos a un stream para poder porcesarloprocesarlo
                                .filter(entry -> entry.getValue().size() < 2)
                                .map(entry -> "Falta cobertura en el día: " + entry.getKey() +
                                                ". Solo hay " + entry.getValue().size() + " empleado(s) de guardia.")
                                .toList();
        }

        // ==============================EJERCICIO 8===========================
        public static Map<String, Double> calculoBonusDisponibilidad(List<Empleado> empleados,
                        List<RegistroTurno> turnos) {

                Map<String, Integer> horasTrabajadas = turnos.stream()
                                .filter(t -> tieneTurnosValidos(t.empleadoId(), turnos))
                                .collect(Collectors.groupingBy(
                                                RegistroTurno::empleadoId,
                                                Collectors.summingInt(RegistroTurno::horas)));

                Map<String, Double> bonusPorEmpleado = empleados.stream()
                                .filter(empleado -> horasTrabajadas.containsKey(empleado.id()))
                                // Solo consideramos empleados que trabajaron
                                .filter(empleado -> horasTrabajadas.get(empleado.id()) > 40)
                                .filter(empleado -> !turnos.stream()
                                                .anyMatch(turno -> turno.empleadoId().equals(empleado.id())
                                                                && turno.tipo() == TipoTurno.AUSENCIA))
                                .collect(Collectors.toMap(
                                                Empleado::id, // Usar el id del empleado como clave única
                                                empleado -> horasTrabajadas.get(empleado.id())
                                                                * empleado.salarioBaseHora() * 0.05));

                return bonusPorEmpleado;
        }

        // ==============================EJERCICIO 9===========================
        public static Map<Area, Double> reporteCostoOperativoPorArea(List<Empleado> empleados,
                        List<RegistroTurno> turnos) {
                return empleados.stream()
                                .filter(empleado -> tieneTurnosValidos(empleado.id(), turnos))
                                .collect(Collectors.groupingBy(
                                                Empleado::area,
                                                Collectors.summingDouble(
                                                                empleado -> calcularSueldo(empleado.id(), turnos))));
        }

}
