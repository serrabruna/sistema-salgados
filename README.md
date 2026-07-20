# 🥐 Sistema Salgados

O **Sistema Salgados** é uma aplicação baseada em Java e Spring Boot desenvolvida como parte do curso de Análise e Desenvolvimento de Sistemas (ADS) do IFSP. O projeto tem como objetivo gerenciar pedidos, clientes e a movimentação de uma produção focada em salgados (com ênfase em coxinhas de múltiplos sabores), aplicando conceitos avançados de arquitetura de software e Design Patterns.

---

## 🚀 Tecnologias Utilizadas

* **Java** (versão suportada pelo Spring Boot)
* **Spring Boot** (Framework base da aplicação)
* **Maven** (Gerador de builds e gerenciador de dependências)
* **Docker** (Containerização inclusa via `Dockerfile`)
* **Padrões de Projeto (Design Patterns)**

---

## 🏗️ Padrões de Projeto Aplicados

A arquitetura do sistema destaca-se pela aplicação rigorosa de padrões de projeto do GoF para garantir desacoplamento e extensibilidade:

* **Factory Method (`patterns.factory`):** Utilizado em `CoxinhaFactory` e `MovimentacaoFactory` para encapsular e centralizar a lógica de criação de instâncias de coxinhas (carne, frango, queijo, catupiry) e movimentações de estoque.
* **Strategy (`patterns.strategy`):** Implementado em `CalculoPrecoStrategy` e `PrecoVendaPadrao` para isolar algoritmos variados de precificação de produtos.
* **Template Method (`patterns.template`):** Estruturado em `CalculadoraPrecoTemplate` e `CalculadoraPedidoProprio` para definir o esqueleto de um algoritmo de cálculo de pedidos, delegando os passos específicos para as subclasses.
* **Command (`patterns.command`):** Aplicado com `Command` e `PedidoCommand` para encapsular requisições de ações em objetos, permitindo parametrizar clientes com diferentes pedidos e enfileirar operações.

---

## 📂 Estrutura de Pastas

Abaixo está a representação da árvore de diretórios principal do projeto:

```text
sistema-salgados/
├── .mvn/                       # Arquivos do Maven Wrapper
├── src/
│   ├── main/
│   │   ├── java/br/edu/ifsp/ads/sistema_salgados/
│   │   │   ├── controller/     # Endpoints HTTP (ClienteController, PedidoController)
│   │   │   ├── model/          # Classes de Domínio (Cliente, Coxinha, Variantes, Movimentacao)
│   │   │   ├── patterns/       # Implementação dos Design Patterns (Command, Factory, Strategy, Template)
│   │   │   ├── repository/     # Camada de Acesso a Dados (DAO) (ClienteDao, MovimentacaoDao)
│   │   │   ├── service/        # Regras de Negócio (ClienteService, PedidoService)
│   │   │   └── SistemaSalgadosApplication.java # Classe Principal do Spring Boot
│   │   └── resources/
│   │       └── application.properties          # Configurações do ambiente/banco de dados
│   └── test/                   # Testes unitários e de integração
├── Dockerfile                  # Configuração para implantação em containers
├── pom.xml                     # Arquivo de dependências Maven
└── mvnw / mvnw.cmd             # Executáveis do Maven Wrapper
```

---

## 🛠️ Como Executar o Projeto

### Pré-requisitos
* **Java JDK** instalado.
* **Docker** (opcional, caso queira rodar via container).

### Modo Local (Maven)
1. Clone o repositório e navegue até a pasta do projeto:
```bash
cd sistema-salgados
```

2. Instale as dependências e compile o projeto:
```bash
./mvnw clean install
```

3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

### Modo Docker
Se preferir rodar a aplicação isolada em um container:

1. Construa a imagem Docker:
```bash
docker build -t sistema-salgados .
```

2. Execute o container:
```bash
docker run -p 8080:8080 sistema-salgados
```

---

## 🧪 Testes

Para rodar a suite de testes automatizados contida na pasta `src/test`:
```bash
./mvnw test
```
