DROP USER "admin"@"localhost";
DROP DATABASE vacunas;

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
    nombre_vacuna VARCHAR(25) NOT NULL,
    id_vacuna INT AUTO_INCREMENT,

    CONSTRAINT pk_vacuna
    PRIMARY KEY (id_vacuna)
) ENGINE=InnoDB;

CREATE TABLE Provincias (
    nombre_provincia VARCHAR(32) NOT NULL,
    id_provincia INT AUTO_INCREMENT,

    CONSTRAINT pk_provincia
    PRIMARY KEY (id_provincia)
) ENGINE=InnoDB;

CREATE TABLE Regiones_Sanitarias (
    id_provincia INT NOT NULL,
    id_region INT NOT NULL,

    CONSTRAINT pk_region
    PRIMARY KEY (id_provincia, id_region),

    CONSTRAINT fk_provincia
    FOREIGN KEY (id_provincia) REFERENCES Provincias (id_provincia)
     ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE Vacunas_Aplicadas (
    primera_dosis DATE NOT NULL,
    segunda_dosis DATE,
    cantidad_dosis INT UNSIGNED NOT NULL,
    dni INT UNSIGNED NOT NULL,
    id_vacuna INT,
    id_provincia INT NOT NULL,
    id_region INT NOT NULL,

    CONSTRAINT pk_aplicadas
    PRIMARY KEY (dni),

    CONSTRAINT fk_aplicada_persona
    FOREIGN KEY (dni) REFERENCES Personas (dni)
     ON DELETE RESTRICT ON UPDATE RESTRICT,

    CONSTRAINT fk_aplicada_vacuna
    FOREIGN KEY (id_vacuna) REFERENCES Vacunas (id_vacuna)
     ON DELETE RESTRICT ON UPDATE RESTRICT,

    CONSTRAINT fk_aplicada_region
    FOREIGN KEY (id_provincia, id_region) REFERENCES Regiones_Sanitarias (id_provincia, id_region)
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
    contrasena CHAR(32) NOT NULL,
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

# ---------------------------------------------------------------------------- VISTAS ----------------------------------------------------------------------------------------------

CREATE VIEW Aplicacion_Vacunas AS
SELECT P.nombre AS Nombre, P.apellido, P.fecha_nacimiento, V.nombre_vacuna, VA.primera_dosis, VA.segunda_dosis,
 VA.cantidad_dosis, P.mail, P.dni, Pr.nombre_provincia, R.id_region
FROM Vacunas_Aplicadas VA NATURAL JOIN Personas P NATURAL JOIN Vacunas V NATURAL JOIN Provincias PR NATURAL JOIN Regiones_Sanitarias R;

# ---------------------------------------------------------------------------- CARGA DE DATOS INICIALES ----------------------------------------------------------------------------

#Tabla Vacunas
INSERT INTO Vacunas (nombre_vacuna) VALUES ('Sputnik V');
INSERT INTO Vacunas (nombre_vacuna) VALUES ('Sinopharm');
INSERT INTO Vacunas (nombre_vacuna) VALUES ('Sputnik V');
INSERT INTO Vacunas (nombre_vacuna) VALUES ('Astrazeneca');
INSERT INTO Vacunas (nombre_vacuna) VALUES ('Johnson & Johnson');

#Tabla Provincias
INSERT INTO Provincias (nombre_provincia) VALUES ('Buenos Aires');
INSERT INTO Provincias (nombre_provincia) VALUES ('Ciudad Autonoma de Buenos Aires');
INSERT INTO Provincias (nombre_provincia) VALUES ('Catamarca');
INSERT INTO Provincias (nombre_provincia) VALUES ('Chaco');
INSERT INTO Provincias (nombre_provincia) VALUES ('Chubut');
INSERT INTO Provincias (nombre_provincia) VALUES ('Cordoba');
INSERT INTO Provincias (nombre_provincia) VALUES ('Corrientes');
INSERT INTO Provincias (nombre_provincia) VALUES ('Entre Rios');
INSERT INTO Provincias (nombre_provincia) VALUES ('Formosa');
INSERT INTO Provincias (nombre_provincia) VALUES ('Jujuy');#10
INSERT INTO Provincias (nombre_provincia) VALUES ('La Pampa');
INSERT INTO Provincias (nombre_provincia) VALUES ('La Rioja');
INSERT INTO Provincias (nombre_provincia) VALUES ('Mendoza');
INSERT INTO Provincias (nombre_provincia) VALUES ('Misiones');
INSERT INTO Provincias (nombre_provincia) VALUES ('Neuquen');#15
INSERT INTO Provincias (nombre_provincia) VALUES ('Rio Negro');
INSERT INTO Provincias (nombre_provincia) VALUES ('Salta');
INSERT INTO Provincias (nombre_provincia) VALUES ('San Juan');
INSERT INTO Provincias (nombre_provincia) VALUES ('San Luis');
INSERT INTO Provincias (nombre_provincia) VALUES ('Santa Cruz');#20
INSERT INTO Provincias (nombre_provincia) VALUES ('Santa Fe');
INSERT INTO Provincias (nombre_provincia) VALUES ('Santiago Del Estero');
INSERT INTO Provincias (nombre_provincia) VALUES ('Tierra del Fuego');
INSERT INTO Provincias (nombre_provincia) VALUES ('Tucuman');#24

#Tabla Regiones_Sanitarias

#Buenos Aires
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,4);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,5);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,6);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,7);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,8);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,9);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,10);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,11);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (1,12);
#Ciudad Autonoma de Buenos Aires
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (2,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (2,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (2,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (2,4);
#Catamarca
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (3,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (3,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (3,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (3,4);
#Chaco
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (4,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (4,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (4,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (4,4);
#Chubut
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (5,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (5,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (5,3);
#Cordoba
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (6,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (6,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (6,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (6,4);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (6,5);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (6,6);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (6,7);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (6,8);
#Corrientes
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (7,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (7,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (7,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (7,4);
#Entre Rios
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (8,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (8,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (8,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (8,4);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (8,5);
#Formosa
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (9,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (9,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (9,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (9,4);
#Jujuy
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (10,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (10,2);
#La Pampa
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (11,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (11,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (11,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (11,4);
#La Rioja
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (12,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (12,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (12,3);
#Mendoza
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (13,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (13,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (13,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (13,4);
#Misiones
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (14,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (14,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (14,3);
#Neuquen
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (15,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (15,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (15,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (15,4);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (15,5);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (15,6);
#Rio Negro
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (16,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (16,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (16,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (16,4);
#Salta
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (17,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (17,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (17,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (17,4);
#San Juan
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (18,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (18,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (18,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (18,4);
#San Luis
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (19,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (19,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (19,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (19,4);
#Santa Cruz
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (20,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (20,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (20,3);
#Santa Fe
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (21,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (21,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (21,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (21,4);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (21,5);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (21,6);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (21,7);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (21,8);
#Santiago Del Estero
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (22,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (22,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (22,3);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (22,4);
#Tierra del Fuego
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (23,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (23,2);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (23,3);
#Tucuman
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (24,1);
INSERT INTO Regiones_Sanitarias (id_provincia,id_region) VALUES (24,2);

# ---------------------------------------------------------------------------- TESTS (BORRAR CUANDO SE TENGA LA UI COMPLETA) ----------------------------------------------------------------------------

INSERT INTO Personas VALUES ("Roberto", "Perez", "1980-5-12", "mail@gmail.com", 25987124);

INSERT INTO Vacunas_Aplicadas VALUES ("2021-08-15", "2021-09-23", 2, 25987124, 1, 2, 1);