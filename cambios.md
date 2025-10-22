## Ejercicio 2
* Implementacion del optional dentro del stream
```
     public static List<String> empleadoMesPorArea(List<Empleado> empleados,
         List<RegistroTurno> turnos) {
         return empleados.stream()
         .filter(empleado -> tieneTurnosValidos(empleado.id(), turnos))  Filtra
         empleados con
          turnos válidos
         .collect(Collectors.groupingBy(
         Empleado::area,  Agrupa por área
         Collectors.maxBy(Comparator.comparingInt(
         empleado -> sumarHorasArea(empleado.id(), turnos)))))
          Para cada área, se mapea la informacion del empleado con mas horas
         .entrySet().stream()
         .filter(entry -> entry.getValue().isPresent())  Filtramos áreas con
         empleados válidos
         .map(entry -> {
         Empleado empleado = entry.getValue().get();  Obtener el empleado si existe
         int horas = sumarHorasArea(empleado.id(), turnos);
         return String.format("Nombre: %s, Área: %s, Horas: %d", empleado.nombre(),
         entry.getKey(), horas);
         })
         .toList();
         }
        
    public static void empleadoDelMesPorArea() {
        Common.separador();
        System.out.println("\n2. Empleado del Mes por Área:\n");

        List<Empleado> empleados = Common.getEmpleados();
        List<RegistroTurno> turnos = Common.getTurnos();

        Servicios.empleadoMesPorArea(empleados, turnos).forEach(System.out::println);
    }
```