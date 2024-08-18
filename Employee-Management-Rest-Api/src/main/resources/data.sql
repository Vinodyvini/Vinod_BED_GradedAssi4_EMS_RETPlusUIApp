INSERT INTO roles (name)
SELECT 'ADMIN' 
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ADMIN');

INSERT INTO roles (name)
SELECT 'USER' 
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'USER');

INSERT INTO users (username, password)
SELECT 'admin', '$2a$12$f57sh5hPAK1UemTe9jrmIuYkb6HLPGzcDtK0v20zuwsjfMfsoCJwW' 
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin');

INSERT INTO users (username, password)
SELECT 'user', '$2a$12$OBZ3/R3ytqUVApQPCfqyz.d8xj16ztmke1HVH6g5XelLjC.tBdtrm' 
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'user');

INSERT INTO user_roles (user_id, role_id)
SELECT (SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ADMIN') 
WHERE NOT EXISTS (SELECT 1 FROM user_roles WHERE user_id = (SELECT id FROM users WHERE username = 'admin') AND role_id = (SELECT id FROM roles WHERE name = 'ADMIN'));

INSERT INTO user_roles (user_id, role_id)
SELECT (SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'USER') 
WHERE NOT EXISTS (SELECT 1 FROM user_roles WHERE user_id = (SELECT id FROM users WHERE username = 'user') AND role_id = (SELECT id FROM roles WHERE name = 'USER'));

INSERT INTO employees (first_name, last_name, email)
SELECT 'John', 'Doe', 'john.doe@example.com'
WHERE NOT EXISTS (SELECT 1 FROM employees WHERE email = 'john.doe@example.com');

INSERT INTO employees (first_name, last_name, email)
SELECT 'Jane', 'Doe', 'jane.doe@example.com'
WHERE NOT EXISTS (SELECT 1 FROM employees WHERE email = 'jane.doe@example.com');

INSERT INTO employees (first_name, last_name, email)
SELECT 'Alice', 'Smith', 'alice.smith@example.com'
WHERE NOT EXISTS (SELECT 1 FROM employees WHERE email = 'alice.smith@example.com');