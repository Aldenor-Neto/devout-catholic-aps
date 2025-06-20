ALTER TABLE anotacoes ADD COLUMN user_id BIGINT;
ALTER TABLE anotacoes ADD CONSTRAINT fk_anotacoes_user FOREIGN KEY (user_id) REFERENCES usuarios(id); 