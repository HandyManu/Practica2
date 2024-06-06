CREATE TABLE Tb_tiket(
numero VARCHAR2(50),
titulo VARCHAR2 (50),
descripcion VARCHAR2 (50),
autor VARCHAR2(50),
email VARCHAR2(50),
fechacreacion VARCHAR2(10),
estado VARCHAR2(50),
CONSTRAINT fk_email FOREIGN KEY (email) REFERENCES Tb_usaurio (email)
);

CREATE TABLE Tb_usaurio(
email VARCHAR2(50)PRIMARY KEY ,
clave VARCHAR2(50)
);

ALTER TABLE Tb_tiket ADD UUID VARCHAR2(50)

UPDATE Tb_Tickets SET uuid = SYS_GUID()

SELECT * FROM Tb_Tickets


drop table Tb_usaurio