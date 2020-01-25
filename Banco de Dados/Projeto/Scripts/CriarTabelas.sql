CREATE TABLE Cliente (
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(50) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    sexo CHAR NOT NULL,
    dataNascimento DATE NOT NULL,
    PRIMARY KEY(cpf)
);

CREATE TABLE Avaliacao (
    nota NUMBER NOT NULL,
    data DATE NOT NULL,
    id INT NOT NULL,
    comentario VARCHAR(200),
    cpfCliente VARCHAR(11) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(cpfCliente) REFERENCES Cliente(cpf)
);

CREATE TABLE Funcionario (
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    salario NUMBER NOT NULL,
    dataNascimento DATE NOT NULL,
    funcao VARCHAR(30) NOT NULL,
    PRIMARY KEY(cpf)
);

CREATE TABLE Quarto (
    valorDiaria NUMBER NOT NULL,
    numero INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    vista VARCHAR(50) NOT NULL,
    PRIMARY KEY(numero)
);

CREATE TABLE Produto (
    id INT NOT NULL,
    valor NUMBER NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE Dependente (
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    dataNascimento DATE NOT NULL,
    cpfCliente VARCHAR(11) NOT NULL,
    PRIMARY KEY(cpf, cpfCliente),
    FOREIGN KEY(cpfCliente) REFERENCES Cliente(cpf)
);

CREATE TABLE Hospedagem (
    id INT NOT NULL,
    cpfCliente VARCHAR(11) NOT NULL,
    dataCheckin DATE NOT NULL,
    dataCheckOut DATE NOT NULL,
    numeroQuarto INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(cpfCliente) REFERENCES Cliente(cpf),
    FOREIGN KEY(numeroQuarto) REFERENCES Quarto(numero)
);

CREATE TABLE Reserva (
    id INT NOT NULL,
    cpfCliente VARCHAR(11) NOT NULL,
    diaCheckIn DATE NOT NULL,
    diaCheckOut DATE NOT NULL,
    numeroQuarto INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(cpfCliente) REFERENCES Cliente(cpf),
    FOREIGN KEY(numeroQuarto) REFERENCES Quarto(numero)
);

CREATE TABLE Manutencao (
    id INT NOT NULL,
    numeroQuarto INT NOT NULL,
    cpfFuncionario VARCHAR(11) NOT NULL,
    data DATE NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    observacao VARCHAR(200),
    PRIMARY KEY(id),
    FOREIGN KEY(cpfFuncionario) REFERENCES Funcionario(cpf),
    FOREIGN KEY(numeroQuarto) REFERENCES Quarto(numero)

CREATE TABLE Venda (
    idVenda INT NOT NULL,
    idProduto INT NOT NULL,
    numeroQuarto INT NOT NULL,
    data DATE NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY(idVenda),
    FOREIGN KEY(numeroQuarto) REFERENCES Quarto(numero),
    FOREIGN KEY(idProduto) REFERENCES Produto(id)
);


CREATE TABLE Telefone (
    cpfCliente VARCHAR(11) NOT NULL,
    numeroTelefone VARCHAR(11) NOT NULL,
    PRIMARY KEY(cpfCliente, numeroTelefone),
    FOREIGN KEY(cpfCliente) REFERENCES Cliente(cpf)
);

CREATE TABLE Equipamento (
    equipamento VARCHAR(30) NOT NULL,
    numeroQuarto INT NOT NULL,
    PRIMARY KEY(numeroQuarto, equipamento),
    FOREIGN KEY(numeroQuarto) REFERENCES Quarto(numero)
);
