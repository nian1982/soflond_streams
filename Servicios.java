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
                        empleado -> calcularSueldo(empleado.id(), turnos)));

        return sueldos;
    }

    private static boolean tieneTurnosValidos(String empleadoId, List<RegistroTurno> turnos) {
        return turnos.stream()
                .filter(turno -> turno.empleadoId().equals(empleadoId) && turno.tipo() != TipoTurno.AUSENCIA)
                .count() > 0;
    }

    private static double calcularSueldo(String empleadoId, List<RegistroTurno> turnos) {
        return turnos.stream()
                .filter(turno -> turno.empleadoId().equals(empleadoId) && turno.tipo() != TipoTurno.AUSENCIA)
                .mapToDouble(turno -> turno.horas() * FACTORES_DE_PAGO.get(turno.tipo()))
                .sum();
    }

    // ==============================EJERCICIO_2=========================================
    public static List<String> empleadoMesPorArea(List<Empleado> empleados, List<RegistroTurno> turnos) {
        return empleados.stream()
                .filter(empleado -> tieneTurnosValidos(empleado.id(), turnos)) // Filtra empleados con turnos válidos
                .collect(Collectors.groupingBy(
                        Empleado::area, // Agrupa los empleados por área
                        Collectors.maxBy(Comparator.comparingInt(empleado -> sumarHorasArea(empleado.id(), turnos)))))
                // Dentro de cada área, selecciona al empleado con más horas trabajadas
                .entrySet().stream() // Convertir el resultado de la agrupación en un flujo de entradas del mapa
                                     // (área -> empleado con más horas)
                .map(entry -> {
                    // Extraemos el empleado con más horas (usamos orElseThrow para garantizar que
                    // siempre habrá un valor)
                    Empleado empleado = entry.getValue().orElseThrow();
                    String nombreEmpleado = empleado.nombre();
                    String area = entry.getKey().toString();
                    int horas = sumarHorasArea(empleado.id(), turnos);

                    return "Nombre: " + nombreEmpleado + ", Área: " + area + ", Horas: " + horas;
                })
                .toList();
    }

    public static int sumarHorasArea(String empleadoId, List<RegistroTurno> turnos) {
        List<RegistroTurno> turnosEmpleado = turnos.stream()
                .filter(turno -> turno.empleadoId().equals(empleadoId))
                .toList();
        
        int totalHoras = turnosEmpleado.stream()
                .mapToInt(RegistroTurno::horas)
                .sum();

        return totalHoras;
    }

    // ==============================EJERCICIO_3=========================================
    public static Map<Area, Map<TipoTurno, Integer>> desgloseHorasPorAreaYTipoTurno(List<Empleado> empleados,
            List<RegistroTurno> turnos) {

        return empleados.stream() // Iniciamos un flujo de empleados
                .filter(empleado -> tieneTurnosValidos(empleado.id(), turnos)) // Filtramos solo los empleados con
                                                                               // turnos válidos
                .flatMap(empleado -> // Obtener los turnos de cada empleado
                turnos.stream()
                        .filter(turno -> turno.empleadoId().equals(empleado.id()) && turno.tipo() != TipoTurno.AUSENCIA)
                        .map(turno -> new AbstractMap.SimpleEntry<>(empleado.area(), turno)) // Crear un par con el área
                                                                                             // y el turno
                )
                .collect(Collectors.groupingBy( // Agrupar por área
                        Map.Entry::getKey, // La clave es el área
                        Collectors.groupingBy( // Dentro de cada área, agrupar por tipo de turno
                                entry -> entry.getValue().tipo(), // Tipo de turno
                                Collectors.summingInt(entry -> entry.getValue().horas()) // Sumar las horas de cada ipo
                                                                                         // de turno
                        )));
    }

    // ==============================EJERCICIO_7=========================================
    public static List<String> auditoriaCoberturaMinima(List<Empleado> empleados, List<RegistroTurno> turnos) {
        return turnos.stream()
                .filter(turno -> turno.tipo() == TipoTurno.GUARDIA)
                .collect(Collectors.groupingBy(RegistroTurno::fecha))
                .entrySet().stream() // Convertir el mapa de grupos a un stream para poder porcesarloprocesarlo
                .filter(entry -> entry.getValue().size() < 2) // Filtrar días donde hay menos de 2 empleados
                .map(entry -> "Falta cobertura en el día: " + entry.getKey() +
                        ". Solo hay " + entry.getValue().size() + " empleado(s) de guardia.")
                .toList();
    }

    // ==============================EJERCICIO 9===========================
    public static Map<Area, Double> reporteCostoOperativoPorArea(List<Empleado> empleados, List<RegistroTurno> turnos) {
        return empleados.stream()
                .filter(empleado -> tieneTurnosValidos(empleado.id(), turnos))
                .collect(Collectors.groupingBy(
                        Empleado::area,
                        Collectors.summingDouble(empleado -> calcularSueldo(empleado.id(), turnos))));
    }

}
