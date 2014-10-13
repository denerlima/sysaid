
11/10
ALTER TABLE MF_APLICACAO ADD (user_name_responsavel VARCHAR2(255));
ALTER TABLE MF_APLICACAO MODIFY (DESCRICAO VARCHAR2(255));
ALTER TABLE MF_APLICACAO ADD CONSTRAINT MF_APLICACAO_fk1 FOREIGN KEY (user_name_responsavel) REFERENCES SYSAID_USER (USER_NAME);


--12/10
ALTER TABLE MF_INVENTARIO_MF_MATERIAL ADD (user_name_inventariante VARCHAR2(255));
ALTER TABLE MF_INVENTARIO_MF_MATERIAL MODIFY (JUSTIFICATIVA VARCHAR2(1000));
ALTER TABLE MF_INVENTARIO_MF_MATERIAL MODIFY (JUSTIFICATIVA_INVENTARIANTE VARCHAR2(1000));
ALTER TABLE MF_INVENTARIO_MF_MATERIAL MODIFY (USER_NAME_USUARIO VARCHAR2(255));
ALTER TABLE MF_INVENTARIO_MF_MATERIAL ADD CONSTRAINT MF_INVENTARIO_MF_MATERIAL_fk4 FOREIGN KEY (user_name_inventariante) REFERENCES SYSAID_USER (USER_NAME);
UPDATE MF_INVENTARIO_MF_MATERIAL im set im.user_name_inventariante = (select i.user_name_atendente from mf_inventario i where im.id_inventario = i.id);
