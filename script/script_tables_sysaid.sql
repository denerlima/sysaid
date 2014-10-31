
create table COMPANY (
    COMPANY_ID number(10,0) not null,
    COMPANY_NAME varchar2(255 char),
    primary key (COMPANY_ID)
);
    
    
create table SYSAID_USER (
    USER_NAME varchar2(255 char) not null,
    FIRST_NAME varchar2(255 char),
    LAST_NAME varchar2(255 char),
    CALCULATED_USER_NAME varchar2(255 char),
    primary key (USER_NAME)
);    
    
