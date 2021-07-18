Create Database ProjectDB;

Use ProjectDB;




/* ////////////////////////////////////////////////////////////////////////////////////////////// */




Drop Table UserLog
Truncate Table UserLog

CREATE TABLE UserLog(
UserID varchar (50) NOT NULL PRIMARY KEY,
Email varchar (50) NOT NULL,
FirstName varchar (50) NOT NULL,
LastName varchar (50) NOT NULL,
Passkey varchar (50) NOT NULL,
DOB varchar (50) NOT NULL,
Gender varchar (50) NOT NULL,
ContactNo varchar (50) NOT NULL Unique,
CountryName varchar (50) NOT NULL 
);

INSERT INTO UserLog VALUES 
('180104000', 'usertest1@gmail.com' ,'Johnson'  , 'Beecher'  , '123', '12-06-1996' , 'Male'   , '0193456789' , 'Bangladesh'),
('180104001', 'usertest2@gmail.com' ,'Karimul'  , 'Kabir'    , '312', '04-08-1996' , 'Male'   , '0176543210' , 'Bangladesh'),
('180104002', 'usertest3@gmail.com' ,'Hasibur'  , 'Rahman'   , '121', '24-04-1990' , 'Male'   , '4147841567' , 'India'),
('180104003', 'usertest4@gmail.com' ,'Afsana'   , 'Islam'    , '743', '16-12-1982' , 'Female' , '7897418637' , 'Abu Dhabi');

INSERT INTO UserLog VALUES 
('180104004', 'usertest5@gmail.com' ,'Justin'   , 'Row'		, '549', '19-07-1988' , 'Male'  , '41475244156' , 'England'),
('180104005', 'usertest6@gmail.com' ,'Ethan'    , 'Hunt'	, '807', '03-09-1995' , 'Male'	, '7850718637'  , 'USA');

Select * FROM UserLog


Select GETDATE()




/* ////////////////////////////////////////////////////////////////////////////////////////////// */







Drop Table Employee
Truncate Table Employee

CREATE TABLE Employee(
EmployeeID varchar (50) NOT NULL PRIMARY KEY,
Email varchar (50) NOT NULL,
Name varchar (50) NOT NULL,
Age INT NOT NULL Check (Age>18),
Contact varchar (50) NOT NULL,
Designation varchar (50) NOT NULL 
);

INSERT INTO Employee VALUES 
('E1' , 'emptest1@gmail.com'   ,'Rofiqul Islam'   , 35, '0161241573' , 'Pilot'		),
('E2' , 'emptest2@gmail.com'   ,'Nafisul Alim'    , 31, '0174574878' , 'Pilot'		),
('E3' , 'emptest3@gmail.com'   ,'Tarek Hasan'     , 29, '0197257274' , 'Pilot'		),
('E4' , 'emptest4@gmail.com'   ,'Samiur Rahman'   , 43, '0185727232' , 'Pilot'		),
('E5' , 'emptest5@gmail.com'   ,'Shofik Islam'    , 33, '0161241573' , 'Cabin Crew'	),
('E6' , 'emptest6@gmail.com'   ,'Faisal Alim'     , 40, '0174574878' , 'Cabin Crew'	),
('E7' , 'emptest7@gmail.com'   ,'Zia Uddin'       , 28, '0197257274' , 'Cabin Crew'	),
('E8' , 'emptest8@gmail.com'   ,'Karim Rahman'    , 38, '0185727232' , 'Cabin Crew' ),
('E9' , 'emptest9@gmail.com'   ,'Aziz Uddin'      , 27, '0197257274' , 'Cabin Crew'	),
('E10', 'emptest10@gmail.com'  ,'Rafik Rahman'    , 38, '0185727232' , 'Cabin Crew' ),
('E11', 'emptest11@gmail.com'  ,'ABC Uddin'       , 26, '0197257274' , 'Cabin Crew'	),
('E12', 'emptest12@gmail.com'  ,'Fajar Rahman'    , 38, '0185727232' , 'Cabin Crew' ),
('E13', 'emptest13@gmail.com'  ,'Taran Hasan'     , 36, '0197257274' , 'Pilot'		),
('E14', 'emptest14@gmail.com'  ,'Wasife Rahman'   , 43, '0185727232' , 'Pilot'		),
('E15', 'emptest15@gmail.com'  ,'Johny Rahman'    , 38, '0185727232' , 'Cabin Crew' ),
('E16', 'emptest16@gmail.com'  ,'Dutch Uddin'     , 26, '0197257274' , 'Cabin Crew'	),
('E17', 'emptest17@gmail.com'  ,'Abir Rahman'     , 38, '0185727232' , 'Cabin Crew' ),
('E18', 'emptest18@gmail.com'  ,'Maruk Hasan'     , 36, '0197257274' , 'Pilot'		),
('E19', 'emptest19@gmail.com'  ,'Musa Habib'      , 43, '0185727232' , 'Pilot'		),
('E20', 'emptest20@gmail.com'  ,'Fedrick Paul'    , 30, '0197257274' , 'Cabin Crew'	),
('E21', 'emptest21@gmail.com'  ,'Delta Sammy'     , 38, '0185727232' , 'Cabin Crew' );



SELECT * FROM Employee Where Designation = 'Pilot'

Select * From Employee Where CONCAT(EmployeeID, Email, Name, Age, Contact, Designation) LIKE '%y%'

Select * From Employee Inner Join FlightInfo On ( FlightInfo.Pilot1 = Employee.EmployeeID )

Select * From Employee Inner Join FlightInfo On ( FlightInfo.Pilot2 = Employee.EmployeeID )

Select * From Employee Inner Join FlightInfo On ( FlightInfo.CabinCrew1 = Employee.EmployeeID )

Select * From Employee Inner Join FlightInfo On ( FlightInfo.CabinCrew2 = Employee.EmployeeID )

Select * From Employee Inner Join FlightInfo On ( FlightInfo.CabinCrew3 = Employee.EmployeeID )


/* ////////////////////////////////////////////////////////////////////////////////////////////// */




Drop Table FlightInfo
Truncate Table FlightInfo


CREATE TABLE FlightInfo(
FlightID varchar (50) NOT NULL PRIMARY KEY,
From1 varchar (50) NOT NULL,
To1 varchar (50) NOT NULL,
DepartureDate  smalldatetime NOT NULL,
ArrivalDate  smalldatetime NOT NULL,
ClassType varchar (50) NOT NULL,
Pilot1 varchar(50) NULL Foreign Key references Employee (EmployeeID),
Pilot2 varchar(50) NULL Foreign Key references Employee (EmployeeID),
CabinCrew1 varchar(50) NULL Foreign Key references Employee (EmployeeID),
CabinCrew2 varchar(50) NULL Foreign Key references Employee (EmployeeID),
CabinCrew3 varchar(50) NULL Foreign Key references Employee (EmployeeID),
Fare INT  NOT NULL
);


INSERT INTO FlightInfo VALUES 
('F1', 'Dhaka'   ,'New York'  , '2021-05-20 11:45:00', '2021-05-21 17:30:00', 'Economic' , 'E1','E2','E5','E6','E7', 45000),
('F2', 'Dhaka'   ,'Bangkok'   , '2021-05-21 12:30:00', '2021-05-21 16:00:00', 'Business' , 'E3','E4','E8','E9','E10', 160000),
('F3', 'Dhaka'   ,'London'    , '2021-05-15 01:00:00', '2021-05-16 06:00:00', 'Business' , 'E13','E14','E11','E12','E15', 370000),
('F4', 'Bangkok' ,'Dhaka'     , '2021-05-26 09:30:00', '2021-05-26 13:00:00', 'Economic' , 'E18','E19','E16','E17','E20', 25000);

INSERT INTO FlightInfo VALUES 
('F7', 'Chattogram'   ,'Dhaka'  , '2021-03-21 11:45:00', '2021-03-21 12:15:00', 'Economic' , 'E1','E2','E5','E6','E7', 45600 ,40 , '');

ALTER Table FlightInfo ADD AvailableSeats INT NULL

ALTER Table FlightInfo ADD BookedSeats varchar(100) NULL

ALTER Table FlightInfo DROP COLUMN SeatNumbers




SELECT * FROM FlightInfo

Select * From FlightInfo Left Join AircraftInfo On FlightInfo.FlightID = AircraftInfo.FlightID Where CONCAT(FlightInfo.FlightID, From1, To1, DepartureDate, ArrivalDate, ClassType, Fare, AC_ID, Capacity) LIKE '%y%'

Select * From FlightInfo Inner Join AircraftInfo On FlightInfo.FlightID = AircraftInfo.FlightID Where FlightInfo.FlightID = 'F2'

Select * From FlightInfo Inner Join PassengerFlight On FlightInfo.FlightID = PassengerFlight.FlightID Inner Join UserLog On PassengerFlight.UserID = UserLog.UserID Where FlightInfo.FlightID = 'F1'



DELETE FROM FlightInfo Where To1='F6'


Update FlightInfo Set Pilot1='E1' , Pilot2='E2', CabinCrew1='E5', CabinCrew2='E6', CabinCrew3='E7' Where FlightID = 'F1'
Update FlightInfo Set Pilot1='E3' , Pilot2='E4', CabinCrew1='E8', CabinCrew2='E9', CabinCrew3='E0' Where FlightID = 'F2'



Update FlightInfo Set  AvailableSeats = 40 Where FlightID = 'F1'
Update FlightInfo Set  AvailableSeats = 40 Where FlightID = 'F2'
Update FlightInfo Set  AvailableSeats = 40 Where FlightID = 'F3'
Update FlightInfo Set  AvailableSeats = 40 Where FlightID = 'F4'
Update FlightInfo Set  AvailableSeats = 40 Where FlightID = 'F5'


/* ////////////////////////////////////////////////////////////////////////////////////////////// */





Drop Table AircraftInfo
Truncate Table AircraftInfo

CREATE TABLE AircraftInfo(
AC_ID varchar (50) NOT NULL PRIMARY KEY,
AC_Name varchar (50) NOT NULL,
FlightID varchar (50) NOT NULL Foreign Key references FlightInfo (FlightID),
Capacity Int NOT NULL
);

INSERT INTO AircraftInfo VALUES 
( 'AC1' , 'Boeing 737-Max' ,  'F1' , 40),
( 'AC2' , 'Boeing 747-8'   ,  'F2' , 40),
( 'AC3' , 'Airbus A318'    ,  'F3' , 40),
( 'AC4' , 'Dash 8-400'     ,  'F4' , 40);

SELECT * FROM AircraftInfo

DELETE FROM AircraftInfo Where AC_ID='AC6'

Select * From FlightInfo Inner Join AircraftInfo On FlightInfo.FlightID = AircraftInfo.FlightID




 
/* ////////////////////////////////////////////////////////////////////////////////////////////// */





Drop Table PassengerFlight
Truncate Table PassengerFlight

CREATE TABLE PassengerFlight(
FlightID varchar(50) NOT NULL Foreign Key references FlightInfo (FlightID), 
UserID varchar (50) NOT NULL Foreign Key references Userlog (UserID),
NoOfSeats INT NULL,
);

INSERT INTO PassengerFlight VALUES 
('F1', '180104003', 2 , 'A1A2');

SELECT * FROM PassengerFlight

Delete From PassengerFlight Where UserID = '180104003'

ALTER Table PassengerFlight ADD SeatNumbers varchar(100) NULL




/* ////////////////////////////////////////////////////////////////////////////////////////////// */



Drop Table CustomerSupport
Truncate Table CustomerSupport

CREATE TABLE CustomerSupport(
UserID varchar (50) NOT NULL Foreign Key references UserLog (UserID),
UserAdminConvo varchar (5000) NOT NULL
);


INSERT INTO CustomerSupport VALUES 
( '180104003' , '~I have lost my ticket. What can I do now?<Please call helpdesk.'),
( '180104001' , '~I want to book another ticket of the same flight.'),
( '180104002' , '~When the F7 Flight will be available?');

SELECT * FROM CustomerSupport

Update CustomerSupport Set UserAdminConvo = '~When the F7 Flight will be available?<It will be available in May, 2021.<Till then stay tuned!~Ok, Thank you!' Where UserID = '180104002'


/* ////////////////////////////////////////////////////////////////////////////////////////////// */





Drop Table Feedback
Truncate Table Feedback

CREATE TABLE Feedback(
UserID varchar (50)  NOT NULL Foreign Key references UserLog (UserID),
FlightID varchar (50) NOT NULL Foreign Key references FlightInfo (FlightID),
Feedback varchar (5000) NOT NULL 
);

INSERT INTO Feedback VALUES 
( '180104003' , 'F1' , 'Bad service form Dhaka flight.'),
( '180104002' , 'F2' , 'Nice collaboration.'),
( '180104000' , 'F3' , 'Service was good.'),
( '180104001' , 'F4' , 'Flight took off timely. Good.');

SELECT * FROM Feedback





/* ////////////////////////////////////////////////////////////////////////////////////////////// */





Drop Table Membership
Truncate Table Membership

CREATE TABLE Membership(
UserID varchar (50)  NOT NULL Foreign Key references UserLog (UserID),
MemberStatus varchar (50) NOT NULL,
Point INT NOT NULL
);

INSERT INTO Membership VALUES 
( '180104003' , 'Gold'    , 427),
( '180104002' , 'Diamond' , 297),
( '180104000' , 'Silver'  , 331),
( '180104001' , 'Bronze'  , 124);

SELECT * FROM Membership





/* ////////////////////////////////////////////////////////////////////////////////////////////// */