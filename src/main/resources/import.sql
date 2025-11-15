-- Asumiendo nombres de columna exactos como en la entidad
INSERT INTO Client (id,firstName, lastName, documentNumber, email)
VALUES (nextval('Client_SEQ'),'Ana', 'Lopez', '73425473', 'ana@example.com')
ON CONFLICT (documentNumber) DO NOTHING;

INSERT INTO Client (id,firstName, lastName, documentNumber, email)
VALUES (nextval('Client_SEQ'),'Luis', 'Perez', '10020030', 'luis@example.com')
ON CONFLICT (documentNumber) DO NOTHING;