
NFA019 - hospital
The application’s purpose is to automate the management of a hospital,keeping track of
various actions taken by doctors, technicians and administrative staff.

## Deployment

To deploy this project run the Main class in the package main.

## Usage Instructions
The software has multiple functionalities specific for each of the different employees:
Every interface has to be accessed using an employee's account.
The interface "Admin" is created specifically for the administrative staff, but anyone can use it. The interface is used to create a new patient in the database, or delete, update or search for an already existing one, the search field works using the ID or the name of the patient. The second functionalities of the interface is to create, search, update, delete a doctor's visit. A visit can be found using it's his ID or the Name of the patient, in this case a list of all patient's visit will be shown.

The "Medecin" interface offers a detailed view of a patient's consultation history and enables the creation, management, and deletion of prescriptions. This interface is exclusively accessible to doctors upon successful login.
One of the crucial capabilities of this interface is the ability to access a patient's complete medical history during a consultation. This allows the doctor to make informed decisions based on the patient's past medical records.
A distinct feature is the system's provision for prescription management. Doctors can not only create prescriptions but also delete them if necessary, offering flexibility in handling treatment plans.
Moreover, the system facilitates direct printing of prescriptions from the interface, simplifying the process of prescription issuance for the medical staff and enhancing overall operational efficiency.

The "Technicien" interface offers a comprehensive overview of all consultations necessitating the allocation of a medical device. It is exclusively accessible to authorized technicians following a successful login.
Technicians can provide patients with the required medical devices and subsequently update the device's status as "provided" in the database, ensuring accurate tracking of medical device allocation.
Additionally, the interface features a search functionality enabling technicians to look up consultations either by patient name or ID. This ability to search and filter makes it easier to manage and navigate through the list of consultations.
Once a specific consultation is located using the search functionality, technicians can access it through the 'Technician View Window.' This feature enables them to delve into the specifics of a consultation, allowing for a more detailed understanding of the medical device requirements. Within this window, technicians can grant the medical device to the patient, further updating the database to reflect this allocation.

The "Super Admin" interface can be accessed only by an account with super Admin privilege and can add, search, update, and delete an employee and a it's relative user account.

The 'Statistic' page provides a range of analytical capabilities that enable users to gather insights about the health care system's performance over time. It offers the following features:

Monthly Consultation Analysis: This feature allows users to identify the months within a specific year that recorded the highest number of doctor visits. It can provide a breakdown of the consultation frequency on a monthly basis, which can help identify patterns, such as peak periods of doctor visits throughout the year. This feature not only displays data for individual months but also provides an aggregated total number of visits for the entire year.

Medical Devices Grant Analysis: This feature enables users to monitor the prescription and distribution of medical devices throughout the year. It offers a count of how many medical devices have been prescribed and actually granted to patients. This can be helpful for tracking the usage and availability of medical equipment in the healthcare system. Just like the consultation analysis, this feature provides data broken down by month, identifying periods with higher rates of device distribution.

These features leverage the data stored in the 'consultation' and 'ordonnance' tables in the database. By applying aggregative and temporal analysis on this data, the 'Statistic' page presents users with a valuable perspective on the operation of the healthcare system.

## Deploiment Instructions
The application is a basic CRUD (Create, Retrieve, Update, Delete) application with this schema would allow users to interact with the data stored in the MySQL database in the following ways:

Create (Insert): The application would provide functionality to add new entries to each of the tables. For example, a new patient or employee could be registered, a new consultation could be scheduled, or a new login account could be created. When a new consultation is created, an associated 'ordonnance' (prescription) may also be created, with medications and proposed treatments.

Retrieve (Select): The application would allow users to retrieve and display data from each of the tables. For instance, users could view a list of all patients or employees, search for consultations by different criteria (like patient ID, doctor ID or date), or view all prescriptions related to a specific consultation. Login information could be retrieved to authenticate users.

Update (Edit): The application would facilitate modification of existing data. For example, a patient's or an employee's details could be updated. The details of a consultation could be edited, and changes could be made to a prescription, like updating the status of a medical device.

Delete (Remove): The application would provide options to remove existing entries from each of the tables. For example, a patient's record or an employee's details could be deleted from the system, or a consultation record could be removed.

Remember, depending on the permissions assigned to a user (which might be defined by their 'profession' as stored in the login table), some users might not have full CRUD capabilities - for example, some users might only be able to view (Retrieve) data, and not Create, Update or Delete it.

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

viewMedecin: This module caters to the needs of the medical practitioners. Doctors use it to administer patient visits, prescribe medications, recommend medical devices, and suggest other treatments.

viewTechnicien: This portion of the software is designed for medical device technicians. It provides a list of patient visits where a medical device has been prescribed but not yet supplied. The corresponding prescription gets removed from the system once the device has been provided, ensuring efficient management of medical device allocation.
## API Reference

Software Design and Implementation
In designing and implementing this system, we have incorporated both frontend and backend technologies for a comprehensive solution. Java Swing is used extensively for frontend development, providing a robust platform for creating an intuitive and interactive user interface.

Java Swing, part of Java's Standard Edition, is a GUI (graphical user interface) toolkit that provides a wide range of widgets and packages to develop a sophisticated user interface. It includes components such as menus, buttons, lists, and more complex elements like tables and scroll panels, which contribute to a user-friendly experience.

On the backend, JDBC (Java Database Connectivity) has been utilized for database connectivity. JDBC is a part of JavaSE (Java Standard Edition), providing a standard Java API for database-independent connectivity between the Java programming language and a wide range of databases. The JDBC library includes APIs for each of the tasks typically associated with database usage, including SQL statement execution and results querying.
## Authors

- [martino.corbellini.bressan@gmail.com](https://gitlab.com/martino.corbellini)

