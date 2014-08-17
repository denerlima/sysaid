
--02/08
ALTER TABLE MF_ORDEMSERVICO_MF_MATERIAL ADD (ID_UNI_MED_SAIDA NUMBER);
ALTER TABLE MF_ORDEMSERVICO_MF_MATERIAL ADD CONSTRAINT MF_OS_MF_MATERIAL_fk3 FOREIGN KEY (ID) REFERENCES MF_UNIDADEMEDIDA_SAIDA (ID);

CREATE TABLE MF_ORDEMSERVICO_MF_MAOOBRA 
        ( 
        ID NUMBER NOT NULL, 
        ATIVO NUMBER NOT NULL, 
        ID_ORDEM_SERVICO NUMBER NOT NULL, 
        ID_MAO_OBRA NUMBER NOT NULL, 
        QUANTIDADE NUMBER NOT NULL, 
        PERIODO NUMBER NOT NULL, 
        UNIDADE_MEDIDA NUMBER NOT NULL, 
        TOTAL NUMBER(20,2) NOT NULL, 
        CONSTRAINT OS_MAOOBRA_PK PRIMARY KEY (ID), 
        CONSTRAINT MF_OS_MF_MAOOBRA_fk1 FOREIGN KEY (ID_ORDEM_SERVICO) REFERENCES MF_ORDEMSERVICO (ID), 
        CONSTRAINT MF_OS_MF_MAOOBRA_fk2 FOREIGN KEY (ID_MAO_OBRA) REFERENCES MF_MAODEOBRA (ID) );
        

CREATE SEQUENCE "MF_OS_MAO_OBRA_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;


--04/08
CREATE TABLE MF_OS_MF_MATERIAL_HIST 
        ( 
        ID NUMBER NOT NULL, 
        ATIVO NUMBER NOT NULL, 
        DATA DATE NOT NULL, 
        ID_ORDEM_SERV_MATERIAL NUMBER NOT NULL, 
        TIPO INTEGER NOT NULL, 
        QUANTIDADE NUMBER NOT NULL,
        QUANTIDADE_ANTERIOR NUMBER NOT NULL,
        CONSTRAINT OS_MAT_HIST_PK PRIMARY KEY (ID), 
        CONSTRAINT MF_OS_MF_MAT_HIST_fk1 FOREIGN KEY (ID_ORDEM_SERV_MATERIAL) REFERENCES MF_ORDEMSERVICO_MF_MATERIAL (ID) 
        );
        
CREATE SEQUENCE MF_OS_MAT_HIST_ID_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;


ALTER TABLE MF_ORDEMDECOMPRA DROP COLUMN ID_USUARIO;
ALTER TABLE MF_ORDEMDECOMPRA ADD (USER_NAME_AUTORIZADOR VARCHAR2(64));
ALTER TABLE MF_ORDEMDECOMPRA MODIFY (JUSTIFICATIVA VARCHAR2(1000));
ALTER TABLE MF_ORDEMDECOMPRA ADD CONSTRAINT MF_ORDEMDECOMPRA_fk1 FOREIGN KEY (USER_NAME_AUTORIZADOR) REFERENCES SYSAID_USER (USER_NAME);

ALTER TABLE MF_INVENTARIO DROP COLUMN ID_ATENDENTE;
ALTER TABLE MF_INVENTARIO ADD (USER_NAME_ATENDENTE VARCHAR2(64));
ALTER TABLE MF_INVENTARIO MODIFY (JUSTIFICATIVA VARCHAR2(1000));
ALTER TABLE MF_INVENTARIO ADD CONSTRAINT MF_INVENTARIO_fk1 FOREIGN KEY (USER_NAME_ATENDENTE) REFERENCES SYSAID_USER (USER_NAME);


--05/08
ALTER TABLE MF_ORDEMDECOMPRA DROP COLUMN ID_FORNECEDOR;
ALTER TABLE MF_ORDEMDECOMPRA ADD (ID_FORNECEDOR NUMBER(11));
ALTER TABLE MF_ORDEMDECOMPRA ADD CONSTRAINT MF_ORDEMDECOMPRA_fk2 FOREIGN KEY (ID_FORNECEDOR) REFERENCES COMPANY (COMPANY_ID);

ALTER TABLE MF_NOTAFISCAL MODIFY (JUSTIFICATIVA VARCHAR2(1000));
ALTER TABLE MF_NOTAFISCAL DROP COLUMN ID_FORNECEDOR;
ALTER TABLE MF_NOTAFISCAL ADD (ID_FORNECEDOR NUMBER(11));
ALTER TABLE MF_NOTAFISCAL ADD CONSTRAINT MF_NOTAFISCAL_fk1 FOREIGN KEY (ID_FORNECEDOR) REFERENCES COMPANY (COMPANY_ID);


--10/08
ALTER TABLE MF_MATERIAL ADD (AJUSTE NUMBER(20,2) DEFAULT 0 NOT NULL);
ALTER TABLE MF_MATERIAL ADD (QTDSOLICITADA NUMBER(20,2) DEFAULT 0 NOT NULL);

ALTER TABLE MF_ORDEMSERVICO_MF_MAOOBRA ADD (VALOR_UNITARIO NUMBER(20,2) DEFAULT 0 NOT NULL);


--12/08
ALTER TABLE MF_ORDEMSERVICO_MF_MATERIAL ADD (QUANTIDADE_DEVOLVIDA NUMBER(20,2) DEFAULT 0 NOT NULL);
ALTER TABLE MF_ORDEMSERVICO_MF_MATERIAL ADD (QUANTIDADE_UTILIZADA NUMBER(20,2) DEFAULT 0 NOT NULL);
ALTER TABLE MF_ORDEMSERVICO_MF_MATERIAL DROP CONSTRAINT MF_OS_MF_MATERIAL_FK3;
ALTER TABLE MF_ORDEMSERVICO_MF_MATERIAL ADD CONSTRAINT OS_MATERIAL_UNIDADESAI_FK FOREIGN KEY (ID_UNI_MED_SAIDA) REFERENCES MF_UNIDADEMEDIDA_SAIDA (ID);

CREATE TABLE MF_PARAMETRO
        ( 
        ID NUMBER NOT NULL, 
        QTDMESES NUMBER NOT NULL,
        CONSTRAINT PARAMETRO_PK PRIMARY KEY (ID)
        );

        
--16/08
ALTER TABLE MF_INVENTARIO ADD (STATUS NUMBER);
ALTER TABLE MF_INVENTARIO_MF_MATERIAL ADD (QUANTIDADE_APROVADA NUMBER(20,2) DEFAULT 0 NOT NULL);
ALTER TABLE MF_INVENTARIO_MF_MATERIAL ADD (JUSTIFICATIVA VARCHAR2(1000));
ALTER TABLE MF_INVENTARIO_MF_MATERIAL ADD (STATUS NUMBER);
ALTER TABLE MF_INVENTARIO_MF_MATERIAL ADD (USER_NAME_USUARIO VARCHAR2(64));
ALTER TABLE MF_INVENTARIO_MF_MATERIAL ADD CONSTRAINT MF_INVENTARIO_MF_MATERIAL_fk3 FOREIGN KEY (USER_NAME_USUARIO) REFERENCES SYSAID_USER (USER_NAME);
