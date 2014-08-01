
    create table MF_APLICACAO (
        id number(10,0) not null,
        ativo number(10,0) not null,
        descricao varchar2(255 char),
        primary key (id)
    )
;
    create table MF_GRUPO (
        id number(10,0) not null,
        ativo number(10,0) not null,
        descricao varchar2(255 char),
        id_grupo_pai number(10,0),
        primary key (id)
    )
;
    create table MF_INVENTARIO (
        id number(10,0) not null,
        ativo number(10,0) not null,
        dataInventario timestamp,
        justificativa varchar2(255 char),
        numeroInventario number(19,0),
        id_atendente number(10,0),
        primary key (id)
    )
;
    create table MF_INVENTARIO_MF_MATERIAL (
        id number(10,0) not null,
        ativo number(10,0),
        quantidade_estoque number(19,2),
        quantidade_inventariada number(19,2),
        id_inventario number(10,0),
        id_material number(10,0),
        primary key (id)
    )
;
    create table MF_MAODEOBRA (
        id number(10,0) not null,
        ativo number(10,0) not null,
        descricao varchar2(255 char),
        valordia number(20,2) not null,
        valorhora number(20,2) not null,
        primary key (id)
    )
;
    create table MF_MARCA (
        id number(10,0) not null,
        ativo number(10,0) not null,
        descricao varchar2(255 char),
        primary key (id)
    )
;
    create table MF_MATERIAL (
        id number(10,0) not null,
        ativo number(10,0),
        codigoBarra varchar2(255 char),
        descricao varchar2(255 char),
        estoque number(19,2) not null,
        estoqueCalculado number(19,2) not null,
        estoqueInformado number(19,2) not null,
        material varchar2(255 char),
        id_aplicacao number(10,0),
        id_grupo number(10,0),
        id_marca number(10,0),
        id_tipomaterial number(10,0),
        id_unidadeMedida number(10,0),
        primary key (id)
    )
;
    create table MF_NOTAFISCAL (
        id number(10,0) not null,
        acrescimos number(20,2) not null,
        ativo number(10,0) not null,
        dataEmissao timestamp,
        dataEntrega timestamp,
        justificativa varchar2(255 char),
        nrNotaFiscal number(19,0),
        totalGeralNota number(20,2) not null,
        totalProdutos number(20,2) not null,
        id_fornecedor number(10,0),
        primary key (id)
    )
;
    create table MF_NOTAFISCAL_MF_MATERIAL (
        id number(10,0) not null,
        ativo number(10,0),
        percentual_desconto number(20,2) not null,
        preco_unitario number(20,2) not null,
        quantidade number(20,2) not null,
        total number(20,2) not null,
        valor_desconto number(20,2) not null,
        id_notafiscal number(10,0),
        id_ordem_compra_material number(10,0),
        primary key (id)
    )
;
    create table MF_ORDEMDECOMPRA (
        id number(10,0) not null,
        ativo number(10,0),
        dataAutorizacao timestamp,
        dataEmissao timestamp,
        justificativa varchar2(255 char),
        numeroOC number(19,0),
        id_usuario number(10,0),
        id_fornecedor number(10,0),
        primary key (id)
    )
;
    create table MF_ORDEMDECOMPRA_MF_MATERIAL (
        id number(10,0) not null,
        ativo number(10,0),
        quantidade_autorizada number(19,2),
        id_material number(10,0),
        id_ordemdecompra number(10,0),
        primary key (id)
    )
;
    create table MF_ORDEMSERVICO (
        id number(10,0) not null,
        ativo number(10,0),
        justificativa varchar2(255 char),
        primary key (id)
    )
;
    create table MF_ORDEMSERVICO_MF_MATERIAL (
        id number(10,0) not null,
        ativo number(10,0),
        quantidade_entregue number(19,2) not null,
        quantidade_pendente number(19,2) not null,
        quantidade_solicitada number(19,2) not null,
        id_material number(10,0),
        id_ordem_servico number(10,0),
        primary key (id)
    )
;
    create table MF_TIPOMATERIAL (
        id number(10,0) not null,
        ativo number(10,0) not null,
        descricao varchar2(255 char),
        primary key (id)
    )
;
    create table MF_UNIDADE (
        id number(10,0) not null,
        ativo number(10,0),
        descricao varchar2(255 char),
        primary key (id)
    )
;
    create table MF_UNIDADEMEDIDA (
        id number(10,0) not null,
        ativo number(10,0),
        id_unidadeentrada number(10,0),
        primary key (id)
    )
;
    create table MF_UNIDADEMEDIDA_SAIDA (
        id number(10,0) not null,
        ativo number(10,0),
        fatorConversao number(19,2) not null,
        id_unidade number(10,0),
        id_unidade_medida number(10,0),
        primary key (id)
    )
;
    alter table MF_GRUPO 
        add constraint FK5BDA2A23207AC2C1 
        foreign key (id_grupo_pai) 
        references MF_GRUPO
;
    alter table MF_INVENTARIO 
        add constraint FKA03121D782E26FE6 
        foreign key (id_atendente) 
        references SYSAID_USER
;
    alter table MF_INVENTARIO_MF_MATERIAL 
        add constraint FKC83BAEA53A6905F8 
        foreign key (id_material) 
        references MF_MATERIAL
;
    alter table MF_INVENTARIO_MF_MATERIAL 
        add constraint FKC83BAEA543FB600C 
        foreign key (id_inventario) 
        references MF_INVENTARIO
;
    alter table MF_MATERIAL 
        add constraint FKDABD390DC63D4380 
        foreign key (id_tipomaterial) 
        references MF_TIPOMATERIAL
;
    alter table MF_MATERIAL 
        add constraint FKDABD390DCA3F85C6 
        foreign key (id_unidadeMedida) 
        references MF_UNIDADEMEDIDA
;
    alter table MF_MATERIAL 
        add constraint FKDABD390D3979190E 
        foreign key (id_marca) 
        references MF_MARCA
;
    alter table MF_MATERIAL 
        add constraint FKDABD390D1645AA24 
        foreign key (id_aplicacao) 
        references MF_APLICACAO
;
    alter table MF_MATERIAL 
        add constraint FKDABD390D38DF8D68 
        foreign key (id_grupo) 
        references MF_GRUPO
;
    alter table MF_NOTAFISCAL 
        add constraint FK484FAD92B5F3BF4 
        foreign key (id_fornecedor) 
        references COMPANY
;
    alter table MF_NOTAFISCAL_MF_MATERIAL 
        add constraint FK919779E0E29E7F77 
        foreign key (id_ordem_compra_material) 
        references MF_ORDEMDECOMPRA_MF_MATERIAL
;
    alter table MF_NOTAFISCAL_MF_MATERIAL 
        add constraint FK919779E05D9D63A2 
        foreign key (id_notafiscal) 
        references MF_NOTAFISCAL
;
    alter table MF_ORDEMDECOMPRA 
        add constraint FK9B52FF424BBC8D72 
        foreign key (id_usuario) 
        references SYSAID_USER
;
    alter table MF_ORDEMDECOMPRA 
        add constraint FK9B52FF42B5F3BF4 
        foreign key (id_fornecedor) 
        references COMPANY
;
    alter table MF_ORDEMDECOMPRA_MF_MATERIAL 
        add constraint FKE73DC390BA9265E6 
        foreign key (id_ordemdecompra) 
        references MF_ORDEMDECOMPRA
;
    alter table MF_ORDEMDECOMPRA_MF_MATERIAL 
        add constraint FKE73DC3903A6905F8 
        foreign key (id_material) 
        references MF_MATERIAL
;
    alter table MF_ORDEMSERVICO_MF_MATERIAL 
        add constraint FK7535B18A3A6905F8 
        foreign key (id_material) 
        references MF_MATERIAL
;
    alter table MF_ORDEMSERVICO_MF_MATERIAL 
        add constraint FK7535B18A303B48E1 
        foreign key (id_ordem_servico) 
        references MF_ORDEMSERVICO
;
    alter table MF_UNIDADEMEDIDA 
        add constraint FK2516C1424B850C75 
        foreign key (id_unidadeentrada) 
        references MF_UNIDADE
;
    alter table MF_UNIDADEMEDIDA_SAIDA 
        add constraint FK5969F03B95C05C11 
        foreign key (id_unidade_medida) 
        references MF_UNIDADEMEDIDA
;
    alter table MF_UNIDADEMEDIDA_SAIDA 
        add constraint FK5969F03B395C1C72 
        foreign key (id_unidade) 
        references MF_UNIDADE
;
    create sequence MF_APLICACAO_ID_SEQ
;
    create sequence MF_GRUPO_ID_SEQ
;
    create sequence MF_INVENTARIO_ID_SEQ
;
    create sequence MF_INVENTARIO_MATERIAL_ID_SEQ
;
    create sequence MF_MAODEOBRA_ID_SEQ
;
    create sequence MF_MARCA_ID_SEQ
;
    create sequence MF_MATERIAL_ID_SEQ
;
    create sequence MF_NOTAFISCAL_ID_SEQ
;
    create sequence MF_NOTAFISCAL_MATERIAL_ID_SEQ
;
    create sequence MF_ORDEMDECOMPRA_ID_SEQ
;
    create sequence MF_ORDEMDECOMPRA_MAT_ID_SEQ
;
    create sequence MF_ORDEMSERVICO_MAT_ID_SEQ
;
    create sequence MF_TIPOMATERIAL_ID_SEQ
;
    create sequence MF_UNIDADEMEDIDA_ID_SEQ
;
    create sequence MF_UNIDADE_ID_SEQ
;
    create sequence MF_UNI_MED_SAIDA_ID_SEQ
;
    