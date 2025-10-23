## Comandos
* Compilar desde fuera del directorio raiz
* `javac -d . *.java`

## Ejercicios

1. Nómina Mensual Detallada: Calcula el salario mensual para cada empleado. La lógica de pago es: `Turno de DÍA paga 1x, NOCHE 1.5x y GUARDIA 2.0x` sobre el salarioBaseHora. El resultado debe ser un `Map<String, Double>` que relacione el nombre del empleado con su salario total.
2. Empleado del Mes por Área: En cada área, encuentra al empleado que acumuló más horas trabajadas (excluyendo ausencias). El resultado debe ser un `Map<Area, Optional<Empleado>>`.
3. Desglose de Horas por Área y Tipo de Turno: Crea una estructura de datos anidada (`Map<Area, Map<TipoTurno, Integer>>`) que muestre el total de horas para cada tipo de turno dentro de cada área.
4. Detección de Turnos Consecutivos Anormales: Identifica a los empleados que trabajaron un turno de DÍA inmediatamente el día después de haber realizado una GUARDIA de 24 horas. Devuelve una lista con los nombres de dichos empleados.
5. Chequeo de Inconsistencias de Datos: Genera una lista (`List<String>`) con los IDs de empleado presentes en registrosMes pero que no existen en la lista de personal.
6. Reporte de Productividad: Crea una lista de un tipo anónimo o un record personalizado llamado ProductividadEmpleado(String nombre, Area area, int totalHoras, double salarioTotal) para todos los empleados que trabajaron, ordenada de mayor a menor por salario total.
7. Auditoría de Cobertura Mínima: La clínica requiere al menos 2 empleados en turno de GUARDIA en cualquier día que haya este tipo de turno. Identifica las fechas (`List<java.time.LocalDate>`) en las que no se cumplió esta regla.
8. Cálculo de Bonus por Disponibilidad: Se otorga un bonus del 5% sobre el salario total (calculado en el ejercicio 1) a los empleados que no tuvieron ninguna AUSENCIA y trabajaron más de 40 horas en total. Genera un `Map<String, Double>` con el nombre del empleado y el monto del bonus.
9. Reporte de Costo Operativo por Área: Calcula el costo total de la nómina para cada área. Este costo es la suma de los salarios de todos los turnos del personal de esa área (usando los multiplicadores del ejercicio 1). El resultado debe ser un `Map<Area, Double>` ordenado de mayor a menor costo.
