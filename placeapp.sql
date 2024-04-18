DROP DATABASE IF EXISTS PROYECTO_PLACEAPP_UNIR_2024;
CREATE DATABASE PROYECTO_PLACEAPP_UNIR_2024;
USE PROYECTO_PLACEAPP_UNIR_2024;
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
  PRIMARY KEY (`ID_ESPACIO`),
  KEY `ID_SUBTIPO` (`ID_SUBTIPO`),
  KEY `ID_PROV` (`ID_PROV`),
  CONSTRAINT `espacios_ibfk_1` FOREIGN KEY (`ID_SUBTIPO`) REFERENCES `subtipos` (`ID_SUBTIPO`),
  CONSTRAINT `espacios_ibfk_2` FOREIGN KEY (`ID_PROV`) REFERENCES `provincias` (`ID_PROV`)
) ;

--
-- Dumping data for table `espacios`
--

LOCK TABLES `espacios` WRITE;
/*!40000 ALTER TABLE `espacios` DISABLE KEYS */;
INSERT INTO `espacios` VALUES (1,'Coworking Centro','Espacio moderno y luminoso','Calle Gran Vía, 123',28001,1,20.00,1,1),(2,'Restaurante El Patio','Ambiente acogedor con terraza','Avenida Diagonal, 456',8001,1,50.00,3,2),(3,'Gimnasio FitnessMax','Equipado con las últimas máquinas','Calle Valencia, 789',46001,1,30.00,5,3);
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
  PRIMARY KEY (`ID_EXTRA`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extras`
--

LOCK TABLES `extras` WRITE;
/*!40000 ALTER TABLE `extras` DISABLE KEYS */;
INSERT INTO `extras` VALUES (1,'Wifi premium','Conexión de alta velocidad',5.00),(2,'Servicio de limpieza','Limpieza diaria del espacio',10.00),(3,'Café ilimitado','Disponible durante toda la estancia',3.50);
/*!40000 ALTER TABLE `extras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extras_espacios`
--

DROP TABLE IF EXISTS `extras_espacios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extras_espacios` (
  `ID_EXTRA` int NOT NULL,
  `ID_ESPACIO` int NOT NULL,
  PRIMARY KEY (`ID_EXTRA`,`ID_ESPACIO`),
  KEY `ID_ESPACIO` (`ID_ESPACIO`),
  CONSTRAINT `extras_espacios_ibfk_1` FOREIGN KEY (`ID_EXTRA`) REFERENCES `extras` (`ID_EXTRA`),
  CONSTRAINT `extras_espacios_ibfk_2` FOREIGN KEY (`ID_ESPACIO`) REFERENCES `espacios` (`ID_ESPACIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extras_espacios`
--

LOCK TABLES `extras_espacios` WRITE;
/*!40000 ALTER TABLE `extras_espacios` DISABLE KEYS */;
INSERT INTO `extras_espacios` VALUES (1,1),(2,1),(1,2),(3,3);
/*!40000 ALTER TABLE `extras_espacios` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincias` (
  `ID_PROV` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_PROV`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
INSERT INTO `provincias` VALUES (1,'Madrid'),(2,'Barcelona'),(3,'Valencia');
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,1,'usuario1@example.com',25.00,'2023-04-01 10:00:00','2023-04-02 10:00:00',1,'Reunión de trabajo'),(2,2,'usuario2@example.com',60.00,'2023-04-03 10:00:00','2023-04-04 10:00:00',1,'Cena de negocios'),(3,3,'usuario3@example.com',40.00,'2023-04-05 10:00:00','2023-04-06 10:00:00',1,'Entrenamiento personal');
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subtipos`
--

LOCK TABLES `subtipos` WRITE;
/*!40000 ALTER TABLE `subtipos` DISABLE KEYS */;
INSERT INTO `subtipos` VALUES (1,'Coworking',1),(2,'Sala de conferencias',1),(3,'Cafetería',2),(4,'Restaurante de comida rápida',2),(5,'Sala de pesas',3),(6,'Clases de fitness',3);
/*!40000 ALTER TABLE `subtipos` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
INSERT INTO `tipos` VALUES (1,'Oficina','Espacios destinados al trabajo y reuniones'),(2,'Restaurante','Lugares para disfrutar de comida y bebida'),(3,'Gimnasio','Espacios para realizar actividad física');
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_espacios`
--

DROP TABLE IF EXISTS `usuario_espacios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_espacios` (
  `USERNAME` varchar(45) NOT NULL,
  `ID_ESPACIO` int NOT NULL,
  PRIMARY KEY (`USERNAME`,`ID_ESPACIO`),
  KEY `ID_ESPACIO` (`ID_ESPACIO`),
  CONSTRAINT `usuario_espacios_ibfk_1` FOREIGN KEY (`USERNAME`) REFERENCES `usuarios` (`USERNAME`),
  CONSTRAINT `usuario_espacios_ibfk_2` FOREIGN KEY (`ID_ESPACIO`) REFERENCES `espacios` (`ID_ESPACIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_espacios`
--

LOCK TABLES `usuario_espacios` WRITE;
/*!40000 ALTER TABLE `usuario_espacios` DISABLE KEYS */;
INSERT INTO `usuario_espacios` VALUES ('usuario1@example.com',1),('usuario2@example.com',2),('usuario3@example.com',3);
/*!40000 ALTER TABLE `usuario_espacios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_perfiles`
--

DROP TABLE IF EXISTS `usuario_perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_perfiles` (
  `USERNAME` varchar(45) NOT NULL,
  `ID_PERFIL` int NOT NULL,
  PRIMARY KEY (`USERNAME`,`ID_PERFIL`),
  KEY `ID_PERFIL` (`ID_PERFIL`),
  CONSTRAINT `usuario_perfiles_ibfk_1` FOREIGN KEY (`USERNAME`) REFERENCES `usuarios` (`USERNAME`),
  CONSTRAINT `usuario_perfiles_ibfk_2` FOREIGN KEY (`ID_PERFIL`) REFERENCES `perfiles` (`ID_PERFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_perfiles`
--

LOCK TABLES `usuario_perfiles` WRITE;
/*!40000 ALTER TABLE `usuario_perfiles` DISABLE KEYS */;
INSERT INTO `usuario_perfiles` VALUES ('mikel@fp.com',1),('alejandro@fp.com',2),('tomas@fp.com',3);
/*!40000 ALTER TABLE `usuario_perfiles` ENABLE KEYS */;
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
  PRIMARY KEY (`USERNAME`),
  KEY `ID_PROV` (`ID_PROV`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`ID_PROV`) REFERENCES `provincias` (`ID_PROV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('alejandro@fp.com','contraseña2','Alejandro','París','Calle Jazmín 20, Madrid',987654321,1,'2021-02-01',1),('mikel@fp.com','contraseña1','Mikel','Ballesteros','Calle Rosa 22, Madrid',123456789,1,'2021-06-01',1),('tomas@fp.com','contraseña3','Tomas','Escudero','Calle Alamin 30, Madrid',456789123,1,'2021-01-01',1),('usuario1@example.com','contraseña1','Juan','García','Calle Mayor, 1',123456789,1,'2023-01-01',1),('usuario2@example.com','contraseña2','María','López','Rambla Catalunya, 2',987654321,1,'2023-02-01',2),('usuario3@example.com','contraseña3','Carlos','Martínez','Avenida del Puerto, 3',456789123,1,'2023-03-01',3);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-18 19:59:17

create user proyecto_unir identified by 'proyecto';
grant all privileges on PROYECTO_PLACEAPP_UNIR_2024.* to proyecto_unir;


