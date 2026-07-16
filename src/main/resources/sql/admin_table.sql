-- No admin table existed in the schema yet (only the Admin.java model did).
-- Run this once against hostel_system before logging in as admin.

CREATE TABLE IF NOT EXISTS admin (
    admin_id      INT AUTO_INCREMENT PRIMARY KEY,
    full_name     VARCHAR(100) NOT NULL,
    user_name     VARCHAR(50)  NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    phone_number  VARCHAR(20)
);
