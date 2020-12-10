CREATE DATABASE IF NOT EXISTS ostap_koziaryk_itunes;
USE ostap_koziaryk_itunes;

DROP TABLE IF EXISTS ostap_koziaryk_itunes.record_label_album;
DROP TABLE IF EXISTS ostap_koziaryk_itunes.song;
DROP TABLE IF EXISTS ostap_koziaryk_itunes.album;
DROP TABLE IF EXISTS ostap_koziaryk_itunes.artist;
DROP TABLE IF EXISTS ostap_koziaryk_itunes.genre;
DROP TABLE IF EXISTS ostap_koziaryk_itunes.record_label;
DROP TABLE IF EXISTS ostap_koziaryk_itunes.music_video;

CREATE TABLE ostap_koziaryk_itunes.artist
(
    id           INT          NOT NULL AUTO_INCREMENT,
    title        VARCHAR(100) NOT NULL,
    total_songs  INT UNSIGNED NOT NULL,
    total_albums INT UNSIGNED NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes.album
(
    id           INT           NOT NULL AUTO_INCREMENT,
    title        VARCHAR(500)  NOT NULL,
    artist_id    INT           NOT NULL,
    release_date DATE          NOT NULL,
    price        DECIMAL(8, 2) NOT NULL,
    genre_id     INT           NULL,
    total_items  INT UNSIGNED  NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes.song
(
    id                          INT               NOT NULL AUTO_INCREMENT,
    title                       VARCHAR(45)       NOT NULL,
    artist_id                   INT               NOT NULL,
    album_id                    INT               NOT NULL,
    price                       DECIMAL(8, 2)     NOT NULL,
    duration_in_minutes         FLOAT             NOT NULL,
    release_date                DATE              NOT NULL,
    genre_id                    INT               NOT NULL,
    popularity                  INT(100) UNSIGNED NULL,
    with_parental_advisory_logo TINYINT           NULL,
    music_video_id              INT               NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes.genre
(
    id          INT          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(30)  NOT NULL UNIQUE,
    description VARCHAR(250) NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes.record_label
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes.music_video
(
    id                INT    NOT NULL AUTO_INCREMENT,
    size_in_megabytes DOUBLE NOT NULL,
    release_date      DATE   NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes.record_label_album
(
    record_label_id INT NOT NULL,
    album_id        INT NOT NULL,
    PRIMARY KEY (record_label_id, album_id)
) ENGINE = InnoDB;

ALTER TABLE ostap_koziaryk_itunes.album
    ADD CONSTRAINT FK_album_artist
        FOREIGN KEY (artist_id)
            REFERENCES ostap_koziaryk_itunes.artist (id),

    ADD CONSTRAINT FK_album_genre
        FOREIGN KEY (genre_id)
            REFERENCES ostap_koziaryk_itunes.genre (id);

ALTER TABLE ostap_koziaryk_itunes.song
    ADD CONSTRAINT FK_song_artist
        FOREIGN KEY (artist_id)
            REFERENCES ostap_koziaryk_itunes.artist (id),

    ADD CONSTRAINT FK_song_album
        FOREIGN KEY (album_id)
            REFERENCES ostap_koziaryk_itunes.album (id),

    ADD CONSTRAINT FK_song_genre
        FOREIGN KEY (genre_id)
            REFERENCES ostap_koziaryk_itunes.genre (id),

    ADD CONSTRAINT FK_song_music_video
        FOREIGN KEY (music_video_id)
            REFERENCES ostap_koziaryk_itunes.music_video (id);

ALTER TABLE ostap_koziaryk_itunes.record_label_album
    ADD CONSTRAINT FK_record_label_album_record_label
        FOREIGN KEY (record_label_id)
            REFERENCES ostap_koziaryk_itunes.record_label (id),

    ADD CONSTRAINT FK_record_label_album_album
        FOREIGN KEY (album_id)
            REFERENCES ostap_koziaryk_itunes.album (id);

INSERT INTO ostap_koziaryk_itunes.artist (title, total_songs, total_albums)
VALUES ('Queen', '150', '15'),
       ('Twenty One Pilots', '87', '6'),
       ('Imagine Dragons', '102', '8'),
       ('The Score', '88', '5'),
       ('PALC', '32', '3'),
       ('One Republic', '31', '4'),
       ('Республика Полина', '11', '1'),
       ('The Weekend', '43', '5'),
       ('Maroon 5', '122', '9'),
       ('The Man Who', '89', '7');

INSERT INTO ostap_koziaryk_itunes.genre (name, description)
VALUES ('rock', NULL),
       ('hip_hop', NULL),
       ('jazz', NULL),
       ('pop', NULL),
       ('blues', NULL),
       ('country', NULL),
       ('classical', NULL),
       ('electronic', NULL),
       ('disco', NULL),
       ('punk_rock', NULL);

INSERT INTO ostap_koziaryk_itunes.album (title, artist_id, release_date, price, genre_id, total_items)
VALUES ('Bohemian Rhapsody', '1', '2018-10-19', '4.99', '1', '22'),
       ('Trench', '2', '2018-10-05', '5.99', NULL, '14'),
       ('Evolve', '3', '2017-06-23', '5.88', NULL, '12'),
       ('Night Visions', '3', '2012-09-04', '9.99', NULL, '15'),
       ('ATLAS', '4', '2017-10-13', '4.99', NULL, '12'),
       ('Пагубное Влияние', '5', '2019-12-20', '1.99', NULL, '4'),
       ('Human', '6', '2020-12-01', '5.99', NULL, '16'),
       ('Бяскоци Красавік', '7', '2015-05-15', '10.89', NULL, '11'),
       ('After Hours', '8', '2020-05-20', '4.99', NULL, '14'),
       ('Starboy', '8', '2016-11-05', '6.99', NULL, '18');

INSERT INTO ostap_koziaryk_itunes.music_video(size_in_megabytes, release_date)
VALUES ('39.6', '1985-07-13'),
       ('50.4', '1987-10-17');

INSERT INTO ostap_koziaryk_itunes.song (title, artist_id, album_id, price, duration_in_minutes, release_date, genre_id,
                                        popularity, with_parental_advisory_logo, music_video_id)
VALUES ('Bohemian Rhapsody', '1', '1', '0.69', '5.54', '2018-10-19', '1', '100', '0', '1'),
       ('Somebody To Love', '1', '1', '0.69', '4.55', '2018-10-19', '1', '70', '1', '2'),
       ('Jumsuit', '2', '2', '0.69', '3.58', '2018-09-28', '4', '100', '1', NULL),
       ('My Blood', '2', '2', '0.69', '3.49', '2018-09-28', '4', '80', '1', NULL),
       ('Nico And The Niners', '2', '2', '0.69', '3.45', '2018-09-28', '4', '90', '1', NULL),
       ('Thunder', '3', '3', '0.49', '03.07', '2017-06-23', '4', '100', '0', NULL),
       ('Yesterday', '3', '3', '0.49', '3.25', '2017-06-23', '4', '70', '1', NULL),
       ('Radioactive', '3', '4', '0.69', '03.06', '2012-09-04', '4', '95', '0', NULL),
       ('Demons', '3', '4', '0.69', '2.57', '2012-09-04', '4', '70', '1', NULL),
       ('Legend', '4', '5', '0.49', '03.09', '2017-10-13', '4', '85', '0', NULL);

INSERT INTO ostap_koziaryk_itunes.record_label(name)
VALUES ('KIDinaKorner'),
       ('Interscope Records'),
       ('Republic records'),
       ('A division of UMG Recordings'),
       ('Sony Music'),
       ('A Warner Music Group Company'),
       ('Yoola'),
       ('Mosley Music'),
       ('The Weekend XO'),
       ('Virgin Records');

INSERT INTO ostap_koziaryk_itunes.record_label_album (record_label_id, album_id)
VALUES ('1', '3'),
       ('1', '1'),
       ('9', '8'),
       ('6', '5'),
       ('2', '7'),
       ('5', '6'),
       ('5', '3');


CREATE INDEX dateIndex
    ON song (release_date);

CREATE INDEX nameIndex
    ON record_label (name);