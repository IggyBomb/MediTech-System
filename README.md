
NFA019 - hospital
The application’s purpose is to automate the management of a hospital,keeping track of
various actions taken by doctors, technicians and administrative staff.

## Deployment

To deploy this project run the Main class in the package main.

## Usage Instructions
The software has multiple functionalities specific for each of the different employees:
Every interface has to be accessed using an employee's account.
The interface "Admin" is created specifically for the administrative staff, but anyone can use it. The interface is used to create a new patient in the database, or delete, update or search for an already existing one, the search field works using the ID or the name of the patient. The second functionalities of the interface is to create, search, update, delete a doctor's visit. A visit can be found using it's his ID or the Name of the patient, in this case a list of all patient's visit will be shown.

The "Medecin" interface provides a comprehensive overview of a patient's consultations and allows for prescriptions. It can be used only by doctors after logging in:

The "Technicien" interface provides an overview of all consultations requiring the allocation of a medical device. Here is how you can use its various features. The interface can be accessed only by authorized technicians after logging in. The technician can privide to the patient a medical device and mark it as "provided" in the database.

The "Super Admin" interface can be accessed only by an account with super Admin privilege and can add, search, update, and delete an employee and a it's relative user account.

## Packages

The project's structure aligns with the MVC (Model-View-Controller) architectural pattern:
The 'model' package encapsulates the backend classes pivotal to the software's structure. The following key classes are contained within this package:
-The 'GestionnaireAdministratif' class interfaces with the database and manages hospital patients and their medical history.
-The 'GestionnaireConsultation' class serves as the model that interfaces with the database for managing doctor's visits and prescriptions, including drugs and medical devices. Furthermore, this model facilitates the provision of prescribed devices to patients.
-The 'GestionnaireSuperAdmin' class interfaces with the database to add new users and employees to the system.
In essence, each class within the model package plays a specific role in managing and maintaining the crucial aspects of the hospital management system, from patient records and consultation schedules to user management.

The collection of packages commencing with 'controller' are specifically designed to establish connections between various views and the underlying model.
The organization is straightforward: each controller relates to its respective views by aligning the two classes via the title as a key, demonstrated by "View" ---> "ControllerView".

The controllers are categorized based on views, and each group that represents a specific view is housed in its corresponding package. For instance, all groups representing the "Medecin" view can be found within the "controllerViewMedecin" package. This organizational pattern is consistent across all views and their related controllers.

The software is comprised of multiple view packages:

viewGestionnaireAdmin: This component of the software is utilized by the administrative staff to manage new and existing patients and their visits. Additionally, it provides a platform for other employees to retrieve pertinent patient information.

viewMedecin: This module caters to the needs of the medical practitioners. Doctors use it to administer patient visits, prescribe medications, recommend medical devices, and suggest other treatments. Furthermore, this part of the software provides access to a patient's medical history upon opening a visit. A noteworthy feature is the ability to print the prescriptions directly from the system.

viewTechnicien: This portion of the software is designed for medical device technicians. It provides a list of patient visits where a medical device has been prescribed but not yet supplied. The corresponding prescription gets removed from the system once the device has been provided, ensuring efficient management of medical device allocation.
## API Reference

Software Design and Implementation
In designing and implementing this system, we have incorporated both frontend and backend technologies for a comprehensive solution. Java Swing is used extensively for frontend development, providing a robust platform for creating an intuitive and interactive user interface.

Java Swing, part of Java's Standard Edition, is a GUI (graphical user interface) toolkit that provides a wide range of widgets and packages to develop a sophisticated user interface. It includes components such as menus, buttons, lists, and more complex elements like tables and scroll panels, which contribute to a user-friendly experience.

On the backend, JDBC (Java Database Connectivity) has been utilized for database connectivity. JDBC is a part of JavaSE (Java Standard Edition), providing a standard Java API for database-independent connectivity between the Java programming language and a wide range of databases. The JDBC library includes APIs for each of the tasks typically associated with database usage, including SQL statement execution and results querying.
## Authors

- [martino.corbellini.bressan@gmail.com](https://gitlab.com/martino.corbellini)

