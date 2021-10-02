CREATE DATABASE vacunas;

USE vacunas;

CREATE TABLE Vacunas_Aplicadas (
    nombre VARCHAR(25) NOT NULL,
    apellido VARCHAR(25) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    vacuna VARCHAR(25) NOT NULL,
    primera_dosis DATE NOT NULL,
    segunda_dosis DATE,
    cantidad_dosis INT UNSIGNED NOT NULL,
    mail VARCHAR(50) NOT NULL,
    dni INT UNSIGNED NOT NULL,
    provincia VARCHAR(25) NOT NULL,
    region_sanitaria INT UNSIGNED NOT NULL,

    CONSTRAINT pk_dni
    PRIMARY KEY (dni)
) ENGINE=InnoDB;

CREATE TABLE Vacunas_Entregadas (
    provincia VARCHAR(25) NOT NULL,
    vacuna VARCHAR(25) NOT NULL,
    cantidad INT UNSIGNED NOT NULL,

    CONSTRAINT pk_entrega
    PRIMARY KEY (provincia, vacuna)
) ENGINE=InnoDB

CREATE TABLE Administradores (
    usuario VARCHAR(25) NOT NULL,
    contraseña CHAR(32) NOT NULL,
    nombre VARCHAR(25) NOT NULL,
    apellido VARCHAR(25) NOT NULL,
    mail VARCHAR(50) NOT NULL,
    dni INT UNSIGNED NOT NULL,
    telefono INT UNSIGNED NOT NULL,

    CONSTRAINT pk_admin
    PRIMRAY KEY (usuario)
) ENGINE=InnoDB

CREATE TABLE Usuarios (
    usuario VARCHAR(25) NOT NULL,
    contraseña CHAR(32) NOT NULL,
    nombre VARCHAR(25) NOT NULL,
    apellido VARCHAR(25) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    mail VARCHAR(50) NOT NULL,
    dni INT UNSIGNED NOT NULL,
    telefono INT UNSIGNED NOT NULL,
    domicilio VARCHAR(50) NOT NULL,

    CONSTRAINT pk_usuario
    PRIMARY KEY usuario    
)

CREATE USER "admin"@"localhost" IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON vacunas.* TO "admin"@"localhost" WITH GRANT OPTION;