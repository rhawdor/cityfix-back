# CityFix - Back

## Pré-requis

- Installer un JDK 18
- Installer un IDE (IntelliJ Ultimate recommandé)
- Installer un serveur MySQL

## Mise en place

- Modifier la chaîne de connexion BDD
> /src/main/resources/application.yml  

Exemple:

    spring:
        datasource:
            url: jdbc:mysql://HOST:PORT/BDD  
            username: USERNAME  
            password: PASSWORD  

- Créer la configuration de lancement

Au sein d'IntelliJ Ultimate, créer une configuration de lancement (situé en haut à droite de la fenêtre).  
Utiliser le modèle Spring Boot et sélectionner la classe de démarrage "CityFixApplication".  

> L'application est prête à être lancée !