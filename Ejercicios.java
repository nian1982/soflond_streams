import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Ejercicios {

    public static void nominaMensualDetallada() {
        Common.separador();
        System.out.println("\n1. Nómina Mensual Detallada:\n");

        List<Empleado> empleados = Common.getEmpleados();
        List<RegistroTurno> turnos = Common.getTurnos();

        Servicios.nominaMensualDetallada(empleados, turnos)
                .forEach((id, sueldo) -> System.out.println("Empleado: " + id + ", Sueldo: " + sueldo));
    }

    public static void empleadoDelMesPorArea() {
        Common.separador();
        System.out.println("\n2. Empleado del Mes por Área:\n");

        List<Empleado> empleados = Common.getEmpleados();
        List<RegistroTurno> turnos = Common.getTurnos();

        Map<Area, Optional<Empleado>> empleadosPorArea = Servicios.empleadoMesPorArea(empleados, turnos);

        empleadosPorArea.forEach((area, empleado) -> {
            if (empleado.isPresent()) {
                Empleado e = empleado.get();
                System.out.println(String.format("Área: %s - Empleado del Mes: %s", area, e.nombre()));
            }
        });
    }

    public static void desgloseDeHorasPorAreaYTipoTurno() {
        Common.separador();
        System.out.println("\n3. Desglose de Horas por Área y Tipo de Turno:\n");

        List<Empleado> empleados = Common.getEmpleados();
        List<RegistroTurno> turnos = Common.getTurnos();

        Map<Area, Map<TipoTurno, Integer>> desglose = Servicios.desgloseHorasPorAreaYTipoTurno(empleados, turnos);

        desglose.forEach((area, tipoTurnoMap) -> {
            System.out.println("Área: " + area);
            tipoTurnoMap.forEach((tipoTurno, horas) -> {
                System.out.println("  Tipo de Turno: " + tipoTurno + ", Horas: " + horas);
            });
        });
    }

    public static void auditoriaDeCoberturaMinima() {
        Common.separador();
        System.out.println("\n7. Auditoría de Cobertura Mínima:\n");

        List<Empleado> empleados = Common.getEmpleados();
        List<RegistroTurno> turnos = Common.getTurnos();

        List<String> resultados = Servicios.auditoriaCoberturaMinima(empleados, turnos);

        Common.imprimirListas(resultados);
    }

    public static void reporteCostoOperativoPorArea() {
        Common.separador();
        System.out.println("\n9. Reporte de Costo Operativo por Área:\n");

        List<Empleado> empleados = Common.getEmpleados();
        List<RegistroTurno> turnos = Common.getTurnos();
        Map<Area, Double> costoPorArea = Servicios.reporteCostoOperativoPorArea(empleados, turnos);

        costoPorArea.forEach((area, costo) -> {
            System.out.println("Área: " + area + ", Costo Total: " + costo);
        });
    }

}
