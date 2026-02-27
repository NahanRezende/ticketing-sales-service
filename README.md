# Ticketing Sales Service

Microsservico de vendas de ingressos da atividade pratica da disciplina CSI607.

## Requisitos

- Java 17
- Docker e Docker Compose

## Subindo o banco PostgreSQL

```bash
docker compose up -d
```

O script `docker/postgres/init.sql` roda automaticamente na primeira criacao do volume e popula dados iniciais.

Para reaplicar o script do zero:

```bash
docker compose down -v
docker compose up -d
```

## Executando a aplicacao

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Aplicacao: `http://localhost:4000`

Swagger UI: `http://localhost:4000/swagger-ui.html`

## Testes

```powershell
.\mvnw.cmd test
```

## Endpoints

Consulte:

- `ENDPOINTS.md`
- `sales.rest`
