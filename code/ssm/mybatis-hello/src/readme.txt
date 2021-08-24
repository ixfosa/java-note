CREATE TABLE `student` (
     `id` int(11) NOT NULL ,
     `name` varchar(255) DEFAULT NULL,
     `email` varchar(255) DEFAULT NULL,
     `age` int(11) DEFAULT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;