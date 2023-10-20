Social Media Dashboard Application
This is a JavaFX application that provides a social media dashboard for users. Users can register, log in, create and manage their posts, and perform various user profile actions. The application is backed by a MySQL database for storing user and post data.
Features
1.	User Registration and Authentication:
•	New users can register by providing their first name, last name, username, and password.
•	Registered users can log in using their username and password.
•	User information is stored securely in a MySQL database.
2.	User Dashboard:
•	Once logged in, users are presented with a dashboard that allows them to:
•	Edit their profile details (first name, last name, username, password).
•	Add new posts, specifying content, likes, and shares.
•	Retrieve posts by their post ID.
•	Remove posts based on post ID.
•	View top posts, sorted by likes and shares.
3.	Database Integration:
•	User and post data is managed using a MySQL database.
•	The application utilizes JDBC for database connection and data retrieval.
•	Users can create, read, update, and delete (CRUD) their posts.
Prerequisites
Before running the application, make sure you have the following installed:
•	Java Development Kit (JDK)
•	JavaFX SDK
•	MySQL Database Server
How to Run
1.	Clone this repository to your local machine.
bashCopy code
git clone https://github.com/Zishan23/AP-Assignment2.git
2.	Make sure your MySQL database server is running.
3.	Import the database.sql file into your MySQL database to create the necessary tables.
4.	Open the project in your favorite Java IDE (Eclipse, IntelliJ IDEA, etc.).
5.	Set up the database connection by modifying the DB_URL, DB_USER, and DB_PASSWORD fields in the UserRegistration class. Adjust the database connection details to match your MySQL configuration.
6.	Run the CombinedDashboard class to start the application.
7.	You will be presented with the login screen. You can create a new user account or log in using an existing account.
8.	Once logged in, you can access the user dashboard and explore the various features.
Documentation
For detailed documentation of the application, you can refer to the source code. Each class and method have been documented for clarity and understanding. Additionally, a brief README file is included in each class for reference.
Contributors
•	Ismam Fatin Zishan 
StudentID:s3991435


