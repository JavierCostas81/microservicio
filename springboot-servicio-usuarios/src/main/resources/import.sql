INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('javi', '$2a$10$gfR0k1lrHuzteKAJEOniPOZ4iHxxnVsDiLuHoPHsgm7HNchMiGcay', '1', 'Javier', 'Costas', 'javiercostas1981@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$yo6BKiCFOqd4S/VsrzdTwesoVmjEagYMtpNBQ8OAAYfjf2lbP5EMy', '1', 'Andy', 'Doe', 'admin@misitio.com');
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1); 
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);