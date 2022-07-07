CREATE TABLE `review` (
  `review_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `place_id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `reg_dtime` datetime DEFAULT NULL,
  `upt_dtime` datetime DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  FOREIGN KEY (place_id) REFERENCES place (place_id)
);

CREATE TABLE `point_hst` (
  `point_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `review_id` varchar(255) NOT NULL,
  `apply_type_cd` varchar(255),
  `point` bigint(20),
  `reg_dtime` datetime DEFAULT NULL,
  `upt_dtime` datetime DEFAULT NULL,
  PRIMARY KEY (`point_id`),
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  FOREIGN KEY (review_id) REFERENCES review (review_id)
);

CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `user_nm` varchar(255) DEFAULT NULL,
  `reg_dtime` datetime DEFAULT NULL,
  `upt_dtime` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE `place` (
  `place_id` varchar(255) NOT NULL,
  `place_nm` varchar(255) DEFAULT NULL,
  `reg_dtime` datetime DEFAULT NULL,
  `upt_dtime` datetime DEFAULT NULL,
  PRIMARY KEY (`place_id`)
);

CREATE TABLE `image` (
  `image_id` varchar(255) NOT NULL,
  `review_id` varchar(255) NOT NULL,
  `reg_dtime` datetime DEFAULT NULL,
  `upt_dtime` datetime DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  FOREIGN KEY (review_id) REFERENCES review (review_id)
);