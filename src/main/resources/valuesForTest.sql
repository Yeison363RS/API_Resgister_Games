SET FOREIGN_KEY_CHECKS=0;
DELETE FROM times_game;
DELETE FROM players ;
DELETE FROM games ;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO players (id_player,
                     birthdate, email,
                     gender, last_name,name,username)
VALUES
    (1, '2012-11-22 19:00:00.000000', 'fabian@hotmail.com', 'Masculino','Perez', 'Fabian', 'FabPerez'),
    (2, '2012-11-22 19:00:00.000000', 'fabio@hotmail.com', 'Masculino','londoño', 'fabio', 'londoFab'),
    (3, '2011-11-22 19:00:00.000000', 'fabricio@hotmail.com', 'Masculino','mendez', 'Fabricio', 'Perfarez'),
    (4, '2016-11-22 19:00:00.000000', 'Luis@hotmail.com', 'Masculino','gonzalez', 'Gonzalez', 'GnazalUis'),
    (5, '2016-11-22 19:00:00.000000', 'ana@hotmail.com', 'Femenino','lopez', 'ana', 'anaeLop'),
    (6, '2013-11-22 19:00:00.000000', 'alexander@hotmail.com', 'Masculino','mendez', 'alexander', 'alexanderez'),
    (7, '2016-11-22 19:00:00.000000', 'juliana@hotmail.com', 'Femenino','lopez', 'ana', 'julianaL');


INSERT INTO games (id_game,name, date_release,
                   description, quatification)
VALUES
    (1,"Counter-Strike", '2018-11-22 19:00:00.000000', 'Game of Shoots', 5),
    (2,"Half-Life",'2014-11-22 19:00:00.000000', 'Game of Shoots', 7),
    (3,"Left Dead 2", '2010-11-22 19:00:00.000000', 'Game of Zoombies', 8);

INSERT INTO times_game (id_time_game,id_game, id_player,
                        number_hours)
VALUES
    (1,3,2, 5),
    (2,2,1, 7),
    (3,2,1, 8),
    (4,1,2, 5),
    (5,2,3, 7),
    (6,3,1, 8),
    (7,1,2, 5),
    (8,3,4, 5),
    (10,1,5, 8),
    (11,1,6, 15),
    (12,1,6, 3),
    (14,1,1, 1),
    (13,1,4, 20);

COMMIT;