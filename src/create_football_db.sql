create database football;
use football;

CREATE TABLE player (
	p_id smallint NOT NULL AUTO_INCREMENT,
	fname varchar(255) NOT NULL,
	lname varchar(255) NOT NULL,
	club_id smallint,
	market_value double NOT NULL,
	position varchar(3) NOT NULL,
	national_team varchar(30) NOT NULL,
	PRIMARY KEY (p_id)
);

CREATE TABLE club (
	club_id smallint NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL UNIQUE,
	league_id smallint NOT NULL,
	PRIMARY KEY (club_id)
);

CREATE TABLE league (
	league_id smallint NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	country varchar(255) NOT NULL,
	PRIMARY KEY (league_id)
);

CREATE TABLE player_stats (
	p_id smallint NOT NULL,
	goal smallint NOT NULL,
	assist smallint NOT NULL,
	yellow_card smallint NOT NULL,
	red_card smallint NOT NULL,
	PRIMARY KEY (p_id)
);

ALTER TABLE player ADD CONSTRAINT player_fk0 FOREIGN KEY (club_id) REFERENCES club(club_id) on delete set null;

ALTER TABLE club ADD CONSTRAINT club_fk0 FOREIGN KEY (league_id) REFERENCES league(league_id);

ALTER TABLE player_stats ADD CONSTRAINT player_stats_fk0 FOREIGN KEY (p_id) REFERENCES player(p_id) on delete cascade;

insert into league(name, country) values
('La Liga', 'Spain'),
('Premier League', 'England'),
('Bundesliga', 'Germany'),
('Serie A', 'Italy');

insert into club(name, league_id) values
('Real Madrid', 1),
('FC Barcelona', 1),
('Atletico Madrid', 1),
('Liverpool', 2),
('Manchester United', 2),
('Bayern Munich', 3),
('Borrusia Dortmund', 3),
('AC Milan', 4),
('Juventus', 4);

insert into player(fname, lname, club_id, market_value, position, national_team) values 
('Cristiano', 'Ronaldo', 1, 200, 'LW', 'Portugal'),
('Luka', 'Modric', 1, 115, 'CM', 'Croatia'),
('Sergio', 'Ramos', 1, 65, 'CB', 'Spain'),
('Lionel', 'Messi', 2, 210, 'RW', 'Argentina'),
('Gerard', 'Pique', 2, 50, 'CB', 'Spain'),
('Luis', 'Suarez', 2, 95, 'ST', 'Uruguay'),
('Antoine', 'Griezzman', 3, 100, 'RW', 'France'),
('Jan', 'Oblak', 3, 40, 'GK', 'Slovenia'),
('Sadio', 'Mane', 4, 60, 'ST', 'Senegal'),
('Muhamed', 'Salah', 4, 80, 'LW', 'Egypt'),
('Harry', 'Maguire', 5, 20, 'CB', 'England'),
('Paul', 'Pogba', 5, 70, 'CAM', 'France'),
('Thomas', 'Muller', 6, 60, 'CM', 'Germany'),
('Robert', 'Lewandowski', 6, 100, 'ST', 'Poland'),
('Erling', 'Halaand', 7, 175, 'ST', 'Norway'),
('Paulo', 'Maldini', 8, 30, 'CB', 'Italy'),
('Olivier', 'Giroud', 8, 34, 'ST', 'France'),
('Paulo', 'Dybala', 9, 50, 'CM', 'Argentina');

insert into player_stats() values
(1, 41, 23, 1, 0),
(2, 3, 17, 2, 0),
(3, 4, 1, 5, 2),
(4, 41, 34, 0, 0),
(5, 0, 0, 7, 1),
(6, 24, 9, 1, 0),
(7, 16, 4, 0, 0),
(8, 0, 0, 0, 0),
(9, 22, 13, 4, 0),
(10, 23, 7, 0, 0),
(11, 0, 0, 6, 1),
(12, 6, 10, 3, 0),
(13, 8, 19, 2, 0),
(14, 29, 3, 0, 0),
(15, 28, 6, 4, 0),
(16, 0, 0, 1, 0),
(17, 12, 3, 0, 0),
(18, 16, 7, 3, 0);