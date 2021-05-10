CREATE USER vulcano_admin WITH PASSWORD 'Vulcano12$%';
ALTER USER vulcano_admin WITH SUPERUSER;
CREATE DATABASE forecast_system;
GRANT ALL PRIVILEGES ON DATABASE forecast_system TO vulcano_admin;