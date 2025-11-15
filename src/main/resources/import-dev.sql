-- src/main/resources/import-dev.sql  (PARA DEV/H2)
MERGE INTO Client (id, firstName, lastName, documentNumber, email)
KEY(documentNumber)
VALUES (NEXT VALUE FOR Client_SEQ, 'Ana',  'Lopez', '73425473', 'ana@example.com');

MERGE INTO Client (id, firstName, lastName, documentNumber, email)
KEY(documentNumber)
VALUES (NEXT VALUE FOR Client_SEQ, 'Luis', 'Perez', '10020030', 'luis@example.com');
