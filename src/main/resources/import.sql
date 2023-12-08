INSERT INTO `festivalrock`.`grupos` (`miembros`, `estilo`, `origen`, `nombre`, `imagen`, `descripcion`, `subtexto`) VALUES ('4', 'Pop', 'Suecia', 'ABBA', '../img/abbaOscura.png', 'Los cuatro componentes de la mítica banda sueca reaparecen en Londres para presentar ‘Voyage’, un disco publicado cuatro décadas después de su última actuación, y un nuevo espectáculo', 'Directamente para ti');
INSERT INTO `festivalrock`.`grupos` (`miembros`, `estilo`, `origen`, `nombre`, `imagen`, `descripcion`, `subtexto`) VALUES ('3', 'Rock', 'UK', 'The Police', '../img/thePolicegrande.jpg', 'So lonely.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus arcu nibh, vehicula a vulputate ut, congue in nibh. Phasellus arcu nibh, vehicula a vulputate ut, congue in nibh.Phasellus arcu nibh.', 'Directamente para ti'); 
INSERT INTO `festivalrock`.`grupos` (`miembros`, `estilo`, `origen`, `nombre`, `imagen`, `descripcion`, `subtexto`) VALUES ('4', 'Rock', 'UK', 'Queen', '../img/queenGrande.jpg', 'Bohemian Rapsody.  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus arcu nibh, vehicula a vulputate ut.', 'Directamente para ti');
INSERT INTO `festivalrock`.`grupos` (`miembros`, `estilo`, `origen`, `nombre`, `imagen`, `descripcion`, `subtexto`) VALUES ('3', 'Gruge', 'USA', 'Nirvana', '../img/nirvana2.jpg', 'Una pena todo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus arcu nibh, vehicula a vulputate ut.', 'Directamente para ti');

INSERT INTO `festivalrock`.`conciertos` (`fecha`, `hora`, `idgrupo`,`plazas`, `precioanticipado`, `preciotaquilla`, `intro`) VALUES ('2023-11-10', '20:30', 1, '400', 45, 60, 'Some quick example text to build on the card title and make up the bulk of the card''s content.');
INSERT INTO `festivalrock`.`conciertos` (`fecha`, `hora`, `idgrupo`, `plazas`, `precioanticipado`, `preciotaquilla`, `intro`) VALUES ('2023-11-30', '22:30', 2, '396', 50, 60, 'Some quick example text to build on the card title and make up the bulk of the card''s content.');
INSERT INTO `festivalrock`.`conciertos` (`fecha`, `hora`, `idgrupo`, `plazas`, `precioanticipado`, `preciotaquilla`, `intro`) VALUES ('2023-12-31', '21:30', 1, '300', 40, 50, 'Some quick example text to build on the card title and make up the bulk of the card''s content.');


INSERT INTO `festivalrock`.`roles` (`nombre`) VALUES ('ROLE_ADMIN');
INSERT INTO `festivalrock`.`roles` (`nombre`) VALUES ('ROLE_USER');
INSERT INTO `festivalrock`.`usuarios` (`dni`, `username`, `apellidos`, `password`, `email`,`fechanacimiento`, `idrol`,`fecharegistro`) VALUES ('111', 'Pilar', 'Fernández', '$2a$10$LGNROB2dz.wjU50QDfvcpOkcZXyUnEt0oQaakELTZewsAg3cd3t26', 'pilar@pilar.com', '1975-03-09', 1, '2020-10-2');
INSERT INTO `festivalrock`.`usuarios` (`dni`, `username`, `apellidos`, `password`,`email`, `fechanacimiento`, `idrol`,`fecharegistro`) VALUES ('222', 'Juan', 'López', '$2a$10$LGNROB2dz.wjU50QDfvcpOkcZXyUnEt0oQaakELTZewsAg3cd3t26', 'juan@juan.com','1960-01-30', 2, '2021-12-5');
INSERT INTO `festivalrock`.`usuarios` (`dni`, `username`, `apellidos`, `password`,`email`, `fechanacimiento`, `idrol`,`fecharegistro`) VALUES ('333', 'Lola', 'Flores', '$2a$10$LGNROB2dz.wjU50QDfvcpOkcZXyUnEt0oQaakELTZewsAg3cd3t26', 'lola@lola.com','2000-05-03', 2, '2022-8-12');


INSERT INTO `festivalrock`.`ventas` (`numeroentradas`, `fechaventa`, `horaventa`, `idusuario`, `idconcierto`) VALUES (4, '2023-10-04', '21:11', 2, 2);




