
NFA019 - h�pital
L'application a pour but d'automatiser la gestion d'un h�pital, en gardant une trace des diff�rentes actions effectu�es par les m�decins, les techniciens et le personnel administratif.
des diff�rentes actions entreprises par les m�decins, les techniciens et le personnel administratif.

## D�ploiement

Pour d�ployer ce projet, ex�cutez la classe Main dans le paquetage main.

## Instructions d'utilisation
Le logiciel poss�de de multiples fonctionnalit�s sp�cifiques pour chacun des diff�rents employ�s :
Chaque interface doit �tre accessible en utilisant le compte d'un employ�.
L'interface "Admin" a �t� cr��e sp�cifiquement pour le personnel administratif, mais tout le monde peut l'utiliser. L'interface est utilis�e pour cr�er un nouveau patient dans la base de donn�es, ou pour supprimer, mettre � jour ou rechercher un patient d�j� existant, le champ de recherche fonctionnant � l'aide de l'ID ou du nom du patient. La deuxi�me fonctionnalit� de l'interface est la cr�ation, la recherche, la mise � jour et la suppression d'une visite m�dicale. Une visite peut �tre recherch�e en utilisant son ID ou le nom du patient, dans ce cas une liste de toutes les visites du patient sera affich�e.

L'interface "M�decin" offre une vue d�taill�e de l'historique des consultations d'un patient et permet la cr�ation, la gestion et la suppression d'ordonnances. Cette interface est exclusivement accessible aux m�decins d�s lors qu'ils se connectent avec succ�s.
L'une des fonctionnalit�s essentielles de cette interface est la possibilit� d'acc�der � l'historique m�dical complet d'un patient au cours d'une consultation. Cela permet au m�decin de prendre des d�cisions �clair�es sur la base des dossiers m�dicaux ant�rieurs du patient.
Le syst�me se distingue �galement par sa capacit� � g�rer les prescriptions. Les m�decins peuvent non seulement cr�er des prescriptions, mais aussi les supprimer si n�cessaire, ce qui offre une certaine souplesse dans la gestion des plans de traitement.
En outre, le syst�me facilite l'impression directe des ordonnances � partir de l'interface, ce qui simplifie le processus d'�mission des ordonnances pour le personnel m�dical et am�liore l'efficacit� op�rationnelle globale.

L'interface "Technicien" offre une vue d'ensemble de toutes les consultations n�cessitant l'attribution d'un dispositif m�dical. Elle est exclusivement accessible aux techniciens autoris�s apr�s une connexion r�ussie.
Les techniciens peuvent fournir aux patients les dispositifs m�dicaux requis et ensuite mettre � jour le statut du dispositif comme "fourni" dans la base de donn�es, assurant ainsi un suivi pr�cis de l'attribution des dispositifs m�dicaux.
En outre, l'interface dispose d'une fonction de recherche permettant aux techniciens de rechercher des consultations par nom ou par num�ro d'identification du patient. Cette capacit� de recherche et de filtrage facilite la gestion et la navigation dans la liste des consultations.
Une fois qu'une consultation sp�cifique est localis�e � l'aide de la fonction de recherche, les techniciens peuvent y acc�der par le biais de la "fen�tre de visualisation du technicien". Cette fonction leur permet de se plonger dans les d�tails d'une consultation, ce qui leur permet de mieux comprendre les exigences relatives aux dispositifs m�dicaux. Dans cette fen�tre, les techniciens peuvent attribuer le dispositif m�dical au patient, en mettant � jour la base de donn�es pour refl�ter cette attribution

L'interface "Super Admin" n'est accessible que par un compte disposant du privil�ge "Super Admin" et permet d'ajouter, de rechercher, de mettre � jour et de supprimer un employ� et le compte utilisateur correspondant.
La page "Statistiques" offre une s�rie de possibilit�s d'analyse qui permettent aux utilisateurs de se faire une id�e des performances du syst�me de soins de sant� au fil du temps. Elle offre les fonctionnalit�s suivantes :
Analyse des consultations mensuelles : Cette fonction permet aux utilisateurs d'identifier les mois d'une ann�e donn�e qui ont enregistr� le plus grand nombre de consultations m�dicales. Elle peut fournir une ventilation de la fr�quence des consultations sur une base mensuelle, ce qui peut aider � identifier des mod�les, tels que les p�riodes de pointe des visites m�dicales tout au long de l'ann�e. Cette fonction n'affiche pas seulement des donn�es pour des mois individuels, mais fournit �galement un nombre total agr�g� de visites pour l'ann�e enti�re.
Analyse des subventions pour les dispositifs m�dicaux : Cette fonction permet aux utilisateurs de surveiller la prescription et la distribution de dispositifs m�dicaux tout au long de l'ann�e. Elle indique le nombre de dispositifs m�dicaux qui ont �t� prescrits et effectivement d�livr�s aux patients. Cette fonction peut �tre utile pour suivre l'utilisation et la disponibilit� du mat�riel m�dical dans le syst�me de sant�. Tout comme l'analyse des consultations, cette fonction fournit des donn�es ventil�es par mois, identifiant les p�riodes o� les taux de distribution des dispositifs sont les plus �lev�s.
Ces fonctions exploitent les donn�es stock�es dans les tables "consultation" et "ordonnance" de la base de donn�es. En appliquant une analyse agr�g�e et temporelle � ces donn�es, la page "Statistiques" offre aux utilisateurs une perspective pr�cieuse sur le fonctionnement du syst�me de soins de sant�.

## Instructions de d�ploiement
L'application est une application CRUD (Create, Retrieve, Update, Delete) de base avec ce sch�ma qui permet aux utilisateurs d'interagir avec les donn�es stock�es dans la base de donn�es MySQL de la mani�re suivante :

Cr�er ("Insert") : L'application offrirait une fonctionnalit� permettant d'ajouter de nouvelles entr�es � chacune des tables. Par exemple, un nouveau patient ou employ� pourrait �tre enregistr�, une nouvelle consultation pourrait �tre programm�e ou un nouveau compte de connexion pourrait �tre cr��. Lorsqu'une nouvelle consultation est cr��e, une "ordonnance" associ�e peut �galement �tre cr��e, avec les m�dicaments et les traitements propos�s.
Rechercher ("Select") : L'application permettrait aux utilisateurs d'extraire et d'afficher les donn�es de chacune des tables. Par exemple, les utilisateurs pourraient consulter une liste de tous les patients ou employ�s, rechercher des consultations selon diff�rents crit�res (comme l'identifiant du patient, l'identifiant du m�decin ou la date), ou consulter toutes les ordonnances li�es � une consultation sp�cifique. Les informations de connexion peuvent �tre r�cup�r�es pour authentifier les utilisateurs.
Mise � jour ("modifier") : L'application faciliterait la modification des donn�es existantes. Par exemple, les coordonn�es d'un patient ou d'un employ� pourraient �tre mises � jour. Les d�tails d'une consultation pourraient �tre �dit�s et des modifications pourraient �tre apport�es � une prescription, comme la mise � jour de l'�tat d'un dispositif m�dical.
Supprimer (Remove) : L'application propose des options permettant de supprimer les entr�es existantes dans chacune des tables. Par exemple, le dossier d'un patient ou les coordonn�es d'un employ� peuvent �tre supprim�s du syst�me, ou un dossier de consultation peut �tre supprim�.
N'oubliez pas qu'en fonction des autorisations attribu�es � un utilisateur (qui peuvent �tre d�finies par sa "profession" telle qu'elle est enregistr�e dans la table de connexion), certains utilisateurs peuvent ne pas disposer de capacit�s CRUD compl�tes - par exemple, certains utilisateurs peuvent uniquement �tre en mesure de visualiser (r�cup�rer) des donn�es, mais pas de les cr�er, de les mettre � jour ou de les supprimer.

## Paquets et Architecture du logiciel

La structure du projet s'aligne sur le mod�le architectural MVC (Mod�le-Vue-Contr�leur) :
Le paquet "mod�le" encapsule les classes dorsales essentielles � la structure du logiciel. Les classes cl�s suivantes sont contenues dans ce paquet :
-La classe 'GestionnaireAdministratif' s'interface avec la base de donn�es et g�re les patients de l'h�pital et leurs ant�c�dents m�dicaux.
-La classe "GestionnaireConsultation" sert de mod�le d'interface avec la base de donn�es pour la gestion des visites chez le m�decin et des prescriptions, y compris les m�dicaments et les dispositifs m�dicaux. En outre, ce mod�le facilite la fourniture des dispositifs prescrits aux patients.
-La classe "GestionnaireSuperAdmin" s'interface avec la base de donn�es pour ajouter de nouveaux utilisateurs et employ�s au syst�me.
En substance, chaque classe du mod�le joue un r�le sp�cifique dans la gestion et la maintenance des aspects cruciaux du syst�me de gestion de l'h�pital, depuis les dossiers des patients et les programmes de consultation jusqu'� la gestion des utilisateurs.

La collection de paquets commen�ant par "controller" est sp�cifiquement con�ue pour �tablir des connexions entre les diff�rentes vues et le mod�le sous-jacent.
L'organisation est simple : chaque contr�leur est li� � ses vues respectives en alignant les deux classes � l'aide du titre comme cl�, comme le montre "View" ---> "ControllerView".
Les contr�leurs sont class�s en fonction des vues, et chaque groupe repr�sentant une vue sp�cifique est log� dans le paquetage correspondant. Par exemple, tous les groupes repr�sentant la vue "Medecin" se trouvent dans le paquet "controllerViewMedecin". Ce sch�ma organisationnel est coh�rent pour toutes les vues et leurs contr�leurs.
Le logiciel est compos� de plusieurs paquets de vues :
vueGestionnaireAdmin : Ce composant du logiciel est utilis� par le personnel administratif pour g�rer les patients nouveaux et existants ainsi que leurs visites. En outre, il fournit une plate-forme permettant aux autres employ�s de r�cup�rer des informations pertinentes sur les patients.
viewMedecin : Ce module r�pond aux besoins des m�decins. Les m�decins l'utilisent pour g�rer les visites des patients, prescrire des m�dicaments, recommander des appareils m�dicaux et sugg�rer d'autres traitements.
viewTechnicien : cette partie du logiciel est con�ue pour les techniciens de dispositifs m�dicaux. Elle fournit une liste des visites de patients pour lesquelles un dispositif m�dical a �t� prescrit mais n'a pas encore �t� fourni. La prescription correspondante est supprim�e du syst�me une fois que le dispositif a �t� fourni, ce qui garantit une gestion efficace de l'attribution des dispositifs m�dicaux.

## R�f�rence API

Conception et mise en �uvre du logiciel
Lors de la conception et de la mise en �uvre de ce syst�me, nous avons int�gr� des technologies front-end et back-end pour obtenir une solution compl�te. Java Swing est largement utilis� pour le d�veloppement du front-end, fournissant une plateforme robuste pour cr�er une interface utilisateur intuitive et interactive.
Java Swing, qui fait partie de l'�dition standard de Java, est une bo�te � outils GUI (graphical user interface) qui fournit une large gamme de widgets et de packages permettant de d�velopper une interface utilisateur sophistiqu�e. Elle comprend des composants tels que des menus, des boutons, des listes et des �l�ments plus complexes tels que des tableaux et des panneaux de d�filement, qui contribuent � une exp�rience conviviale.

En arri�re-plan, JDBC (Java Database Connectivity) a �t� utilis� pour la connectivit� avec les bases de donn�es. JDBC fait partie de JavaSE (Java Standard Edition) et fournit une API Java standard pour une connectivit� ind�pendante des bases de donn�es entre le langage de programmation Java et une large gamme de bases de donn�es. La biblioth�que JDBC comprend des API pour chacune des t�ches g�n�ralement associ�es � l'utilisation d'une base de donn�es, y compris l'ex�cution des instructions SQL et l'interrogation des r�sultats.
## Auteurs

- [martino.corbellini.bressan@gmail.com](https://gitlab.com/martino.corbellini)