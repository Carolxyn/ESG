
📋 Sobre o Projeto
O Projeto ESG é uma aplicação desenvolvida para auxiliar empresas na gestão e monitoramento de suas práticas ambientais, sociais e de governança. A plataforma oferece ferramentas para rastrear, analisar e reportar métricas ESG, permitindo que organizações tomem decisões mais sustentáveis e responsáveis.
🔧 Tecnologias Utilizadas

Java 17: Linguagem de programação principal
Spring Boot: Framework para desenvolvimento da aplicação
Spring Security: Implementação de autenticação e autorização
Spring Web: Para endpoints RESTful
Spring Data JPA: Persistência de dados simplificada
Oracle Database: Armazenamento de dados
Flyway: Controle de versão para banco de dados
Docker: Containerização da aplicação
Maven: Gerenciamento de dependências e build

📦 Pré-requisitos
Para executar este projeto localmente, você precisará ter instalado:

Java JDK 17
Maven
Docker e Docker Compose
Oracle Database (ou configuração Docker para Oracle)

🚀 Como Executar
Usando Docker

Clone o repositório:
bashgit clone https://github.com/Carolxyn/ESG.git
cd ESG

Execute com Docker Compose:
bashdocker-compose up -d

A aplicação estará disponível em: http://localhost:8080

Execução Local

Clone o repositório:
bashgit clone https://github.com/Carolxyn/ESG.git
cd ESG

Configure o banco de dados Oracle no arquivo application.properties
Execute o aplicativo:
bashmvn spring-boot:run


🔐 Autenticação e Segurança
O projeto utiliza Spring Security para proteção de endpoints e autenticação de usuários. As credenciais padrão podem ser encontradas nos arquivos de configuração.
📊 Funcionalidades Principais

Dashboard ESG: Visualização de métricas ESG chave
Gestão Ambiental: Monitoramento de pegada de carbono, consumo de recursos e iniciativas sustentáveis
Conformidade Social: Rastreamento de diversidade, segurança no local de trabalho e envolvimento com a comunidade
Governança Corporativa: Acompanhamento de políticas de ética, transparência e responsabilidade corporativa
Relatórios: Geração de relatórios personalizados para stakeholders

🗄️ Estrutura do Banco de Dados
O projeto utiliza Oracle SQL como banco de dados principal e Flyway para migrações. Os scripts de migração estão localizados em src/main/resources/db/migration.
📂 Estrutura do Projeto
ESG/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── esg/
│   │   │           ├── config/          # Configurações da aplicação
│   │   │           ├── controller/      # Controllers REST
│   │   │           ├── exceptions/      #exceptions 
│   │   │           ├── model/           # Entidades JPA
│   │   │           ├── repository/      # Repositórios Spring Data
│   │   │           ├── security/        # Configurações de segurança
│   │   │           └── service/         # Camada de serviço
│   │   └── resources/
│   │       ├── db/migration/            # Scripts Flyway
│   │       ├── static/                  # Recursos estáticos
│   │       ├── templates/               # Templates 
│   │       └── application.properties   # Configurações da aplicação
│   └── test/                            # Testes unitários e de integração
├── docker-compose.yml                   # Configuração Docker Compose
├── Dockerfile                           # Dockerfile para containerização
├── pom.xml                              # Dependências Maven
└── README.md                            # Este arquivo
👥 Contribuição

Faça um fork do projeto
Crie uma branch para sua feature (git checkout -b feature/nova-funcionalidade)
Commit suas mudanças (git commit -m 'Adiciona nova funcionalidade')
Push para a branch (git push origin feature/nova-funcionalidade)
Abra um Pull Request

📄 Licença
Este projeto está licenciado sob a Licença MIT.
📞 Contato
Para mais informações, entre em contato através do GitHub.

Desenvolvido com ❤️ por Carolxyn
