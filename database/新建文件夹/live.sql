/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : live

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-02-27 22:33:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for anchor
-- ----------------------------
DROP TABLE IF EXISTS `anchor`;
CREATE TABLE `anchor` (
  `userId` int(11) NOT NULL COMMENT '主播id==用户id',
  `anchorCategory` varchar(20) DEFAULT NULL COMMENT '主播类别：分为组织，学院，学生，教师等等',
  `playTime` int(11) DEFAULT NULL COMMENT '播放总时长',
  `totalUpvoteNum` int(11) DEFAULT NULL COMMENT '总共被点赞个数',
  `followedNumber` int(11) DEFAULT NULL COMMENT '总共被关注个数',
  `applyAnchorTime` date DEFAULT NULL COMMENT '注册主播时间',
  `totalGiftsNumber` int(11) DEFAULT NULL COMMENT '获得礼物个数',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主播表';

-- ----------------------------
-- Records of anchor
-- ----------------------------
INSERT INTO `anchor` VALUES ('1', '教师', '1000', '500', '101', '2017-07-17', '100');
INSERT INTO `anchor` VALUES ('2', '学生', '1000', '500', '342', null, '20');
INSERT INTO `anchor` VALUES ('17', '1', '1', '1', '1', '2017-07-15', '1');
INSERT INTO `anchor` VALUES ('18', '1', '1', '1', '1', '2017-07-15', '1');

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `userId` int(11) DEFAULT NULL,
  `anchorId` int(11) DEFAULT NULL,
  `followTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注表(收藏表)';

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES ('1', '2', '2017-07-25 19:17:30');
INSERT INTO `follow` VALUES ('1', '1', '2017-07-27 19:31:29');

-- ----------------------------
-- Table structure for live
-- ----------------------------
DROP TABLE IF EXISTS `live`;
CREATE TABLE `live` (
  `liveId` int(11) NOT NULL AUTO_INCREMENT COMMENT '直播id，自增长，主键，',
  `userId` int(11) NOT NULL COMMENT '用户id==主播id 外键',
  `liveName` varchar(20) DEFAULT NULL COMMENT '直播名称',
  `liveDescription` varchar(50) DEFAULT NULL COMMENT '直播描述',
  `liveType` varchar(20) DEFAULT NULL COMMENT '直播类型：体育，娱乐，官方',
  `livePwd` varchar(50) DEFAULT NULL COMMENT '直播密码',
  `liveStartTime` datetime DEFAULT NULL COMMENT '直播开始时间',
  `liveEndTime` date DEFAULT NULL COMMENT '直播结束时间',
  `audienceNum` int(11) DEFAULT NULL COMMENT '当前观众个数',
  `upvoteNum` int(11) DEFAULT NULL COMMENT '当前点赞个数',
  `giftNum` int(11) DEFAULT NULL COMMENT '当前礼物个数',
  `screenShot` varchar(50) DEFAULT NULL COMMENT '最新截图地址',
  `liveUrl` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`liveId`),
  UNIQUE KEY `live_liveId_uindex` (`liveId`),
  KEY `live_anchor_userId_fk` (`userId`),
  CONSTRAINT `live_anchor_userId_fk` FOREIGN KEY (`userId`) REFERENCES `anchor` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='直播表';

-- ----------------------------
-- Records of live
-- ----------------------------
INSERT INTO `live` VALUES ('1', '7', 'cctv5', '', '1', '', '2017-07-15 00:03:00', '2017-07-15', '1', '41', '0', 'live_04.png', 'http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8');
INSERT INTO `live` VALUES ('2', '19', '香港电视台', '', '1', '', '2017-07-15 00:00:00', '2017-07-15', '3', '1', '0', 'live_02.png', 'rtmp://live.hkstv.hk.lxdns.com/live/hks');
INSERT INTO `live` VALUES ('3', '1', '直播3', '', '2', '', '2017-07-15 00:00:00', '2017-07-15', '3', '0', '0', 'live_01.png', 'rtmp://live.hkstv.hk.lxdns.com/live/hks');
INSERT INTO `live` VALUES ('4', '19', '宋小宝', '', '3', '', '2017-07-15 00:00:00', '2017-07-15', '4', '0', '0', 'xiaobao.png', 'http://rotation.vod.zlive.cc/channel/1578.m3u8');
INSERT INTO `live` VALUES ('5', '19', 'CCTV-6电影', '', '4', '', '2017-07-15 00:00:00', '2006-01-01', '5', '0', '0', 'live_04.png', 'http://223.110.243.140/PLTV/2510088/224/3221227145/1.m3u8');
INSERT INTO `live` VALUES ('6', '19', 'CCTV-4中文国际', '', '5', '', '2017-07-15 00:00:00', '2017-07-15', '7', '0', '0', 'live_02.png', 'http://223.110.243.138/PLTV/2510088/224/3221227162/1.m3u8');
INSERT INTO `live` VALUES ('7', '1', '直播7', '', '1', '', '2017-07-15 00:04:00', '2017-07-15', '8', '0', '0', 'live_01.png', 'rtmp://wv4.tp33.net:1935/sat/md021');
INSERT INTO `live` VALUES ('8', '1', '直播8', '1', '3', '1', '2017-01-02 00:02:00', '2017-01-02', '7', '1', '1', 'live_03.png', 'rtmp://live.hkstv.hk.lxdns.com/live/hks');
INSERT INTO `live` VALUES ('9', '20', '直播9', '2', '2', '2', '2017-11-01 00:00:00', null, '0', '0', '0', 'live_02.png', 'rtmp://live.hkstv.hk.lxdns.com/live/hks');
INSERT INTO `live` VALUES ('10', '1', '直播10', '213', '4', '213', '2017-11-01 00:00:00', null, '0', '0', '0', 'live_03.png', 'rtmp://live.hkstv.hk.lxdns.com/live/hks');
INSERT INTO `live` VALUES ('11', '20', 'NewTV-中国功夫', '', '20', '', '2017-11-07 00:00:00', null, '0', '0', '0', 'live_01.png', 'http://223.110.245.135/PLTV/3/224/3221226935/index.m3u8');
INSERT INTO `live` VALUES ('12', '21', 'HD电影', '2', '2', '2', '2018-03-06 00:00:00', null, '0', '0', '0', 'live_01.png', 'http://rotation.vod.zlive.cc/channel/1260.m3u8');
INSERT INTO `live` VALUES ('13', '21', 'HKS', 'REWWERF', '1', '333', '2017-11-07 00:00:00', null, '0', '0', '0', 'live_04.png', 'rtmp://live.hkstv.hk.lxdns.com/live/hks');
INSERT INTO `live` VALUES ('14', '21', 'HKS', 'REWWERF', '4', '333', '2017-11-07 00:00:00', null, '0', '0', '0', 'live_01.png', 'rtmp://live.hkstv.hk.lxdns.com/live/hks');
INSERT INTO `live` VALUES ('15', '19', 'CCTV-7军事农业', 'REWWERF', '2', '333', '2017-11-07 00:00:00', null, '0', '0', '0', 'live_04.png', 'http://223.110.243.136/PLTV/2510088/224/3221227142/1.m3u8');
INSERT INTO `live` VALUES ('16', '19', '中华卫视神州台', 'REWWERF', '3', '333', '2017-11-07 00:00:00', null, '0', '0', '0', 'live_02.png', 'http://pull.kapad.cn/live/livechtv/playlist.m3u8');
INSERT INTO `live` VALUES ('17', '19', '怀旧剧场', 'REWWERF', '4', '333', '2017-11-07 00:00:00', null, '0', '0', '0', 'live_03.png', 'http://223.110.243.170/PLTV/2510088/224/3221227346/1.m3u8');
INSERT INTO `live` VALUES ('18', '1', '直播19', '12', '5', '12', '2017-11-07 00:00:00', null, '0', '0', '0', 'live_04.png', 'rtmp://live.hkstv.hk.lxdns.com/live/hks');
INSERT INTO `live` VALUES ('19', '19', 'cctv1', '====', '5', '123', '2017-11-07 00:00:00', null, '0', '0', '0', 'live_03.png', 'http://223.110.243.138/PLTV/2510088/224/3221227177/1.m3u8');
INSERT INTO `live` VALUES ('20', '1', '直播21', '这是描述', '2', '123', '2017-11-07 00:00:00', null, '0', '0', '0', 'live_02.png', 'rtmp://live.hkstv.hk.lxdns.com/live/hks');
INSERT INTO `live` VALUES ('21', '7', 'cctv5', '反反复复', '2', '', '2018-02-14 00:54:30', null, '111', '22', '0', 'live_02.png', 'http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8');
INSERT INTO `live` VALUES ('22', '1', '直播测试', '666666666666666', '6666666666', '6666666666666666', '2018-02-28 21:55:05', null, '0', '0', '0', 'live_01.png', 'http://bw.voovo.top/mytv.php?id=litv-ftv15');
INSERT INTO `live` VALUES ('23', '1', '应该穿件来', '啦啦啦，我是快乐的小行家', '6', '', '2018-03-02 20:45:11', null, '0', '0', '0', '1.jpg', 'rtmp://wenchuqi.cn/live/def');
INSERT INTO `live` VALUES ('24', '1', '应该穿件来', '啦啦啦，我是快乐的小行家', '6', '', '2018-03-02 20:47:03', null, '0', '0', '0', '1.jpg', 'rtmp://wenchuqi.cn/live/1');
INSERT INTO `live` VALUES ('25', '19', '炫动卡通', '动漫', '19', '', '2018-03-06 21:55:05', null, '0', '0', '0', 'live_01.png', 'http://223.110.243.158/PLTV/3/224/3221225694/index.m3u8');
INSERT INTO `live` VALUES ('26', '19', '电影-非常营救', '666666666666666', '6666666666', '6666666666666666', '2018-02-28 21:55:05', null, '0', '0', '0', 'live_01.png', 'http://chyd-wsvod.wasu.tv/data9/ott/344/2013-06/07/1370600803122_737204/playlist.m3u8');
INSERT INTO `live` VALUES ('27', '19', '电影-公路美人', '666666666666666', '6666666666', '6666666666666666', '2018-02-28 21:55:05', null, '0', '0', '0', 'live_01.png', 'http://chyd-wsvod.wasu.tv/data13/ott/344/2015-05/28/1432782476341_377935/playlist.m3u8');
INSERT INTO `live` VALUES ('28', '19', '电影-博物馆奇妙夜3', '666666666666666', '6666666666', '6666666666666666', '2018-02-28 21:55:05', null, '0', '0', '0', 'live_01.png', 'http://chyd-wsvod.wasu.tv/data12/ott/344/2015-03/05/1425521511475_343177/playlist.m3u8');
INSERT INTO `live` VALUES ('29', '19', '电影-涉足荒野', '666666666666666', '6666666666', '6666666666666666', '2018-02-28 21:55:05', null, '0', '0', '0', 'http://rotation.vod.zlive.cc/channel/1086.m3u8', 'http://chyd-wsvod.wasu.tv/data13/ott/344/2015-06/01/1433146056644_381163/playlist.m3u8');
INSERT INTO `live` VALUES ('30', '2', '欢乐喜剧人', '啦啦啦，我是快乐的小行家', '6', '', '2018-03-03 15:02:26', null, '0', '0', '0', 'xijuren.png', 'http://rotation.vod.zlive.cc/channel/1925.m3u8');
INSERT INTO `live` VALUES ('31', '2', '恐怖电影', '啦啦啦，我是快乐的小行家', '6', '', '2018-03-03 15:12:58', null, '0', '0', '0', '2_45.jpg', 'http://rotation.vod.zlive.cc/channel/2284.m3u8');
INSERT INTO `live` VALUES ('32', '2', '相声频道', '啦啦啦，我是快乐的小行家', '6', '', '2018-03-03 21:25:52', null, '0', '0', '0', 'xiangsheng.png', 'http://rotation.vod.zlive.cc/channel/2418.m3u8');
INSERT INTO `live` VALUES ('33', '19', 'CCTV-3综艺', '啦啦啦，我是快乐的小行家', '6', '', '2018-03-03 21:27:34', null, '0', '0', '0', '1_99.jpg', 'http://223.110.243.140/PLTV/2510088/224/3221227165/1.m3u8');
INSERT INTO `live` VALUES ('34', '7', '五星体育高清', '啦啦啦，我是快乐的小行家', '6', '', '2018-03-04 19:17:18', null, '0', '0', '0', '1_99.jpg', 'http://hls.mv.wa5.com/live/gssports1_900/playlist.m3u8');
INSERT INTO `live` VALUES ('35', '19', 'NewTV-动画王国', '动画片', '19', '', '2018-03-04 19:17:18', null, '0', '0', '0', '1_99.jpg', 'http://111.12.130.68/PLTV/88888888/224/3221225727/index.m3u8');
INSERT INTO `live` VALUES ('36', '19', '第1财经', '财经频道', '2', '', '2018-03-06 19:17:18', null, '0', '0', '0', '1_99.jpg', 'http://w1.livecache.yicai.com/hls/live/CBN_hd/live.m3u8');
INSERT INTO `live` VALUES ('37', '19', '香港影展1086', '惊悚 恐怖 吓人', '3', null, '2018-04-05 22:26:47', null, '0', '0', '0', 'live_01.png', 'http://rotation.vod.zlive.cc/channel/1086.m3u8');
INSERT INTO `live` VALUES ('38', '19', '哆啦A梦', '动画片', '19', null, '2018-03-06 10:25:20', null, '0', '0', '0', '1_99.jpg', 'http://rotation.vod.zlive.cc/channel/1092.m3u8');
INSERT INTO `live` VALUES ('39', '19', '蜡笔小新', '动画片', '19', null, '2018-03-06 10:28:48', null, '0', '0', '0', '1_99.jpg', 'http://rotation.vod.zlive.cc/channel/1093.m3u8');
INSERT INTO `live` VALUES ('40', '19', '河北少儿', '少儿频道', '19', null, '2018-03-06 10:32:29', null, '0', '0', '0', '1_99.jpg', 'http://weblive.hebtv.com/live/hbse_bq/index.m3u8');
INSERT INTO `live` VALUES ('41', '1', '悟空传', '试试链接', '19', null, '2018-03-06 12:50:12', null, '0', '0', '0', '1_99.jpg', 'http://pl.cp31.ott.cibntv.net/playlist/m3u8?vid=XMjk3ODcwNzgzMg%3D%3D&type=mp4&ups_client_netip=1bbb3289&utid=fDXdEb0ybAUCARu7MEUdxII8&ccode=0508&psid=31a0c82f20c40b9e80dc6428eeb39e96&ups_userid=91295141&ups_ytid=559407493&duration=7371&expire=18000&ups_ts=1520311548&onOff=0&encr=0&ups_key=bb4a9abe2c70d439f69fb81d608ec916');
INSERT INTO `live` VALUES ('42', '1', '羞羞的铁拳', '试试链接', '19', null, '2018-03-06 13:00:43', null, '0', '0', '0', '1_99.jpg', 'http://disp.titan.mgtv.com/vod.do?fmt=4&pno=2010&fid=F97FDA7D984F7E9CA8CC706C88558E3C&file=/c1/2017/12/21_0/F97FDA7D984F7E9CA8CC706C88558E3C_20171221_1_1_642.mp4');
INSERT INTO `live` VALUES ('43', '1', '缝纫机乐队', '试试链接', '19', null, '2018-03-06 13:14:32', null, '0', '0', '0', '1_99.jpg', 'http://disp.titan.mgtv.com/vod.do?fmt=4&pno=2010&fid=47EE16C3DF82496A0976552FC3DCCA0C&file=/c1/2017/12/06_0/47EE16C3DF82496A0976552FC3DCCA0C_20171206_1_1_998.mp4');
INSERT INTO `live` VALUES ('44', '1', '侠盗联盟', '试试链接', '19', null, '2018-03-07 12:12:38', null, '0', '0', '0', '1_99.jpg', 'http://pl.cp31.ott.cibntv.net/playlist/m3u8?vid=XMzA4MjMyOTA1Ng%3D%3D&type=mp4&ups_client_netip=1bbb3289&utid=fDXdEb0ybAUCARu7MEUdxII8&ccode=0508&psid=1af3ae6bba58a5871be6dd95fe55a33b&ups_userid=91295141&ups_ytid=559407493&duration=6432&expire=18000&ups_ts=1520395578&onOff=0&encr=0&ups_key=6965e2da65882c27d131bc2db1cf5c6c');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `organizationName` varchar(50) DEFAULT NULL COMMENT '组织名称',
  `number` int(11) DEFAULT NULL COMMENT '学号或者编号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `college` varchar(50) DEFAULT NULL COMMENT '学院',
  `nickName` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `signature` varchar(100) DEFAULT NULL COMMENT '个性签名',
  `phoneNumber` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `whetherAnchor` int(11) DEFAULT NULL COMMENT '是否主播',
  `followNumber` int(11) DEFAULT NULL COMMENT '关注个数',
  `registrationTime` date DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `User_userId_uindex` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '组织aii', '111111', '12', '王涛', '数信', '大伟', 'live_01.png', '打球', '12321321321', '1', '30', '2017-07-17');
INSERT INTO `user` VALUES ('2', 'aaaa', '111112', '123456', 'test', '数信学院', '一剑飘飘', 'live_02.png', '111', '2314321', '0', '30', '2017-07-18');
INSERT INTO `user` VALUES ('3', '', '222', '', '', '', '', 'live_03.png', '', '', '1', '0', '2017-07-19');
INSERT INTO `user` VALUES ('4', '', '314', '', '', '', '', 'live_04.png', '', null, '1', '0', '2017-07-19');
INSERT INTO `user` VALUES ('5', '', '3333333', '', '', '', '', 'live_01.png', '', '', '0', '0', '2017-07-19');
INSERT INTO `user` VALUES ('6', '', '22111', '', '', '', '', 'live_02.png', '', '', '0', '0', '2017-07-19');
INSERT INTO `user` VALUES ('7', '', '1112', '', '', '', '', 'live_03.png', '', '', '0', '0', '2017-07-19');
INSERT INTO `user` VALUES ('8', '', '11111', '', '', '', '', 'live_04.png', '', '', '0', '0', '2017-07-19');
INSERT INTO `user` VALUES ('46', null, null, null, '阿三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('47', null, null, null, '张三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('48', null, null, null, '张三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('49', null, null, null, '阿三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('50', null, null, null, '阿三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('51', null, null, null, '阿三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('52', null, null, null, '阿三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('53', null, null, null, '阿三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('54', null, null, null, '阿三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('55', null, null, null, '阿三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('56', null, null, null, '阿三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('57', null, null, null, '阿三', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('58', null, '21321', '12', null, '324', null, null, '得到的', null, '0', '0', '2018-03-25');
INSERT INTO `user` VALUES ('59', null, '324432', '123', null, 'dfsa', 'sdf', null, 'dsf', '213', '0', '0', '2018-03-25');
INSERT INTO `user` VALUES ('60', null, '34321', 'df', null, 'cv', 'fds', null, 'dsaf', '3214', '0', '0', '2018-03-28');

-- ----------------------------
-- Table structure for view
-- ----------------------------
DROP TABLE IF EXISTS `view`;
CREATE TABLE `view` (
  `liveId` int(11) DEFAULT NULL COMMENT '直播id',
  `userId` int(11) DEFAULT NULL COMMENT '主播id==用户id',
  `viewDuration` int(11) DEFAULT NULL COMMENT '观看时长',
  `userUpvoteNum` int(11) DEFAULT NULL COMMENT '用户点赞个数',
  `sendGiftNum` int(11) DEFAULT NULL COMMENT '送礼物个数',
  `viewTime` date DEFAULT NULL COMMENT '开始观看时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='观看表';

-- ----------------------------
-- Records of view
-- ----------------------------
INSERT INTO `view` VALUES ('1', '2', null, '15', '10', '2017-07-22');
