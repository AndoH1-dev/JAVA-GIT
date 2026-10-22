# Ghibli Explorer

**Application web de découverte des films du Studio Ghibli**, développée dans le cadre du cours **Java Avancé (M1)** à SUP DE VINCI, encadré par **Jérôme Bâton**.

Le projet met en pratique la création d'une application web avec **Java, Spring Boot et Thymeleaf**, ainsi que la consommation d'une API REST externe.

## Fonctionnalités

- Consultation du catalogue des films du Studio Ghibli.
- Affichage des affiches, titres, réalisateurs et années de sortie.
- Recherche de films par titre.
- Navigation vers une fiche détaillée par film (à confirmer par un test de bout en bout).
- Interface web avec Thymeleaf et CSS.
- Gestion simple des erreurs lors de l'appel à l'API.

> **Extension possible :** traduction en français à l'aide d'une seconde API (option proposée par le formateur, non confirmée comme intégrée).

## Technologies

| Technologie | Utilisation |
| --- | --- |
| Java 25 | Langage principal |
| Spring Boot 4.1.1 | Application web et logique serveur |
| Spring Web MVC | Contrôleurs et routes HTTP |
| `RestClient` | Consommation de l'API externe |
| Thymeleaf | Génération des pages HTML |
| HTML / CSS | Interface utilisateur |
| Maven / Maven Wrapper | Gestion du projet et des dépendances |
| Git / GitHub | Versionnement et travail en binôme |

## API utilisée

**Studio Ghibli API** : https://ghibliapi.dev/

Endpoints exploités par le backend :

- `GET https://ghibliapi.dev/films` : récupérer le catalogue.
- `GET https://ghibliapi.dev/films/{id}` : récupérer un film par son identifiant.

Les données JSON de l'API sont converties en objets Java puis transmises aux vues Thymeleaf.

## Organisation du projet

```text
src/
├── main/
│   ├── java/fr/vinci/ghibli/
│   │   ├── GhibliExplorerApplication.java
│   │   ├── controllers/
│   │   │   ├── HomeController.java
│   │   │   └── FilmController.java
│   │   ├── models/
│   │   │   └── Film.java
│   │   └── services/
│   │       └── GhibliService.java
│   └── resources/
│       ├── templates/
│       │   ├── index.html
│       │   ├── films.html
│       │   └── film-detail.html
│       ├── static/
│       │   └── css/
│       └── application.properties
└── test/java/fr/vinci/ghibli/
    ├── GhibliExplorerApplicationTests.java
    ├── controllers/FilmControllerTest.java       # si ajouté
    └── services/GhibliServiceTest.java           # si ajouté
```

*L'organisation exacte des fichiers HTML et CSS est à rapprocher de la branche finale fusionnée.*

## Prérequis

- **JDK 25** (version déclarée dans le projet).
- Git pour cloner le dépôt.
- Connexion Internet pour les appels à l'API Ghibli.

Maven n'a pas besoin d'être installé globalement : le projet inclut le **Maven Wrapper**.

## Installation et exécution

### 1. Cloner le dépôt

```bash
git clone https://github.com/AndoH1-dev/JAVA-GIT.git
cd JAVA-GIT
```

### 2. Démarrer l'application

Sous Windows avec Git Bash :

```bash
./mvnw spring-boot:run
```

Sous PowerShell :

```powershell
.\mvnw.cmd spring-boot:run
```

### 3. Ouvrir l'application

Accéder à **http://localhost:8080/** dans un navigateur.

> Si le port 8080 est déjà utilisé, arrêter l'autre application ou modifier `server.port` dans `src/main/resources/application.properties`.

## Pages et routes

| Route | Description |
| --- | --- |
| `/` | Accueil de Ghibli Explorer |
| `/films` | Catalogue des films |
| `/films?recherche=totoro` | Recherche de films par titre |
| `/films/{id}` | Fiche d'un film |

## Tests et vérification

### Tests automatisés prévus

Les tests suivants constituent la couverture minimale proposée. **Leur présence effective et leur succès doivent être vérifiés dans le dépôt final** :

| Classe de test | Type | Objectif |
| --- | --- | --- |
| `GhibliExplorerApplicationTests` | Intégration Spring | Vérifier le chargement du contexte Spring Boot. |
| `FilmControllerTest` | Test web avec MockMvc et service simulé | Vérifier les routes `/films` et `/films?recherche=totoro`, la vue renvoyée et les attributs du modèle. |
| `GhibliServiceTest` | Test avec API distante (si implémenté ainsi) | Vérifier le traitement d'une recherche sans résultat ; ce test dépend de la disponibilité du service Ghibli. |

**Attention :** un test qui appelle directement `ghibliapi.dev` n'est pas un test unitaire isolé. Il peut échouer à cause d'une panne réseau même si le code local n'a pas changé.

Depuis la racine du projet, exécuter :

```bash
./mvnw clean test
```

Pour la vérification complète du cycle Maven :

```bash
./mvnw clean verify
```

Sous PowerShell, remplacer `./mvnw` par `.\mvnw.cmd`.

**Statut des tests : à renseigner après exécution.** Ne pas considérer les tests comme réussis sans voir `BUILD SUCCESS` et le récapitulatif `Failures: 0, Errors: 0`.

### Recette manuelle

1. Accéder à `/` et ouvrir le catalogue `/films`.
2. Vérifier que les titres, affiches, réalisateurs et années apparaissent.
3. Rechercher `totoro`, puis vérifier les résultats.
4. Ouvrir une fiche détaillée depuis le catalogue.
5. Tester une recherche sans résultat et le comportement en cas d'indisponibilité de l'API.

### État de validation

- **Confirmé visuellement :** catalogue `/films` affiché avec images et formulaire de recherche.
- **À confirmer :** résultat de recherche, fiche détaillée, tests automatisés et gestion des erreurs en situation réelle.
- **Non inclus dans le socle :** traduction automatique, option facultative proposée par le formateur.

## Équipe et répartition du travail

**Projet réalisé en binôme :**

| Étudiant | Responsabilités principales | Branche Git |
| --- | --- | --- |
| **Andil ABAYOMI** | Initialisation du projet Spring Boot/Maven et du dépôt Git, modèle `Film`, intégration de l’API Ghibli avec `RestClient`, `GhibliService`, `FilmController`, récupération du catalogue, recherche par titre et gestion des erreurs côté backend. | `feature/films` |
| **Thomas VALLET** | Développement de l’interface web avec Thymeleaf et CSS, mise en page du catalogue et des fiches de films, navigation entre les pages et adaptation de l’affichage. | `feature/interface` |

**Intégration commune :** fusion des branches dans `main`, vérifications fonctionnelles, corrections et préparation du rendu. Les tests automatisés sont documentés ci-dessus ; leur exécution doit être confirmée avant livraison.

## Cadre pédagogique

**Établissement :** SUP DE VINCI  
**Cours :** D41 Java Avancé — M1  
**Formateur :** Jérôme Bâton  
**Sujet :** application web Spring Boot basée sur une API REST publique  
**Étudiants :** Andil ABAYOMI et Thomas VALLET

## Améliorations envisageables

- Traduction automatique de descriptions en français.
- Filtres par réalisateur ou année de sortie.
- Tests unitaires isolés du service (avec réponses API simulées).
- Gestion de favoris persistants.

---

*Projet réalisé à des fins pédagogiques. Les informations et visuels relatifs aux films proviennent de sources tierces et demeurent la propriété de leurs titulaires respectifs.*
