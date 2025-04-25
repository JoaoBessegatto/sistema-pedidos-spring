# 🛒 Sistema de Gestão de Pedidos com Spring Boot

Este projeto é uma API REST desenvolvida com **Spring Boot**, estruturada em **N camadas**, que simula um sistema de controle de produtos e ordens de pedido. Ideal para fins de estudo, prática com arquitetura limpa e desenvolvimento de APIs RESTful usando Java e Spring.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- H2 Database (ou MySQL)
- Maven

---
## 📦 Funcionalidades

- Cadastro de produtos
- Listagem de produtos
- Criação de pedidos com múltiplos itens
- Listagem de pedidos

---

## 🧱 Estrutura do Projeto

O projeto segue o padrão em **N camadas**:

---

## 📁 Entidades

### ✅ Produto
- `id` (Long)
- `nome` (String)
- `descricao` (String)
- `preco` (BigDecimal)
- `quantidadeEstoque` (Integer)

### ✅ Ordem (Order)
- `id` (Long)
- `data` (LocalDateTime)
- `valorTotal` (BigDecimal)
- Lista de `ItemPedido`

### ✅ ItemPedido
- `id` (Long)
- `quantidade` (Integer)
- `precoUnitario` (BigDecimal)
- `subtotal` (BigDecimal)
- Relacionamento com `Produto` e `Ordem`

---

## 🔁 Relacionamentos

- **Order ↔ ItemPedido**: 1:N  
- **Produto ↔ ItemPedido**: 1:N  

---

## 📬 Endpoints Principais (Exemplos)

### 🔹 Produtos
- `POST /produtos` – Cadastrar produto
- `GET /produtos` – Listar produtos
- `PUT /produtos/{id}` – Atualizar produto
- `DELETE /produtos/{id}` – Remover produto

### 🔹 Ordens
- `POST /pedido` – Criar nova ordem com itens
- `GET /pedido` – Listar ordens

---


## 📥 Exemplo de JSON de POST para Produto

```json
{
  "nome": "Teclado Gamer",
  "descricao": "Teclado RGB mecânico",
  "preco": 249.90,
  "quantidadeEstoque": 50
}
```
---
## 📥 Exemplo de JSON de POST para Pedido

```json
{
  "itemPedidos": [
    {
      "produtoId": 1,
      "quantidade": 2
    },
    {
      "produtoId": 2,
      "quantidade": 3
    }
  ]
}
```


