/*
Navicat MySQL Data Transfer

Source Server         : Michelle
Source Server Version : 50517
Source Host           : localhost:3306
Source Database       : kolcdstore

Target Server Type    : MYSQL
Target Server Version : 50517
File Encoding         : 65001

Date: 2015-11-01 20:35:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `addrid` bigint(20) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) NOT NULL,
  `addrline1` varchar(255) NOT NULL,
  `addrline2` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `zipcode` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL DEFAULT 'Canada',
  `phone` varchar(255) NOT NULL,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`addrid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '6131234567', '1');
INSERT INTO `address` VALUES ('2', 'Yu Sun', 'No.1882,West Yan-an Road', '208A', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '6134567892', '1');
INSERT INTO `address` VALUES ('9', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '9084576345', '1');

-- ----------------------------
-- Table structure for `bill`
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `billid` bigint(20) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) NOT NULL,
  `Addrline1` varchar(255) NOT NULL,
  `Addrline2` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL DEFAULT '',
  `zipcode` varchar(255) NOT NULL,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`billid`),
  KEY `customer` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES ('1', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('5', '', '', '', '', 'State/Province/Region :', '', 'Canada', '1');
INSERT INTO `bill` VALUES ('6', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('7', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('8', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('9', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('10', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('11', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('12', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('13', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('14', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('15', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('16', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('17', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('18', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('19', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('20', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('21', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('22', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('23', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('24', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('25', 'Yu Sun', '8 Sequoia Grove Drive', '', 'Markham', 'Ontario', 'L63CH2', 'Canada', '1');
INSERT INTO `bill` VALUES ('26', 'wenqian wang', '', '', '', 'Ontario', '', 'Canada', '1');
INSERT INTO `bill` VALUES ('27', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('28', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('29', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('30', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('31', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('32', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('33', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('34', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('35', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('36', 'Yu Sun', 'No.1882,West Yan-an Road', '208A', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('37', 'Yu Sun', 'No.1882,West Yan-an Road', '208A', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('38', 'Yu Sun', 'No.1882,West Yan-an Road', '208A', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('39', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('40', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('41', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('42', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('43', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('44', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('45', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('46', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('47', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('48', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('49', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('50', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('51', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('52', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('53', 'Yu Sun', 'No.1882,West Yan-an Road', '208A', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');
INSERT INTO `bill` VALUES ('54', 'Zhibo Zhang', '1233 Colonel By Drive', '633B', 'Ottawa', 'Ontario', 'K1S5B7', 'Canada', '1');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cateid` bigint(20) NOT NULL AUTO_INCREMENT,
  `catename` varchar(255) NOT NULL,
  PRIMARY KEY (`cateid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'Pop');
INSERT INTO `category` VALUES ('2', 'Jazz');
INSERT INTO `category` VALUES ('3', 'Country');
INSERT INTO `category` VALUES ('4', 'Blues');
INSERT INTO `category` VALUES ('5', 'Hip-Hop');
INSERT INTO `category` VALUES ('6', 'Rock');
INSERT INTO `category` VALUES ('7', 'Dance');
INSERT INTO `category` VALUES ('8', 'Folk');

-- ----------------------------
-- Table structure for `cd`
-- ----------------------------
DROP TABLE IF EXISTS `cd`;
CREATE TABLE `cd` (
  `cdid` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `artist` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `intro` text,
  `price` float NOT NULL,
  `stock` int(11) NOT NULL,
  `imgurl` varchar(255) NOT NULL,
  `cateid` bigint(20) NOT NULL,
  PRIMARY KEY (`cdid`),
  KEY `cateid` (`cateid`),
  CONSTRAINT `cateid` FOREIGN KEY (`cateid`) REFERENCES `category` (`cateid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cd
-- ----------------------------
INSERT INTO `cd` VALUES ('1', 'revival', 'Selena Gomez', '2015-10-09', 'Revival is the second solo studio album by American singer Selena Gomez. It was released on October 9, 2015, through Interscope and Polydor Records as a follow-up to Stars Dance (2013). It is her first project released under her new recording contract with the labels, after completing her contractual obligations with previous label, Hollywood Records, with the compilation album For You and the single \"The Heart Wants What It Wants\", in 2014. Gomez has cited artists such as Janet Jackson and Christina Aguilera as influences on the record, which has been described as a mixture of pop and electronic, and features lyrical themes of love, heartbreak, and confidence.', '49', '0', 'images/cdcover/1.png', '1');
INSERT INTO `cd` VALUES ('2', 'hands of love', 'Miley Cyrus', '2015-09-16', 'This is miley cyrus first album to love.', '46', '109', 'images/cdcover/c2.jpg', '1');
INSERT INTO `cd` VALUES ('3', 'STORYTELLER', 'Carrier Underwood', '2015-10-23', 'For this album Underwood worked with frequent collaborators such as Hillary Lindsey, Ashley Gorley, Chris DeStefano, David Hodges, Brett James. and Mark Bright, however Underwood also worked with new collaborators such as Liz Rose, Zach Crowell, and Jay Joyce.[5] Joyce produced the albums lead single \"Smoke Break\", the song was written by Underwood, Chris DeStefano and Hillary Lindsay and was inspired by their experience during the recording, when writing the album the songwriters kept taking breaks because they were stuck on one of the albums tracks \"were writing and kept taking breaks to go outside because we were getting a little stuck on a song we were already working on. It was so beautiful outside that we had a hard time focusing, so we decided to write a song about taking breaks! \'Smoke Break\' seemed like a great title, so we ran with it!\".', '58', '112', 'images/cdcover/c3.jpg', '3');
INSERT INTO `cd` VALUES ('4', 'MUSIC TO WATCH BOYS TO', 'Lana Del Rey', '2015-09-11', 'Del Rey first mentioned the song as a potential title for the whole album in late June 2014. On January 2, 2015, Del Rey opened up a little more on the song, elaborating on how she wrote it in a visual, noirish way saying \"the title (of the song) lends itself to a visual of shadows of men passing by, this girl\'s eyes, her face. I can definitely see things.\"[1] The song was originally eyed as the lead single for the album.', '32', '134', 'images/cdcover/c4.jpg', '2');
INSERT INTO `cd` VALUES ('5', 'THE HILLS', 'The Weekends', '2015-05-27', '\"The Hills\" received positive reviews for its return to form for the singer after his pop-oriented \"Earned It\", and was a commercial success. In the singer\'s native Canada, the song peaked at number 5. In the United States, it reached number 1 on the Billboard Hot 100, replacing his own \"Can\'t Feel My Face\", which was released later. The song also made the top 20 in Australia, Denmark, Ireland, and Sweden while reaching the top 40 in the Netherlands, New Zealand, Norway, Switzerland, and United Kingdom. At the height of the song\'s popularity, two remixes featuring rappers Eminem and Nicki Minaj were released.', '19', '219', 'images/cdcover/c5.jpg', '8');
INSERT INTO `cd` VALUES ('6', '1989', 'Taylor Swift', '2014-10-27', '1989 is the fifth studio album by American singer-songwriter Taylor Swift. It was released on October 27, 2014, through Big Machine Records. Swift began preparing for the album during the same year that Red was released, and during a significant amount of media scrutiny. Over the course of the two-year songwriting period, she primarily collaborated with producers Max Martin and Shellback—Martin served as the album\'s executive producer alongside Swift. The album\'s title was inspired by the pop-music scene of the 1980s, particularly Swift\'s birth year.', '45', '57', 'images/cdcover/2-.jpg', '1');
INSERT INTO `cd` VALUES ('7', 'WHAT DO YOU MEAN?', 'Justin Bibber', '2015-07-28', '\"What Do You Mean?\" is a song recorded by Canadian singer Justin Bieber for his upcoming fourth studio album Purpose. The song was announced on July 28, 2015, on On Air with Ryan Seacrest, and released on August 28, 2015, as the album\'s lead single.[2] It was well received by music critics. The track was also a commercial success, it topped the charts in 17 countries and became Bieber\'s first number one single on the US Billboard Hot 100 for a single week, debuting in the position with first-week sales of 337,000 copies.', '48', '18', 'images/cdcover/c6.jpg', '4');
INSERT INTO `cd` VALUES ('8', 'HOTLINE BLING', 'Dreke', '2015-07-31', '\"Hotline Bling\" is a song by Canadian hip hop recording artist Drake, released as a single digitally on July 31, 2015.[1] Produced by Nineteen85, the song\'s instrumental heavily samples R&B singer Timmy Thomas\' 1972 song \"Why Can\'t We Live Together\".[2] The song is going to act as the lead single to Drake\'s upcoming fourth studio album Views from the 6. Upon release, it received various comparisons to \"Cha Cha\" by American rapper D.R.A.M., which Drake had expressed interest in remixing, with Rap-Up calling it a \"quasi-cover\" of D.R.A.M.\'s song.[3] Drake leaked the song along with \"Back to Back\" on his blog. The song has been covered by English DJ duo Disclosure and English recording artist Sam Smith together in the BBC Radio 1 Live Lounge.', '34', '29', 'images/cdcover/c8.jpg', '7');
INSERT INTO `cd` VALUES ('9', 'PHOTOGRAPH', 'Ed Sheeran', '2015-05-11', '\"Photograph\" is a song recorded by English singer-songwriter Ed Sheeran for his second studio album, x (2014). Sheeran co-wrote the song with Snow Patrol member Johnny McDaid, who had a piano loop upon which the composition developed. After recording several versions with other producers, Sheeran eventually solicited help from Jeff Bhasker; the collaboration generated a version that Bhasker further enhanced for months. The ballad derives its music primarily from an acoustic guitar, piano and programmed drums. With visually descriptive lyrics, the song discusses a long-distance relationship inspired by Sheeran\'s own experience of being away from his then-girlfriend while he was on tour. It received generally positive commentary from critics who noted the lyrics and Sheeran\'s use of imagery.\r\n\r\n\"Photograph\" is a song recorded by English singer-songwriter Ed Sheeran for his second studio album, x (2014). Sheeran co-wrote the song with Snow Patrol member Johnny McDaid, who had a piano loop upon which the composition developed. After recording several versions with other producers, Sheeran eventually solicited help from Jeff Bhasker; the collaboration generated a version that Bhasker further enhanced for months. The ballad derives its music primarily from an acoustic guitar, piano and programmed drums. With visually descriptive lyrics, the song discusses a long-distance relationship inspired by Sheeran\'s own experience of being away from his then-girlfriend while he was on tour. It received generally positive commentary from critics who noted the lyrics and Sheeran\'s use of imagery.', '38', '32', 'images/cdcover/c9.png', '8');
INSERT INTO `cd` VALUES ('10', 'DOWNTOWN', 'Macklemore & Ryan Lewis', '2015-08-27', '\"Downtown\" is a song by American hip hop duo Macklemore & Ryan Lewis featuring Eric Nally, Melle Mel, Kool Moe Dee, and Grandmaster Caz. The song was officially released as a single on August 27, 2015. A music video for the song was uploaded to Ryan Lewis\' own YouTube channel on the day of the song\'s release.', '55', '15', 'images/cdcover/c10.jpg', '6');
INSERT INTO `cd` VALUES ('11', 'COOL FOR THE SUMMER', 'Demi Lovato', '2015-07-01', '\"Cool for the Summer\" is a song by American singer Demi Lovato. It serves as the lead single from her fifth studio album Confident.[2][3] The song was released on July 1, 2015, by Hollywood, and Island Records, and made its radio premiere on the same date via Republic Records.[4][5] It was written by Lovato, Max Martin, Ali Payami, Alexander Erik Kronlund and Savan Kotecha.[6] The song has received attention for its bi-curious insinuations and sexually suggestive lyrics.', '40', '37', 'images/cdcover/c11.jpg', '5');
INSERT INTO `cd` VALUES ('12', 'DRAG ME DOWN', 'One Direction', '2015-07-31', '\"Drag Me Down\" is a song recorded by British-Irish boy band One Direction for their fifth studio album, Made in the A.M. (2015).[2] The song was released worldwide on 31 July 2015. Written by Jamie Scott, John Ryan and Julian Bunetta, the song is produced by the latter two.[1] The track marked their first single since Zayn Malik\'s departure in March 2015. \"Drag Me Down\" debuted at the top of the charts in the UK, Ireland, France, Austria, Australia, and New Zealand upon its release. It became the group\'s first number one single in France and Australia, as well as their fourth number one in New Zealand and the UK. It debuted at number three on the Billboard Hot 100 chart.', '45', '46', 'images/cdcover/c12.jpg', '1');

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '123', 'zhibo', 'zhang', 'zhibo_zhang@yahoo.com', 'female');
INSERT INTO `customer` VALUES ('2', '123', 'wenqian', 'wang', 'wenqianwang@yahoo.com', 'male');
INSERT INTO `customer` VALUES ('3', '123', 'Mengxue', 'Zhang', '254296510@qq.com', 'female');
INSERT INTO `customer` VALUES ('4', '123', 'yu', 'sun', 'yusun@yahoo.com', 'female');

-- ----------------------------
-- Table structure for `items`
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `itemsid` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL,
  `orderid` bigint(20) NOT NULL,
  `cdid` bigint(20) NOT NULL,
  PRIMARY KEY (`itemsid`),
  KEY `order_item` (`orderid`),
  KEY `cd_item` (`cdid`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES ('51', '2', '39', '1');
INSERT INTO `items` VALUES ('52', '1', '39', '3');
INSERT INTO `items` VALUES ('53', '2', '40', '1');
INSERT INTO `items` VALUES ('54', '1', '40', '3');
INSERT INTO `items` VALUES ('55', '2', '41', '1');
INSERT INTO `items` VALUES ('56', '1', '41', '3');
INSERT INTO `items` VALUES ('57', '2', '42', '1');
INSERT INTO `items` VALUES ('58', '1', '42', '3');
INSERT INTO `items` VALUES ('59', '2', '43', '1');
INSERT INTO `items` VALUES ('60', '1', '43', '3');
INSERT INTO `items` VALUES ('61', '2', '44', '1');
INSERT INTO `items` VALUES ('62', '1', '44', '3');
INSERT INTO `items` VALUES ('63', '2', '45', '1');
INSERT INTO `items` VALUES ('64', '1', '45', '3');
INSERT INTO `items` VALUES ('65', '2', '46', '1');
INSERT INTO `items` VALUES ('66', '1', '46', '3');
INSERT INTO `items` VALUES ('67', '2', '47', '1');
INSERT INTO `items` VALUES ('68', '1', '47', '3');
INSERT INTO `items` VALUES ('69', '2', '48', '1');
INSERT INTO `items` VALUES ('70', '1', '48', '3');
INSERT INTO `items` VALUES ('71', '2', '49', '1');
INSERT INTO `items` VALUES ('72', '1', '49', '3');
INSERT INTO `items` VALUES ('73', '2', '50', '1');
INSERT INTO `items` VALUES ('74', '1', '50', '3');
INSERT INTO `items` VALUES ('75', '2', '51', '1');
INSERT INTO `items` VALUES ('76', '1', '51', '3');
INSERT INTO `items` VALUES ('77', '2', '52', '1');
INSERT INTO `items` VALUES ('78', '1', '52', '3');
INSERT INTO `items` VALUES ('79', '2', '53', '1');
INSERT INTO `items` VALUES ('80', '1', '53', '3');
INSERT INTO `items` VALUES ('81', '2', '54', '1');
INSERT INTO `items` VALUES ('82', '1', '54', '3');
INSERT INTO `items` VALUES ('83', '2', '55', '1');
INSERT INTO `items` VALUES ('84', '1', '55', '3');
INSERT INTO `items` VALUES ('85', '2', '56', '1');
INSERT INTO `items` VALUES ('86', '1', '56', '3');
INSERT INTO `items` VALUES ('87', '2', '57', '1');
INSERT INTO `items` VALUES ('88', '1', '57', '3');
INSERT INTO `items` VALUES ('89', '2', '58', '1');
INSERT INTO `items` VALUES ('90', '1', '58', '3');
INSERT INTO `items` VALUES ('91', '2', '59', '1');
INSERT INTO `items` VALUES ('92', '1', '59', '3');
INSERT INTO `items` VALUES ('93', '2', '60', '1');
INSERT INTO `items` VALUES ('94', '1', '60', '3');
INSERT INTO `items` VALUES ('95', '2', '61', '1');
INSERT INTO `items` VALUES ('96', '1', '61', '3');
INSERT INTO `items` VALUES ('97', '2', '62', '1');
INSERT INTO `items` VALUES ('98', '1', '62', '3');
INSERT INTO `items` VALUES ('99', '1', '63', '3');
INSERT INTO `items` VALUES ('100', '1', '64', '3');
INSERT INTO `items` VALUES ('101', '1', '65', '3');
INSERT INTO `items` VALUES ('102', '1', '66', '3');
INSERT INTO `items` VALUES ('103', '1', '67', '3');
INSERT INTO `items` VALUES ('104', '1', '68', '3');
INSERT INTO `items` VALUES ('105', '1', '69', '3');
INSERT INTO `items` VALUES ('106', '1', '70', '2');
INSERT INTO `items` VALUES ('107', '1', '72', '2');
INSERT INTO `items` VALUES ('108', '1', '73', '2');
INSERT INTO `items` VALUES ('109', '1', '74', '2');
INSERT INTO `items` VALUES ('110', '1', '75', '2');
INSERT INTO `items` VALUES ('111', '1', '76', '2');
INSERT INTO `items` VALUES ('112', '1', '77', '2');
INSERT INTO `items` VALUES ('113', '1', '78', '2');
INSERT INTO `items` VALUES ('114', '1', '79', '3');
INSERT INTO `items` VALUES ('115', '1', '79', '2');
INSERT INTO `items` VALUES ('116', '1', '80', '3');
INSERT INTO `items` VALUES ('117', '1', '80', '2');
INSERT INTO `items` VALUES ('118', '1', '81', '3');
INSERT INTO `items` VALUES ('119', '1', '81', '2');
INSERT INTO `items` VALUES ('120', '1', '82', '3');
INSERT INTO `items` VALUES ('121', '1', '82', '2');
INSERT INTO `items` VALUES ('122', '1', '83', '3');
INSERT INTO `items` VALUES ('123', '2', '83', '2');
INSERT INTO `items` VALUES ('124', '1', '84', '2');
INSERT INTO `items` VALUES ('125', '1', '85', '2');
INSERT INTO `items` VALUES ('126', '1', '86', '4');
INSERT INTO `items` VALUES ('127', '1', '87', '12');
INSERT INTO `items` VALUES ('128', '1', '88', '4');
INSERT INTO `items` VALUES ('129', '1', '89', '4');
INSERT INTO `items` VALUES ('130', '1', '90', '8');
INSERT INTO `items` VALUES ('131', '1', '91', '6');
INSERT INTO `items` VALUES ('132', '1', '92', '11');
INSERT INTO `items` VALUES ('133', '1', '93', '9');
INSERT INTO `items` VALUES ('134', '1', '94', '4');

-- ----------------------------
-- Table structure for `shipping`
-- ----------------------------
DROP TABLE IF EXISTS `shipping`;
CREATE TABLE `shipping` (
  `shipid` bigint(20) NOT NULL AUTO_INCREMENT,
  `method` varchar(255) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`shipid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shipping
-- ----------------------------
INSERT INTO `shipping` VALUES ('1', 'Standard Shipping', '15');
INSERT INTO `shipping` VALUES ('2', 'Express Shipping', '25');
INSERT INTO `shipping` VALUES ('3', 'One-Day Shipping', '35');

-- ----------------------------
-- Table structure for `tableo`
-- ----------------------------
DROP TABLE IF EXISTS `tableo`;
CREATE TABLE `tableo` (
  `orderid` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `status` char(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  `addrid` bigint(20) DEFAULT NULL,
  `billid` bigint(20) DEFAULT NULL,
  `shipid` bigint(20) DEFAULT NULL,
  `taxid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `user_cus` (`userid`) USING BTREE,
  KEY `add_cus` (`addrid`) USING BTREE,
  KEY `ship_cus` (`shipid`) USING BTREE,
  KEY `tax_cus` (`taxid`) USING BTREE,
  KEY `order_bill` (`billid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tableo
-- ----------------------------
INSERT INTO `tableo` VALUES ('39', '2015-10-20 22:42:08.706', '1', '191.28', '1', '4', '0', '1', '1');
INSERT INTO `tableo` VALUES ('40', '2015-10-20 22:43:33.086', '1', '191.28', '1', '2', '0', '1', '1');
INSERT INTO `tableo` VALUES ('41', '2015-10-20 22:47:29.568', '1', '191.28', '1', '8', '0', '1', '1');
INSERT INTO `tableo` VALUES ('42', '2015-10-20 23:00:01.440', '1', '191.28', '1', '9', '5', '1', '1');
INSERT INTO `tableo` VALUES ('43', '2015-10-21 24:03:07.434', '1', '191.28', '1', '6', '0', '1', '1');
INSERT INTO `tableo` VALUES ('44', '2015-10-21 24:04:13.612', '1', '191.28', '1', '7', '0', '1', '1');
INSERT INTO `tableo` VALUES ('45', '2015-10-21 24:05:10.271', '1', '191.28', '1', '8', '0', '1', '1');
INSERT INTO `tableo` VALUES ('46', '2015-10-21 24:06:31.430', '1', '191.28', '1', '9', '0', '1', '1');
INSERT INTO `tableo` VALUES ('47', '2015-10-21 24:07:22.126', '1', '191.28', '1', '10', '0', '1', '1');
INSERT INTO `tableo` VALUES ('48', '2015-10-21 24:13:10.924', '1', '191.28', '1', '11', '0', '1', '1');
INSERT INTO `tableo` VALUES ('49', '2015-10-21 24:13:48.632', '1', '191.28', '1', '12', '0', '1', '1');
INSERT INTO `tableo` VALUES ('50', '2015-10-21 24:14:55.569', '1', '191.28', '1', '13', '0', '1', '1');
INSERT INTO `tableo` VALUES ('51', '2015-10-21 24:18:08.112', '1', '191.28', '1', '14', '0', '1', '1');
INSERT INTO `tableo` VALUES ('52', '2015-10-21 24:23:11.614', '1', '191.28', '1', '15', '15', '1', '1');
INSERT INTO `tableo` VALUES ('53', '2015-10-21 24:32:24.517', '1', '191.28', '1', '16', '0', '1', '1');
INSERT INTO `tableo` VALUES ('54', '2015-10-21 24:33:09.278', '1', '191.28', '1', '1', '17', '1', '1');
INSERT INTO `tableo` VALUES ('55', '2015-10-21 24:34:25.783', '1', '191.28', '1', '9', '18', '1', '1');
INSERT INTO `tableo` VALUES ('56', '2015-10-21 24:36:16.672', '1', '191.28', '1', '9', '19', '1', '1');
INSERT INTO `tableo` VALUES ('57', '2015-10-21 24:38:46.451', '1', '191.28', '1', '9', '20', '1', '1');
INSERT INTO `tableo` VALUES ('58', '2015-10-21 24:41:34.295', '1', '191.28', '1', '9', '21', '1', '1');
INSERT INTO `tableo` VALUES ('59', '2015-10-21 24:44:26.440', '1', '191.28', '1', '9', '22', '1', '1');
INSERT INTO `tableo` VALUES ('60', '2015-10-21 24:48:03.793', '1', '191.28', '1', '9', '23', '1', '1');
INSERT INTO `tableo` VALUES ('61', '2015-10-21 24:49:04.187', '1', '191.28', '1', '9', '24', '1', '1');
INSERT INTO `tableo` VALUES ('62', '2015-10-21 24:51:18.936', '1', '191.28', '1', '10', '26', '1', '1');
INSERT INTO `tableo` VALUES ('63', '2015-10-22 10:55:08.877', '0', '80.54', '1', '1', '27', '1', '1');
INSERT INTO `tableo` VALUES ('64', '2015-10-22 10:56:33.783', '1', '80.54', '1', '1', '28', '1', '1');
INSERT INTO `tableo` VALUES ('65', '2015-10-22 11:01:30.240', '1', '80.54', '1', '1', '29', '1', '1');
INSERT INTO `tableo` VALUES ('66', '2015-10-22 11:01:48.212', '1', '80.54', '1', '1', '30', '1', '1');
INSERT INTO `tableo` VALUES ('67', '2015-10-22 11:02:04.770', '0', '80.54', '1', '1', '31', '1', '1');
INSERT INTO `tableo` VALUES ('68', '2015-10-22 12:05:23.248', '0', '80.54', '1', '1', '32', '1', '1');
INSERT INTO `tableo` VALUES ('69', '2015-10-22 12:07:03.458', '1', '80.54', '1', '1', '33', '1', '1');
INSERT INTO `tableo` VALUES ('70', '2015-10-22 12:08:23.451', '1', '66.98', '1', '1', '34', '1', '1');
INSERT INTO `tableo` VALUES ('71', '2015-10-24 20:35:21.414', '0', '66.98', '1', '0', '0', '1', '1');
INSERT INTO `tableo` VALUES ('72', '2015-10-24 20:35:57.766', '0', '66.98', '1', '1', '0', '1', '1');
INSERT INTO `tableo` VALUES ('73', '2015-10-24 20:43:38.101', '0', '66.98', '1', '1', '0', '1', '1');
INSERT INTO `tableo` VALUES ('74', '2015-10-24 20:46:15.068', '0', '66.98', '1', '2', '0', '1', '1');
INSERT INTO `tableo` VALUES ('75', '2015-10-24 20:48:04.375', '0', '66.98', '1', '1', '35', '1', '1');
INSERT INTO `tableo` VALUES ('76', '2015-10-24 20:54:39.370', '0', '66.98', '1', '2', '36', '1', '1');
INSERT INTO `tableo` VALUES ('77', '2015-10-24 20:58:11.726', '0', '66.98', '1', '2', '37', '1', '1');
INSERT INTO `tableo` VALUES ('78', '2015-10-24 21:00:17.924', '1', '66.98', '1', '2', '38', '1', '1');
INSERT INTO `tableo` VALUES ('79', '2015-10-31 23:30:08.167', '0', '152.52', '1', '1', '39', '3', '1');
INSERT INTO `tableo` VALUES ('80', '2015-10-31 23:37:14.134', '0', '132.52', '1', '1', '40', '1', '1');
INSERT INTO `tableo` VALUES ('81', '2015-10-31 23:45:49.320', '0', '132.52', '1', '1', '41', '1', '1');
INSERT INTO `tableo` VALUES ('82', '2015-10-31 23:52:16.949', '1', '132.52', '1', '1', '42', '1', '1');
INSERT INTO `tableo` VALUES ('83', '2015-11-01 14:25:28.677', '1', '184.5', '1', '1', '43', '1', '1');
INSERT INTO `tableo` VALUES ('84', '2015-11-01 14:29:27.187', '0', '66.98', '1', '1', '44', '1', '1');
INSERT INTO `tableo` VALUES ('85', '2015-11-01 14:42:30.292', '1', '66.98', '1', '1', '45', '1', '1');
INSERT INTO `tableo` VALUES ('86', '2015-11-01 14:42:58.661', '1', '51.16', '1', '1', '46', '1', '1');
INSERT INTO `tableo` VALUES ('87', '2015-11-01 14:43:17.090', '1', '65.85', '1', '1', '47', '1', '1');
INSERT INTO `tableo` VALUES ('88', '2015-11-01 14:44:41.768', '0', '51.16', '1', '1', '48', '1', '1');
INSERT INTO `tableo` VALUES ('89', '2015-11-01 14:49:47.167', '1', '51.16', '1', '1', '49', '1', '1');
INSERT INTO `tableo` VALUES ('90', '2015-11-01 14:50:07.895', '1', '53.42', '1', '1', '50', '1', '1');
INSERT INTO `tableo` VALUES ('91', '2015-11-01 14:50:26.833', '1', '65.85', '1', '1', '51', '1', '1');
INSERT INTO `tableo` VALUES ('92', '2015-11-01 14:50:45.684', '1', '60.2', '1', '1', '52', '1', '1');
INSERT INTO `tableo` VALUES ('93', '2015-11-01 14:51:10.735', '1', '57.94', '1', '2', '53', '1', '1');
INSERT INTO `tableo` VALUES ('94', '2015-11-01 14:51:31.627', '1', '51.16', '1', '1', '54', '1', '1');

-- ----------------------------
-- Table structure for `tax`
-- ----------------------------
DROP TABLE IF EXISTS `tax`;
CREATE TABLE `tax` (
  `taxid` bigint(20) NOT NULL AUTO_INCREMENT,
  `taxname` varchar(255) NOT NULL,
  `taxrate` float(255,2) NOT NULL,
  PRIMARY KEY (`taxid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tax
-- ----------------------------
INSERT INTO `tax` VALUES ('1', 'HST', '0.13');
