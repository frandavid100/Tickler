DROP TABLE Tickler_Contexts; 
DROP TABLE Tickler_Tasks;
DROP TABLE Tickler_Activetimes;
DROP TABLE Tickler_Activeregions;
DROP TABLE Tickler_ContextsTasks;
DROP TABLE Tickler_ContextsActivetimes;
DROP TABLE Tickler_ContextsActiveregions;
DROP TABLE Tickler_Aux_Units;

CREATE TABLE IF NOT EXISTS Tickler_Contexts
	(ID INTEGER PRIMARY KEY AUTOINCREMENT, 
	name VARCHAR NOT NULL, 
	icon INT(3) NOT NULL DEFAULT 1,
	notifications INT(1) NOT NULL DEFAULT 0);
	
CREATE TABLE IF NOT EXISTS Tickler_Tasks
	(ID INTEGER PRIMARY KEY AUTOINCREMENT, 
	name VARCHAR NOT NULL, 
	priority INT(1) NOT NULL DEFAULT 2,
	note VARCHAR,
	dt_creation NOT NULL DEFAULT CURRENT_TIMESTAMP,
	dt_start,
	dt_deadline,
	dt_completed,
	dt_abandoned,
	dt_begin_work,
	dt_end_work,
	mins_working INT(5),
	someday INT(1) NOT NULL DEFAULT 0,
	repeat INT(1) DEFAULT 0,
	rp_unit_id INT(2) DEFAULT 2,
	rp_from INT(1) DEFAULT 0,
	rp_parent INT(5),
	simultaneus INT(1) DEFAULT 0);
	
CREATE TABLE IF NOT EXISTS Tickler_Activetimes
	(ID INTEGER PRIMARY KEY AUTOINCREMENT, 
	name VARCHAR NOT NULL, 
	start NOT NULL,
	end NOT NULL,
	day VARCHAR NOT NULL DEFAULT "1234567");
 
CREATE TABLE Tickler_Activeregions
	(ID INTEGER PRIMARY KEY AUTOINCREMENT, 
	name VARCHAR NOT NULL, 
	location_long REAL,
	location_lat REAL,  
	radius_km REAL DEFAULT 0.5);
 
CREATE TABLE IF NOT EXISTS Tickler_ContextsTasks
	(ID INTEGER PRIMARY KEY AUTOINCREMENT, 
	context_id INT(5), 
	task_id INT(5));
	
CREATE TABLE IF NOT EXISTS Tickler_ContextsActivetimes
	(ID INTEGER PRIMARY KEY AUTOINCREMENT, 
	context_id INT(5), 
	activetime_id INT(5),
	enabled INT(1) DEFAULT 1);

CREATE TABLE IF NOT EXISTS Tickler_ContextsActiveregions
	(ID INTEGER PRIMARY KEY AUTOINCREMENT, 
	context_id INT(5), 
	activeregion_id INT(5),
	enabled INT(1) DEFAULT 1);	
	
CREATE TABLE IF NOT EXISTS Tickler_Aux_Units
	(ID INTEGER PRIMARY KEY AUTOINCREMENT,
	description VARCHAR NOT NULL);
	
	
