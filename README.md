
NFA019 - hôpital
L'application a pour but d'automatiser la gestion d'un hôpital, en gardant une trace des différentes actions effectuées par les médecins, les techniciens et le personnel administratif.
des différentes actions entreprises par les médecins, les techniciens et le personnel administratif.

## Déploiement

Pour déployer ce projet, exécutez la classe Main dans le paquetage main.

## Instructions d'utilisation
Le logiciel possède de multiples fonctionnalités spécifiques pour chacun des différents employés :
Chaque interface doit être accessible en utilisant le compte d'un employé.
L'interface "Admin" a été créée spécifiquement pour le personnel administratif, mais tout le monde peut l'utiliser. L'interface est utilisée pour créer un nouveau patient dans la base de données, ou pour supprimer, mettre à jour ou rechercher un patient déjà existant, le champ de recherche fonctionnant à l'aide de l'ID ou du nom du patient. La deuxième fonctionnalité de l'interface est la création, la recherche, la mise à jour et la suppression d'une visite médicale. Une visite peut être recherchée en utilisant son ID ou le nom du patient, dans ce cas une liste de toutes les visites du patient sera affichée.

L'interface "Médecin" offre une vue détaillée de l'historique des consultations d'un patient et permet la création, la gestion et la suppression d'ordonnances. Cette interface est exclusivement accessible aux médecins dès lors qu'ils se connectent avec succès.
L'une des fonctionnalités essentielles de cette interface est la possibilité d'accéder à l'historique médical complet d'un patient au cours d'une consultation. Cela permet au médecin de prendre des décisions éclairées sur la base des dossiers médicaux antérieurs du patient.
Le système se distingue également par sa capacité à gérer les prescriptions. Les médecins peuvent non seulement créer des prescriptions, mais aussi les supprimer si nécessaire, ce qui offre une certaine souplesse dans la gestion des plans de traitement.
En outre, le système facilite l'impression directe des ordonnances à partir de l'interface, ce qui simplifie le processus d'émission des ordonnances pour le personnel médical et améliore l'efficacité opérationnelle globale.

L'interface "Technicien" offre une vue d'ensemble de toutes les consultations nécessitant l'attribution d'un dispositif médical. Elle est exclusivement accessible aux techniciens autorisés après une connexion réussie.
Les techniciens peuvent fournir aux patients les dispositifs médicaux requis et ensuite mettre à jour le statut du dispositif comme "fourni" dans la base de données, assurant ainsi un suivi précis de l'attribution des dispositifs médicaux.
En outre, l'interface dispose d'une fonction de recherche permettant aux techniciens de rechercher des consultations par nom ou par numéro d'identification du patient. Cette capacité de recherche et de filtrage facilite la gestion et la navigation dans la liste des consultations.
Une fois qu'une consultation spécifique est localisée à l'aide de la fonction de recherche, les techniciens peuvent y accéder par le biais de la "fenêtre de visualisation du technicien". Cette fonction leur permet de se plonger dans les détails d'une consultation, ce qui leur permet de mieux comprendre les exigences relatives aux dispositifs médicaux. Dans cette fenêtre, les techniciens peuvent attribuer le dispositif médical au patient, en mettant à jour la base de données pour refléter cette attribution

L'interface "Super Admin" n'est accessible que par un compte disposant du privilège "Super Admin" et permet d'ajouter, de rechercher, de mettre à jour et de supprimer un employé et le compte utilisateur correspondant.
La page "Statistiques" offre une série de possibilités d'analyse qui permettent aux utilisateurs de se faire une idée des performances du système de soins de santé au fil du temps. Elle offre les fonctionnalités suivantes :
Analyse des consultations mensuelles : Cette fonction permet aux utilisateurs d'identifier les mois d'une année donnée qui ont enregistré le plus grand nombre de consultations médicales. Elle peut fournir une ventilation de la fréquence des consultations sur une base mensuelle, ce qui peut aider à identifier des modèles, tels que les périodes de pointe des visites médicales tout au long de l'année. Cette fonction n'affiche pas seulement des données pour des mois individuels, mais fournit également un nombre total agrégé de visites pour l'année entière.
Analyse des subventions pour les dispositifs médicaux : Cette fonction permet aux utilisateurs de surveiller la prescription et la distribution de dispositifs médicaux tout au long de l'année. Elle indique le nombre de dispositifs médicaux qui ont été prescrits et effectivement délivrés aux patients. Cette fonction peut être utile pour suivre l'utilisation et la disponibilité du matériel médical dans le système de santé. Tout comme l'analyse des consultations, cette fonction fournit des données ventilées par mois, identifiant les périodes où les taux de distribution des dispositifs sont les plus élevés.
Ces fonctions exploitent les données stockées dans les tables "consultation" et "ordonnance" de la base de données. En appliquant une analyse agrégée et temporelle à ces données, la page "Statistiques" offre aux utilisateurs une perspective précieuse sur le fonctionnement du système de soins de santé.

## Instructions de déploiement
L'application est une application CRUD (Create, Retrieve, Update, Delete) de base avec ce schéma qui permet aux utilisateurs d'interagir avec les données stockées dans la base de données MySQL de la manière suivante :

Créer ("Insert") : L'application offrirait une fonctionnalité permettant d'ajouter de nouvelles entrées à chacune des tables. Par exemple, un nouveau patient ou employé pourrait être enregistré, une nouvelle consultation pourrait être programmée ou un nouveau compte de connexion pourrait être créé. Lorsqu'une nouvelle consultation est créée, une "ordonnance" associée peut également être créée, avec les médicaments et les traitements proposés.
Rechercher ("Select") : L'application permettrait aux utilisateurs d'extraire et d'afficher les données de chacune des tables. Par exemple, les utilisateurs pourraient consulter une liste de tous les patients ou employés, rechercher des consultations selon différents critères (comme l'identifiant du patient, l'identifiant du médecin ou la date), ou consulter toutes les ordonnances liées à une consultation spécifique. Les informations de connexion peuvent être récupérées pour authentifier les utilisateurs.
Mise à jour ("modifier") : L'application faciliterait la modification des données existantes. Par exemple, les coordonnées d'un patient ou d'un employé pourraient être mises à jour. Les détails d'une consultation pourraient être édités et des modifications pourraient être apportées à une prescription, comme la mise à jour de l'état d'un dispositif médical.
Supprimer (Remove) : L'application propose des options permettant de supprimer les entrées existantes dans chacune des tables. Par exemple, le dossier d'un patient ou les coordonnées d'un employé peuvent être supprimés du système, ou un dossier de consultation peut être supprimé.
N'oubliez pas qu'en fonction des autorisations attribuées à un utilisateur (qui peuvent être définies par sa "profession" telle qu'elle est enregistrée dans la table de connexion), certains utilisateurs peuvent ne pas disposer de capacités CRUD complètes - par exemple, certains utilisateurs peuvent uniquement être en mesure de visualiser (récupérer) des données, mais pas de les créer, de les mettre à jour ou de les supprimer.

## Paquets et Architecture du logiciel

La structure du projet s'aligne sur le modèle architectural MVC (Modèle-Vue-Contrôleur) :
Le paquet "modèle" encapsule les classes dorsales essentielles à la structure du logiciel. Les classes clés suivantes sont contenues dans ce paquet :
-La classe 'GestionnaireAdministratif' s'interface avec la base de données et gère les patients de l'hôpital et leurs antécédents médicaux.
-La classe "GestionnaireConsultation" sert de modèle d'interface avec la base de données pour la gestion des visites chez le médecin et des prescriptions, y compris les médicaments et les dispositifs médicaux. En outre, ce modèle facilite la fourniture des dispositifs prescrits aux patients.
-La classe "GestionnaireSuperAdmin" s'interface avec la base de données pour ajouter de nouveaux utilisateurs et employés au système.
En substance, chaque classe du modèle joue un rôle spécifique dans la gestion et la maintenance des aspects cruciaux du système de gestion de l'hôpital, depuis les dossiers des patients et les programmes de consultation jusqu'à la gestion des utilisateurs.

La collection de paquets commençant par "controller" est spécifiquement conçue pour établir des connexions entre les différentes vues et le modèle sous-jacent.
L'organisation est simple : chaque contrôleur est lié à ses vues respectives en alignant les deux classes à l'aide du titre comme clé, comme le montre "View" ---> "ControllerView".
Les contrôleurs sont classés en fonction des vues, et chaque groupe représentant une vue spécifique est logé dans le paquetage correspondant. Par exemple, tous les groupes représentant la vue "Medecin" se trouvent dans le paquet "controllerViewMedecin". Ce schéma organisationnel est cohérent pour toutes les vues et leurs contrôleurs.
Le logiciel est composé de plusieurs paquets de vues :
vueGestionnaireAdmin : Ce composant du logiciel est utilisé par le personnel administratif pour gérer les patients nouveaux et existants ainsi que leurs visites. En outre, il fournit une plate-forme permettant aux autres employés de récupérer des informations pertinentes sur les patients.
viewMedecin : Ce module répond aux besoins des médecins. Les médecins l'utilisent pour gérer les visites des patients, prescrire des médicaments, recommander des appareils médicaux et suggérer d'autres traitements.
viewTechnicien : cette partie du logiciel est conçue pour les techniciens de dispositifs médicaux. Elle fournit une liste des visites de patients pour lesquelles un dispositif médical a été prescrit mais n'a pas encore été fourni. La prescription correspondante est supprimée du système une fois que le dispositif a été fourni, ce qui garantit une gestion efficace de l'attribution des dispositifs médicaux.

## Référence API

Conception et mise en œuvre du logiciel
Lors de la conception et de la mise en œuvre de ce système, nous avons intégré des technologies front-end et back-end pour obtenir une solution complète. Java Swing est largement utilisé pour le développement du front-end, fournissant une plateforme robuste pour créer une interface utilisateur intuitive et interactive.
Java Swing, qui fait partie de l'édition standard de Java, est une boîte à outils GUI (graphical user interface) qui fournit une large gamme de widgets et de packages permettant de développer une interface utilisateur sophistiquée. Elle comprend des composants tels que des menus, des boutons, des listes et des éléments plus complexes tels que des tableaux et des panneaux de défilement, qui contribuent à une expérience conviviale.

En arrière-plan, JDBC (Java Database Connectivity) a été utilisé pour la connectivité avec les bases de données. JDBC fait partie de JavaSE (Java Standard Edition) et fournit une API Java standard pour une connectivité indépendante des bases de données entre le langage de programmation Java et une large gamme de bases de données. La bibliothèque JDBC comprend des API pour chacune des tâches généralement associées à l'utilisation d'une base de données, y compris l'exécution des instructions SQL et l'interrogation des résultats.
## Auteurs

- [martino.corbellini.bressan@gmail.com](https://gitlab.com/martino.corbellini)