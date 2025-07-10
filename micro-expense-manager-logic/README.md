# Gestión de Gastos con Spring Boot

Este repositorio contiene dos microservicios independientes desarrollados con Spring Boot. Cada uno cuenta con su propio wrapper de Maven (`mvnw`) para facilitar la construcción y las pruebas.

## Requisitos

- **Java 17**. La versión está definida en los `pom.xml` de los módulos.
- **Maven** no es necesario instalarlo manualmente, se utiliza el wrapper incluido.
- **PostgreSQL** para el microservicio de gestión de gastos.

## Microservicios

### micro-expense-manager-logic
Gestor de gastos que expone una API REST para crear, listar, actualizar y eliminar gastos. La configuración de la base de datos se encuentra en `micro-expense-manager-logic/src/main/resources/application.yml`.

### micro-login
Microservicio inicial para el proceso de autenticación. Actualmente solo contiene la aplicación base sin lógica adicional.

## Construcción de los módulos

Cada módulo se construye de manera independiente. Desde la raíz del repositorio ejecute:

```bash
cd micro-expense-manager-logic
./mvnw clean package
cd ..

cd micro-login
./mvnw clean package
cd ..
```

## Base de datos y Flyway

El microservicio de gestión de gastos utiliza PostgreSQL. La conexión por defecto es:

- URL: `jdbc:postgresql://localhost:5432/expense_manager`
- Usuario: `${DB_USERNAME}`
- Contraseña: `${DB_PASSWORD}`

Cree la base de datos y el esquema indicados antes de iniciar la aplicación. Una vez que se integre Flyway, podrá ejecutar las migraciones con:

```bash
./mvnw flyway:migrate
```

## Ejecución de los tests

Cada microservicio dispone de su propio conjunto de pruebas. Para ejecutarlas utilice:

```bash
cd <nombre-del-microservicio>
./mvnw test
```

Por ejemplo, para el microservicio de gastos:

```bash
cd micro-expense-manager-logic
./mvnw test
```

## Base de datos con Docker

Si no desea instalar PostgreSQL localmente puede levantarla mediante Docker. En
la raíz del proyecto encontrará un `docker-compose.yml` que define el servicio
de base de datos con las mismas credenciales usadas por la aplicación.

```bash
docker compose up -d
```

Esto iniciará un contenedor llamado `expense-db` accesible en el puerto
`5432`. Los datos se almacenan en un volumen persistente llamado
`expense_data`.