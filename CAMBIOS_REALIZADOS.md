# Cambios Realizados - Hackathon 251-S2
## Inventario de Computadoras

### Fecha: 08/12/2025

## Resumen de Modificaciones

Se han realizado las siguientes modificaciones para adaptar el sistema al caso de **Inventario de Computadoras** según los criterios del Hackathon 251-S2.

---

## 1. Modelo de Datos (Producto.java)

### Campos Modificados:
- **codigo**: Código único del equipo
- **tipoEquipo**: Tipo de equipo (Laptop, Desktop, All-in-One, Servidor, etc.)
- **marca**: Marca del equipo (HP, ASUS, Dell, Lenovo, Acer, etc.)
- **modelo**: Modelo específico del equipo
- **sistemaOperativo**: Sistema operativo instalado (Windows 10, Windows 11, Linux, MacOS)
- **almacenamiento**: Almacenamiento en GB
- **ram**: Memoria RAM en GB
- **estado**: Estado del equipo (Activo/Inactivo)
- **fechaMantenimiento**: Fecha de mantenimiento programado
- **fechaRegistro**: Fecha de registro automática
- **fechaActualizacion**: Fecha de última actualización automática

---

## 2. Base de Datos (schema.sql)

### Tabla: `equipos`
```sql
CREATE TABLE IF NOT EXISTS equipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    tipo VARCHAR(50) NOT NULL,
    marcas VARCHAR(50) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    so VARCHAR(50) NOT NULL,
    almacenamiento INT NOT NULL COMMENT 'Almacenamiento en GB',
    ram INT NOT NULL COMMENT 'RAM en GB',
    estado VARCHAR(50) NOT NULL,
    mantenimiento DATE NOT NULL COMMENT 'Fecha de mantenimiento',
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_codigo (codigo),
    INDEX idx_tipo (tipo),
    INDEX idx_marcas (marcas),
    INDEX idx_estado (estado),
    INDEX idx_fecha_mantenimiento (mantenimiento)
)
```

---

## 3. Servicio (ProductoService.java)

### Operaciones CRUD Implementadas:

- **crear()**: Registra un nuevo equipo en la base de datos
- **listarTodos()**: Lista todos los equipos registrados
- **buscarPorId()**: Busca un equipo por su ID
- **buscarPorTipo()**: Busca equipos por tipo
- **buscarPorCodigo()**: Busca equipos por código
- **actualizar()**: Actualiza la información de un equipo
- **eliminar()**: Elimina un equipo de la base de datos
- **obtenerEquiposProximoMantenimiento()**: Obtiene equipos con mantenimiento próximo

---

## 4. Vista (ProductView.java)

### Componentes de la Interfaz:

#### Campos de Entrada:
- **JTextField**: Código, Almacenamiento (GB), RAM (GB), Fecha de Mantenimiento
- **JComboBox**: Tipo de equipo, Marca, Modelo, Sistema Operativo, Estado

#### Botones:
- **Registrar**: Agrega un nuevo equipo
- **Actualizar**: Modifica los datos de un equipo existente
- **Eliminar**: Elimina un equipo seleccionado
- **Limpiar**: Limpia el formulario
- **Cerrar**: Cierra la ventana

#### Tabla:
Muestra los siguientes campos:
- ID
- Código
- Tipo
- Marca
- Modelo
- SO
- Almacenamiento (GB)
- RAM (GB)
- Estado
- Fecha de Mantenimiento
- Fecha de Registro

---

## 5. Validaciones Implementadas

- Validación de campos obligatorios (código, tipo, almacenamiento, RAM, fecha de mantenimiento)
- Validación de formato de fecha (dd/MM/yyyy)
- Validación de valores numéricos para almacenamiento y RAM
- Confirmación antes de eliminar un equipo

---

## Tecnologías Utilizadas

- **Java Swing**: Para la interfaz gráfica
- **JDBC**: Para la conexión con la base de datos
- **MySQL**: Base de datos relacional
- **MVC**: Patrón de diseño Modelo-Vista-Controlador

---

## Instrucciones de Uso

1. Asegúrese de tener MySQL instalado y en ejecución
2. Ejecute el script `schema.sql` para crear la base de datos y la tabla
3. Configure las credenciales de conexión en `DatabaseConnection.java`
4. Compile y ejecute el proyecto
5. La ventana principal mostrará el formulario de registro y la lista de equipos

---

## Notas Adicionales

- El sistema cumple con todos los requisitos especificados en el Hackathon 251-S2
- Se implementaron operaciones CRUD completas
- La interfaz es intuitiva y fácil de usar
- Los datos se almacenan en una base de datos MySQL en AWS RDS (según configuración)

