-- Alunos: Axel Crispim, José Ivan, Joeberth Souza, Matheus Silva, Thaynnara Gonçalves

-- Questão 1: Não Implementada

-- Questão 2 
SELECT COUNT(*) 
FROM RESERVA
WHERE (DIA_CHECK_IN LIKE '%2018%' OR DIA_CHECK_OUT LIKE '%2018%');

-- Questão 3
CREATE VIEW quartosDisponiveis AS (
    SELECT DISTINCT q.* 
    FROM QUARTO q 
    WHERE q.numero IN (
        SELECT q.numero 
        FROM QUARTO q 
        WHERE NOT EXISTS (
            SELECT * 
            FROM RESERVA r, HOSPEDA h 
            WHERE (r.numero_quarto = q.numero OR h.numero_quarto = q.numero)
        )    
        GROUP BY q.numero
    )
);

-- Questão 4
SELECT e.cpf FROM cliente e MINUS SELECT e.cpf FROM cliente e, hospeda h, venda v, produto p WHERE  v.data BETWEEN h.dia_check_in AND h.dia_check_out AND e.cpf = h.cpf_cliente AND h.numero_quarto = v.numero_quarto AND v.id_produto = p.id GROUP BY e.cpf HAVING SUM(p.valor * v.quantidade) > 100;

-- Questão 5
SELECT DISTINCT c.* 
FROM CLIENTE c, AVALIACAO a 
WHERE (a.nota >= (
    SELECT AVG(nota) 
    FROM AVALIACAO) 
AND a.cpf_cliente = c.cpf);

-- Questão 6
SELECT c.cpf, c.email, c.nome, count(*) as Num_Dependente 
FROM CLIENTE c, DEPENDENTE d
WHERE  c.cpf = d.cpf_cliente AND c.cpf IN (
    SELECT CPF_CLIENTE 
    FROM HOSPEDA 
    WHERE DIA_CHECK_IN LIKE '%2018%'	OR DIA_CHECK_OUT LIKE '%2018%')
GROUP BY c.cpf, c.email, c.nome
ORDER BY Num_Dependente;

-- Questão 7
SELECT c.nome 
FROM CLIENTE c 
WHERE NOT EXISTS (
    SELECT * 
    FROM DEPENDENTE d 
    WHERE c.cpf = d.cpf_cliente
) and c.sexo = 'Feminino';

-- Questão 8
SELECT tipo_manutencao
FROM MANUTENCAO
GROUP BY tipo_manutencao
HAVING COUNT(*) >= all
    ( 
      SELECT COUNT(*)
      FROM MANUTENCAO
      GROUP BY tipo_manutencao  
    );

-- Questão 9
SELECT id, nome, valor, sum(v.quantidade * p.valor) AS total_vendas 
FROM produto p, venda v 
WHERE p.id = v.id_produto
GROUP BY id, nome, valor
HAVING SUM(v.quantidade * p.valor) < 500;

-- Questão 10
SELECT DISTINCT funcao FROM funcionario WHERE nome LIKE '%João%' MINUS SELECT DISTINCT funcao FROM funcionario WHERE nome NOT LIKE '%João%';

-- Questão 11
CREATE VIEW quartosGastaramMais1000Frigobar AS 
SELECT DISTINCT q.* 
FROM QUARTO q 
WHERE q.numero IN (
    SELECT q.numero 
    FROM QUARTO q, VENDA v, PRODUTO p 
    WHERE p.id = v.id_produto AND q.numero = v.numero_quarto AND p.tipo = 'Frigobar'
    GROUP BY q.numero
    HAVING SUM(v.quantidade * p.valor) > 1000
);

-- Questão 12
CREATE VIEW quartosMaisReservadosEm2018 AS 
    SELECT DISTINCT q.* 
    FROM quarto q 
    WHERE q.numero IN (
        SELECT numero_quarto
        FROM Hospeda
        GROUP BY numero_quarto
        HAVING COUNT(*) >= all (
            SELECT COUNT(*)
            FROM Hospeda
            WHERE dia_check_in LIKE '%2018%')  
    )
);

-- Questão 13
ALTER TABLE QUARTO ADD CONSTRAINT check_quarto 
    CHECK (REGEXP_LIKE (numero, '^[0-9]{3}-[A-Z]$'));

-- Questão 14
CREATE OR REPLACE TRIGGER verificaPeriodoReserva
BEFORE INSERT ON RESERVA
FOR EACH ROW
DECLARE
    v_quarto VARCHAR2(20);
BEGIN
    SELECT numero_quarto INTO v_quarto
    FROM Reserva
    WHERE numero_quarto = :new.numero_quarto AND
          dia_check_in = :new.dia_check_in AND
          dia_check_out = :new.dia_check_out;
    EXCEPTION
    WHEN no_data_found THEN
        v_quarto := NULL; 
    IF (v_quarto IS NOT NULL) THEN
        RAISE_APPLICATION_ERROR (-20202,'Ja existe uma reserva para esse quarto no mesmo periodo de tempo');
    END IF;          
END verificaPeriodoReserva;
/ 

-- Questão 15
CREATE OR REPLACE TRIGGER verificaReserva
BEFORE INSERT ON HOSPEDA
FOR EACH ROW
DECLARE
    v_cpf VARCHAR(14);
BEGIN    
    SELECT cpf_cliente INTO v_cpf
    FROM Reserva r
    WHERE r.cpf_cliente = :new.cpf_cliente AND
          r.numero_quarto = :new.numero_quarto ;    
    EXCEPTION
    WHEN no_data_found THEN
        v_cpf := NULL;    
    IF (v_cpf IS NULL) THEN
        RAISE_APPLICATION_ERROR (-20202,'Cliente não tem reserva para este quarto, logo, não poderá ser hospedar.');
    END IF;
END verificaReserva;
/

-- Questão 16
CREATE OR REPLACE FUNCTION calculaOrcamentoReserva (cpf IN VARCHAR, numero_quarto IN NUMBER) 
RETURN NUMBER IS
    diaria NUMBER;
    dia_checkin DATE;
    dia_checkout DATE;
    diferenca NUMBER;
    BEGIN
      select q.valor_diaria into diaria from reserva r, quarto q where r.numero_quarto = q.numero and cpf = r.cpf_cliente;
      select r.DIA_CHECK_IN into dia_checkin from reserva r, quarto q where r.numero_quarto = q.numero and cpf = r.cpf_cliente;
      select r.DIA_CHECK_OUT into dia_checkout from reserva r, quarto q where r.numero_quarto = q.numero and cpf = r.cpf_cliente;
      select (dia_checkout - dia_checkin) into diferenca FROM DUAL;
     RETURN diferenca * diaria;
END calculaOrcamentoReserva;
/
SET SERVEOUTPUT ON
DECLARE
   texto VARCHAR2(1000);
BEGIN
   texto := calculaOrcamentoReserva('111','1');
   DBMS_OUTPUT.PUT_LINE(texto);
END;
/

-- Questão 17
CREATE OR REPLACE FUNCTION calculaMediaAvaliacoesByMes (mes IN VARCHAR) 
RETURN NUMBER IS
    media NUMBER;
    mes_num VARCHAR(2);
    BEGIN        
        IF mes = 'JAN' THEN
            mes_num := '01';
        ELSIF mes = 'FEV' THEN 
            mes_num := '02';
        ELSIF mes = 'MAR' THEN
            mes_num := '03';
        ELSIF mes = 'ABR' THEN
            mes_num := '04';
        ELSIF mes = 'MAI' THEN
            mes_num := '05';
        ELSIF mes = 'JUN' THEN
            mes_num := '06';
        ELSIF mes = 'JUL' THEN
            mes_num := '07';
        ELSIF mes = 'AGO' THEN
            mes_num := '08';
        ELSIF mes = 'SET' THEN
            mes_num := '09';
        ELSIF mes = 'OTU' THEN
            mes_num := '10';
        ELSIF mes = 'NOV' THEN
            mes_num := '11';
        ELSE
            mes_num := '12';
        END IF;        
        SELECT AVG(nota) INTO media
        FROM Avaliacao
        WHERE REGEXP_LIKE(data, ('^[0-9]{1,2}/' || mes_num));        
        RETURN media;
END calculaMediaAvaliacoesByMes;
/
SET serveroutput ON;
DECLARE
    m NUMBER;  
BEGIN
    m := calculaMediaAvaliacoesByMes('JUL');
    dbms_output.put_line('media das notas neste mes eh  ' || m);  
END;
