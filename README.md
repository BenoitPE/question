# Domain Driven Development

Membres du groupe:
- Benoit PEGAZ
- Aldrick CLERET
- Tristan DA COSTA
- Eliott DELANNAY

N'ayant pas réussi à mettre en place une communication inter-microservices avec Kafka, notre code diffèrent de l'implémentation réalisée.

L'architecture que nous avons mis en place se base sur plusieurs sources de connaissances:
- Le cours
- L'expérience professionnelle d'un membre du groupe
- D'articles qui nous ont aidés à visualiser comment implémenter en code du DDD dans un projet (L'article qui nous a le plus apporté: https://alexsoyes.com/ddd-domain-driven-design/#bounded-context-ou-contexte-borne)

Voici une représentation de l'arborescence que nous avons réalisé:

```
src
└── main
    ├── java
    │   └── poller
    │       ├── questionContext
    │       |   ├── presentation
    │       |   │   └── controllers
    │       |   ├── application
    │       |   │   ├── commands
    │       |   │   ├── dtos
    │       |   │   ├── services
    │       |   │   │   ├── interfaces
    │       |   │   │   └── implementations
    │       |   │   └── mappers
    │       |   ├── domain
    │       |   │   ├── model
    │       |   │   ├── repository
    │       |   │   ├── service
    │       |   │   │   ├── interfaces
    │       |   │   │   └── implementations
    │       |   │   └── events
    │       |   └── infrastructure
    │       └── skillContext
    │           ├── presentation
    │           │   └── controllers
    │           ├── application
    │           │   ├── commands
    │           │   ├── dtos
    │           │   ├── services
    │           │   │   ├── interfaces
    │           │   │   └── implementations
    │           │   └── mappers
    │           ├── domain
    │           │   ├── model
    │           │   ├── repository
    │           │   ├── service
    │           │   │   ├── interfaces
    │           │   │   └── implementations
    │           │   └── events
    │           └── infrastructure
    └── resources
        └── application.properties

```