INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('javi', '123456', '1', 'Javier', 'Costas', 'javiercostas1981@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin', '123456', '1', 'Andy', 'Doe', 'admin@misitio.com');
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1); 
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);