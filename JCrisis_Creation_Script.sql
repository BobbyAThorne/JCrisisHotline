CREATE DATABASE JCrisis_Hotline_DB;

Create Table Call_Record (
    Call_Record_ID INT AUTO_INCREMENT NOT NULL,
    Start_Time DATETIME NOT NULL,
    Counselor_ID INT NOT NULL,
    Call_Description TEXT,
    Call_Type_ID VARCHAR(25) NOT NULL,
    Caller_ID INT,
    End_Time DATETIME NOT NULL,
    PRIMARY KEY (Call_Record_ID)
);

Create Table Call_Record_Resource (
    Call_Record_ID INT NOT NULL,
    Resource_ID INT NOT NULL,
    PRIMARY KEY (Call_Record_ID, Resource_ID)
);

Create Table Caller (
    Caller_ID INT NOT NULL,
    First_Name VARCHAR(25),
    Last_Name VARCHAR(25),
    Phone VARCHAR(20),
    Address VARCHAR(50),
    City VARCHAR(25),
    Territory CHAR(2),
    ZIP VARCHAR(10),
    PRIMARY KEY(Caller_ID)
);

Create Table Call_Type (
    Call_Type_ID VARCHAR(25) NOT NULL,
    Description TEXT,
    PRIMARY KEY(Call_Type_ID)
);

Create Table Limitation(
    Limitation_ID INT NOT NULL,
    Limitation_Category_ID VARCHAR(25),
    Description TEXT,
    PRIMARY KEY (Limitation_ID)
);

Create Table Limitation_Category (
    Limitation_Category_ID VARCHAR(25),
    Description TEXT,
    PRIMARY KEY (Limitation_Category_ID)
);

Create Table Resource_Provider (
    Resource_ID INT AUTO_INCREMENT NOT NULL,
    Name VARCHAR(50) NOT NULL,
    Phone VARCHAR(15) NOT NULL,
    Address_One VARCHAR(50) NOT NULL,
    Address_Two VARCHAR(50),
    City VARCHAR(50) NOT NULL,
    Territory VARCHAR(50) NOT NULL,
    Country VARCHAR(50) NOT NULL,
    Postal_Code VARCHAR(10) NOT NULL,
    Description TEXT NOT NULL,
    PRIMARY KEY(Resource_ID)
);

Create Table Resource_Limitation (
    Resource_ID INT NOT NULL,
    Limitation_ID INT NOT NULL,
    PRIMARY KEY(Resource_ID, Limitation_ID)
);

Create Table Resource_Category (
    Resource_Category_ID VARCHAR(25) NOT NULL,
    Description TEXT NOT NULL,
    PRIMARY KEY (Resource_Category_ID)
);

Create Table Resource_Category_Hierarchy (
    Super_Category VARCHAR(25) NOT NULL,
    Sub_Category VARCHAR(25) NOT NULL,
    PRIMARY KEY(Super_Category,Sub_Category)
);

Create Table Resource_Category_Resource (
    Resource_Category_ID VARCHAR(25),
    Resource_ID INT NOT NULL,
    PRIMARY KEY(Resource_Category_ID, Resource_ID)
);

Create Table Role (
    Role_ID VARCHAR(20) NOT NULL,
    Description TEXT NOT NULL,
    PRIMARY KEY(Role_ID)
);

Create Table App_User (
    User_ID INT AUTO_INCREMENT NOT NULL,
    Password_Hash CHAR(64) NOT NULL,
    Password_Salt CHAR(64) NOT NULL,
    First_Name VARCHAR(200) NOT NULL,
    Last_Name VARCHAR(200) NOT NULL,
    Phone VARCHAR(20) NOT NULL,
    Address_One VARCHAR(100) NOT NULL,
    Address_Two VARCHAR(100),
    City VARCHAR(100) NOT NULL,
    Territory VARCHAR(50) NOT NULL,
    Zip VARCHAR(10) NOT NULL,
    PRIMARY KEY(User_ID)
);

Create Table User_Role (
    User_ID INT NOT NULL,
    Role_ID VARCHAR(20) NOT NULL,
    Start_Date DATETIME NOT NULL,
    End_Date DATETIME,
    PRIMARY KEY (User_ID, Role_ID, Start_Date)
);

ALTER TABLE Call_Record
ADD FOREIGN KEY Call_Record_Counselor_ID(Counselor_ID)
REFERENCES App_User(User_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Call_Record
ADD FOREIGN KEY Call_Record_Call_Type_ID(Call_Type_ID)
REFERENCES Call_Type(Call_Type_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Call_Record
ADD FOREIGN KEY Call_Record_Caller_ID(Caller_ID)
REFERENCES Caller(Caller_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Call_Record_Resource
ADD FOREIGN KEY Call_Record_Resource_Call_Record_ID(Call_Record_ID)
REFERENCES Call_Record(Call_Record_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Call_Record_Resource
ADD FOREIGN KEY Call_Record_Resource_Resource_ID(Resource_ID)
REFERENCES Resource_Provider(Resource_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Limitation
ADD FOREIGN KEY Limitation_Limitation_Category_ID(Limitation_Category_ID)
REFERENCES Limitation_Category(Limitation_Category_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Resource_Limitation
ADD FOREIGN KEY Resource_Limitation_Resource_ID(Resource_ID)
REFERENCES Resource_Provider(Resource_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Resource_Limitation
ADD FOREIGN KEY Resource_Limitation_Limitation_ID(Limitation_ID)
REFERENCES Limitation(Limitation_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Resource_Category_Hierarchy
ADD FOREIGN KEY Resource_Category_Hierarchy_Super_Category(Super_Category)
REFERENCES Resource_Category(Resource_Category_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Resource_Category_Hierarchy
ADD FOREIGN KEY Resource_Category_Hierarchy_Sub_Category(Sub_Category)
REFERENCES Resource_Category(Resource_Category_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Resource_Category_Resource
ADD FOREIGN KEY Resource_Category_Resource_Resource_Category_ID(Resource_Category_ID)
REFERENCES Resource_Category(Resource_Category_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Resource_Category_Resource
ADD FOREIGN KEY Resource_Category_Resource_Resource_ID(Resource_ID)
REFERENCES Resource_Provider(Resource_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE User_Role
ADD FOREIGN KEY User_Role_User_ID(User_ID)
REFERENCES App_User(User_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE User_Role
ADD FOREIGN KEY User_Role_Role_ID(Role_ID)
REFERENCES Role(Role_ID)
ON DELETE CASCADE
ON UPDATE CASCADE;
