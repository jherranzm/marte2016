delimiter $$

CREATE TABLE maestras.tbl_role (
  id int(11) NOT NULL AUTO_INCREMENT,
  nombre varchar(255) NOT NULL,
  comentarios text,
  PRIMARY KEY (id),
  UNIQUE KEY nombre_UNIQUE (nombre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE maestras.tbl_usuario (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  password varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  enabled tinyint(1) NOT NULL DEFAULT '1',
  comentarios text,
  PRIMARY KEY (id),
  UNIQUE KEY username_UNIQUE (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE maestras.tbl_usuario_role (
  id int(11) NOT NULL AUTO_INCREMENT,
  usuario_id int(11) NOT NULL,
  role_id int(11) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY user_role (usuario_id,role_id),
  KEY marte_usuario_role_ibfk_2 (role_id),
  CONSTRAINT marte_usuario_role_ibfk_1 FOREIGN KEY (usuario_id) REFERENCES maestras.tbl_usuario (id) ON DELETE CASCADE,
  CONSTRAINT marte_usuario_role_ibfk_2 FOREIGN KEY (role_id) REFERENCES maestras.tbl_role (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$





