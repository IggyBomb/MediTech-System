

NFA019 - hôpital
L'objectif de l'application est d'automatiser la gestion d'un hôpital, en assurant le suivi de diverses actions effectuées par les médecins, les techniciens et le personnel administratif.
## Deployment

Pour déployer ce projet, exécutez la classe Main dans le package main.
## Usage Instructions
Le logiciel offre de multiples fonctionnalités spécifiques à chacun des différents employés :
Chaque interface doit être accédée à l'aide d'un compte d'employé.
L'interface "Admin" est créée spécifiquement pour le personnel administratif, mais tout le monde peut l'utiliser. L'interface sert à créer un nouveau patient dans la base de données, ou à supprimer, mettre à jour ou rechercher un patient déjà existant. Le champ de recherche fonctionne en utilisant l'ID ou le nom du patient. La deuxième fonctionnalité de l'interface est de créer, rechercher, mettre à jour, supprimer une visite de médecin. Une visite peut être trouvée en utilisant son ID ou le nom du patient, dans ce cas une liste de toutes les visites du patient sera affichée.

L'interface "Médecin" offre un aperçu complet des consultations d'un patient et permet de faire des prescriptions. Elle ne peut être utilisée que par les médecins après s'être connectés :

L'interface "Technicien" offre un aperçu de toutes les consultations nécessitant l'attribution d'un appareil médical. Voici comment vous pouvez utiliser ses différentes fonctionnalités. L'interface ne peut être accédée que par les techniciens autorisés après connexion. Le technicien peut fournir à un patient un appareil médical et le marquer comme "fourni" dans la base de données.

L'interface "Super Admin" ne peut être accédée que par un compte ayant le privilège de super administrateur et peut ajouter, rechercher, mettre à jour et supprimer un employé et son compte utilisateur relatif.

## Packages

La structure du projet suit le modèle d'architecture MVC (Model-View-Controller) :
Le package 'model' encapsule les classes backend essentielles à la structure du logiciel. Les classes clés suivantes sont contenues dans ce package :
-La classe 'GestionnaireAdministratif' se connecte à la base de données et gère les patients de l'hôpital et leur historique médical.
-La classe 'GestionnaireConsultation' sert de modèle qui se connecte à la base de données pour gérer les visites de médecins et les prescriptions, y compris les médicaments et les appareils médicaux. De plus, ce modèle facilite la fourniture d'appareils prescrits aux patients.
-La classe 'GestionnaireSuperAdmin' se connecte à la base de données pour ajouter de nouveaux utilisateurs et employés au système.
En essence, chaque classe du package model joue un rôle spécifique dans la gestion et le maintien des aspects cruciaux du système de gestion d'hôpital, des dossiers de patients et des horaires de consultation à la gestion des utilisateurs.

L'ensemble de packages commençant par 'controller' est spécialement conçu pour établir des connexions entre les différentes vues et le modèle sous-jacent.
L'organisation est simple : chaque contrôleur est lié à ses vuesrespectives en alignant les deux classes via le titre comme clé, démontré par "View" ---> "ControllerView".

Les contrôleurs sont catégorisés en fonction des vues, et chaque groupe qui représente une vue spécifique est contenu dans son package correspondant. Par exemple, tous les groupes représentant la vue "Médecin" se trouvent dans le package "controllerViewMedecin". Cette organisation est cohérente pour toutes les vues et leurs contrôleurs associés.

Le logiciel est composé de plusieurs packages de vues :

viewGestionnaireAdmin : Cette composante du logiciel est utilisée par le personnel administratif pour gérer les nouveaux patients et leurs visites. De plus, elle offre une plateforme aux autres employés pour récupérer les informations pertinentes sur le patient.

viewMedecin : Ce module répond aux besoins des praticiens médicaux. Les médecins l'utilisent pour administrer les visites des patients, prescrire des médicaments, recommander des appareils médicaux et proposer d'autres traitements. De plus, cette partie du logiciel offre un accès à l'historique médical d'un patient lors de l'ouverture d'une visite. Une caractéristique notable est la possibilité d'imprimer directement les prescriptions à partir du système.

viewTechnicien : Cette partie du logiciel est conçue pour les techniciens d'appareils médicaux. Elle fournit une liste des visites des patients où un appareil médical a été prescrit mais pas encore fourni. La prescription correspondante est retirée du système une fois que l'appareil a été fourni, garantissant une gestion efficace de l'attribution des appareils médicaux.
## API Reference

Conception et mise en œuvre du logiciel
Pour concevoir et mettre en œuvre ce système, nous avons intégré des technologies de frontend et de backend pour une solution complète. Java Swing est largement utilisé pour le développement du frontend, fournissant une plateforme robuste pour la création d'une interface utilisateur intuitive et interactive.

Java Swing, qui fait partie de l'édition standard de Java, est un kit d'outils d'interface utilisateur graphique (GUI) qui offre une large gamme de widgets et de packages pour développer une interface utilisateur sophistiquée. Il comprend des composants tels que des menus, des boutons, des listes et des éléments plus complexes comme des tables et des panneaux déroulants, qui contribuent à une expérience utilisateur agréable.

Pour la connectivité à la base de données, nous avons utilisé JDBC (Java Database Connectivity) en backend. JDBC fait partie de JavaSE (Java Standard Edition), fournissant une API Java standard pour une connectivité indépendante de la base de données entre le langage de programmation Java et une large gamme de bases de données. La bibliothèque JDBC inclut des APIs pour chacune des tâches généralement associées à l'utilisation de la base de données, y compris l'exécution des instructions SQL et la requête des résultats.
## Authors

- [martino.corbellini.bressan@gmail.com](https://gitlab.com/martino.corbellini)

