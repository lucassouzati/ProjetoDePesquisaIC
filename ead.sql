-- MySQL dump 10.11
--
-- Host: localhost    Database: ead
-- ------------------------------------------------------
-- Server version	5.0.51b-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `aluno` (
  `id` int(6) NOT NULL default '0',
  `nome` varchar(50) default NULL,
  `login` varchar(10) default NULL,
  `senha` varchar(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,'José da Silva','jose','minhasenha'),(2,'Fulano de Tal','fulano','');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alunodisci`
--

DROP TABLE IF EXISTS `alunodisci`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `alunodisci` (
  `id` int(6) NOT NULL default '0',
  `idAluno` int(6) default NULL,
  `idDisciplina` int(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `alunodisci`
--

LOCK TABLES `alunodisci` WRITE;
/*!40000 ALTER TABLE `alunodisci` DISABLE KEYS */;
INSERT INTO `alunodisci` VALUES (1,1,1),(2,1,2),(3,2,3),(4,2,4),(5,1,3),(6,2,1),(7,2,2),(8,2,5);
/*!40000 ALTER TABLE `alunodisci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceito`
--

DROP TABLE IF EXISTS `conceito`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `conceito` (
  `id` int(11) NOT NULL default '0',
  `descricao` varchar(500) default NULL,
  `idTermo` int(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `conceito`
--

LOCK TABLES `conceito` WRITE;
/*!40000 ALTER TABLE `conceito` DISABLE KEYS */;
INSERT INTO `conceito` VALUES (1,'É a instância de uma classe.',2),(2,'É a forma como organizamos os objetos em um sistema.',1),(3,'É a forma como organizamos os objetos em um sistema.',1),(4,'É um diagrama presente na análise orientada a objetos.',3),(5,'É uma estrutura em forma de lista, onde cada nó aponta para outro nó.',8),(6,'São características de um determinado objeto ou classe.',4),(7,'É uma função que a classe deve desempenhar.',5),(8,'Nome técnico dado à linha de uma tabela em um banco de dados.',10),(9,'É um tipo de classificador representando uma unidade funcional coerente provida pelo sistema, subsistema, ou classe manifestada por seqüências de mensagens intercambiáveis entre os sistemas e um ou mais atores.',9),(10,'Ilustra como as classes deverão se encontrar organizadas através da noção de componentes de trabalho. Por exemplo, pode-se explicitar, para cada componente, qual das classes que ele representa.',11),(11,'É um modelo de desenvolvimento de software seqüencial no qual o desenvolvimento é visto como um fluir constante para frente (como uma cascata) através das fases de análise de requisitos, projeto, implementação, testes (validação), integração, e manutenção de software.',7),(12,'É uma rede de computadores.',12),(13,'Vários computadores interligados.',13),(14,'Tipo de cabo mais antigo, usado em redes mais antigas.',14),(15,'Também chamado de roteador, faz a ponte entre uma rede e outra.',18),(16,'Liga duas ou mais redes.',17);
/*!40000 ALTER TABLE `conceito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `disciplina` (
  `id` int(6) NOT NULL default '0',
  `nome` varchar(50) default NULL,
  `idProfessor` int(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `disciplina`
--

LOCK TABLES `disciplina` WRITE;
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` VALUES (1,'Análise Orientada a Objetos',1),(2,'Engenharia de Software',1),(3,'Estrutura de Dados',1),(4,'Banco de Dados',1),(5,'Redes de Computadores',1);
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exemplo`
--

DROP TABLE IF EXISTS `exemplo`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `exemplo` (
  `id` int(6) NOT NULL default '0',
  `descricao` varchar(50) default NULL,
  `idTermo` int(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `exemplo`
--

LOCK TABLES `exemplo` WRITE;
/*!40000 ALTER TABLE `exemplo` DISABLE KEYS */;
INSERT INTO `exemplo` VALUES (4,'João',2),(5,'Fiat Uno',2),(6,'Arroz Tio João',2);
/*!40000 ALTER TABLE `exemplo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pergunta`
--

DROP TABLE IF EXISTS `pergunta`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `pergunta` (
  `id` int(6) NOT NULL,
  `texto` varchar(200) default NULL,
  `idDisciplina` int(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `pergunta`
--

LOCK TABLES `pergunta` WRITE;
/*!40000 ALTER TABLE `pergunta` DISABLE KEYS */;
INSERT INTO `pergunta` VALUES (1,'O que é uma rede de computadores?',5),(2,'Qual a diferença entre um hub e um roteador?',5),(3,'O que é uma classe?',1),(4,'O que é um objeto?',1);
/*!40000 ALTER TABLE `pergunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `professor` (
  `id` int(6) NOT NULL default '0',
  `nome` varchar(50) default NULL,
  `login` varchar(10) default NULL,
  `senha` varchar(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,'Leandro','lefoly',''),(2,'Silvia Lane Freitas','silvia','blabla'),(3,'Fulano de Tal','fulano','123456'),(4,'VamosVer','hahaha','12345'),(5,'MaisUmaVez','yahooooooo','vaivaivai');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resposta`
--

DROP TABLE IF EXISTS `resposta`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `resposta` (
  `id` int(6) NOT NULL,
  `idPergunta` int(6) default NULL,
  `idAluno` int(6) default NULL,
  `texto` varchar(500) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `resposta`
--

LOCK TABLES `resposta` WRITE;
/*!40000 ALTER TABLE `resposta` DISABLE KEYS */;
INSERT INTO `resposta` VALUES (1,1,2,'É um amaranhado de computadores interligados trocando informação.'),(2,2,2,'Um hub liga vários computadores entre si dentro de uma rede.\r\nUm roteador liga várias redes diferentes entre si, cada uma com seus próprios computadores.'),(3,3,2,'É uma representação abstrata de um conceito do mundo real.'),(4,4,2,'É a instância de uma classe.');
/*!40000 ALTER TABLE `resposta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sinonimo`
--

DROP TABLE IF EXISTS `sinonimo`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `sinonimo` (
  `id` int(6) NOT NULL,
  `descricao` varchar(50) default NULL,
  `idTermo` int(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `sinonimo`
--

LOCK TABLES `sinonimo` WRITE;
/*!40000 ALTER TABLE `sinonimo` DISABLE KEYS */;
INSERT INTO `sinonimo` VALUES (3,'Fiat Uno',2),(4,'João',2);
/*!40000 ALTER TABLE `sinonimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `termo`
--

DROP TABLE IF EXISTS `termo`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `termo` (
  `id` int(6) NOT NULL default '0',
  `nome` varchar(50) default NULL,
  `idDisciplina` int(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `termo`
--

LOCK TABLES `termo` WRITE;
/*!40000 ALTER TABLE `termo` DISABLE KEYS */;
INSERT INTO `termo` VALUES (1,'Classe',1),(2,'Objeto',1),(3,'teste',1),(4,'Atributo',1),(5,'teste2',1),(6,'Modelo Incremental',2),(7,'Modelo em Cascata',2),(8,'Lista Encadeada',3),(9,'Caso de Uso',1),(10,'Tupla',4),(11,'Diagrama de Componentes',1),(12,'Internet',5),(13,'Rede',5),(14,'Cabo Coaxial',5),(15,'teste3',5),(16,'Hub',5),(17,'Roteador',5),(18,'Switch',5);
/*!40000 ALTER TABLE `termo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `termoignorado`
--

DROP TABLE IF EXISTS `termoignorado`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `termoignorado` (
  `id` int(6) NOT NULL default '0',
  `nome` varchar(50) default NULL,
  `idDisciplina` int(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `termoignorado`
--

LOCK TABLES `termoignorado` WRITE;
/*!40000 ALTER TABLE `termoignorado` DISABLE KEYS */;
INSERT INTO `termoignorado` VALUES (1,'vários',NULL),(2,'interligados',NULL),(3,'computadores',NULL),(4,'tipo',NULL),(5,'cabo',NULL),(6,'mais',NULL),(7,'antigo',NULL),(8,'usado',NULL),(9,'antigas',NULL),(10,'também',NULL),(11,'chamado',NULL),(12,'faz',NULL),(13,'ponte',NULL),(14,'outra',NULL),(15,'liga',NULL),(16,'duas',NULL),(17,'mais',NULL);
/*!40000 ALTER TABLE `termoignorado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `termorelac`
--

DROP TABLE IF EXISTS `termorelac`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `termorelac` (
  `id` int(6) NOT NULL default '0',
  `id1` int(6) default NULL,
  `id2` int(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `termorelac`
--

LOCK TABLES `termorelac` WRITE;
/*!40000 ALTER TABLE `termorelac` DISABLE KEYS */;
INSERT INTO `termorelac` VALUES (1,12,13),(2,13,12),(3,14,13),(4,13,14),(5,18,17),(6,17,18),(7,18,13),(8,13,18),(9,17,13),(10,13,17);
/*!40000 ALTER TABLE `termorelac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `termotemp`
--

DROP TABLE IF EXISTS `termotemp`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `termotemp` (
  `id` int(6) NOT NULL,
  `nome` varchar(50) default NULL,
  `idDisciplina` int(6) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `termotemp`
--

LOCK TABLES `termotemp` WRITE;
/*!40000 ALTER TABLE `termotemp` DISABLE KEYS */;
/*!40000 ALTER TABLE `termotemp` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2009-10-13 19:25:52
