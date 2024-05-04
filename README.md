# Projet Hibernate
- Suite du projet "Java-Learn-JDBC" (https://github.com/MathEyraud/Java-Learn-JDBC).
- Exactement la même chose que ce dépôt, mais en utilisant Hibernate plutot que JDBC.
- Petite subtilité par rapport à JDBC, il y a besoin d'un projet qui implémente des contrôleurs pour être propre.
- Il faut donc créer un nouveau projet et gérer les dépendances pour que les deux projets communiquent entre.
- Vous trouverez les contrôleurs et l'interface utilisateur dans le fichier "UI.zip" à la racine du projet (/src/UI.zip).

# JPA & Hibernate
## Java Persistence API (JPA)
- Qu'est-ce que JPA ?
=> JPA est une API Java standard qui permet aux développeurs de persister des données dans des bases de données relationnelles.
=> Elle définit une interface de programmation commune pour la gestion des entités, des relations et des opérations de persistance.
=> JPA facilite le développement d'applications Java en fournissant une couche d'abstraction entre le code Java et la base de données.
- Caractéristiques de JPA :
=> Mapping Objet-Relationnel (ORM) : JPA permet de mapper les objets Java aux tables de la base de données et vice versa.
=> Annotations : JPA utilise des annotations Java pour définir le mapping entre les entités Java et les tables de la base de données.
=> API Facilitante : JPA fournit une API simple et puissante pour effectuer des opérations de CRUD (Create, Read, Update, Delete) sur les entités.
=> Transactions : JPA prend en charge la gestion des transactions, ce qui permet de garantir l'intégrité des données et la cohérence dans les opérations de persistance.

## Hibernate
- Qu'est-ce que Hibernate ?
=> Hibernate est une implémentation populaire de JPA.
=> Il fournit une implémentation robuste et performante de JPA ainsi que des fonctionnalités supplémentaires pour simplifier le développement d'applications Java basées sur la persistance.
=> Hibernate est largement utilisé dans l'industrie pour ses fonctionnalités avancées, sa flexibilité et sa facilité d'utilisation.
- Caractéristiques de Hibernate :
=> Mapping Objet-Relationnel (ORM) : Hibernate permet de mapper facilement les objets Java aux tables de la base de données à l'aide d'annotations ou de fichiers de configuration XML.
=> HQL (Hibernate Query Language) : Hibernate propose une langue de requête orientée objet (HQL) qui ressemble au SQL mais s'applique aux objets Java, ce qui simplifie les opérations de requête.
=> Gestion des Sessions et des Transactions : Hibernate gère automatiquement les sessions et les transactions, ce qui facilite la gestion des opérations de persistance.
=> Caching : Hibernate prend en charge différents types de mise en cache (caches de premier et deuxième niveaux) pour améliorer les performances des applications en réduisant les accès à la base de données.

# Projet JDBC (https://github.com/MathEyraud/Java-Learn-JDBC)
## Introduction
- Ce dépôt est un espace pour apprendre à utiliser JDBC de zéro. Il suffit juste de jouer avec le code.
- Il faudra penser à créer les bases de données au préalable (cf script dans le projet pour créer des DB MySQL et PostgreSQL).
- Il faudra créer des utilisateurs pour ces bases de données, puis changer les accès dans le code.
- Dans un premier temps en faisant des requêtes directes avec les DB "core\src\main\java\com\mycompany\tennis\coreTestDeConnection". 
- Par la suite, on apprendra à utiliser JDBC via des implémentations de repositories "core\src\main\java\com\mycompany\tennis\TestDeConnectionWithRepository".
- Finalement, on utilisera des services qui utilise les repositories "core\src\main\java\com\mycompany\tennis\TestDeConnectionWithService".

# Critique
- N'hésitez pas à me faire des retours ou à critiquer ce que j'ai fait, tous les conseils sont bons à prendre !
- L'ensemble du code n'est pas terminé, l'objectif est juste de prendre la main sur JDBC & Hibernate.
