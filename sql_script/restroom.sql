DROP TABLE IF EXISTS `restroom`;

CREATE TABLE `restroom` (
    `restroom_id` int NOT NULL AUTO_INCREMENT,
    `building_id` int NOT NULL DEFAULT '0',
    `floor_name` varchar(200) NOT NULL,
    `room_num` varchar(200) NOT NULL,
    `product_status` int NOT NULL DEFAULT '1',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted` tinyint(1) NOT NULL DEFAULT '0',
    PRIMARY KEY (`restroom_id`),
    UNIQUE KEY `id_UNIQUE` (`restroom_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- add column building_num
alter table restroom
add building_num int;

INSERT INTO restroom (
    building_num,
    floor_name,
    room_num)
VALUES
(111,'1','106'),
(111,'2','214'),
(653,'1','1141'),
(722,'1','169'),
(714,'1','192B'),
(817,'1','C104'),
(802,'1','204'),
(515,'1','112'),
(519,'1','1604'),
(519,'1','1002'),
(519,'1','1206'),
(314,'1','1414'),
(899,'1','103D'),
(325,'1','1201'),
(325,'1','1004'),
(902,'2','2331'),
(711,'B','B03A'),
(711,'B','B03B'),
(711,'B','B17A'),
(903,'1','1222'),
(417,'1','1303'),
(313,'1','116'),
(715,'1','113'),
(3,'1','1006'),
(3,'2','2105'),
(317,'1','125'),
(321,'1','E1126'),
(308,'1','1101'),
(323,'1','128'),
(305,'1','102'),
(303,'4','430'),
(101,'1','1028G'),
(101,'2','2000I'),
(837,'1','1145'),
(514,'1','107'),
(514,'1','109A'),
(897,'1','106'),
(845,'1','1021'),
(843,'1','1006'),
(843,'1','1123'),
(843,'1','1133'),
(611,'1','1103'),
(601,'1','123'),
(601,'1','122'),
(601,'2','272'),
(610,'1','117'),
(302,'1','166'),
(302,'2','239'),
(304,'1','118'),
(304,'2','230'),
(304,'2','222'),
(92,'1','138'),
(835,'1','124'),
(835,'1','146'),
(835,'1','185'),
(835,'1','245'),
(835,'1','229'),
(835,'1','276'),
(600,'1','114'),
(600,'2','214'),
(102,'2','260'),
(102,'2','216'),
(4,'2','2300'),
(503,'1','1225'),
(836,'1','1103'),
(811,'1','B143'),
(811,'2','B243'),
(825,'3','D343'),
(825,'4','D443'),
(827,'1','E104'),
(810,'1','115'),
(812,'1','325B'),
(812,'1','312A'),
(812,'1','361C'),
(58,'1','122'),
(58,'2','232'),
(2,'1','118'),
(726,'1','114'),
(726,'2','212'),
(517,'1','1102'),
(517,'2','2102'),
(402,'1','1004'),
(728,'1','1120'),
(413,'1','C105'),
(404,'1','1920'),
(411,'1','L009'),
(829,'1','143'),
(723,'1','136'),
(723,'1','1205'),
(723,'1','1208'),
(506,'2','210C'),
(401,'1','B011'),
(710,'1','190'),
(710,'1','185'),
(710,'1','183'),
(311,'1','104'),
(400,'1','141'),
(221,'1','110'),
(520,'1','106'),
(710,'1','187'),
(214,'1','1105'),
(210,'1','137'),
(215,'1','1343'),
(202,'1','103'),
(202,'2','201'),
(211,'1','1152'),
(213,'1','1230'),
(201,'1','193'),
(839,'1','119B'),
(502,'1','181'),
(502,'1','118'),
(105,'1','105'),
(105,'1','1030'),
(831,'1','F111');

-- add values for the building_id column
update restroom inner join
(select r.restroom_id as restroom_id, b.building_id as building_id
    from building as b, restroom as r
    where b.building_num = r.building_num
    order by r.restroom_id) as t1
on restroom.restroom_id = t1.restroom_id
set restroom.`building_id` = t1.`building_id`;

-- corner case: change building_id for restroom 113
update restroom
set building_id = 11
where restroom_id = 113;

-- delete building_num column
alter table restroom
drop column building_num;

-- unset default value for building_id
ALTER TABLE `menstruation_equity_map`.`restroom` 
CHANGE COLUMN `building_id` `building_id` INT NOT NULL ;