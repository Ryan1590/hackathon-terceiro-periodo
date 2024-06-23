# Documentação do Sistema de Gestão de Saúde para Idosos

## Comandos Iniciais
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
npm install
npm run migrate
npm run dev
npm install cors

-------------------------------------------------------------------------------------------

Índice
1.Introdução
2.Visão Geral do Sistema
3.Arquivos e Estrutura
4.Funcionalidades Principais
6.Requisitos do Sistema
7.Guia de Instalação
8.Guia de Uso
9.Segurança
10.Manutenção e Suporte
11.Conclusão

-------------------------------------------------------------------------------------------

Introdução:
Este documento detalha o sistema de Gestão de Saúde para Idosos, que inclui uma aplicação web e uma aplicação desktop em Java. O sistema visa registrar o histórico médico do idoso, indicar vacinas e agendar visitas de profissionais de saúde para vacinação em domicílio.

-------------------------------------------------------------------------------------------

Visão Geral do Sistema:
O sistema é composto por duas partes principais:

Aplicação Web: Responsável pelo registro do histórico médico, indicação das vacinas e agendamento de visitas.
Aplicação Desktop em Java: Permite aos agentes de saúde gerenciar e controlar as visitas, especificando datas e horários convenientes.

-------------------------------------------------------------------------------------------

Arquivos e Estrutura
app-web/: Contém os arquivos da aplicação web.
app-desktop/: Contém os arquivos da aplicação desktop.
database/: Contém os scripts de criação e configuração do banco de dados MySQL.
docs/: Documentação e manuais do sistema.
README.md: Instruções básicas de instalação e uso.

-------------------------------------------------------------------------------------------

Funcionalidades Principais:

Aplicação Web
Registro de Saúde do Idoso:
Cadastro e manutenção do histórico médico.
Indicação de vacinas e cronograma de administração.
Agendamento de Visitas:
Interface para agendamento de visitas por familiares ou cuidadores.
Notificação sobre próximas vacinas e lembretes regulares.
Aplicação Desktop em Java
Gestão de Visitas:
Interface para agentes de saúde gerenciarem visitas de profissionais.
Edição e cancelamento de agendamentos conforme necessário.

-------------------------------------------------------------------------------------------

Requisitos do Sistema:

Especificações Técnicas
Frontend:
Utilização da biblioteca Swing para a interface gráfica da aplicação desktop.
Interface amigável e de fácil navegação.
Backend:
Conexão com o banco de dados MySQL usando JDBC.
Validação de dados para garantir integridade.
Requisitos Não Funcionais
Segurança:
Armazenamento seguro dos dados dos usuários e idosos.
Usabilidade:
Interface acessível para diferentes níveis de familiaridade com tecnologia.
Responsividade e velocidade do sistema.

-------------------------------------------------------------------------------------------

Guia de Instalação:

Clone o repositório do projeto.
Execute os scripts do banco de dados para criar as tabelas e configurar o ambiente e de os comandos mencionados a cima.
Compile e execute a aplicação desktop em Java.
Abra a aplicação web em um navegador para acessar as funcionalidades.

-----------------------------------------------------------------------------------------

Guia de Uso:

Aplicação Web
Registre o histórico médico do idoso.
Agende visitas de profissionais de saúde.
Receba alertas e lembretes sobre as próximas vacinas.
Aplicação Desktop em Java
Gerencie as visitas dos profissionais de saúde.
Edite e cancele agendamentos conforme necessário.

-----------------------------------------------------------------------------------------

Conclusão
O sistema de Gestão de Saúde para Idosos visa melhorar o acesso à saúde para essa população, facilitando o agendamento de visitas e garantindo a administração correta das vacinas. Para suporte ou dúvidas, entre em contato com a equipe técnica.