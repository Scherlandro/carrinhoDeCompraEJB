# Projeto Carrinho com Arquitetura Hexagonal - Java EE

Este projeto demonstra a arquitetura hexagonal com Java EE (JAX-RS, JPA, EJB, CDI, JMS) simulando um carrinho de compras.

src/main/java/com.standard.carrinho
├── domain/
│   ├── model/
│   │   ├── Produto.java
│   │   ├── ItemCarrinho.java
│   │   └── Carrinho.java
│   └── port/
│       └── CarrinhoRepository.java         ← Driven Port
│
├── application/
│   └── CarrinhoService.java                ← EJB @Stateless
│
├── adapter/
│   ├── in/rest/
│   │   └── CarrinhoResource.java           ← JAX-RS
│   └── out/persistence/
│       ├── CarrinhoRepositoryImpl.java     ← CDI + JPA
│       └── entidades/
│           ├── CarrinhoEntity.java
│           └── ItemCarrinhoEntity.java
│
├── messaging/
│   ├── CarrinhoEventProducer.java          ← @Singleton + JMS
│   └── CarrinhoListener.java               ← @MessageDriven
└── AppConfig.java                          ← @ApplicationPath
