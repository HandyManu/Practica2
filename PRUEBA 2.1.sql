CREATE TABLE Tb_Tikets(
uuidNumero VARCHAR2(50),
titulo VARCHAR2(50),
descripcion VARCHAR2(100),
autor VARCHAR2(50),
email VARCHAR2(50),
fechaCreacion VARCHAR2(50),
estado VARCHAR2(50),
fechaFinalizacion VARCHAR2(50),
CONSTRAINT fk_mail FOREIGN KEY (email)REFERENCES Tb_Usuarios(email)
);

UPDATE Tb_Tikets SET uuidNumero= SYS_GUID()

CREATE TABLE Tb_Usuarios(
uuid VARCHAR2(50),
email VARCHAR2(50)PRIMARY KEY,
clave VARCHAR2 (20)
);

UPDATE Tb_Usuarios SET uuid= SYS_GUID()

select * from Tb_Tikets

