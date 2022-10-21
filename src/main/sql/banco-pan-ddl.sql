DROP TABLE PAN_CLIENTE CASCADE CONSTRAINTS;
DROP TABLE PAN_ENDERECO CASCADE CONSTRAINTS;
DROP TABLE PAN_CIDADE CASCADE CONSTRAINTS;

DROP SEQUENCE PAN_SQ_ID_CLIENTE;
DROP SEQUENCE PAN_SQ_ID_ENDERECO;
DROP SEQUENCE PAN_SQ_ID_CIDADE;

CREATE SEQUENCE PAN_SQ_ID_CLIENTE START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE PAN_SQ_ID_ENDERECO START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE PAN_SQ_ID_CIDADE START WITH 1 INCREMENT BY 1;

CREATE TABLE PAN_CLIENTE (
    id_cliente NUMBER(10) NOT NULL,
    nm_cliente VARCHAR2(80) NOT NULL,
    nr_cpf NVARCHAR2(11) NOT NULL,
    dt_nascimento DATE NOT NULL,
    dt_adesao DATE DEFAULT SYSDATE,
    ds_email VARCHAR2(320),
    CONSTRAINT pan_cliente_pk PRIMARY KEY(id_cliente),
    CONSTRAINT pan_email_sk UNIQUE(ds_email),
    CONSTRAINT pan_cpf_sk UNIQUE(nr_cpf)
);

CREATE TABLE PAN_ENDERECO (
    id_endereco NUMBER(10) NOT NULL,
    ds_complemento VARCHAR2(20),
    nr_rua NUMBER(5) NOT NULL,
    nm_rua VARCHAR2(80) NOT NULL,
    nm_bairro VARCHAR2(80) NOT NULL,
    nr_cep NUMBER(8) NOT NULL,
    id_cliente NUMBER(10) NOT NULL,
    id_cidade NUMBER(7) NOT NULL,
    CONSTRAINT pan_endereco_pk PRIMARY KEY(id_endereco)
);

CREATE TABLE PAN_CIDADE (
    id_cidade NUMBER(7) NOT NULL,
    nm_cidade VARCHAR2(60) NOT NULL,
    sg_estado VARCHAR2(2) NOT NULL,
    CONSTRAINT pan_cidade_pk PRIMARY KEY(id_cidade),
    CONSTRAINT pan_cidade_un UNIQUE(nm_cidade, sg_estado),
    CONSTRAINT pan_estado_ck CHECK (sg_estado IN 
        ('AC', 'AL', 'AP', 'AM', 'BA', 'CE', 
        'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 
        'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 
        'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 
        'SE', 'TO', 'DF')
    )
);

ALTER TABLE PAN_ENDERECO 
    ADD CONSTRAINT pan_endereco_cliente_fk FOREIGN KEY(id_cliente)
        REFERENCES PAN_CLIENTE(id_cliente);

ALTER TABLE PAN_ENDERECO
    ADD CONSTRAINT pan_endereco_cidade_fk FOREIGN KEY(id_cidade)
        REFERENCES PAN_CIDADE(id_cidade);


INSERT INTO PAN_CLIENTE(id_cliente, nr_cpf, dt_nascimento, nm_cliente, ds_email) 
VALUES(pan_sq_id_cliente.nextval, '87983954085', SYSDATE, 'Ana Eloiza', 'ana.eloiza@gmail.com');
COMMIT;

INSERT INTO PAN_CLIENTE(id_cliente, nr_cpf, dt_nascimento, nm_cliente, ds_email) 
VALUES(pan_sq_id_cliente.nextval, '73866422024', SYSDATE, 'Gabriel Guedes', 'gabriel.guedes@gmail.com');
COMMIT;

INSERT INTO PAN_CLIENTE(id_cliente, nr_cpf, dt_nascimento, nm_cliente, ds_email)
VALUES(pan_sq_id_cliente.nextval, '34120669076', SYSDATE, 'Ronaldo Santos', 'ronaldo.santos@gmail.com.br');
COMMIT;

INSERT INTO PAN_CIDADE(id_cidade, nm_cidade, sg_estado) VALUES(pan_sq_id_cidade.nextval, 'Belo Horizonte', 'MG');
COMMIT;

INSERT INTO PAN_CIDADE(id_cidade, nm_cidade, sg_estado) VALUES(pan_sq_id_cidade.nextval, 'Cotia', 'SP');
COMMIT;

INSERT INTO PAN_CIDADE(id_cidade, nm_cidade, sg_estado) VALUES(pan_sq_id_cidade.nextval, 'Campinas', 'SP');
COMMIT;

INSERT INTO PAN_ENDERECO(id_endereco, nm_rua, nr_rua, ds_complemento, nr_cep, nm_bairro, id_cidade, id_cliente)
    VALUES(pan_sq_id_endereco.nextval, 'Avenida Carlos Lacerda', 2093, null, 5658895, 'Campo Limpo', 1, 1);
COMMIT;

INSERT INTO PAN_ENDERECO(id_endereco, nm_rua, nr_rua, ds_complemento, nr_cep, nm_bairro, id_cidade, id_cliente)
    VALUES(pan_sq_id_endereco.nextval, 'Avenida Abrahão Caram', 1001, null, 31275000, 'São José', 2, 2);
COMMIT;

INSERT INTO PAN_ENDERECO(id_endereco, nm_rua, nr_rua, ds_complemento, nr_cep, nm_bairro, id_cidade, id_cliente)
    VALUES(pan_sq_id_endereco.nextval, 'Rua Santo Afonso', 117, 'Apto 58', 06708395, 'Vila Santo Antônio Costa', 3, 3);
COMMIT;

SELECT * FROM PAN_CLIENTE;
SELECT * FROM PAN_CIDADE;
select * from pan_endereco;

