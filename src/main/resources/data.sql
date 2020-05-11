INSERT INTO role (pk_role, role_name)
SELECT 1 AS pk_role, 'ADMIN' as role_name
WHERE NOT EXISTS
(
	SELECT pk_role, role_name FROM role
    WHERE role_name = 'ADMIN'
);

INSERT INTO role (pk_role, role_name)
SELECT 2 AS pk_role, 'USER' as role_name
WHERE NOT EXISTS
(
	SELECT pk_role, role_name FROM role
    WHERE role_name = 'USER'
);