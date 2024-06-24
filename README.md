
# Documentação do Projeto de Gestão de Saúde para Idosos

## Comandos Básicos
Para iniciar o projeto, execute os seguintes comandos:

```bash
git clone https://github.com/Ryan1590/hackathon-terceiro-periodo.git
npm install
npm run migrate
npm run dev
```

# Índice
1. [Introdução](#introducao)
2. [Visão Geral do Sistema](#visao-geral-do-sistema)
3. [Arquivos e Estrutura](#arquivos-e-estrutura)
4. [Funcionalidades Principais](#funcionalidades-principais)
5. [Requisitos do Sistema](#requisitos-do-sistema)
6. [Guia de Instalação](#guia-de-instalacao)
7. [Testes](#testes)


---

# 1. Introdução
Em nossa sociedade, o envelhecimento da população traz novos desafios sociais e de saúde, exigindo soluções inovadoras e eficazes. Um desses desafios é a vacinação adequada dos idosos, uma população particularmente vulnerável a doenças infecciosas. No entanto, muitos idosos enfrentam barreiras significativas para acessar os serviços de vacinação, como mobilidade reduzida, falta de transporte adequado e desinformação.

Este documento detalha o sistema de Gestão de Saúde para Idosos, que inclui uma aplicação web e uma aplicação desktop em Java. O sistema visa registrar o histórico médico do idoso, indicar vacinas e agendar visitas de profissionais de saúde para vacinação em domicílio.

---

# 2. Visão Geral do Sistema 
O sistema é composto por duas partes principais:

### Aplicação Web
- Registro do histórico médico.
- Indicação de vacinas e agendamento de visitas.
- Envio de alertas e lembretes.

### Aplicação Desktop em Java
- Gerenciamento e controle das visitas por agentes de saúde.
- Edição e cancelamento de agendamentos.


# 3. Arquivos e Estrutura
- `app-web/`: Contém os arquivos da aplicação web.
- `app-desktop/`: Contém os arquivos da aplicação desktop.
- `database/`: Contém os scripts de criação e configuração do banco de dados MySQL.
- `docs/`: Documentação e manuais do sistema.
- `README.md`: Instruções básicas de instalação e uso.

---

# 4. Funcionalidades Principais 

### Aplicação Web

#### Registro de Saúde do Idoso
- Cadastro e manutenção do histórico médico.
- Indicação de vacinas e cronograma de administração.

#### Agendamento de Visitas
- Interface para agendamento de visitas por familiares ou cuidadores.
- Notificação sobre próximas vacinas e lembretes regulares.

### Aplicação Desktop em Java

#### Gestão de Visitas
- Interface para agentes de saúde gerenciarem visitas de profissionais.
- Edição e cancelamento de agendamentos conforme necessário.

#### Alertas e Lembretes
- Envio de alertas automáticos para os idosos ou seus familiares sobre as próximas vacinas.
- Configuração de lembretes regulares para garantir que nenhuma data importante de vacinação seja esquecida.

---

# 5. Requisitos do Sistema 

### Especificações Técnicas

#### Frontend
- Utilização da biblioteca Swing para a interface gráfica da aplicação desktop.
- Interface amigável e de fácil navegação.

#### Backend
- Conexão com o banco de dados MySQL usando JDBC.
- Validação de dados para garantir integridade.


# 6. Guia de Instalação 

1. Clone o repositório do projeto:
   ```bash
   git clone https://github.com/Ryan1590/hackathon-terceiro-periodo.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd back-end
   ```
3. Instale as dependências:
   ```bash
   npm install
   ```
4. Execute as migrações do banco de dados:
   ```bash
   npm run migrate
   ```
5. Inicie o servidor de desenvolvimento:
   ```bash
   npm run dev
   ```
6. Configure as informações do banco de dados no knexfile.js
```bash
module.exports = {
  development: {
    client: 'mysql',
    connection: {
      host: '127.0.0.1',
      user: 'seu_usuario',
      password: 'sua_senha',
      database: 'hackathon_vacina'
    }
  },
  test: {
    client: 'mysql',
    connection: {
      host: '127.0.0.1',
      user: 'seu_usuario',
      password: 'sua_senha',
      database: 'hackathon_vacina_test'
    }
  }
};

 ```
 7. Configure as informações do banco de dados no DataBase.java

```bash

public class DataBase {

    public static final String URL = "jdbc:mysql://localhost/hackathon_vacina";
    public static final String USER = "root";
    public static final String PASSWORD = "sua_senha";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

 ```

# 7. Testes 

# Criar o Banco de Dados de Teste
-Acesse seu sistema de gerenciamento de banco de dados (como MySQL Workbench, phpMyAdmin, etc.).
-Crie um novo banco de dados chamado hackathon_vacina_test.

# Executar as Migrações no Banco de Dados de Teste
-Abra o terminal na raiz do seu projeto.
-Execute o comando abaixo para aplicar as migrações no banco de dados de teste:  knex migrate:latest --env test


# Executar os Testes:
Execute esse comando  executar os testes:  npm test


