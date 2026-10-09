-- COLORS
INSERT INTO colors (name, hex_code)
VALUES ('GREEN_01', '#97F7CD'),
       ('GREEN_02', '#00DD00'),
       ('GREEN_03', '#36B83F'),
       ('CYAN', '#00F0FF'),
       ('BLUE_01', '#75C1E0'),
       ('BLUE_02', '#0031F5'),
       ('BLUE_03', '#1E2B5E'),
       ('PURPLE', '#7115C2'),
       ('PINK', '#B666B2'),
       ('RED', '#FF0505'),
       ('BROWN', '#470419'),
       ('YELLOW', '#FFB405');

-- USERS
INSERT INTO users (name, email, password, avatar_id, role, is_active)
VALUES ('admin', 'admin@email.com', 'admin',
        (SELECT id FROM colors WHERE name = 'GREEN_01'), 1, true),
       ('Carlos Oliveira', 'carlos.oliveira@email.com', 'senha123',
        (SELECT id FROM colors WHERE name = 'GREEN_02'), 0, true),
       ('Mariana Santos', 'mariana.santos@email.com', 'senha123',
        (SELECT id FROM colors WHERE name = 'GREEN_03'), 0, true),
       ('Lucas Ferreira', 'lucas.ferreira@email.com', 'senha123',
        (SELECT id FROM colors WHERE name = 'CYAN'), 0, true),
       ('Beatriz Costa', 'beatriz.costa@email.com', 'senha123',
        (SELECT id FROM colors WHERE name = 'PURPLE'), 0, false),
       ('admin2', 'admin2@email.com', 'admin',
        (SELECT id FROM colors WHERE name = 'PURPLE'), 1, true),
       ('Fernanda Lima', 'fernanda.lima@email.com', 'senha123',
        (SELECT id FROM colors WHERE name = 'RED'), 0, false),
       ('Gabriel Alves', 'gabriel.alves@email.com', 'senha123',
        (SELECT id FROM colors WHERE name = 'BLUE_02'), 0, true),
       ('Juliana Rocha', 'juliana.rocha@email.com', 'senha123',
        (SELECT id FROM colors WHERE name = 'YELLOW'), 0, false),
       ('Thiago Martins', 'thiago.martins@email.com', 'senha123',
        (SELECT id FROM colors WHERE name = 'GREEN_01'), 0, true);

-- PANTRIES INVITES
INSERT INTO pantries_invites (share_invite, created_at, expires_at)
VALUES ('INVITE-001', '2026-03-01 15:00:00', '2026-03-08 15:00:00'),
       ('INVITE-002', '2026-03-02 20:30:00', '2026-03-09 20:30:00'),
       ('INVITE-003', '2026-03-03 13:00:00', '2026-03-10 13:00:00'),
       ('INVITE-004', '2026-03-04 13:00:00', '2026-03-11 13:00:00'),
       ('INVITE-005', '2026-03-05 23:50:00', '2026-03-12 23:50:00'),
       ('INVITE-006', '2026-09-05 10:00:00', '2026-09-12 10:00:00'),
       ('INVITE-007', '2026-09-06 14:30:00', '2026-09-13 14:30:00'),
       ('INVITE-008', '2026-09-07 09:15:00', '2026-09-14 09:15:00'),
       ('INVITE-009', '2026-09-08 16:45:00', '2026-09-15 16:45:00'),
       ('INVITE-010', '2026-09-09 11:20:00', '2026-09-16 11:20:00');

-- PANTRIES
INSERT INTO pantries (title, location, color_id, share_invite_id)
VALUES ('Despensa Principal', '01310100',
        (SELECT id FROM colors WHERE name = 'PINK'),
        (SELECT id FROM pantries_invites WHERE share_invite = 'INVITE-001')),
       ('Casa da Praia', '22041002',
        (SELECT id FROM colors WHERE name = 'GREEN_03')
           , (SELECT id FROM pantries_invites WHERE share_invite = 'INVITE-002')),
       ('Escritório da Empresa Taltal', '30140071',
        (SELECT id FROM colors WHERE name = 'PURPLE'),
        (SELECT id FROM pantries_invites WHERE share_invite = 'INVITE-003')),
       ('Sítio', '04538132',
        (SELECT id FROM colors WHERE name = 'BROWN')
           , (SELECT id FROM pantries_invites WHERE share_invite = 'INVITE-004')),
       ('Apartamento Lucas', '80010000',
        (SELECT id FROM colors WHERE name = 'BLUE_01'),
        (SELECT id FROM pantries_invites WHERE share_invite = 'INVITE-005')),
       ('Estúdio da Aninha', '60160120',
        (SELECT id FROM colors WHERE name = 'BLUE_01'),
        (SELECT id FROM pantries_invites WHERE share_invite = 'INVITE-006')),
       ('República', '90010000',
        (SELECT id FROM colors WHERE name = 'YELLOW'),
        (SELECT id FROM pantries_invites WHERE share_invite = 'INVITE-007')),
       ('Despensinhaaa', '70040900',
        (SELECT id FROM colors WHERE name = 'PINK'),
        (SELECT id FROM pantries_invites WHERE share_invite = 'INVITE-008')),
       ('Casa de Campo', '13010000',
        (SELECT id FROM colors WHERE name = 'GREEN_01'),
        (SELECT id FROM pantries_invites WHERE share_invite = 'INVITE-009')),
       ('Reserva de Emergência', '88010000',
        (SELECT id FROM colors WHERE name = 'GREEN_02'),
        (SELECT id FROM pantries_invites WHERE share_invite = 'INVITE-010'));

-- USERS_PANTRIES
INSERT INTO users_pantries (user_id, pantry_id)
VALUES ((SELECT id FROM users WHERE email = 'admin2@email.com'),
        (SELECT id FROM pantries WHERE title = 'Despensa Principal')),

       ((SELECT id FROM users WHERE email = 'carlos.oliveira@email.com'),
        (SELECT id FROM pantries WHERE title = 'Despensa Principal')),

       ((SELECT id FROM users WHERE email = 'mariana.santos@email.com'),
        (SELECT id FROM pantries WHERE title = 'Despensa Principal')),

       ((SELECT id FROM users WHERE email = 'lucas.ferreira@email.com'),
        (SELECT id FROM pantries WHERE title = 'Escritório da Empresa Taltal')),

       ((SELECT id FROM users WHERE email = 'beatriz.costa@email.com'),
        (SELECT id FROM pantries WHERE title = 'Sítio')),

       ((SELECT id FROM users WHERE email = 'fernanda.lima@email.com'),
        (SELECT id FROM pantries WHERE title = 'Estúdio da Aninha')),

       ((SELECT id FROM users WHERE email = 'gabriel.alves@email.com'),
        (SELECT id FROM pantries WHERE title = 'República')),

       ((SELECT id FROM users WHERE email = 'juliana.rocha@email.com'),
        (SELECT id FROM pantries WHERE title = 'Despensinhaaa')),

       ((SELECT id FROM users WHERE email = 'thiago.martins@email.com'),
        (SELECT id FROM pantries WHERE title = 'Casa de Campo'));

-- CATEGORIES
INSERT INTO categories (name)
VALUES ('Laticínios'),
       ('Grãos e Cereais'),
       ('Limpeza'),
       ('Bebidas'),
       ('Hortifruti'),
       ('Carnes e Aves'),
       ('Padaria'),
       ('Enlatados'),
       ('Doces e Snacks'),
       ('Higiene Pessoal');

-- PURCHASES
INSERT INTO purchases (title, location, purchase_date, total_price, total_products, is_finished, finish_date,
                       qr_code_id, pantry_id)
VALUES ('Compra do mês', '01310100', '2026-03-01', 37.20, 3,
        true, '2026-03-25', '58392017463829104736581920473658192047365819',
        (SELECT id FROM pantries WHERE title = 'Despensa Principal')),

       ('Reposição semanal', '01310100', '2026-03-02', 80.50, 1,
        true, '2026-03-20', '72946183027591836402951763840592716384051729',
        (SELECT id FROM pantries WHERE title = 'Despensa Principal')),

       ('Compras da praia', '22041002', '2026-03-03', 210.00, 2,
        true, '2026-04-01', '10482736501928473650192847365019283746501928',
        (SELECT id FROM pantries WHERE title = 'Casa da Praia')),

       ('Produtos do escritório', '30140071', '2026-03-04', 45.90, 1,
        true, '2026-03-15', '83629104758203917465029183746502918374650291',
        (SELECT id FROM pantries WHERE title = 'Escritório da Empresa Taltal')),

       ('Compra do apartamento', '80010000', '2026-03-05', 99.00, 1,
        true, '2026-03-30', '49281736502918473650291837465029183746502918',
        (SELECT id FROM pantries WHERE title = 'Apartamento Lucas')),

       ('Reposição do estúdio', '60160120', '2026-03-06', 120.30, 1,
        true, '2026-03-28', '67102938475620193847562019384756201938475620',
        (SELECT id FROM pantries WHERE title = 'Estúdio da Aninha')),

       ('Compra da república', '90010000', '2026-03-07', 35.00, 1,
        true, '2026-03-18', '91827364501928374650192837465019283746501928',
        (SELECT id FROM pantries WHERE title = 'República')),

       ('Compra para a mini despensa', '70040900', '2026-03-08', 310.00, 0,
        false, NULL, '35719204836571029384756102938475610293847561',
        (SELECT id FROM pantries WHERE title = 'Despensinhaaa')),

       ('Casa de Campo', '13010000', '2026-03-09', 67.80, 0,
        true, '2026-03-22', '62039485710293847561029384756102938475610293',
        (SELECT id FROM pantries WHERE title = 'Casa de Campo')),

       ('Estoque de emergência', '88010000', '2026-03-10', 189.90, 0,
        false, NULL, '84572019384650192837465019283746501928374650',
        (SELECT id FROM pantries WHERE title = 'Reserva de Emergência')),

       ('Casa de Campo', '13010000', '2026-03-07', 63.47, 3,
        true, '2026-03-22', '27384910572638491057263849105726384910572638',
        (SELECT id FROM pantries WHERE title = 'Casa de Campo')),

       ('Compra do Sítio', '04538132', '2026-07-09', 40.00, 1,
        true, '2026-07-25', '50918273645091827364509182736450918273645091',
        (SELECT id FROM pantries WHERE title = 'Sítio'));

-- PANTRY PRODUCTS
INSERT INTO pantry_products
(name, quantity, current_quantity, is_in_pantry, content_value,
 unit_of_measure, price, brand, expiration_date, finish_date,
 is_deleted, purchase_id, category_id)
VALUES ('Leite Integral 1L', 4, 4, true, 1.0,
        1, 7.50, 'Piracanjuba', '2026-04-10', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Compra do mês'
           AND purchase_date = '2026-03-01'),
        (SELECT id FROM categories WHERE name = 'Laticínios')),

       ('Arroz Branco 5kg', 1, 1, true, 5.0,
        1, 28.90, 'Camil', '2026-12-31', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Compra do mês'
           AND purchase_date = '2026-03-01'),
        (SELECT id FROM categories WHERE name = 'Grãos e Cereais')),

       ('Detergente Neutro', 2, 0, false, 0.5,
        1, 2.80, 'Ypê', '2027-01-01', '2026-03-06', false,
        (SELECT id
         FROM purchases
         WHERE title = 'Compra do mês'
           AND purchase_date = '2026-03-01'),
        (SELECT id FROM categories WHERE name = 'Limpeza')),

       ('Suco de Laranja 1L', 2, 2, true, 1.0,
        1, 12.00, 'Prats', '2026-03-20', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Reposição semanal'
           AND purchase_date = '2026-03-02'),
        (SELECT id FROM categories WHERE name = 'Bebidas')),

       ('Maçã Gala 1kg', 6, 6, true, 1.0,
        1, 8.90, 'Horti', '2026-03-18', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Compras da praia'
           AND purchase_date = '2026-03-03'),
        (SELECT id FROM categories WHERE name = 'Hortifruti')),

       ('Peito de Frango 1kg', 2, 2, true, 1.0,
        1, 19.90, 'Sadia', '2026-03-25', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Compras da praia'
           AND purchase_date = '2026-03-03'),
        (SELECT id FROM categories WHERE name = 'Carnes e Aves')),

       ('Pão de Forma', 1, 0, false, 0.5,
        1, 7.50, 'Bauducco', '2026-03-12', '2026-03-10', false,
        (SELECT id
         FROM purchases
         WHERE title = 'Produtos do escritório'
           AND purchase_date = '2026-03-04'),
        (SELECT id FROM categories WHERE name = 'Padaria')),

       ('Milho em Conserva', 3, 3, true, 0.2,
        1, 4.20, 'Quero', '2027-05-10', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Compra do apartamento'
           AND purchase_date = '2026-03-05'),
        (SELECT id FROM categories WHERE name = 'Enlatados')),

       ('Chocolate Ao Leite', 2, 2, true, 0.1,
        1, 6.00, 'Lacta', '2026-09-01', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Reposição do estúdio'
           AND purchase_date = '2026-03-06'),
        (SELECT id FROM categories WHERE name = 'Doces e Snacks')),

       ('Sabonete em Barra', 5, 5, true, NULL,
        NULL, 3.10, 'Dove', '2028-01-01', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Compra da república'
           AND purchase_date = '2026-03-07'),
        (SELECT id FROM categories WHERE name = 'Higiene Pessoal')),

       ('Shampoo Cabelos Lisos', 2, 2, true, NULL,
        NULL, 23.99, 'Seda', '2028-01-01', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Casa de Campo'
           AND purchase_date = '2026-03-07'),
        (SELECT id FROM categories WHERE name = 'Higiene Pessoal')),

       ('Bacon', 3, 3, true, NULL,
        NULL, 10.49, 'Seara', '2028-01-01', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Casa de Campo'
           AND purchase_date = '2026-03-07'),
        (SELECT id FROM categories WHERE name = 'Carnes e Aves')),

       ('Pote de Sorvete', 1, 1, true, 1.0,
        1, 28.99, 'Kibon', '2028-01-01', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Casa de Campo'
           AND purchase_date = '2026-03-07'),
        (SELECT id FROM categories WHERE name = 'Doces e Snacks')),

       ('Ração de Peixe', 1, 1, true, 1.0,
        1, 40.00, 'PetLove', '2028-01-01', NULL, false,
        (SELECT id
         FROM purchases
         WHERE title = 'Compra do Sítio'
           AND purchase_date = '2026-07-09'),
        (SELECT id FROM categories WHERE name = 'Grãos e Cereais'));


-- GROCERY LISTS
INSERT INTO grocery_lists (name, date, location, suggestion, estimated_price, pantry_id, is_active)
VALUES ('Compras de Março', '2026-03-01', '01310100', 0, 250.00,
        (SELECT id FROM pantries WHERE title = 'Despensa Principal'), true),

       ('Reposição Semanal', '2026-03-08', '01310100', 1, 120.00,
        (SELECT id FROM pantries WHERE title = 'Despensa Principal'), true),

       ('Churrasco Fim de Semana', '2026-03-15', '22041002', 0, 350.00,
        (SELECT id FROM pantries WHERE title = 'Casa da Praia'), false),

       ('Produtos de Limpeza', '2026-03-20', '30140071', 1, 180.00,
        (SELECT id FROM pantries WHERE title = 'Escritório da Empresa Taltal'), true),

       ('Feira do Mês', '2026-04-01', '01310100', 0, 200.00,
        (SELECT id FROM pantries WHERE title = 'Despensa Principal'), true),

       ('Bebidas e Snacks', '2026-04-05', '90010000', 0, 150.00,
        (SELECT id FROM pantries WHERE title = 'República'), false),

       ('Higiene Básica', '2026-04-10', '80010000', 1, 100.00,
        (SELECT id FROM pantries WHERE title = 'Apartamento Lucas'), true),

       ('Café da Manhã', '2026-04-15', '60160120', 0, 90.00,
        (SELECT id FROM pantries WHERE title = 'Estúdio da Aninha'), false),

       ('Lanches Rápidos', '2026-04-20', '70040900', 1, 130.00,
        (SELECT id FROM pantries WHERE title = 'Despensinhaaa'), true),

       ('Estoque de Emergência', '2026-04-25', '88010000', 0, 400.00,
        (SELECT id FROM pantries WHERE title = 'Reserva de Emergência'), true);

INSERT INTO grocery_list_products
(name, quantity, content_value, unit_of_measure, is_taken,
 grocery_list_id, category_id)
VALUES ('Leite Integral 1L', 2, 1.0, 1, false,
        (SELECT id FROM grocery_lists WHERE name = 'Compras de Março'),
        (SELECT id FROM categories WHERE name = 'Laticínios')),

       ('Arroz Branco 5kg', 1, 5.0, 1, true,
        (SELECT id FROM grocery_lists WHERE name = 'Compras de Março'),
        (SELECT id FROM categories WHERE name = 'Grãos e Cereais')),

       ('Detergente Neutro', 3, 0.5, 1, false,
        (SELECT id FROM grocery_lists WHERE name = 'Produtos de Limpeza'),
        (SELECT id FROM categories WHERE name = 'Limpeza')),

       ('Suco de Laranja 1L', 2, 1.0, 1, true,
        (SELECT id FROM grocery_lists WHERE name = 'Bebidas e Snacks'),
        (SELECT id FROM categories WHERE name = 'Bebidas')),

       ('Maçã Gala 1kg', 3, 1.0, 1, false,
        (SELECT id FROM grocery_lists WHERE name = 'Feira do Mês'),
        (SELECT id FROM categories WHERE name = 'Hortifruti')),

       ('Peito de Frango 1kg', 2, 1.0, 1, false,
        (SELECT id FROM grocery_lists WHERE name = 'Churrasco Fim de Semana'),
        (SELECT id FROM categories WHERE name = 'Carnes e Aves')),

       ('Pão de Forma', 2, 0.5, 1, true,
        (SELECT id FROM grocery_lists WHERE name = 'Café da Manhã'),
        (SELECT id FROM categories WHERE name = 'Padaria')),

       ('Milho em Conserva', 2, 0.2, 1, false,
        (SELECT id FROM grocery_lists WHERE name = 'Estoque de Emergência'),
        (SELECT id FROM categories WHERE name = 'Enlatados')),

       ('Chocolate Ao Leite', 3, 0.1, 1, false,
        (SELECT id FROM grocery_lists WHERE name = 'Lanches Rápidos'),
        (SELECT id FROM categories WHERE name = 'Doces e Snacks')),

       ('Sabonete em Barra', 4, NULL, NULL, true,
        (SELECT id FROM grocery_lists WHERE name = 'Higiene Básica'),
        (SELECT id FROM categories WHERE name = 'Higiene Pessoal'));

