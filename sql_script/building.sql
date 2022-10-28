CREATE TABLE `building` (
    `building_id` int NOT NULL AUTO_INCREMENT,
    `building_num` int NOT NULL,
    `building_name` varchar(200) NOT NULL,
    `latitude` decimal(5,2) NOT NULL COMMENT 'latitude',
    `longitude` decimal(5,2) NOT NULL COMMENT 'longtitude',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO building (
    building_num,
    building_name,
    latitude,
    longitude)
VALUES('2','Multipurpose Academic & Administration Bldg.',0.00,0.00),
('3','Education (Berkely Pl. N.)',0.00,0.00),
('4','Law (Berkeley Pl. S)',0.00,0.00),
('58','Mesa Arts Building   MAB',0.00,0.00),
('59','Mesa Office Building  MOB',0.00,0.00),
('92','Interim Office Building',0.00,0.00),
('96','Arboretum',0.00,0.00),
('101','Gateway Study Center  GC',0.00,0.00),
('102','Langson Library  LLIB',0.00,0.00),
('105','Student Ser I  - SS1  SS2',0.00,0.00),
('105','Student Ser I  II - SS1  SS2',0.00,0.00),
('111','Aldrich Hall  ALH',0.00,0.00),
('201','Social Science Tower  SST',0.00,0.00),
('202','Social Science Laboratory  SSL',0.00,0.00),
('210','Social Ecology I SE',0.00,0.00),
('211','Social Science Plaza A   SSPA',0.00,0.00),
('213','Social Science Plaza B   SSPB',0.00,0.00),
('214','Social & Behavioral Sciences',0.00,0.00),
('215','Social Ecology II  SE2',0.00,0.00),
('221','School Of Business 2',0.00,0.00),
('222','School Of Business 1',0.00,0.00),
('302','Information & Computer Science  ICS',0.00,0.00),
('303','Engineering Tower   ET',0.00,0.00),
('304','Information & Computer Science 2   ICS2',0.00,0.00),
('305','Engineering Lecture Hall ELH',0.00,0.00),
('308','Engineering Hall',0.00,0.00),
('311','Rockwell Engineering Center  REC',0.00,0.00),
('313','Disability Services Center',0.00,0.00),
('314','Bren Hall  DBH',0.00,0.00),
('317','Engineering  & Computing Trailer  ECT',0.00,0.00),
('321','Engineering Gateway  EG',0.00,0.00),
('323','Engineering Laboratory Facility   ELF',0.00,0.00),
('325','Calif.Inst. For Telecom & Info. Tech.  Calit 2',0.00,0.00),
('400','Rowland Hall   RH',0.00,0.00),
('401','Reines Hall  RH',0.00,0.00),
('402','Natural Sciences II  NS2',0.00,0.00),
('404','Physical Sciences High Bay ',0.00,0.00),
('411','Physical Sciences Lecture Hall  PSLH  ',0.00,0.00),
('413','Physical Sciences Classroom Building  PSCB',0.00,0.00),
('417','Croul Hall    CRH',0.00,0.00),
('419','Interdisciplinary Science and Engineering Building',0.00,0.00),
('423','Admin Modular ',0.00,0.00),
('502','Steinhaus Hall  SH',0.00,0.00),
('503','McGaugh Hall   MH',0.00,0.00),
('506','Qureshey Research Laboratory  QRL',0.00,0.00),
('512','Bonney Research Laboratory   BRL',0.00,0.00),
('514','Greenhouse',0.00,0.00),
('515','Biological Sciences Admin.  BSA',0.00,0.00),
('517','Natural Sciences I   NS1',0.00,0.00),
('519','Biological Sciences III',0.00,0.00),
('520','Science Library  SLIB',0.00,0.00),
('600','Krieger Hall   KH',0.00,0.00),
('601','Humanities Hall  HH',0.00,0.00),
('605','Anteater Learning Pavillion',0.00,0.00),
('610','Humanities Instructional Bldg.  HIB',0.00,0.00),
('611','Humanities Gateway   HG',0.00,0.00),
('625','Intercollegiate Athletics Building',0.00,0.00),
('653','Anteater Instruction & Research Building  AIRB',0.00,0.00),
('710','Robert Winifred Smith',0.00,0.00),
('710','Smith Hall   WSH',0.00,0.00),
('711','Claire Trevor Theater   CTT',0.00,0.00),
('714','Arts Instruction & Technology Resourse Center  AITR',0.00,0.00),
('715','Drama Building   DRA',0.00,0.00),
('722','Art Studio   ART',0.00,0.00),
('723','Production Studio',0.00,0.00),
('725','Studio Four',0.00,0.00),
('726','Music & Media Building   MM',0.00,0.00),
('728','Performance Studios  PSTU',0.00,0.00),
('802','Berk Hall   BH',0.00,0.00),
('810','Medical Surge I   MS1',0.00,0.00),
('811','Medical Sciences B   MS-B',0.00,0.00),
('812','Medical Surge II   MS2',0.00,0.00),
('817','Beckman Laser Institute   BLI',0.00,0.00),
('819','Medical Sciences A  Annex',0.00,0.00),
('825','Medical Sciences D    MS-D',0.00,0.00),
('827','Medical Sciences E    MS-E',0.00,0.00),
('829','Plumwood House    PH',0.00,0.00),
('831','Tamkin Student Lecture Bldg.',0.00,0.00),
('835','Irvine Hall   IH',0.00,0.00),
('836','Medical Education Bldg.',0.00,0.00),
('837','Gillespie Neuroscience Research Facility   GNRF',0.00,0.00),
('839','Sprague Hall   SPH',0.00,0.00),
('843','Hewitt Research Hall   HRH',0.00,0.00),
('845','Gross Hall',0.00,0.00),
('854','Sue And Bill Gross Hall Nursing and Health Science',0.00,0.00),
('856','Susan And Henry Samueli College of Health Sciences',0.00,0.00),
('897','Grounds Maintenance Facility  G5',0.00,0.00),
('899','Building Services Bldg.  G5',0.00,0.00),
('902','Central Plant    CP',0.00,0.00),
('903','Crawford Hall   CH',0.00,0.00),
('907','Track & Field House ',0.00,0.00);

