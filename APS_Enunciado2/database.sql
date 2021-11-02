DROP USER "administrador"@"localhost";
DROP DATABASE vacunas;

CREATE DATABASE vacunas;

USE vacunas;


CREATE TABLE Cargos (
    id_cargo INT AUTO_INCREMENT,
    nombre VARCHAR(25) NOT NULL,
    es_esencial TINYINT(1),

    CONSTRAINT pk_cargo
    PRIMARY KEY (id_cargo)

) ENGINE=InnoDB;

CREATE TABLE Personas (
    nombre VARCHAR(25) NOT NULL,
    apellido VARCHAR(25) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    mail VARCHAR(50) NOT NULL,
    dni INT UNSIGNED NOT NULL,
    cargo INT NOT NULL,

    CONSTRAINT pk_dni PRIMARY KEY (dni), 

    CONSTRAINT fk_cargo
    FOREIGN KEY (cargo) REFERENCES Cargos (id_cargo)
     ON DELETE RESTRICT ON UPDATE RESTRICT
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
    id_provincia INT,
    id_vacuna INT,
    cantidad INT UNSIGNED NOT NULL,

    CONSTRAINT pk_entrega
    PRIMARY KEY (id_provincia, id_vacuna),

    CONSTRAINT fk_entregada_provincia
    FOREIGN KEY (id_provincia) REFERENCES Provincias (id_provincia)
     ON DELETE RESTRICT ON UPDATE RESTRICT,

    CONSTRAINT fk_entregada_vacuna
    FOREIGN KEY (id_vacuna) REFERENCES Vacunas (id_vacuna)
     ON DELETE RESTRICT ON UPDATE RESTRICT
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
    es_admin BOOLEAN NOT NULL,

    CONSTRAINT pk_usuario
    PRIMARY KEY (usuario)
) ENGINE=InnoDB;


CREATE USER "administrador"@"localhost" IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON vacunas.* TO "administrador"@"localhost" WITH GRANT OPTION;

# ---------------------------------------------------------------------------- VISTAS ----------------------------------------------------------------------------------------------

CREATE VIEW Aplicacion_Vacunas AS
SELECT P.nombre AS Nombre, P.apellido AS Apellido, P.fecha_nacimiento AS "Fecha Nacimiento",  C.nombre AS Cargo, V.nombre_vacuna AS Vacuna, VA.primera_dosis AS "Primera Dosis", VA.segunda_dosis AS "Segunda Dosis",
 VA.cantidad_dosis AS "Cantidad Dosis", P.mail AS Mail, P.dni as DNI, Pr.nombre_provincia AS Provincia, R.id_region AS "Region Sanitaria"
FROM Vacunas_Aplicadas VA NATURAL JOIN Personas P NATURAL JOIN Cargos C NATURAL JOIN Vacunas V NATURAL JOIN Provincias PR NATURAL JOIN Regiones_Sanitarias R;

CREATE VIEW Vacunas_Vencidas AS
SELECT P.nombre AS Nombre, P.apellido AS Apellido, P.fecha_nacimiento AS "Fecha Nacimiento", V.nombre_vacuna AS Vacuna, VA.primera_dosis AS "Primera Dosis", VA.segunda_dosis AS "Segunda Dosis",
 VA.cantidad_dosis AS "Cantidad Dosis", P.mail AS Mail, P.dni as DNI, Pr.nombre_provincia AS Provincia, R.id_region AS "Region Sanitaria", (CASE WHEN (VA.segunda_dosis IS NOT NULL) THEN "No" ELSE (IF(V.nombre_vacuna = 'Sinopharm', IF(TIMESTAMPDIFF(WEEK, VA.primera_dosis, curdate())>4,"Si","No"),IF(TIMESTAMPDIFF(WEEK, VA.primera_dosis, curdate())>12,"Si","No"))) END) AS "1ra Dosis Vencida"
FROM Vacunas_Aplicadas VA NATURAL JOIN Personas P NATURAL JOIN Vacunas V NATURAL JOIN Provincias PR NATURAL JOIN Regiones_Sanitarias R;

CREATE VIEW Vacunas_Disponibles AS
SELECT p.nombre_provincia AS 'Provincia',(CASE WHEN (tabla_aplicadas.idpcia IS NOT NULL) THEN vent.cantidad - tabla_aplicadas.aplicadas ELSE vent.cantidad END) AS 'Cantidad de Vacunas Disponibles', v.nombre_vacuna 'Tipo de Vacuna'
FROM Vacunas_Entregadas vent 
INNER JOIN Provincias p ON vent.id_provincia = p.id_provincia 
INNER JOIN Vacunas v ON vent.id_vacuna = v.id_vacuna
LEFT JOIN 
        (
            SELECT p.id_provincia idpcia,p.nombre_provincia,vap.id_vacuna, v.nombre_vacuna, SUM(CASE WHEN (vap.segunda_dosis IS NOT NULL) THEN 2 ELSE 1 END) aplicadas 
            FROM Vacunas_Aplicadas vap 
            INNER JOIN Provincias p ON vap.id_provincia = p.id_provincia 
            INNER JOIN Vacunas v ON v.id_vacuna = vap.id_vacuna
            GROUP BY p.nombre_provincia,vap.id_vacuna
        ) tabla_aplicadas ON vent.id_provincia = tabla_aplicadas.idpcia AND vent.id_vacuna = tabla_aplicadas.id_vacuna
GROUP BY p.nombre_provincia,vent.id_vacuna
ORDER BY p.nombre_provincia;

CREATE VIEW Usuarios_Registrados AS
SELECT U.usuario AS Usuario, U.nombre AS Nombre, U.apellido as Apellido, U.cargo as Cargo, U.mail as Mail, U.dni as DNI, U.telefono as Telefono
FROM Usuarios U
WHERE U.es_admin = 0;

# ---------------------------------------------------------------------------- CARGA DE DATOS INICIALES ----------------------------------------------------------------------------

#Tabla Vacunas
INSERT INTO Vacunas (nombre_vacuna) VALUES ('Sputnik V');
INSERT INTO Vacunas (nombre_vacuna) VALUES ('Sinopharm');
INSERT INTO Vacunas (nombre_vacuna) VALUES ('Moderna');
INSERT INTO Vacunas (nombre_vacuna) VALUES ('Astrazeneca');
INSERT INTO Vacunas (nombre_vacuna) VALUES ('Johnson & Johnson');

#Tabla Cargos
INSERT INTO Cargos (nombre, es_esencial) VALUES ('Personal de salud', 1);
INSERT INTO Cargos (nombre, es_esencial) VALUES ('Docente', 1);
INSERT INTO Cargos (nombre, es_esencial) VALUES ('Policía', 1);
INSERT INTO Cargos (nombre, es_esencial) VALUES ('Otros', 0);


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
INSERT INTO Provincias (nombre_provincia) VALUES ('Jujuy');
INSERT INTO Provincias (nombre_provincia) VALUES ('La Pampa');
INSERT INTO Provincias (nombre_provincia) VALUES ('La Rioja');
INSERT INTO Provincias (nombre_provincia) VALUES ('Mendoza');
INSERT INTO Provincias (nombre_provincia) VALUES ('Misiones');
INSERT INTO Provincias (nombre_provincia) VALUES ('Neuquen');
INSERT INTO Provincias (nombre_provincia) VALUES ('Rio Negro');
INSERT INTO Provincias (nombre_provincia) VALUES ('Salta');
INSERT INTO Provincias (nombre_provincia) VALUES ('San Juan');
INSERT INTO Provincias (nombre_provincia) VALUES ('San Luis');
INSERT INTO Provincias (nombre_provincia) VALUES ('Santa Cruz');
INSERT INTO Provincias (nombre_provincia) VALUES ('Santa Fe');
INSERT INTO Provincias (nombre_provincia) VALUES ('Santiago Del Estero');
INSERT INTO Provincias (nombre_provincia) VALUES ('Tierra del Fuego');
INSERT INTO Provincias (nombre_provincia) VALUES ('Tucuman');

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

#Tabla Personas
INSERT INTO Personas VALUES ("Roberto", "Perez", "1980-05-12", "rp@gmail.com", 25987124, 1);
INSERT INTO Personas VALUES ("Luis", "Miguel", "1983-11-02", "lm@gmail.com", 42581357, 1);
INSERT INTO Personas VALUES ("Raul", "Rodriguez", "1960-01-30", "rr@gmail.com", 12345678, 1);
INSERT INTO Personas (nombre,apellido,fecha_nacimiento,mail,dni,cargo) VALUES ('Domingo Faustino','Sarmiento','1850-07-15','dsarmi_bokita@gmail.com',11111111, 2);
INSERT INTO Personas (nombre,apellido,fecha_nacimiento,mail,dni,cargo) VALUES ('Julian','Alvarez','1950-11-01','madrid_091218@hotmail.com',11111112, 4);
INSERT INTO Personas (nombre,apellido,fecha_nacimiento,mail,dni,cargo) VALUES ('Marcelo','Gallardo','1980-08-10','elmuneco@river.com',11111113, 3);
INSERT INTO Personas (nombre,apellido,fecha_nacimiento,mail,dni,cargo) VALUES ('Juan Carlos','Hola','1953-12-24','dsarmi_bokita@gmail.com',11111114, 2);
INSERT INTO Personas (nombre,apellido,fecha_nacimiento,mail,dni,cargo) VALUES ('Juan Pedro','Molina','1990-02-14','hola1234@gmail.com',11111115, 4);
INSERT INTO Personas (nombre,apellido,fecha_nacimiento,mail,dni,cargo) VALUES ('Juan','Lopez','1978-07-03','hola1234@gmail.com',11111116, 3);
INSERT INTO Personas (nombre,apellido,fecha_nacimiento,mail,dni,cargo) VALUES ('Rodrigo','Rodriguez','1958-01-12','hola12345@gmail.com',11111117, 2);
INSERT INTO Personas (nombre,apellido,fecha_nacimiento,mail,dni,cargo) VALUES ('Alan','Turing','1940-05-25','enigma123@gmail.com',11111118, 1);

INSERT INTO Vacunas_Aplicadas VALUES ("2021-08-15", "2021-09-23", 2, 25987124, 1, 2, 1);
INSERT INTO Vacunas_Aplicadas VALUES ("2021-08-16", "2021-09-20", 2, 42581357, 1, 2, 1);
INSERT INTO Vacunas_Aplicadas VALUES ("2021-06-11", "2021-07-14", 2, 12345678, 3, 3, 1);
INSERT INTO Vacunas_Aplicadas (primera_dosis,segunda_dosis,cantidad_dosis,dni,id_vacuna,id_provincia,id_region) VALUES ('2021-01-01','2021-01-22',2,11111111,1,1,10);
INSERT INTO Vacunas_Aplicadas (primera_dosis,segunda_dosis,cantidad_dosis,dni,id_vacuna,id_provincia,id_region) VALUES ('2021-01-01','2021-01-22',2,11111112,1,7,1);
INSERT INTO Vacunas_Aplicadas (primera_dosis,segunda_dosis,cantidad_dosis,dni,id_vacuna,id_provincia,id_region) VALUES ('2021-01-01','2021-01-22',2,11111113,2,19,4);
INSERT INTO Vacunas_Aplicadas (primera_dosis,segunda_dosis,cantidad_dosis,dni,id_vacuna,id_provincia,id_region) VALUES ('2021-01-01','2021-01-22',2,11111114,2,18,2);
INSERT INTO Vacunas_Aplicadas (primera_dosis,segunda_dosis,cantidad_dosis,dni,id_vacuna,id_provincia,id_region) VALUES ('2021-01-01','2021-01-22',2,11111115,3,6,5);
INSERT INTO Vacunas_Aplicadas (primera_dosis,segunda_dosis,cantidad_dosis,dni,id_vacuna,id_provincia,id_region) VALUES ('2021-01-01','2021-01-22',2,11111116,3,8,3);
INSERT INTO Vacunas_Aplicadas (primera_dosis,segunda_dosis,cantidad_dosis,dni,id_vacuna,id_provincia,id_region) VALUES ('2021-01-01','2021-01-22',2,11111117,4,14,1);
INSERT INTO Vacunas_Aplicadas (primera_dosis,segunda_dosis,cantidad_dosis,dni,id_vacuna,id_provincia,id_region) VALUES ('2021-01-01',NULL,1,11111118,1,1,10);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (1,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (1,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (1,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (1,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (1,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (2,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (2,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (2,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (2,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (2,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (3,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (3,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (3,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (3,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (3,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (4,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (4,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (4,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (4,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (4,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (5,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (5,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (5,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (5,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (5,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (6,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (6,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (6,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (6,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (6,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (7,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (7,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (7,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (7,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (7,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (8,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (8,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (8,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (8,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (8,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (9,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (9,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (9,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (9,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (9,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (10,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (10,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (10,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (10,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (10,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (11,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (11,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (11,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (11,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (11,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (12,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (12,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (12,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (12,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (12,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (13,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (13,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (13,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (13,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (13,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (14,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (14,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (14,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (14,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (14,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (15,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (15,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (15,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (15,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (15,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (16,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (16,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (16,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (16,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (16,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (17,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (17,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (17,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (17,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (17,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (18,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (18,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (18,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (18,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (18,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (19,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (19,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (19,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (19,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (19,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (20,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (20,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (20,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (20,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (20,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (21,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (21,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (21,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (21,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (21,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (22,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (22,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (22,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (22,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (22,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (23,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (23,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (23,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (23,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (23,5,1000000);

INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (24,1,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (24,2,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (24,3,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (24,4,1000000);
INSERT INTO Vacunas_Entregadas (id_provincia,id_vacuna,cantidad) VALUES (24,5,1000000);


INSERT INTO Usuarios (usuario, contrasena, nombre, apellido, cargo, mail, dni, telefono, es_admin) VALUES ("admin", "admin", "Jose", "Admin", "Empleado", "admin@gmail.com", 1111, 291457231, 1);
INSERT INTO Usuarios (usuario, contrasena, nombre, apellido, cargo, mail, dni, telefono, es_admin) VALUES ("user", "user", "Jose", "User", "Empleado", "user@gmail.com", 1112, 291457331, 0);
INSERT INTO Usuarios (usuario, contrasena, nombre, apellido, cargo, mail, dni, telefono, es_admin) VALUES ("Fede45", "123", "Federico", "Garcia", "Empleado", "fg@gmail.com", 25681248, 291457131, 0);