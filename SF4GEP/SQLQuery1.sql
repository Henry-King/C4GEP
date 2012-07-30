create database SF4GEP

create table DataSet(
	id int primary key identity(1,1),
	name varchar(256) not null unique,
	rowNum int not null,
	columnNum int not null
)
create table DataRow(
	id int primary key identity(1,1),
	idx int,
	dataSet int ,
	resultColumn int,
	foreign key (dataSet) references DataSet(id),
)
create table DataColumn(
	id int primary key identity(1,1),
	idx int,
	columnName varchar(64) not null,
	value float not null,
	dataRow int ,
	foreign key (dataRow) references DataRow(id)
)
create table OperatorConfiguration(
	id int primary key identity(1,1),
	mutateRate float not null,
	isTransportRate float not null,
	isElementString varchar(1024) not null,
	risTransportRate float not null,
	risElementString varchar(1024) not null,
	geneTransportRate float not null,
	onePointRecombineRate float not null,
	twoPointRecombineRate float not null,
	geneRecombineRate float not null
)
create table geneConfiguration(
	id int primary key identity(1,1),
	useHomeoticGene bit,
	connectionFunctionString varchar(1024),
	normalGeneNumber int not null,
	normalGeneHeaderLength int not null,
	homeoticGeneNumber int not null,
	homeoticGeneHeaderLength int not null,
	functionClass varchar(1024) not null
)
create table IndividualConfiguration(
	id int primary key identity(1,1),
	individualNumber int not null,
	geneConfiguration int not null,
	foreign key (geneConfiguration) references geneConfiguration(id) 
)

create table GepAlgConfiguration(
	id int primary key identity(1,1),
	name varchar(900) unique not null,
	maxGeneration bigint not null,
	selectionRange float,
	accuracy float,
	operatorConfiguration int not null,
	individualConfiguration int not null,
	foreign key (operatorConfiguration) references OperatorConfiguration(id),
	foreign key (individualConfiguration) references IndividualConfiguration(id),
)

create table GepAlgRun(
	id int primary key identity(1,1),
	gepAlgConfiguration int,
	dataSet int,
	foreign key (gepAlgConfiguration) references GepAlgConfiguration(id),
	foreign key (dataSet) references DataSet(id)
)

create table Populations(
	id int primary key identity(1,1),
	gepAlgRun int,
	generationNum bigint,
	foreign key (gepAlgRun) references GepAlgRun(id)
)

create table Individual (
	id int primary key identity(1,1),
	idx int,
	fitness float,
	selectedHomeoticGeneNumber int,
	
	populations int,
	foreign key (populations) references Populations(id)
)

create table FittedValue(
	id int primary key identity(1,1),
	idx int,
	fittedValue float,
	dataRow int,
	individual int,
	foreign key (dataRow) references dataRow(id),
	foreign key (individual) references Individual(id),
)

create table Gene(
	id int primary key identity(1,1),
	idx int,
	individual int,
	geneTypeString varchar(32),
	foreign key (individual) references Individual(id)
)

create table GenePiece(
	id int primary key identity(1,1),
	idx int,
	gene int,
	symbol varchar(64),
	name varchar(64),
	genePieceTypeString varchar(64),
	variableIndex int,
	funcString varchar(64),
	foreign key (gene) references Gene(id)
)