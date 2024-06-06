CREATE TABLE Tb_tiket(
numero VARCHAR2(50),
titulo VARCHAR2 (50),
descripcion VARCHAR2 (50),
autor VARCHAR2(50),
fechacreacion DATE,
estado VARCHAR2(50),
id_usuario INT,
CONSTRAINT fk_idusuario2 FOREIGN KEY (id_usuario) REFERENCES Tb_usaurio (id_usuario)
);

CREATE TABLE Tb_usaurio(
id_usuario INT PRIMARY KEY,
email VARCHAR2(50),
clave VARCHAR2(50)
);

ALTER TABLE Tb_tiket ADD UUID VARCHAR2(50)

UPDATE Tb_Tickets SET uuid = SYS_GUID()

SELECT * FROM Tb_Tickets

drop table Tb_Tiket
DROP table Tb_usaurio
