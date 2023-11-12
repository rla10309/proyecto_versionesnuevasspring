INSERT INTO `festivalrock`.`grupos` (`miembros`, `estilo`, `origen`, `nombre`, `imagen`, `descripcion`) VALUES ('4', 'Pop', 'Suecia', 'ABBA', '../img/abba.jpg', 'Waterloooooo.  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus arcu nibh, vehicula a vulputate ut, congue in nibh. Phasellus arcu nibh, vehicula a vulputate ut, congue in nibh.Phasellus arcu nibh. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus arcu nibh, vehicula a vulputate ut, congue in nibh.');
INSERT INTO `festivalrock`.`grupos` (`miembros`, `estilo`, `origen`, `nombre`, `imagen`, `descripcion`) VALUES ('3', 'Rock', 'UK', 'The Police', '../img/thepolice3.jpeg', 'So lonely.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus arcu nibh, vehicula a vulputate ut, congue in nibh. Phasellus arcu nibh, vehicula a vulputate ut, congue in nibh.Phasellus arcu nibh. .'); 
INSERT INTO `festivalrock`.`grupos` (`miembros`, `estilo`, `origen`, `nombre`, `imagen`, `descripcion`) VALUES ('4', 'Rock', 'UK', 'Queen', '../img/queen1.jpg', 'Bohemian Rapsody.  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus arcu nibh, vehicula a vulputate ut.');
INSERT INTO `festivalrock`.`grupos` (`miembros`, `estilo`, `origen`, `nombre`, `imagen`, `descripcion`) VALUES ('3', 'Gruge', 'USA', 'Nirvana', '../img/nirvana2.jpg', 'Una pena todo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus arcu nibh, vehicula a vulputate ut.');

INSERT INTO `festivalrock`.`conciertos` (`fecha`, `hora`, `idgrupo`,`plazas`, `precioanticipado`, `preciotaquilla`, `intro`) VALUES ('2023-11-10', '20:30', 1, '400', 45, 60, 'Some quick example text to build on the card title and make up the bulk of the card''s content.');
INSERT INTO `festivalrock`.`conciertos` (`fecha`, `hora`, `idgrupo`, `plazas`, `precioanticipado`, `preciotaquilla`, `intro`) VALUES ('2023-11-30', '22:30', 2, '396', 50, 60, 'Some quick example text to build on the card title and make up the bulk of the card''s content.');
INSERT INTO `festivalrock`.`conciertos` (`fecha`, `hora`, `idgrupo`, `plazas`, `precioanticipado`, `preciotaquilla`, `intro`) VALUES ('2023-12-31', '21:30', 1, '300', 40, 50, 'Some quick example text to build on the card title and make up the bulk of the card''s content.');


INSERT INTO `festivalrock`.`roles` (`nombre`) VALUES ('ROLE_ADMIN');
INSERT INTO `festivalrock`.`roles` (`nombre`) VALUES ('ROLE_USER');
INSERT INTO `festivalrock`.`usuarios` (`dni`, `username`, `apellidos`, `password`, `email`, `idrol`) VALUES ('111', 'Pilar', 'Fernández', '$2a$10$LGNROB2dz.wjU50QDfvcpOkcZXyUnEt0oQaakELTZewsAg3cd3t26', 'pilar@pilar.com', 1);
INSERT INTO `festivalrock`.`usuarios` (`dni`, `username`, `apellidos`, `password`,`email`, `idrol`) VALUES ('222', 'Juan', 'López', '$2a$10$LGNROB2dz.wjU50QDfvcpOkcZXyUnEt0oQaakELTZewsAg3cd3t26', 'juan@juan.com', 2);
INSERT INTO `festivalrock`.`usuarios` (`dni`, `username`, `apellidos`, `password`,`email`, `idrol`) VALUES ('333', 'Lola', 'Flores', '$2a$10$LGNROB2dz.wjU50QDfvcpOkcZXyUnEt0oQaakELTZewsAg3cd3t26', 'lola@lola.com', 2);


INSERT INTO `festivalrock`.`ventas` (`numeroentradas`, `fechaventa`, `horaventa`, `idusuario`, `idconcierto`) VALUES (4, '2023-10-04', '21:11', 2, 2);




