CREATE TABLE `admin`  (
                          `id` int NOT NULL,
                          `age` int NOT NULL,
                          `sex` int NOT NULL,
                          `name` varchar(25) NOT NULL,
                          `password` varchar(25) NOT NULL,
                          `email` varchar(25) NOT NULL,
                          PRIMARY KEY (`id`)
);

CREATE TABLE `post`  (
                         `id` int NOT NULL,
                         `starttime` datetime NOT NULL,
                         `user_id` int NOT NULL,
                         `ispass` int NOT NULL,
                         `isend` int NOT NULL,
                         `timecoin` int NOT NULL,
                         `volunteers` int NOT NULL,
                         `content` varchar(255) NULL,
                         PRIMARY KEY (`id`)
);

CREATE TABLE `user`  (
                         `id` int NOT NULL,
                         `sex` int NOT NULL,
                         `age` int NOT NULL,
                         `name` varchar(25) NOT NULL,
                         `password` varchar(25) NOT NULL,
                         `email` varchar(25) NOT NULL,
                         `timecoin` int NOT NULL,
                         PRIMARY KEY (`id`)
);
