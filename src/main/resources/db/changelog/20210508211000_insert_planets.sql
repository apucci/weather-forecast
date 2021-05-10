--liquibase formatted sql
--changeset author:apucci
INSERT INTO planets
values ('8f0d42fd-0c1a-45b7-b774-6a415aa17ac8', 'Vulcano', 5, 1000);
INSERT INTO planets
values ('a19ac633-108a-45b5-91b4-9a33e2922aac', 'Ferengi', -1, 500);
INSERT INTO planets
values ('455f7fb3-668f-46e3-88e4-2cddff28c394', 'Betasoide', -3, 2000);

--rollback: DELETE FROM TABLE planets where id in ('8f0d42fd-0c1a-45b7-b774-6a415aa17ac8', 'a19ac633-108a-45b5-91b4-9a33e2922aac', '455f7fb3-668f-46e3-88e4-2cddff28c394')