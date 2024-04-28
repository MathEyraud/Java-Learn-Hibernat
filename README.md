## Projet Hibernat :
- Suite du projet "Java-Learn-JDBC" (https://github.com/MathEyraud/Java-Learn-JDBC).
- Exactement la même chose que ce dépôt, mais en utilisant Hibernate plutot que JDBC.
- Petite subtilité par rapport à JDBC, il y a besoin d'un projet qui implémente des contrôleurs pour être propre.
- Il faut donc créer un nouveau projet et gérer les dépendances pour que les deux projets communiquent entre.
- Vous trouverez les contrôleurs et l'interface utilisateur dans le fichier "UI.zip" à la racine du projet (/src/UI.zip).


## Projet JDBC (https://github.com/MathEyraud/Java-Learn-JDBC) :
# Introduction
- Ce dépôt est un espace pour apprendre à utiliser JDBC de zéro. Il suffit juste de jouer avec le code.
- Il faudra penser à créer les bases de données au préalable (cf script dans le projet pour créer des DB MySQL et PostgreSQL).
- Il faudra créer des utilisateurs pour ces bases de données, puis changer les accès dans le code.
- Dans un premier temps en faisant des requêtes directes avec les DB "core\src\main\java\com\mycompany\tennis\coreTestDeConnection". 
- Par la suite, on apprendra à utiliser JDBC via des implémentations de repositories "core\src\main\java\com\mycompany\tennis\TestDeConnectionWithRepository".
- Finalement, on utilisera des services qui utilise les repositories "core\src\main\java\com\mycompany\tennis\TestDeConnectionWithService".

# Critique
- N'hésitez pas à me faire des retours ou à critiquer ce que j'ai fait, tous les conseils sont bons à prendre !
- L'ensemble du code n'est pas terminé, l'objectif est juste de prendre la main sur JDBC.
