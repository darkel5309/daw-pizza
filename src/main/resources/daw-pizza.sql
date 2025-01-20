INSERT INTO cliente (nombre, direccion, email, telefono) 
VALUES 
('Carlos Sánchez', 'Calle de la Paz 789', 'carlos.sanchez@email.com', '111223344'),
('Laura Díaz', 'Plaza Mayor 101', 'laura.diaz@email.com', '223344556'),
('Pedro Martínez', 'Calle Larga 202', 'pedro.martinez@email.com', '334455667'),
('María López', 'Avenida del Sol 303', 'maria.lopez@email.com', '445566778');


INSERT INTO pizza (nombre, descripcion, precio, vegetariana, vegana, disponible) 
VALUES 
('Peperoni', 'Pizza con tomate, queso y pepperoni', 9.00, 0, 0, 1),
('Hawaiana', 'Pizza con tomate, queso, piña y jamón', 10.50, 0, 0, 1),
('Caprichosa', 'Pizza con tomate, queso, jamón, champiñones, alcachofas y aceitunas', 12.00, 0, 0, 1),
('Marinera', 'Pizza con tomate, queso, mariscos y almejas', 13.00, 0, 0, 1),
('Veggie Deluxe', 'Pizza vegana con tomate, queso vegano, brócoli, espinacas y pimientos', 12.50, 1, 1, 1);


INSERT INTO pedido (id_cliente, fecha, total, metodo, notas) 
VALUES 
(3, '2025-01-20 15:00:00', 19.00, 'R', 'Recoger en el local'),
(4, '2025-01-20 16:00:00', 25.50, 'D', 'Entrega a domicilio, urgente'),
(1, '2025-01-20 17:30:00', 28.50, 'D', 'Entregar en casa de un amigo'),
(2, '2025-01-20 18:00:00', 15.00, 'L', 'Recoger en la tienda a las 18:30');


INSERT INTO pizza_pedido (id_pedido, id_pizza, cantidad, precio) 
VALUES 
(3, 5, 1, 12.50),
(3, 4, 1, 13.00),
(4, 2, 2, 10.50),
(4, 3, 1, 12.00),
(1, 1, 2, 8.50),
(1, 2, 1, 9.90),
(2, 3, 1, 12.00),
(2, 5, 1, 12.50);
