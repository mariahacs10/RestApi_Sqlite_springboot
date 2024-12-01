CREATE TABLE hte_anime_convention_art (
    rn_ BIGSERIAL PRIMARY KEY,
    anime_convention_id BIGINT,
    hib_sess_id VARCHAR(36) NOT NULL,
    anime_convention_description VARCHAR(255),
    anime_convention_image_url VARCHAR(255)
);

CREATE TABLE hte_artwork (
    rn_ BIGSERIAL PRIMARY KEY,
    art_work_id BIGINT,
    hib_sess_id VARCHAR(36) NOT NULL,
    author_image_url VARCHAR(255)
);

CREATE TABLE hte_app_user (
    rn_ BIGSERIAL PRIMARY KEY,
    id BIGINT,
    hib_sess_id VARCHAR(36) NOT NULL,
    password VARCHAR(255),
    role VARCHAR(255),
    username VARCHAR(255)
);

CREATE SEQUENCE anime_convention_art_seq START WITH 1351;
CREATE TABLE anime_convention_art (
    anime_convention_id BIGSERIAL PRIMARY KEY,
    anime_convention_description VARCHAR(255),
    anime_convention_image_url VARCHAR(255)
);

INSERT INTO anime_convention_art (anime_convention_description, anime_convention_image_url)
VALUES
('aaron goofy pic', 'https://i.ibb.co/F89g6pB/animephoto1.jpg'),
('Group Photo 1', 'https://i.ibb.co/Ln6621f/animephoto2.jpg'),
('Photo With Mariah and Erika', 'https://i.ibb.co/LJzwYrF/animephoto3.jpg'),
('Gojo Pic with Erika', 'https://i.ibb.co/mc8VvqM/animephoto4.jpg'),
('Mahito Pic with Erika Pose 1', 'https://i.ibb.co/mJKHkCd/animephoto5.jpg'),
('Mahito Pic with Erika Pose 2', 'https://i.ibb.co/FX0nmnc/animephoto6.jpg'),
('Gojo Solo Pic', 'https://i.ibb.co/XWh3Syy/animephoto7.jpg'),
('Toji picture pose 1', 'https://i.ibb.co/Cswm6BG/animephoto8.jpg'),
('Riko Picture pose 1', 'https://i.ibb.co/mFKcyRn/animephoto9.jpg'),
('Riko Picture pose 2', 'https://i.ibb.co/Nn3bGZx/animephoto10.jpg'),
('Toji picture pose 2', 'https://i.ibb.co/stz5vwz/animephoto11.jpg'),
('Nanimi picture with Erika', 'https://i.ibb.co/YPgvLqS/animephoto12.jpg'),
('Inumaki picture with Mariah/Me pose 1', 'https://i.ibb.co/JxrGwsY/animephoto13.jpg'),
('Inumaki picture with Mariah/Me pose 2', 'https://i.ibb.co/XYPPCxL/animephoto14.jpg'),
('Mommy and Erika picture', 'https://i.ibb.co/5rKp2bb/animephoto15.jpg'),
('Uraume picture with Mariah and Erika', 'https://i.ibb.co/XxMyc2f/animephoto16.jpg'),
('Choso, Toji group photo', 'https://i.ibb.co/4Jw3WmQ/animephoto17.jpg'),
('Geto and Geto photo :)', 'https://i.ibb.co/JKfBzRY/animephoto18.jpg'),
('Group Photo 2', 'https://i.ibb.co/y8S82w1/animephoto19.jpg'),
('Random person picture, Mariah and Erika', 'https://i.ibb.co/Q62byf8/animephoto20.jpg');

CREATE SEQUENCE app_user_seq START WITH 1601;
CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    google_id VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    password_reset_token VARCHAR(255),
    password_reset_token_expiration DATE
);

INSERT INTO app_user (password, role, username, first_name, google_id, email)
VALUES
('$2a$10$hCot7aYtAn9fIxoXWXFRpuWUOyx.TerVx7l8KznewoiVVdR7If60O', 'USER', 'jjkleaks', '', '', 'rofran32143@gmail.com'),
('$2a$10$k2Lwh8a1NS08lfQ7DSQaAuMeeFlbxpKtkzvbAAEYKm/o9Y5psweLW', 'USER', 'springboot', '', '', 'yuyusoamazing@gmail.com'),
('$2a$10$zm/ziSXffuLcZetkmPQK3e.Fikt.UlOyI17iNclnBUOq64Izp2z2W', 'USER', 'poopyButt', '', '', 'qwe12@gmail.com');

CREATE TABLE artwork (
    art_work_id BIGSERIAL PRIMARY KEY,
    author_image_url VARCHAR(255)
);

INSERT INTO artwork (author_image_url)
VALUES
('https://i.postimg.cc/3rtbd3v4/jjkimage1.jpg'),
('https://i.postimg.cc/MKX3Bg7s/jjkimage2.jpg'),
('https://i.postimg.cc/m2qXyVVT/jjkimage3.jpg'),
('https://i.postimg.cc/pLF0G81z/jjkimage4.jpg'),
('https://i.postimg.cc/g0bgfJf5/jjkimage5.jpg'),
('https://i.postimg.cc/NMgpjnGk/jjkimage6.jpg'),
('https://i.postimg.cc/3Rn94Zd5/jjkimage7.jpg'),
('https://i.postimg.cc/ZnnHryd9/jjkimage8.jpg'),
('https://i.postimg.cc/nLK3ZxxL/jjkimage9.jpg'),
('https://i.postimg.cc/X7ZxqdWm/jjkimage10.jpg'),
('https://i.postimg.cc/ZYsVJjG6/jjkimage11.jpg'),
('https://i.postimg.cc/1XdHM5Gv/jjkimage12.jpg'),
('https://i.postimg.cc/c4VhnC4H/jjkimage13.jpg');

CREATE SEQUENCE all_images_table_seq START WITH 3901;
CREATE TABLE all_images_table (
    all_images_id BIGSERIAL PRIMARY KEY,
    all_image_descriptions VARCHAR(255),
    all_image_url VARCHAR(255),
    category VARCHAR(255)
);

INSERT INTO all_images_table (all_image_descriptions, all_image_url, category)
VALUES
('aaron goofy pic anime', 'https://i.ibb.co/F89g6pB/animephoto1.jpg', 'anime'),
('Group Photo 1 anime', 'https://i.ibb.co/Ln6621f/animephoto2.jpg', 'anime'),
('Photo With Mariah and Erika anime', 'https://i.ibb.co/LJzwYrF/animephoto3.jpg', 'anime'),
('Gojo Pic with Erika anime', 'https://i.ibb.co/mc8VvqM/animephoto4.jpg', 'anime'),
('Mahito Pic with Erika Pose 1 anime', 'https://i.ibb.co/mJKHkCd/animephoto5.jpg', 'anime'),
('Mahito Pic with Erika Pose 2 anime', 'https://i.ibb.co/FX0nmnc/animephoto6.jpg', 'anime'),
('Gojo Solo Pic anime', 'https://i.ibb.co/XWh3Syy/animephoto7.jpg', 'anime'),
('Toji picture pose 1 anime', 'https://i.ibb.co/Cswm6BG/animephoto8.jpg', 'anime'),
('Riko Picture pose 1 anime', 'https://i.ibb.co/mFKcyRn/animephoto9.jpg', 'anime'),
('Riko Picture pose 2 anime', 'https://i.ibb.co/Nn3bGZx/animephoto10.jpg', 'anime');

CREATE TABLE favorites (
    id BIGSERIAL PRIMARY KEY,
    image_id BIGINT,
    user_id BIGINT,
    image_all_images_id BIGINT
);