CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS tb_events (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    description VARCHAR(255) NOT NULL,
    type VARCHAR(30) NOT NULL,
    date_time TIMESTAMP NOT NULL,
    starting_sales TIMESTAMP NOT NULL,
    ending_sales TIMESTAMP NOT NULL,
    price REAL NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_sales (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    event_id UUID NOT NULL REFERENCES tb_events(id),
    date_time TIMESTAMP,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

INSERT INTO tb_events (description, type, date_time, starting_sales, ending_sales, price, created_at, updated_at)
SELECT
    'Palestra de Java e Spring Boot',
    'PALESTRA',
    '2026-04-20 19:00:00',
    '2026-04-01 08:00:00',
    '2026-04-19 23:59:59',
    59.90,
    '2026-03-01 10:00:00',
    '2026-03-01 10:00:00'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_events WHERE description = 'Palestra de Java e Spring Boot'
);

INSERT INTO tb_events (description, type, date_time, starting_sales, ending_sales, price, created_at, updated_at)
SELECT
    'Show da Semana Academica',
    'SHOW',
    '2026-05-10 21:00:00',
    '2026-04-01 08:00:00',
    '2026-05-09 23:59:59',
    120.00,
    '2026-03-01 10:00:00',
    '2026-03-01 10:00:00'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_events WHERE description = 'Show da Semana Academica'
);

INSERT INTO tb_events (description, type, date_time, starting_sales, ending_sales, price, created_at, updated_at)
SELECT
    'Curso de APIs REST com Spring',
    'CURSO',
    '2026-06-15 18:30:00',
    '2026-05-20 08:00:00',
    '2026-06-14 23:59:59',
    180.00,
    '2026-03-02 09:00:00',
    '2026-03-02 09:00:00'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_events WHERE description = 'Curso de APIs REST com Spring'
);

INSERT INTO tb_events (description, type, date_time, starting_sales, ending_sales, price, created_at, updated_at)
SELECT
    'Peca de Teatro Universitario',
    'TEATRO',
    '2026-07-03 20:00:00',
    '2026-06-01 08:00:00',
    '2026-07-02 23:59:59',
    75.00,
    '2026-03-02 09:00:00',
    '2026-03-02 09:00:00'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_events WHERE description = 'Peca de Teatro Universitario'
);

INSERT INTO tb_events (description, type, date_time, starting_sales, ending_sales, price, created_at, updated_at)
SELECT
    'Evento Integracao de Calouros',
    'OUTROS',
    '2026-08-10 14:00:00',
    '2026-07-01 08:00:00',
    '2026-08-09 23:59:59',
    25.00,
    '2026-03-02 09:00:00',
    '2026-03-02 09:00:00'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_events WHERE description = 'Evento Integracao de Calouros'
);

INSERT INTO tb_sales (user_id, event_id, date_time, status, created_at, updated_at)
SELECT
    '8f3a3478-6509-4a91-9494-5b9adcb8e102',
    e.id,
    '2026-04-05 14:30:00',
    'PAGO',
    '2026-04-05 14:30:00',
    '2026-04-05 14:30:00'
FROM tb_events e
WHERE e.description = 'Palestra de Java e Spring Boot'
  AND NOT EXISTS (
    SELECT 1
    FROM tb_sales s
    WHERE s.user_id = '8f3a3478-6509-4a91-9494-5b9adcb8e102'
      AND s.event_id = e.id
  );

INSERT INTO tb_sales (user_id, event_id, date_time, status, created_at, updated_at)
SELECT
    'd333a66d-17db-4c17-88cc-81f8754d530f',
    e.id,
    '2026-04-20 19:10:00',
    'EM_ABERTO',
    '2026-04-20 19:10:00',
    '2026-04-20 19:10:00'
FROM tb_events e
WHERE e.description = 'Show da Semana Academica'
  AND NOT EXISTS (
    SELECT 1
    FROM tb_sales s
    WHERE s.user_id = 'd333a66d-17db-4c17-88cc-81f8754d530f'
      AND s.event_id = e.id
  );

INSERT INTO tb_sales (user_id, event_id, date_time, status, created_at, updated_at)
SELECT
    '53f30766-9c53-472b-98ef-978769fd9b7c',
    e.id,
    '2026-06-01 11:00:00',
    'CANCELADO',
    '2026-06-01 11:00:00',
    '2026-06-01 11:00:00'
FROM tb_events e
WHERE e.description = 'Curso de APIs REST com Spring'
  AND NOT EXISTS (
    SELECT 1
    FROM tb_sales s
    WHERE s.user_id = '53f30766-9c53-472b-98ef-978769fd9b7c'
      AND s.event_id = e.id
  );

INSERT INTO tb_sales (user_id, event_id, date_time, status, created_at, updated_at)
SELECT
    '21afc4f6-11c8-412f-a2ce-c0184bc748d2',
    e.id,
    '2026-06-20 16:45:00',
    'ESTORNADO',
    '2026-06-20 16:45:00',
    '2026-06-20 16:45:00'
FROM tb_events e
WHERE e.description = 'Peca de Teatro Universitario'
  AND NOT EXISTS (
    SELECT 1
    FROM tb_sales s
    WHERE s.user_id = '21afc4f6-11c8-412f-a2ce-c0184bc748d2'
      AND s.event_id = e.id
  );
