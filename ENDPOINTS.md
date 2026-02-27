# Ticketing Sales Service - Endpoints

Base URL: `http://localhost:4000`

Swagger UI: `http://localhost:4000/swagger-ui.html`

## Event

- `GET /events`
- `GET /events/{id}`
- `POST /events`
- `PUT /events/{id}`
- `DELETE /events/{id}`

## Sale

- `GET /sales`
- `GET /sales/{id}`
- `POST /sales`
- `PUT /sales/{id}`
- `DELETE /sales/{id}`

## Example Event payload (POST/PUT)

```json
{
  "description": "Curso Spring Boot",
  "type": "CURSO",
  "dateTime": "2026-04-20T19:00:00",
  "startingSales": "2026-04-01T08:00:00",
  "endingSales": "2026-04-19T23:59:59",
  "price": 150.0
}
```

## Example Sale payload (POST/PUT)

```json
{
  "userId": "92ff4138-3ae5-4282-a918-46a663c17629",
  "eventId": "11111111-1111-1111-1111-111111111111",
  "status": "EM_ABERTO"
}
```
