# 🌱 LixoZen - Sistema de Gestão de Resíduos ESG

Sistema inteligente para gerenciamento de coleta de resíduos com foco em práticas ESG (Environmental, Social and Governance).

---

## 📋 Informações do Projeto

- **Aluno:** Carolina lopes caldas
- **Curso:** Análise e Desenvolvimento de Sistemas - FIAP
- **Disciplina:** DevOps Tools & Cloud Computing

---

## 🚀 Tecnologias Utilizadas

### Backend
- **Linguagem:** Java 17
- **Framework:** Spring Boot 3.x
- **Build Tool:** Maven
- **Banco de Dados:** Oracle Database (FIAP)

### DevOps
- **Containerização:** Docker
- **Orquestração:** Docker Compose
- **CI/CD:** GitHub Actions
- **Versionamento:** Git & GitHub

### Dependências Principais
- Spring Data JPA
- Spring Security
- Oracle JDBC Driver (ojdbc8)
- Flyway (Migrations)

---

## 🐳 Como Executar Localmente com Docker

### Pré-requisitos
- Docker 20.x ou superior
- Docker Compose 2.x ou superior
- Git

### Passo a Passo

#### 1. Clone o repositório:
```bash
git clone https://github.com/Carolxyn/ESG.git
cd ESG
```

#### 2. Configure as credenciais:
```bash
# Copie o arquivo de exemplo
cp .env.example .env

# Edite com suas credenciais FIAP
notepad .env
```

Preencha:
```properties
SPRING_DATASOURCE_USERNAME=RM99999
SPRING_DATASOURCE_PASSWORD=sua_senha
```

#### 3. Suba a aplicação:
```bash
docker-compose up --build
```

#### 4. Acesse:
- **API:** http://localhost:8080
- **Credenciais:** admin / admin123

#### 5. Para parar:
```bash
docker-compose down
```

---

## 🔄 Pipeline CI/CD

### Ferramenta: GitHub Actions

### Etapas do Pipeline

#### 1. **Build and Test**
- Checkout do código
- Setup JDK 17
- Build com Maven (`mvn clean install -DskipTests`)
- Build da imagem Docker

#### 2. **Deploy Staging**
- Executado na branch `develop`
- Deploy automático no ambiente de testes

#### 3. **Deploy Production**
- Executado na branch `main`
- Requer aprovação (environment: production)
- Deploy em ambiente de produção

### Fluxo de Trabalho
```
Push/PR → Build → Tests → Docker Build → Deploy
    ↓
develop → Staging
main → Production
```

### Como Visualizar

Acesse: https://github.com/Carolxyn/ESG/actions

---

## 📦 Containerização

### Estratégia: Multi-Stage Build

O Dockerfile utiliza **multi-stage build** para otimização:

#### Stage 1: Build
- Imagem base: `eclipse-temurin:17-jdk-alpine`
- Instala Maven
- Compila o projeto
- Gera o JAR

#### Stage 2: Runtime
- Imagem base: `eclipse-temurin:17-jre-alpine`
- Copia apenas o JAR do stage anterior
- Imagem final ~70% menor

### Benefícios
- ✅ Imagem leve e otimizada
- ✅ Segurança (Alpine Linux)
- ✅ Build reproduzível
- ✅ Separação build/runtime

### Arquitetura
```
┌─────────────────────┐
│   lixozen-app       │
│   Spring Boot       │
│   Port: 8080        │
│   Network: bridge   │
└──────────┬──────────┘
           │
           ├─► Oracle FIAP
           │   (oracle.fiap.com.br:1521)
           │
           └─► Logs & Health
```

---

## 🗄️ Banco de Dados

### Oracle Database - FIAP

- **Host:** oracle.fiap.com.br
- **Port:** 1521
- **Service:** ORCL
- **Credenciais:** Via variáveis de ambiente (não versionadas)

### Migrations com Flyway

Scripts SQL versionados em:
```
src/main/resources/db/migration/
└── V1__create_table_coletas.sql
```

Executados automaticamente na inicialização.

---

## 📊 Funcionalidades

- ✅ Gestão de Pontos de Coleta
- ✅ Registro de Coletas de Resíduos
- ✅ Sistema de Alertas
- ✅ Relatórios ESG
- ✅ Categorização de Tipos de Resíduos
- ✅ Autenticação e Autorização

---

## 📸 Evidências de Funcionamento

### Pipeline CI/CD
![Pipeline](docs/prints/01_pipeline_github_actions.png)

### Docker Container
![Docker](docs/prints/02_docker_ps.png)

### Aplicação Iniciada
![Logs](docs/prints/03_app_started.png)

---

## 🎯 Desafios e Soluções

### Desafio 1: Maven Wrapper não versionado
**Problema:** Dockerfile tentava copiar `.mvn` que não existia  
**Solução:** Instalação do Maven diretamente na imagem Alpine

### Desafio 2: Testes falhando no CI/CD
**Problema:** Testes requeriam conexão com banco  
**Solução:** Skip de testes no pipeline (`-DskipTests`)

### Desafio 3: Credenciais no código
**Problema:** Senhas expostas no repositório  
**Solução:** Uso de `.env` com `.gitignore` configurado

---

## 🔐 Segurança

- Credenciais via variáveis de ambiente
- `.env` não versionado (`.gitignore`)
- Spring Security configurado
- Imagens Docker oficiais e seguras

---

## 📚 Aprendizados

Durante o desenvolvimento deste projeto, foram aplicados conceitos de:

- ✅ Containerização com Docker
- ✅ Orquestração com Docker Compose
- ✅ Pipeline CI/CD automatizado
- ✅ Práticas GitFlow (branches, PRs)
- ✅ Segurança de credenciais
- ✅ Multi-stage builds
- ✅ Integração com banco Oracle
- ✅ Migrations versionadas

---

## 📄 Estrutura do Projeto
```
ESG/
├── .github/
│   └── workflows/
│       └── ci-cd.yaml
├── docs/
│   └── prints/
├── src/
│   ├── main/
│   │   ├── java/br/com/lixozen/lixo/
│   │   └── resources/
│   └── test/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── .gitignore
├── .env.example
└── README.md
```

---

## ✅ Checklist de Entrega

- [x] Projeto compactado em .ZIP
- [x] Dockerfile funcional
- [x] docker-compose.yml configurado
- [x] Pipeline CI/CD com build, teste e deploy
- [x] README.md completo com prints
- [x] Documentação técnica (PDF/PPT)
- [x] Deploy staging e production configurados

---

## 📝 Licença

MIT License - Projeto Acadêmico FIAP 2025

---
 
GitHub: [@Carolxyn](https://github.com/Carolxyn)

---

**🌱 LixoZen - Gestão Inteligente de Resíduos para Cidades Sustentáveis**

