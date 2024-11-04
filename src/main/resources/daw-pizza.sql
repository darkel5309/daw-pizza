CREATE TABLE pizza (
  id INT AUTO_INCREMENT PRIMARY KEY,
  descripcion VARCHAR(150) NOT NULL,
  disponible BOOLEAN,
  nombre VARCHAR(30) NOT NULL,
  precio DECIMAL(5,2) NOT NULL,
  vegana BOOLEAN,
  vegetariana BOOLEAN 
);
