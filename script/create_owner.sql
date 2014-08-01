
DROP USER SYSAID CASCADE;

create user SYSAID IDENTIFIED BY sysaid default tablespace USERS temporary tablespace TEMP;
grant create session to SYSAID;
GRANT CONNECT,RESOURCE TO SYSAID;