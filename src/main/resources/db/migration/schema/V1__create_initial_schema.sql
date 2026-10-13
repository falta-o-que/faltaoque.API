-- COLORS
CREATE TABLE colors
(
    id       INT         NOT NULL AUTO_INCREMENT,
    name     VARCHAR(50) NOT NULL,
    hex_code VARCHAR(7)  NOT NULL,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- USERS
CREATE TABLE users
(
    id        VARCHAR(36)  NOT NULL DEFAULT (UUID()),
    name      VARCHAR(50)  NOT NULL,
    email     VARCHAR(254) NOT NULL UNIQUE,
    password  VARCHAR(64)  NOT NULL,
    avatar_id INT          NOT NULL,
    role      TINYINT      NOT NULL DEFAULT 0,
    is_active BOOLEAN      NOT NULL DEFAULT 1,

    PRIMARY KEY (id),

    CONSTRAINT fk_user_avatar_color
        FOREIGN KEY (avatar_id)
            REFERENCES colors (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- PANTRIES INVITES
CREATE TABLE pantries_invites
(
    id           VARCHAR(36)  NOT NULL DEFAULT (UUID()),
    share_invite VARCHAR(255) NOT NULL,
    created_at   DATETIME     NOT NULL,
    expires_at   DATETIME     NOT NULL,

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- PANTRIES
CREATE TABLE pantries
(
    id              VARCHAR(36)  NOT NULL DEFAULT (UUID()),
    title           VARCHAR(150) NOT NULL,
    location        VARCHAR(8),
    color_id        INT          NOT NULL,
    share_invite_id VARCHAR(36),
    PRIMARY KEY (id),

    CONSTRAINT fk_pantry_color
        FOREIGN KEY (color_id)
            REFERENCES colors (id),

    CONSTRAINT fk_pantries_invite
        FOREIGN KEY (share_invite_id)
            REFERENCES pantries_invites (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- USERS_PANTRIES
CREATE TABLE users_pantries
(
    user_id   VARCHAR(36) NOT NULL,
    pantry_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (user_id, pantry_id),

    CONSTRAINT fk_users_pantries_user
        FOREIGN KEY (user_id)
            REFERENCES users (id),

    CONSTRAINT fk_users_pantries_pantry
        FOREIGN KEY (pantry_id)
            REFERENCES pantries (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- CATEGORIES
CREATE TABLE categories
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- PURCHASES
CREATE TABLE purchases
(
    id             VARCHAR(36)    NOT NULL DEFAULT (UUID()),
    title          VARCHAR(100)   NOT NULL,
    location       VARCHAR(8),
    purchase_date  DATE           NOT NULL,
    total_price    DECIMAL(10, 2) NOT NULL,
    total_products INT            NOT NULL,
    is_finished    BOOLEAN        NOT NULL,
    finish_date    DATE,
    qr_code_id     VARCHAR(44),
    pantry_id      VARCHAR(36)    NOT NULL,
    PRIMARY KEY (id),

    CONSTRAINT fk_purchases_pantry
        FOREIGN KEY (pantry_id)
            REFERENCES pantries (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- PANTRY PRODUCTS
CREATE TABLE pantry_products
(
    id               VARCHAR(36)  NOT NULL DEFAULT (UUID()),
    name             VARCHAR(100) NOT NULL,
    quantity         INT          NOT NULL DEFAULT 1,
    current_quantity INT          NOT NULL DEFAULT 1,
    content_value DOUBLE,
    is_in_pantry     BOOLEAN      NOT NULL DEFAULT TRUE,
    unit_of_measure  TINYINT,
    price            DECIMAL(10, 2),
    brand            VARCHAR(100),
    expiration_date  DATE,
    finish_date      DATE,
    is_deleted       BOOLEAN      NOT NULL DEFAULT FALSE,
    purchase_id      VARCHAR(36)  NOT NULL,
    category_id      INT          NOT NULL,
    PRIMARY KEY (id),

    CONSTRAINT fk_pantry_products_purchase
        FOREIGN KEY (purchase_id)
            REFERENCES purchases (id),

    CONSTRAINT fk_pantry_products_category
        FOREIGN KEY (category_id)
            REFERENCES categories (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- GROCERY LISTS
CREATE TABLE grocery_lists
(
    id              VARCHAR(36)  NOT NULL DEFAULT (UUID()),
    name            VARCHAR(100) NOT NULL,
    date            DATE,
    location        VARCHAR(8),
    suggestion      TINYINT,
    estimated_price DECIMAL(10, 2),
    pantry_id       VARCHAR(36)  NOT NULL,
    is_active       BOOLEAN      NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),

    CONSTRAINT fk_grocery_lists_pantry_id_pantries
        FOREIGN KEY (pantry_id)
            REFERENCES pantries (id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- GROCERY_LIST_PRODUCTS

CREATE TABLE grocery_list_products
(
    id              VARCHAR(36)  NOT NULL DEFAULT (UUID()),
    name            VARCHAR(100) NOT NULL,
    quantity        INT,
    content_value   DOUBLE,
    unit_of_measure TINYINT,
    is_taken        BOOLEAN      NOT NULL DEFAULT FALSE,
    grocery_list_id VARCHAR(36)  NOT NULL,
    category_id     INT          NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_grocery_list_products_grocery_list_id_grocery_lists
        FOREIGN KEY (grocery_list_id)
            REFERENCES grocery_lists (id),


    CONSTRAINT fk_grocery_list_products_category_id_categories
        FOREIGN KEY (category_id)
            REFERENCES categories (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



