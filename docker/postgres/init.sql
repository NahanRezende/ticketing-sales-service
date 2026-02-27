CREATE TABLE IF NOT EXISTS tb_events (
    id UUID PRIMARY KEY,
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
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    event_id UUID NOT NULL REFERENCES tb_events(id),
    date_time TIMESTAMP,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

INSERT INTO tb_events (id, description, type, date_time, starting_sales, ending_sales, price, created_at, updated_at)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'Palestra de Arquitetura de Software', 'PALESTRA',
     NOW() + INTERVAL '10 day', NOW() - INTERVAL '1 day', NOW() + INTERVAL '9 day', 59.90, NOW(), NOW()),
    ('22222222-2222-2222-2222-222222222222', 'Show da Semana Academica', 'SHOW',
     NOW() + INTERVAL '15 day', NOW() - INTERVAL '1 day', NOW() + INTERVAL '12 day', 120.00, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

INSERT INTO tb_sales (id, user_id, event_id, date_time, status, created_at, updated_at)
VALUES
    ('33333333-3333-3333-3333-333333333333', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
     '11111111-1111-1111-1111-111111111111', NOW(), 'PAGO', NOW(), NOW())
ON CONFLICT (id) DO NOTHING;
