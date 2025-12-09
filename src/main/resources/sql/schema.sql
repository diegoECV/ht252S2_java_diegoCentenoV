-- Script de creación de base de datos para el sistema de registro de equipos
-- Base de datos: hackaton

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS hackaton;
USE hackaton;

-- Tabla para almacenar la información de los equipos
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
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
