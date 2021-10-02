CREATE DATABASE vacunas;

USE vacunas;

CREATE TABLE Personas (
    nombre VARCHAR(25) NOT NULL,
    apellido VARCHAR(25) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    mail VARCHAR(50) NOT NULL,
    dni INT UNSIGNED NOT NULL,

    CONSTRAINT pk_dni
    PRIMARY KEY (dni)
) ENGINE=InnoDB;

CREATE TABLE Vacunas (
    nombre VARCHAR(25) NOT NULL,
    id INT AUTO_INCREMENT,

    CONSTRAINT pk_vacuna
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE Provincias (
    nombre VARCHAR(25) NOT NULL,
    id INT AUTO_INCREMENT,

    CONSTRAINT pk_provincia
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE Regiones_Sanitarias (
    id_provincia INT NOT NULL,
    id_region INT NOT NULL,

    CONSTRAINT pk_region
    PRIMARY KEY (id_provincia, id_region),

    CONSTRAINT fk_provincia
    FOREIGN KEY (id_provincia) REFERENCES Provincias (id)
     ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE Vacunas_Aplicadas (
    primera_dosis DATE NOT NULL,
    segunda_dosis DATE,
    cantidad_dosis INT UNSIGNED NOT NULL,
    dni INT UNSIGNED NOT NULL,
    id_vacuna INT,
    provincia INT NOT NULL,
    id_region INT NOT NULL,

    CONSTRAINT pk_aplicadas
    PRIMARY KEY (dni),

    CONSTRAINT fk_aplicada_persona
    FOREIGN KEY (dni) REFERENCES Personas (dni)
     ON DELETE RESTRICT ON UPDATE RESTRICT,

    CONSTRAINT fk_aplicada_vacuna
    FOREIGN KEY (id_vacuna) REFERENCES Vacunas (id)
     ON DELETE RESTRICT ON UPDATE RESTRICT,

    CONSTRAINT fk_aplicada_region
    FOREIGN KEY (provincia, id_region) REFERENCES Regiones_Sanitarias (id_provincia, id_region)
     ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE Vacunas_Entregadas (
    provincia VARCHAR(25) NOT NULL,
    vacuna VARCHAR(25) NOT NULL,
    cantidad INT UNSIGNED NOT NULL,

    CONSTRAINT pk_entrega
    PRIMARY KEY (provincia, vacuna)
) ENGINE=InnoDB;

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
    es_admin BOOLEAN NOT NULL,

    CONSTRAINT pk_usuario
    PRIMARY KEY (usuario)
) ENGINE=InnoDB;

CREATE USER "admin"@"localhost" IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON vacunas.* TO "admin"@"localhost" WITH GRANT OPTION;