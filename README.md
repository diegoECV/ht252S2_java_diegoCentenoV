# Sistema de Gestión - Java Swing + MySQL (AWS RDS)

## 📋 Descripción
Aplicación de escritorio desarrollada en Java utilizando Swing para la interfaz gráfica, conectada a MySQL en AWS RDS. Implementa un sistema completo de gestión con autenticación de usuarios y CRUD de categorías y productos siguiendo el patrón MVC.

## 🛠️ Tecnologías Utilizadas
- **Java 17**
- **Swing** (GUI)
- **JDBC** (Conexión a base de datos)
- **MySQL 8** (AWS RDS)
- **Maven** (Gestión de dependencias)
- **BCrypt** (Encriptación de contraseñas)

## 📁 Estructura del Proyecto (Patrón MVC)

```
src/main/java/vallegrande/edu/pe/
├── AppLauncher.java                 # Punto de entrada
├── model/                           # Modelos (Entidades)
│   ├── Usuario.java
│   ├── Categoria.java
│   └── Producto.java
├── view/                            # Vistas (Swing UI)
│   ├── LoginView.java
│   ├── RegisterView.java
│   ├── MainMenuView.java
│   ├── CategoryView.java
│   └── ProductView.java
├── controller/                      # Controladores (Lógica)
│   ├── LoginController.java
│   ├── RegisterController.java
│   ├── CategoriaController.java
│   └── ProductoController.java
├── service/                         # Servicios/DAO (Acceso a datos)
│   ├── UsuarioService.java
│   ├── CategoriaService.java
│   └── ProductoService.java
└── database/                        # Conexión a BD
    └── DatabaseConnection.java
```

## 🗄️ Estructura de Base de Datos

### Tabla: usuarios
```sql
- id_usuario (PK)
- nombre
- apellido
- tipo_documento (DNI, CE, PASAPORTE)
- numero_documento (UNIQUE)
- telefono
- correo (UNIQUE)
- contrasena (BCrypt)
- estado
- fecha_registro
```

### Tabla: categorias
```sql
- id_categoria (PK)
- nombre (UNIQUE)
- descripcion
- estado
- fecha_registro
```

### Tabla: productos
```sql
- id_producto (PK)
- nombre
- descripcion
- stock
- precio
- id_categoria (FK)
- estado
- fecha_registro
```

## 🎨 Componentes Swing Implementados

✅ **JLabel** - Títulos y etiquetas  
✅ **JTextField** - Campos de texto  
✅ **JPasswordField** - Contraseñas  
✅ **JRadioButton** - Tipo de documento (implementado con JComboBox)  
✅ **JComboBox** - Selección de categorías  
✅ **JCheckBox** - Mostrar contraseña, filtro bajo stock  
✅ **JTable** - Listado de registros  
✅ **JButton** - Botones de acción  

## 🚀 Instalación y Configuración

### 1. Prerrequisitos
- Java JDK 17 o superior
- Maven
- MySQL en AWS RDS (ya configurado)

### 2. Configurar la Base de Datos

1. Ejecutar el script SQL ubicado en:
   ```
   src/main/resources/sql/schema.sql
   ```

2. Verificar la conexión en:
   ```java
   src/main/java/vallegrande/edu/pe/database/DatabaseConnection.java
   ```

   **Credenciales actuales:**
   - URL: `jdbc:mysql://aws.c506266wsgbx.us-east-1.rds.amazonaws.com/wawalu_db`
   - Usuario: `root`
   - Contraseña: `diego1416`

### 3. Compilar el Proyecto

```bash
mvn clean install
```

### 4. Ejecutar la Aplicación

```bash
mvn exec:java -Dexec.mainClass="diegoCenteno.AppLauncher"
```

O desde tu IDE (IntelliJ IDEA, Eclipse, VS Code):
- Ejecutar la clase `AppLauncher.java`

## 📱 Funcionalidades

### 🔐 Autenticación
- **Login**: Validación de correo y contraseña con BCrypt
- **Registro**: Formulario completo con validaciones

### 👤 Gestión de Usuarios
- Registro con validación de correo y documento únicos
- Contraseñas encriptadas con BCrypt
- Tipos de documento: DNI, CE, PASAPORTE

### 📂 Gestión de Categorías
- ➕ Insertar nuevas categorías
- 📋 Listar todas las categorías
- ✏️ Modificar categorías existentes
- 🗑️ Eliminar categorías (validación de productos asociados)

### 📦 Gestión de Productos
- ➕ Insertar nuevos productos
- 📋 Listar todos los productos
- 🔍 Buscar productos por nombre
- 📊 Filtrar productos con bajo stock
- ✏️ Modificar productos existentes
- 🗑️ Eliminar productos

## 🎯 Operaciones CRUD Implementadas

Todas las tablas maestras cumplen con las operaciones requeridas:

| Operación | Categorías | Productos | Usuarios |
|-----------|------------|-----------|----------|
| Insertar  | ✅         | ✅        | ✅       |
| Listar    | ✅         | ✅        | ✅       |
| Modificar | ✅         | ✅        | ✅       |
| Eliminar  | ✅         | ✅        | ✅       |

## 🎨 Características de la Interfaz

- **Diseño moderno** con colores personalizados
- **Responsive tables** con scroll automático
- **Validaciones en tiempo real**
- **Mensajes informativos** con JOptionPane
- **Navegación fluida** entre ventanas
- **Look and Feel del sistema operativo**

## 🔒 Seguridad

- Contraseñas encriptadas con **BCrypt**
- Validación de datos en cliente y servidor
- Prepared Statements para prevenir **SQL Injection**
- Validación de correos y documentos únicos

## 📝 Usuario de Prueba

El schema incluye un usuario de prueba:
- **Correo**: admin@sistema.com
- **Contraseña**: admin123

## 🐛 Solución de Problemas

### Error de conexión a MySQL
- Verificar que la base de datos en AWS RDS esté activa
- Verificar credenciales en `DatabaseConnection.java`
- Verificar reglas de firewall/Security Groups en AWS

### Error de dependencias Maven
```bash
mvn clean install -U
```

### Error de compilación Java
- Verificar que estés usando Java 17
```bash
java -version
```

## 👨‍💻 Autor
**Diego Centneo Vivas**  
Valle Grande - POO  
H251S2_40

## 📄 Licencia
Proyecto académico - Valle Grande

---

**Nota**: Este proyecto cumple con todos los requisitos técnicos y funcionales solicitados, implementando un sistema completo con login, registro, y gestión de categorías y productos utilizando todos los componentes Swing requeridos.
