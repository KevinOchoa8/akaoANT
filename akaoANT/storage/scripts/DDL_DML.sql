/*
|---------------------------------------------------|
|  Copyright (©) 2K25 EPN-FIS. All rights reserved  |
|  kevin.ochoa@epn.edu.ec                  ok       |
|  anthonny.almeida.epn.edu.ec             aa       |
|                                         akao      |
|---------------------------------------------------|
Autor: Anthonny Almeida, Kevin Ochoa
Fecha: 14.Enero.2026
Script: Creacion de la estructura de datos para akaoAnt
*/


DROP VIEW IF EXISTS akaoVwHormiga;

DROP TABLE IF EXISTS akaoAntCiberDron; 
DROP TABLE IF EXISTS akaoHormigaAlimento; 
DROP TABLE IF EXISTS akaoHormiga;
DROP TABLE IF EXISTS akaoAlimento;
DROP TABLE IF EXISTS akaoHormigaTipo;
DROP TABLE IF EXISTS akaoAlimentoTipo; 
DROP TABLE IF EXISTS akaoSexo;
DROP TABLE IF EXISTS akaoEstado;

CREATE TABLE akaoEstado (
     IdEstado       INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(15)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);

CREATE TABLE akaoSexo (
     IdSexo         INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(15)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);


CREATE TABLE akaoAlimentoTipo(
     IdAlimentoTipo INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(50)  NOT NULL UNIQUE 
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);

CREATE TABLE akaoHormigaTipo (
     IdHormigaTipo  INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(50)  NOT NULL UNIQUE 
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);

CREATE TABLE akaoAlimento (
     IdAlimento     INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdAlimentoTipo INTEGER NOT NULL REFERENCES akaoAlimentoTipo (IdAlimentoTipo)
    ,Cantidad       INTEGER DEFAULT 1
    ,Estado         VARCHAR(1) DEFAULT 'A' 
    ,FechaCreacion  DATETIME DEFAULT (datetime('now','localtime'))
);

CREATE TABLE akaoHormiga (
     IdHormiga      INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdHormigaTipo  INTEGER NOT NULL REFERENCES akaoHormigaTipo (IdHormigaTipo) 
    ,IdSexo         INTEGER NOT NULL REFERENCES akaoSexo        (IdSexo)        
    ,IdEstado       INTEGER NOT NULL REFERENCES akaoEstado      (IdEstado)      
    ,Nombre         VARCHAR(50) NULL  
    ,Genoma         VARCHAR(10) NULL  
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);


INSERT INTO akaoSexo (Nombre, Descripcion) VALUES 
 ('Macho'   ,'masculino')
,('Hembra'  ,'femenina') 
,('Asexual' ,'Asexual');

INSERT INTO akaoAlimentoTipo (Nombre, Descripcion) VALUES
 ('Carnívoro',   'Proteina')    --"Refactorización" por Estructura de Arquitectónica
,('Herbívoro',   'Vegetales')   --
,('Omnívoro',    'Todo')        --
,('Insectívoro', 'Insectos')    --
,('Nectarívoros','Azucar');     --

INSERT INTO akaoHormigaTipo (Nombre, Descripcion) VALUES 
 ('HLarva'      ,'Fase inicial')
,('HSoldado'    ,'Defensa')
,('HRastreadora','Busca comida')
,('HReina'      ,'Reproducción')
,('HZángano'    ,'Macho reproductor')
,('HObrera'     ,'Trabajadora');

INSERT INTO akaoEstado (Nombre, Descripcion) VALUES 
 ('VIVA',  'Activa'),
 ('MUERTA','Eliminada');


CREATE VIEW akaoVwHormiga AS
SELECT 
     H.IdHormiga
    ,HT.Nombre AS Tipo
    ,S.Nombre  AS Sexo
    ,E.Nombre  AS EstadoVital
    ,H.Nombre  AS NombrePropio
    ,H.FechaCreacion
FROM akaoHormiga H
JOIN akaoHormigaTipo HT ON H.IdHormigaTipo = HT.IdHormigaTipo
JOIN akaoSexo        S  ON H.IdSexo        = S.IdSexo
JOIN akaoEstado      E  ON H.IdEstado      = E.IdEstado
WHERE H.Estado = 'A';

SELECT * FROM akaoHormigaTipo;