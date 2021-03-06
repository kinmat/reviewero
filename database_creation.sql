DROP TABLE IF EXISTS user_books;
DROP TABLE IF EXISTS book_genres;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS author_books;
DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS book_states;
DROP TABLE IF EXISTS publishers;


CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR (max) NOT NULL,
    email NVARCHAR (max) NOT NULL,
	password NVARCHAR (max) NOT NULL,
);

create TABLE roles (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(max) NOT NULL,
);

create TABLE genres (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(max) NOT NULL,
);

create table publishers (
	id INT IDENTITY(1,1) PRIMARY KEY,
	name NVARCHAR(max) NOT NULL,
	city NVARCHAr(max)
);

create table books (
	id INT IDENTITY(1,1) PRIMARY KEY,
	title NVARCHAR(max) NOT NULL,
	synopsis NVARCHAR(max),
	publisher_id int FOREIGN KEY REFERENCES publishers(id),
	publication_date DATE,
	pages int,	
);

create table authors (
	id INT IDENTITY(1,1) PRIMARY KEY,
	full_name NVARCHAR(max) NOT NULL,
	biography NVARCHAR(max),
	user_id int FOREIGN KEY REFERENCES users(id),
);

create table reviews (
	user_id int NOT NULL FOREIGN KEY REFERENCES users(id),
	book_id int NOT NULL FOREIGN KEY REFERENCES users(id),
	rating int,
	text NVARCHAR(MAX),
	PRIMARY KEY(book_id, user_id)
);

create table book_states (
	id INT IDENTITY(1,1) PRIMARY KEY,
	name NVARCHAR(max)
);

create table user_books (
	user_id int NOT NULL FOREIGN KEY REFERENCES users(id),
	book_id int NOT NULL FOREIGN KEY REFERENCES books(id),
	state_id int  NOT NULL FOREIGN KEY REFERENCES book_states(id),
	date_added DATE,
	PRIMARY KEY(book_id, user_id)
);

create table book_genres  (
	book_id int NOT NULL FOREIGN KEY REFERENCES books(id),
	genre_id int NOT NULL FOREIGN KEY REFERENCES genres(id),
	PRIMARY KEY(book_id, genre_id)
);

create TABLE user_roles (
    user_id int NOT NULL FOREIGN KEY REFERENCES users(id),
	role_id int NOT NULL FOREIGN KEY REFERENCES roles(id),
	PRIMARY KEY(role_id, user_id)
);

create TABLE author_books (
    author_id int NOT NULL FOREIGN KEY REFERENCES authors(id),
	book_id int NOT NULL FOREIGN KEY REFERENCES books(id),
	PRIMARY KEY(author_id, book_id)
);

create table friends ( 
	user_id int NOT NULL FOREIGN KEY REFERENCES users(id),
	friend_id int NOT NULL FOREIGN KEY REFERENCES users(id),
	PRIMARY KEY(user_id, friend_id)
);

create table chat_message (
	id INT IDENTITY(1,1) PRIMARY KEY,
	sender int NOT NULL FOREIGN KEY REFERENCES users(id),
	reciever int NOT NULL FOREIGN KEY REFERENCES users(id),
	content NVARCHAR(MAX),
	date_sent SMALLDATETIME
);

SET IDENTITY_INSERT roles ON;
INSERT INTO roles (id, name)
VALUES (1, 'ROLE_USER'), (2, 'ADMIN'), (3, 'AUTHOR'), (4, 'REVIEWER')
SET IDENTITY_INSERT roles OFF;

SET IDENTITY_INSERT books ON; 
INSERT INTO books (id, title, synopsis, pages)
values (1, 'Pride and Prejudice', 'Since its immediate success in 1813, Pride and Prejudice has remained one of the most popular novels in the English language. Jane Austen called this brilliant work "her own darling child" and its vivacious heroine, Elizabeth Bennet, "as delightful a creature as ever appeared in print." The romantic clash between the opinionated Elizabeth and her proud beau, Mr. Darcy, is a splendid performance of civilized sparring. And Jane Austens radiant wit sparkles as her characters dance a delicate quadrille of flirtation and intrigue, making this book the most superb comedy of manners of Regency England.', 334),
(2, 'The Great Gatsby', 'The Great Gatsby, F. Scott Fitzgeralds third book, stands as the supreme achievement of his career. This exemplary novel of the Jazz Age has been acclaimed by generations of readers. The story is of the fabulously wealthy Jay Gatsby and his new love for the beautiful Daisy Buchanan, of lavish parties on Long Island at a time when The New York Times noted "gin was the national drink and sex the national obsession," it is an exquisitely crafted tale of America in the 1920s.', 200),
(3, 'To Kill a Mockingbird', 'The unforgettable novel of a childhood in a sleepy Southern town and the crisis of conscience that rocked it. "To Kill A Mockingbird" became both an instant bestseller and a critical success when it was first published in 1960. It went on to win the Pulitzer Prize in 1961 and was later made into an Academy Award-winning film, also a classic.', 324)
SET IDENTITY_INSERT books OFF; 

SET IDENTITY_INSERT authors ON;
INSERT INTO authors(id, full_name, biography)
values(1, 'Jane Austen', '<p>Jane Austen, (born December 16, 1775, Steventon, Hampshire, England—died July 18, 1817, Winchester, Hampshire), English writer who first gave the novel its distinctly modern character through her treatment of ordinary people in everyday life.</p> <p> She published four novels during her lifetime: Sense and Sensibility (1811), Pride and Prejudice (1813), Mansfield Park (1814), and Emma (1815). In these and in Persuasion and Northanger Abbey (published together posthumously, 1817), she vividly depicted English middle-class life during the early 19th century. Her novels defined the era’s novel of manners, but they also became timeless classics that remained critical and popular successes for over two centuries after her death.</p>'),
 (2, 'F. Scott Fitzgerald', '<p>Scott Fitzgerald (1896-1940) rose to prominence as a chronicler of the jazz age.</p> <p>Born in St. Paul, Minn., Fitzgerald dropped out of Princeton University to join the U.S. Army. The success of his first novel, This Side of Paradise (1920), made him an instant celebrity.</p>'),
(3, 'Harper Lee', '')
SET IDENTITY_INSERT authors OFF;

INSERT INTO author_books(book_id, author_id)
VALUES (1,1), (2,2), (3,3)



