-- Criação da tabela de contatos
CREATE TABLE IF NOT EXISTS contatos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    endereco VARCHAR(255),
    data_nascimento DATE,
    observacoes VARCHAR(500)
);

-- Inserção dos contatos de exemplo
INSERT INTO contatos (nome, telefone, email, endereco, data_nascimento, observacoes) VALUES
('Pedro da Silva', '1234567890', 'pedro.silva@example.com', 'Rua dos Exemplos, 123', '1990-01-01', 'Cliente VIP'),
('Maria Oliveira', '9876543210', 'maria.oliveira@example.com', 'Avenida Central, 456', '1985-02-15', 'Cliente regular'),
('Carlos Souza', '1122334455', 'carlos.souza@example.com', 'Rua das Palmeiras, 789', '1980-07-30', 'Frequente'),
('Fernanda Lima', '6677889900', 'fernanda.lima@example.com', 'Rua das Flores, 101', '1995-05-10', 'Novo cliente'),
('Ricardo Pereira', '2233445566', 'ricardo.pereira@example.com', 'Avenida Brasil, 202', '1987-11-22', 'Cliente em potencial'),
('Ana Costa', '3344556677', 'ana.costa@example.com', 'Rua dos Três, 303', '1992-03-17', 'Ativo'),
('Roberto Alves', '4455667788', 'roberto.alves@example.com', 'Rua do Sol, 404', '1993-09-25', 'Novo contato'),
('Luciana Gomes', '5566778899', 'luciana.gomes@example.com', 'Rua do Rio, 505', '1988-12-01', 'VIP'),
('Eduardo Martins', '6677889900', 'eduardo.martins@example.com', 'Avenida Verde, 606', '1994-02-05', 'Regulador'),
('Pedro Carlo', '7788990011', 'pedro.carlo@example.com', 'Rua dos Pinhais, 707', '1989-08-20', 'Regular');
