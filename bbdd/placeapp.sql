DROP DATABASE IF EXISTS PROYECTO_PLACEAPP_UNIR_2024;
CREATE DATABASE PROYECTO_PLACEAPP_UNIR_2024;
USE PROYECTO_PLACEAPP_UNIR_2024;

--
-- Table structure for table `tipos`
--

DROP TABLE IF EXISTS `tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos` (
  `ID_TIPO` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) NOT NULL,
  `DESCRIPCION` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID_TIPO`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
INSERT INTO `tipos` VALUES 
(1,'Trabajo','Espacios destinados al trabajo y reuniones'),
(2,'Grandes eventos','Lugares para eventos de gran capacidad'),
(3,'Salas privadas','Espacios para actividades privadas'),
(4,'Espacios Deportivos','Todo tipo de espacios para la práctica de deportes');
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `subtipos`
--

DROP TABLE IF EXISTS `subtipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subtipos` (
  `ID_SUBTIPO` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) NOT NULL,
  `ID_TIPO` int NOT NULL,
  PRIMARY KEY (`ID_SUBTIPO`),
  KEY `ID_TIPO` (`ID_TIPO`),
  CONSTRAINT `subtipos_ibfk_1` FOREIGN KEY (`ID_TIPO`) REFERENCES `tipos` (`ID_TIPO`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subtipos`
--

LOCK TABLES `subtipos` WRITE;
/*!40000 ALTER TABLE `subtipos` DISABLE KEYS */;
INSERT INTO `subtipos` VALUES 
    (1,'Coworking',1),
    (2,'Sala de conferencias',1),
    (3,'Salón de actos',2),
    (4,'Pistas de baloncesto',4),
    (5,'Clases de pádel',4),
    (6,'Pistas de fútbol',4),
    (7,'Sala polivalente', 3);
/*!40000 ALTER TABLE `subtipos` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincias` (
  `ID_PROV` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_PROV`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
INSERT INTO `provincias` VALUES   
  (1, 'Madrid'),
  (2, 'Barcelona'),
  (3, 'Valencia'),
  (4, 'Álava (Araba)'),
  (5, 'Albacete'),
  (6, 'Alicante/Alacant'),
  (7, 'Almería'),
  (8, 'Asturias'),
  (9, 'Ávila'),
  (10, 'Badajoz'),
  (11, 'Burgos'),
  (12, 'Cáceres'),
  (13, 'Cádiz'),
  (14, 'Cantabria'),
  (15, 'Castellón/Castaló'),
  (16, 'Ciudad Real'),
  (17, 'Córdoba'),
  (18, 'Cuenca'),
  (19, 'Gerona/Girona'),
  (20, 'Granada'),
  (21, 'Guadalajara'),
  (22, 'Guipúzcoa'),
  (23, 'Huelva'),
  (24, 'Huesca'),
  (25, 'Jaén'),
  (26, 'La Rioja'),
  (27, 'Las Palmas'),
  (28, 'León'),
  (29, 'Lleida'),
  (30, 'Lugo'),
  (31, 'Málaga'),
  (32, 'Melilla'),
  (33, 'Menorca'),
  (34, 'Murcia'),
  (35, 'Navarra'),
  (36, 'Orense/Ourense'),
  (37, 'Palencia'),
  (38, 'Palmas'),
  (39, 'Pontevedra'),
  (40, 'La Coruña/A Coruña'),
  (41, 'Rioja (La)'),
  (42, 'Salamanca'),
  (43, 'Santa Cruz de Tenerife'),
  (44, 'Segovia'),
  (45, 'Sevilla'),
  (46, 'Soria'),
  (47, 'Tarragona'),
  (48, 'Teruel'),
  (49, 'Toledo'),
  (50, 'Valladolid'),
  (51, 'Vizcaya/Bizkaia'),
  (52, 'Zamora');
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfiles` (
  `ID_PERFIL` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_PERFIL`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles`
--

LOCK TABLES `perfiles` WRITE;
/*!40000 ALTER TABLE `perfiles` DISABLE KEYS */;
INSERT INTO `perfiles` VALUES (1,'ROLE_ADMINISTRADOR'),(2,'ROLE_PROPIETARIO'),(3,'ROLE_CLIENTE');
/*!40000 ALTER TABLE `perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(200) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `APELLIDOS` varchar(50) DEFAULT NULL,
  `DIRECCION` varchar(100) DEFAULT NULL,
  `TELEFONO` int DEFAULT NULL,
  `ENABLED` int NOT NULL DEFAULT '1',
  `FECHA_REGISTRO` date DEFAULT NULL,
  `ID_PROV` int NOT NULL,
  `ID_PERFIL` int NOT NULL,
  PRIMARY KEY (`USERNAME`),
  KEY `ID_PROV` (`ID_PROV`),
  KEY `ID_PERFIL` (`ID_PERFIL`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`ID_PROV`) REFERENCES `provincias` (`ID_PROV`),
  CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`ID_PERFIL`) REFERENCES `perfiles` (`ID_PERFIL`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES 
('alejandro@fp.com','contraseña2','Alejandro','París','Calle Jazmín 20, Madrid',987654321,1,'2021-02-01',1,2),
('mikel@fp.com','contraseña1','Mikel','Ballesteros','Calle Rosa 22, Madrid',123456789,1,'2021-06-01',1,2),
('usuario1@example.com','contraseña1','Juan','García','Calle Mayor, 1',123456789,1,'2023-01-01',1,3),
('usuario2@example.com','contraseña2','María','López','Rambla Catalunya, 2',987654321,1,'2023-02-01',2,3),
('usuario3@example.com','contraseña3','Carlos','Martínez','Avenida del Puerto, 3',456789123,1,'2023-03-01',3,3);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `espacios`
--

DROP TABLE IF EXISTS `espacios`;
CREATE TABLE `espacios` (
  `ID_ESPACIO` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(200) DEFAULT NULL,
  `DIRECCION` varchar(100) DEFAULT NULL,
  `CP` int DEFAULT NULL,
  `ENABLED` int DEFAULT NULL,
  `PRECIO` decimal(9,2) DEFAULT NULL,
  `ID_SUBTIPO` int NOT NULL,
  `ID_PROV` int NOT NULL,
  `USERNAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_ESPACIO`),
  KEY `ID_SUBTIPO` (`ID_SUBTIPO`),
  KEY `ID_PROV` (`ID_PROV`),
  KEY `USERNAME` (`USERNAME`),
  CONSTRAINT `espacios_ibfk_1` FOREIGN KEY (`ID_SUBTIPO`) REFERENCES `subtipos` (`ID_SUBTIPO`),
  CONSTRAINT `espacios_ibfk_2` FOREIGN KEY (`ID_PROV`) REFERENCES `provincias` (`ID_PROV`),
  CONSTRAINT `espacios_ibfk_3` FOREIGN KEY (`USERNAME`) REFERENCES `usuarios` (`USERNAME`)
) ;

--
-- Dumping data for table `espacios`
--

LOCK TABLES `espacios` WRITE;
/*!40000 ALTER TABLE `espacios` DISABLE KEYS */;
INSERT INTO `espacios` VALUES 
	(1,'Coworking Centro','Espacio moderno y luminoso','Calle Gran Vía, 123',28001,1,20.00,1,1,'alejandro@fp.com'),
	(2,'Salón de actos Ayto.Zarautz','Salón de actos del Ayuntamiento de Zarautz','Musika Plaza',20800,1,350.00,3,22,'mikel@fp.com'),
	(3, 'Sala multiusos Ayuntamiento Valdetorres','Sala polivalente para organizar eventos que cuenta con 30 m2','Calle Mayor,22', 28150, 1, 25.00, 7, 1, 'alejandro@fp.com'),
  (4, 'Pista de baloncesto Polideportivo Zarautz','Pista de baloncesto en el polideportivo municipal','Calle Deportes, 1', 28000,1, 10.00, 4, 22, 'mikel@fp.com'),
  (5, 'Pista de pádel 1 Club Deportivo Zarautz','Pista de pádel exterior. Cristal','Calle Deportes, 2', 28000,1, 4.00, 4, 22, 'mikel@fp.com'),
  (6, 'Pista de pádel 2 Club Deportivo Zarautz','Pista de pádel interior. Cristal','Calle Deportes, 2', 28000,1, 5.00, 4, 22, 'mikel@fp.com'),
  (7, 'Pista de fútbol 11 Polideportivo Zarautz','Campo de fútbol 11 de césped artificial','Calle Deportes, 3', 28000,1, 50.00, 4, 22, 'mikel@fp.com'),
  (8, 'Sala de Reuniones Innovación', 'Sala de reuniones equipada con tecnología de punta para presentaciones y videoconferencias.', 'Calle Innovación, 10', 28010, 1, 30.00, 2, 1, 'alejandro@fp.com'),
  (9, 'Sala de Eventos Máxima - Salón Principal', 'Espacio amplio y versátil para eventos de todo tipo, capacidad para 100 personas.', 'Avenida Principal, 50', 28020, 1, 200.00, 3, 1, 'mikel@fp.com'),
  (10, 'Pista de pádel Cubierta - Número 1', 'Pista de pádel techada con iluminación LED, ideal para jugar en cualquier clima.', 'Calle Deportes, 3', 28000, 1, 8.00, 5, 1, 'alejandro@fp.com'),
  (11, 'Pista de tenis - Número 2', 'Pista de tenis al aire libre, superficie de tierra batida.', 'Calle Tenis, 5', 28005, 1, 15.00, 6, 22, 'mikel@fp.com');

/*!40000 ALTER TABLE `espacios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extras`
--
DROP TABLE IF EXISTS `extras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extras` (
  `ID_EXTRA` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) NOT NULL,
  `DESCRIPCION` varchar(200) DEFAULT NULL,
  `PRECIO` decimal(9,2) DEFAULT NULL,
  `ID_ESPACIO` int NOT NULL,
  PRIMARY KEY (`ID_EXTRA`),
  KEY `ID_ESPACIO` (`ID_ESPACIO`),
  CONSTRAINT `extras_ibfk_1` FOREIGN KEY (`ID_ESPACIO`) REFERENCES `espacios` (`ID_ESPACIO`)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extras`
--

LOCK TABLES `extras` WRITE;
/*!40000 ALTER TABLE `extras` DISABLE KEYS */;
INSERT INTO `extras` VALUES 
(1,'Wifi premium','Conexión de alta velocidad',5.00,1),
(2,'Servicio de limpieza','Limpieza diaria del espacio',10.00,2),
(3,'Iluminación LED','Iluminación LED de última generación',1.00,5),
(4,'Iluminación LED','Iluminación LED de última generación',1.00,6),
(5,'Iluminación LED','Iluminación LED de última generación',3.00,7),
(6, 'Servicio de Catering', 'Opciones variadas de catering para eventos y reuniones.', 50.00, 9),
(7, 'Parking Privado', 'Acceso a parking privado para los asistentes al evento.', 20.00, 9),
(8, 'Alquiler de Raquetas', 'Alquiler de raquetas y pelotas para jugar al pádel.', 5.00, 10),
(9, 'Clases de Tenis', 'Clases particulares de tenis con entrenadores profesionales.', 30.00, 11);

/*!40000 ALTER TABLE `extras` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `ID_RESERVA` int NOT NULL AUTO_INCREMENT,
  `ID_ESPACIO` int NOT NULL,
  `USERNAME` varchar(45) NOT NULL,
  `PRECIO_VENTA` decimal(9,2) DEFAULT NULL,
  `FECHA_INICIO` datetime DEFAULT NULL,
  `FECHA_FIN` datetime DEFAULT NULL,
  `ENABLED` int NOT NULL DEFAULT '1',
  `OBSERVACIONES` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID_RESERVA`),
  KEY `USERNAME` (`USERNAME`),
  KEY `ID_ESPACIO` (`ID_ESPACIO`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`USERNAME`) REFERENCES `usuarios` (`USERNAME`),
  CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`ID_ESPACIO`) REFERENCES `espacios` (`ID_ESPACIO`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES 
(1,1,'usuario1@example.com',25.00,'2024-08-01 10:00:00','2023-08-01 12:00:00',1,'Reunión de trabajo'),
(2,6,'usuario2@example.com',5.00,'2024-08-05 10:00:00','2024-08-05 11:00:00',1,'Llegaremos 5 min tarde'),
(3,6,'usuario3@example.com',5.00,'2024-08-05 11:00:00','2024-08-05 12:00:00',1,'---- Ejemplo de observaciones ---'),
(4,3,'usuario3@example.com',50.00,'2024-08-05 10:00:00','2024-08-05 12:00:00',1,''),
(5, 11, 'usuario1@example.com', 15.00, '2024-08-10 09:00:00', '2024-08-10 11:00:00', 1, 'Reunión de trabajo con clientes'),
(6, 10, 'usuario2@example.com', 8.00, '2024-08-12 18:00:00', '2024-08-12 19:00:00', 1, 'Partido de pádel después del trabajo'),
(7, 6, 'usuario3@example.com', 10.00, '2024-08-15 17:00:00', '2024-08-15 18:00:00', 1, 'Clase de tenis con el entrenador Mario'),
(8, 7, 'usuario3@example.com', 20.00, '2024-08-20 10:00:00', '2024-08-20 12:00:00', 1, 'Torneo de pádel con amigos');

/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

-- Dump completed on 2024-04-18 19:59:17

create user proyecto_unir identified by 'proyecto';
grant all privileges on PROYECTO_PLACEAPP_UNIR_2024.* to proyecto_unir;




