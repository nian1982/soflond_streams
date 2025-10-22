import java.time.LocalDate;
import java.util.List;

public class Common {

    public static void separador() {
        System.out.println("\n"+"=".repeat(100));
    }

    public static <T> void imprimirListas(List<T> lista) {
        lista.forEach(System.out::println);
    }

    public static List<Empleado> getEmpleados() {
        List<Empleado> empleados = List.of(
                // CARDIOLOGIA
                new Empleado("E01", "Ana Gómez", Area.CARDIOLOGIA, 25.0),
                new Empleado("E04", "Juan Mora", Area.CARDIOLOGIA, 22.0),
                new Empleado("E12", "Ricardo León", Area.CARDIOLOGIA, 26.5),
                new Empleado("E16", "Patricia Torres", Area.CARDIOLOGIA, 27.0),
                new Empleado("E17", "Raúl Pérez", Area.CARDIOLOGIA, 30.0),
                new Empleado("E18", "Elena Ruiz", Area.CARDIOLOGIA, 33.5),
                new Empleado("E19", "Tomás Sánchez", Area.CARDIOLOGIA, 29.5),

                // CIRUGIA
                new Empleado("E02", "Luis Vera", Area.CIRUGIA, 45.0),
                new Empleado("E09", "Isabel Luna", Area.CIRUGIA, 48.0),
                new Empleado("E20", "Mario Gómez", Area.CIRUGIA, 41.0),
                new Empleado("E21", "Carmen Morales", Area.CIRUGIA, 39.0),
                new Empleado("E22", "David Jiménez", Area.CIRUGIA, 50.0),
                new Empleado("E23", "Olga López", Area.CIRUGIA, 42.0),
                new Empleado("E24", "José García", Area.CIRUGIA, 46.0),

                // PEDIATRIA
                new Empleado("E03", "Carlos Rivas", Area.PEDIATRIA, 28.0),
                new Empleado("E10", "Pedro Navas", Area.PEDIATRIA, 29.0),
                new Empleado("E25", "Lucía Méndez", Area.PEDIATRIA, 32.0),
                new Empleado("E26", "David Castro", Area.PEDIATRIA, 26.5),
                new Empleado("E27", "Andrea Morales", Area.PEDIATRIA, 34.0),
                new Empleado("E28", "Víctor Pérez", Area.PEDIATRIA, 31.0),
                new Empleado("E29", "Gabriela Martínez", Area.PEDIATRIA, 27.5),

                // ONCOLOGIA
                new Empleado("E05", "Sofía Castro", Area.ONCOLOGIA, 35.0),
                new Empleado("E13", "Mónica Marín", Area.ONCOLOGIA, 36.0),
                new Empleado("E30", "Martín Vázquez", Area.ONCOLOGIA, 40.0),
                new Empleado("E31", "Ana Sánchez", Area.ONCOLOGIA, 38.0),
                new Empleado("E32", "Ricardo Pérez", Area.ONCOLOGIA, 42.5),
                new Empleado("E33", "Patricia Fernández", Area.ONCOLOGIA, 39.5),
                new Empleado("E34", "Jesús Romero", Area.ONCOLOGIA, 41.0),

                // URGENCIAS
                new Empleado("E06", "Miguel Peña", Area.URGENCIAS, 38.0),
                new Empleado("E11", "Valeria Sol", Area.URGENCIAS, 40.0),
                new Empleado("E35", "Luis Hernández", Area.URGENCIAS, 36.0),
                new Empleado("E36", "Sara Ruiz", Area.URGENCIAS, 34.5),
                new Empleado("E37", "Javier Soto", Area.URGENCIAS, 33.0),
                new Empleado("E38", "Nerea Martín", Area.URGENCIAS, 32.5),
                new Empleado("E39", "Carlos Delgado", Area.URGENCIAS, 37.0),

                // ADMINISTRACION
                new Empleado("E07", "Laura Díaz", Area.ADMINISTRACION, 18.0),
                new Empleado("E14", "Andrés Salas", Area.ADMINISTRACION, 19.5),
                new Empleado("E40", "Fernanda López", Area.ADMINISTRACION, 22.0),
                new Empleado("E41", "Pablo Sánchez", Area.ADMINISTRACION, 24.0),
                new Empleado("E42", "María Rodríguez", Area.ADMINISTRACION, 23.0),
                new Empleado("E43", "Daniela Cruz", Area.ADMINISTRACION, 21.5),
                new Empleado("E44", "Felipe García", Area.ADMINISTRACION, 25.0),

                // RADIOLOGIA
                new Empleado("E08", "Fernando Gil", Area.RADIOLOGIA, 33.0),
                new Empleado("E15", "Gabriela Paz", Area.RADIOLOGIA, 34.5),
                new Empleado("E45", "Antonio Morales", Area.RADIOLOGIA, 37.0),
                new Empleado("E46", "Isabel Fernández", Area.RADIOLOGIA, 30.5),
                new Empleado("E47", "Raúl García", Area.RADIOLOGIA, 32.0),
                new Empleado("E48", "Sonia López", Area.RADIOLOGIA, 29.0),
                new Empleado("E49", "Ricardo Sánchez", Area.RADIOLOGIA, 35.0));

        return empleados;
    }

    public static List<RegistroTurno> getTurnos() {
        return List.of(
                // CARDIOLOGIA
                new RegistroTurno("E01", LocalDate.of(2024, 10, 1), TipoTurno.NOCHE, 12),
                new RegistroTurno("E04", LocalDate.of(2024, 10, 1), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E12", LocalDate.of(2024, 10, 1), TipoTurno.DIA, 8),
                new RegistroTurno("E16", LocalDate.of(2024, 10, 2), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E17", LocalDate.of(2024, 10, 2), TipoTurno.DIA, 8),
                new RegistroTurno("E18", LocalDate.of(2024, 10, 3), TipoTurno.NOCHE, 12),
                new RegistroTurno("E19", LocalDate.of(2024, 10, 3), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E01", LocalDate.of(2024, 10, 4), TipoTurno.DIA, 8),
                new RegistroTurno("E16", LocalDate.of(2024, 10, 5), TipoTurno.NOCHE, 12),
                new RegistroTurno("E17", LocalDate.of(2024, 10, 5), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E18", LocalDate.of(2024, 10, 6), TipoTurno.DIA, 8),
                new RegistroTurno("E19", LocalDate.of(2024, 10, 6), TipoTurno.AUSENCIA, 0),

                // CIRUGIA
                new RegistroTurno("E02", LocalDate.of(2024, 10, 1), TipoTurno.NOCHE, 12),
                new RegistroTurno("E09", LocalDate.of(2024, 10, 1), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E20", LocalDate.of(2024, 10, 2), TipoTurno.DIA, 8),
                new RegistroTurno("E21", LocalDate.of(2024, 10, 2), TipoTurno.NOCHE, 12),
                new RegistroTurno("E22", LocalDate.of(2024, 10, 3), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E23", LocalDate.of(2024, 10, 3), TipoTurno.DIA, 8),
                new RegistroTurno("E24", LocalDate.of(2024, 10, 4), TipoTurno.NOCHE, 12),
                new RegistroTurno("E02", LocalDate.of(2024, 10, 5), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E09", LocalDate.of(2024, 10, 5), TipoTurno.DIA, 8),
                new RegistroTurno("E20", LocalDate.of(2024, 10, 6), TipoTurno.AUSENCIA, 0),
                new RegistroTurno("E21", LocalDate.of(2024, 10, 6), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E22", LocalDate.of(2024, 10, 7), TipoTurno.NOCHE, 12),

                // PEDIATRIA
                new RegistroTurno("E03", LocalDate.of(2024, 10, 1), TipoTurno.DIA, 8),
                new RegistroTurno("E10", LocalDate.of(2024, 10, 2), TipoTurno.NOCHE, 12),
                new RegistroTurno("E25", LocalDate.of(2024, 10, 2), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E26", LocalDate.of(2024, 10, 3), TipoTurno.DIA, 8),
                new RegistroTurno("E27", LocalDate.of(2024, 10, 4), TipoTurno.NOCHE, 12),
                new RegistroTurno("E28", LocalDate.of(2024, 10, 4), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E29", LocalDate.of(2024, 10, 5), TipoTurno.DIA, 8),
                new RegistroTurno("E10", LocalDate.of(2024, 10, 5), TipoTurno.AUSENCIA, 0),
                new RegistroTurno("E25", LocalDate.of(2024, 10, 6), TipoTurno.NOCHE, 12),
                new RegistroTurno("E26", LocalDate.of(2024, 10, 6), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E27", LocalDate.of(2024, 10, 7), TipoTurno.DIA, 8),

                // ONCOLOGIA
                new RegistroTurno("E05", LocalDate.of(2024, 10, 1), TipoTurno.NOCHE, 12),
                new RegistroTurno("E13", LocalDate.of(2024, 10, 1), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E30", LocalDate.of(2024, 10, 2), TipoTurno.DIA, 8),
                new RegistroTurno("E31", LocalDate.of(2024, 10, 2), TipoTurno.NOCHE, 12),
                new RegistroTurno("E32", LocalDate.of(2024, 10, 3), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E33", LocalDate.of(2024, 10, 3), TipoTurno.DIA, 8),
                new RegistroTurno("E34", LocalDate.of(2024, 10, 4), TipoTurno.NOCHE, 12),
                new RegistroTurno("E05", LocalDate.of(2024, 10, 5), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E13", LocalDate.of(2024, 10, 5), TipoTurno.DIA, 8),
                new RegistroTurno("E30", LocalDate.of(2024, 10, 6), TipoTurno.AUSENCIA, 0),
                new RegistroTurno("E31", LocalDate.of(2024, 10, 6), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E32", LocalDate.of(2024, 10, 7), TipoTurno.NOCHE, 12),

                // URGENCIAS
                new RegistroTurno("E06", LocalDate.of(2024, 10, 1), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E11", LocalDate.of(2024, 10, 2), TipoTurno.DIA, 8),
                new RegistroTurno("E35", LocalDate.of(2024, 10, 2), TipoTurno.NOCHE, 12),
                new RegistroTurno("E36", LocalDate.of(2024, 10, 3), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E37", LocalDate.of(2024, 10, 3), TipoTurno.DIA, 8),
                new RegistroTurno("E38", LocalDate.of(2024, 10, 4), TipoTurno.NOCHE, 12),
                new RegistroTurno("E39", LocalDate.of(2024, 10, 5), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E06", LocalDate.of(2024, 10, 5), TipoTurno.DIA, 8),
                new RegistroTurno("E11", LocalDate.of(2024, 10, 6), TipoTurno.NOCHE, 12),
                new RegistroTurno("E35", LocalDate.of(2024, 10, 7), TipoTurno.AUSENCIA, 0),
                new RegistroTurno("E36", LocalDate.of(2024, 10, 7), TipoTurno.GUARDIA, 24),

                // ADMINISTRACION
                new RegistroTurno("E07", LocalDate.of(2024, 10, 1), TipoTurno.DIA, 8),
                new RegistroTurno("E14", LocalDate.of(2024, 10, 2), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E40", LocalDate.of(2024, 10, 2), TipoTurno.NOCHE, 12),
                new RegistroTurno("E41", LocalDate.of(2024, 10, 3), TipoTurno.DIA, 8),
                new RegistroTurno("E42", LocalDate.of(2024, 10, 3), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E43", LocalDate.of(2024, 10, 4), TipoTurno.DIA, 8),
                new RegistroTurno("E44", LocalDate.of(2024, 10, 4), TipoTurno.NOCHE, 12),
                new RegistroTurno("E07", LocalDate.of(2024, 10, 5), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E14", LocalDate.of(2024, 10, 5), TipoTurno.DIA, 8),
                new RegistroTurno("E40", LocalDate.of(2024, 10, 6), TipoTurno.NOCHE, 12),

                // RADIOLOGIA
                new RegistroTurno("E08", LocalDate.of(2024, 10, 1), TipoTurno.DIA, 8),
                new RegistroTurno("E15", LocalDate.of(2024, 10, 1), TipoTurno.NOCHE, 12),
                new RegistroTurno("E45", LocalDate.of(2024, 10, 2), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E46", LocalDate.of(2024, 10, 2), TipoTurno.DIA, 8),
                new RegistroTurno("E47", LocalDate.of(2024, 10, 3), TipoTurno.NOCHE, 12),
                new RegistroTurno("E48", LocalDate.of(2024, 10, 3), TipoTurno.GUARDIA, 24),
                new RegistroTurno("E49", LocalDate.of(2024, 10, 4), TipoTurno.DIA, 8));
    }

}
