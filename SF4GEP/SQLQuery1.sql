
create database SF4GEP
create table gepconfiguration(
id bigint identity(1,1) primary key,
inputFile varchar(200),
name varchar(100) unique,
	  populationSize varchar(40),
	  normalGeneNumber varchar(20),
	  homeoticGeneNumber varchar(20),
	  normalHeaderLength varchar(40),
	  homeoticHeaderLength varchar(40),
	  randomConstantStart varchar(100),
	  randomConstantEnd varchar(100),
	  constantListSize varchar(100),
	  creator varchar(1024),
	  calculator varchar(1024),
	  selectionRange varchar(100),
	  accuray varchar(100),
	  maxGeneration varchar(1024),
	  functionList varchar(1024),
	  selector varchar(1024),
	  modify varchar(1024),
	  mutateRate varchar(20),
	  isTransportRate varchar(20),
	  isElement varchar(100),
	  risTransportRate varchar(20),
	  risElement varchar(100),
	  geneTransportRate varchar(20),
	  onePointRecombineRate varchar(20),
	  twoPointRecombineRate varchar(20),
	  geneRecombineRate varchar(20),
)
create table algInstance(
id bigint identity(1,1) primary key,
configuration bigint,
runTimes bigint,
totalGeneration bigint,
foreign key (configuration) references gepconfiguration(id)
)
create table outputPopulation(
id bigint identity(1,1) primary key,
generation bigint,
algInstance bigint,
foreign key (algInstance) references algInstance(id)
)
create table outputIndividual(
id bigint identity(1,1) primary key,
outputPopulation bigint,
fitness float,
expression varchar(1024),
foreign key (outputPopulation) references outputPopulation(id)
)

select * from gepconfiguration
select * from algInstance
select * from outputIndividual
select * from outputPopulation