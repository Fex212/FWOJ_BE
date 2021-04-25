/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : onlineJudge

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 25/04/2021 19:17:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `title` varchar(40) DEFAULT '',
  `content` varchar(5005) DEFAULT '',
  `authorId` int(11) DEFAULT '0',
  `visible` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1015 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of announcement
-- ----------------------------
BEGIN;
INSERT INTO `announcement` VALUES (1000, '2020-11-12 16:00:26', '如何将默认语言改为中文', '本来就是中文qaq', 1000, 1);
INSERT INTO `announcement` VALUES (1001, '2020-11-12 16:51:26', '山东建筑大学第八届ICPC校赛参赛通知', '明天就是校赛了，大家今晚好好休息！明天一定超常发挥！\n比赛地点是：信息楼207，209\n明天上午10点开始签到（签到时间很长，不用着急），10点半开始热身赛，11点开始正式赛，下午四点比赛结束，大家可以早一点过来找到自己的对应的座号，调试机器。然后呢，可以食物入场（毕竟得吃午饭对吧，但是不要带味儿大的，不然可能被赶出去，建议带面包）\n然后，参加校赛的同学可以去pu口袋上报名，签到成功后据说有0.08分！！！！\n校赛不允许携带任何电子设备哦，要是被发现携带电子设备，可能会被我们当场锤死，当然，你们可以自行准备纸质资料，这个我们很提倡。\n好！大家今晚好好休息吧！', 1000, 1);
INSERT INTO `announcement` VALUES (1002, '2020-11-12 16:51:31', '山东建筑大学第九届ICPC校赛参赛通知', '明天就是校赛了，大家今晚好好休息！明天一定超常发挥！\n比赛地点是：信息楼207，209\n明天上午10点开始签到（签到时间很长，不用着急），10点半开始热身赛，11点开始正式赛，下午四点比赛结束，大家可以早一点过来找到自己的对应的座号，调试机器。然后呢，可以食物入场（毕竟得吃午饭对吧，但是不要带味儿大的，不然可能被赶出去，建议带面包）\n然后，参加校赛的同学可以去pu口袋上报名，签到成功后据说有0.08分！！！！\n校赛不允许携带任何电子设备哦，要是被发现携带电子设备，可能会被我们当场锤死，当然，你们可以自行准备纸质资料，这个我们很提倡。\n好！大家今晚好好休息吧！', 1000, 1);
INSERT INTO `announcement` VALUES (1003, '2020-11-12 16:51:21', '休伯利安执勤规定Beta3', '德丽莎不要随便拿十字架打人', 1000, 1);
INSERT INTO `announcement` VALUES (1004, '2020-11-12 16:51:18', 'onlineJudge网站变更通知', '由于维护人员变更，我校OnlineJudge(OJ)网址改为 oj.k423.tech (不会影响数据)，造成的不便请大家谅解', 1000, 0);
INSERT INTO `announcement` VALUES (1005, '2020-11-12 16:51:20', '校赛奖品领取通知', '第七届ACM-ICPC 校赛奖品已经到位，请凭有效证件或获奖证书到信息楼315西领取\n获奖名单见群文件\n奖项如下： \n一等奖：键盘 / 小米手环4二选一\n二等奖：鼠标 / 耳机 二选一\n三等奖：充电宝 / U盘 二选一\n一血：纪念书签\n参与奖：凡是参与本次比赛的大一新生，且 \n在此次比赛中解出一道以上题目的同 \n学，均可以领取鼠标垫一张', 1000, 0);
INSERT INTO `announcement` VALUES (1006, '2020-11-12 16:51:19', '奖状领取须知', '因为校团委的老师比较忙，奖状盖章延后，领取奖状时间延后，奖状盖好章后会在群里通知。\n', 1000, 0);
INSERT INTO `announcement` VALUES (1007, '2020-12-08 10:39:07', '校赛榜单2', '校赛榜单在http://724vector.cn:82/比赛列表中“第七届校赛”查看，另外闭幕式短片在这看：https://www.bilibili.com/video/av77653838/', 1000, 1);
INSERT INTO `announcement` VALUES (1008, '2020-12-08 10:39:07', '参赛须知1', '明天信息楼206有考试，大家来的时候安静一点，不要打扰到考试的人。\n', 1000, 0);
INSERT INTO `announcement` VALUES (1009, '2020-12-08 10:39:10', '关于gets()', '使用C/C++答题的同学请注意，使用gets()函数会导致编译错误，请使用其他替代方法，例如c++中的cin.get()，C中的fgets(不是)\n', 1000, 1);
INSERT INTO `announcement` VALUES (1010, '2020-12-08 10:39:08', '赛事通告', '我们的国庆新生训练即将开始，题目已经就绪，希望同学们多多参与，这个对编程学习有帮助。\n做题中碰到的问题，都可以找管理询问。\n希望同学们可以在班里啥的帮着宣传一下哈，争取更多人的参与。\n\n祝同学们做题顺利，假期快乐。\n(附此次训练赛网址：\nhttp://724vector.cn:82/contest/45\n)', 1000, 1);
INSERT INTO `announcement` VALUES (1013, '2021-04-25 18:25:05', '测试公告', '测试内容体\n', 1000, 1);
COMMIT;

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(105) DEFAULT '',
  `des` varchar(1005) DEFAULT '',
  `input` varchar(255) DEFAULT '',
  `output` varchar(255) DEFAULT '',
  `inputExample` varchar(255) DEFAULT '',
  `outputExample` varchar(255) DEFAULT '',
  `hint` varchar(1005) DEFAULT '',
  `acSubmit` int(11) DEFAULT '0',
  `totalSubmit` int(11) DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `authorId` int(11) DEFAULT '0',
  `visible` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1015 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of problem
-- ----------------------------
BEGIN;
INSERT INTO `problem` VALUES (1000, '奶牛排队', '样例输出即可。', '无需输入', 'this is out', '无需输入', 'this is out', 'no hint', 1, 2, '2020-11-04 13:58:32', 1000, 1);
INSERT INTO `problem` VALUES (1013, 'A+B for Input-Output Practice', 'our task is to Calculate a + b. Too easy?! Of course! I specially designed the problem for acm beginners. You must have found that some problems have the same titles with this one, yes, all these problems were designed for the same aim。\nthe same line:our task is to Calculate a + b. Too easy?! Of course! I specially designed the problem for acm beginners. You must have found that some problems have the same titles with this one, yes, all these problems were designed for the same aim。', 'The input will consist of a series of pairs of integers a and b, separated by a space, one pair of integers per line.\n\n\n', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.\n\n\n', '1 5\n10 20', '6\n30', '这是一个求两数之和的题目，输入多对用空格分开的两个数a b，输出a+b的和，每一对数据的和占一行。\n编写代码时需要注意的是，由于没有指出有多少对输入数据，因此我们可以编写如下代码：\nC语言\n#include <stdio.h>\nint main() //把main函数定义成int类型\n{\n    int a,b;\n    while(scanf(\"%d %d\",&a, &b) != EOF) // 输入结束时，scanf函数返回值为EOF，即没有数据输入时则退出while循环\n        printf(\"%d\\n\",a+b);\n    return 0; //返回值为0\n}\nC++\n#include <iostream> //注意头文件的使用方法\nusing namespace std;\nint main()\n{\n    int a,b;\n    while(cin >> a >> b)\n        cout << a+b << endl;\n    return 0;\n}\n这道题目用以熟悉oj上的做题方式。\n真正的挑战在后面(关于题目不要害怕，并不是所有题都是英文的)', 1, 1, '2020-11-04 13:58:32', 1003, 1);
COMMIT;

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `problemId` int(11) DEFAULT '0',
  `authorId` int(11) DEFAULT '0',
  `date` datetime DEFAULT NULL,
  `language` varchar(255) DEFAULT '',
  `state` varchar(255) DEFAULT '',
  `compileInfo` varchar(5005) DEFAULT '',
  `memoryCost` int(11) DEFAULT '0',
  `timeCost` int(11) DEFAULT '0',
  `code` varchar(5005) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1094 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of state
-- ----------------------------
BEGIN;
INSERT INTO `state` VALUES (1000, 1000, 1000, '2021-04-25 18:27:28', 'C++', 'ce', 'testInfo', 0, 0, 'testCode');
INSERT INTO `state` VALUES (1092, 1000, 1000, '2021-04-25 19:16:47', 'C++', 'ac', '', 3399680, 0, '#include \"iostream\"\nusing namespace std;\nint main()\n{\ncout << \"this is out\";\n}');
INSERT INTO `state` VALUES (1093, 1013, 1000, '2021-04-25 19:17:22', 'C++', 'ac', '', 3403776, 2, '#include <iostream> //注意头文件的使用方法\nusing namespace std;\nint main()\n{\n    int a,b;\n    while(cin >> a >> b)\n        cout << a+b << endl;\n    return 0;\n}');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT '',
  `passwd` varchar(255) DEFAULT '',
  `type` varchar(255) DEFAULT '',
  `email` varchar(255) DEFAULT '',
  `available` int(1) DEFAULT '0',
  `token` varchar(255) DEFAULT '',
  `site` varchar(255) DEFAULT '',
  `github` varchar(255) DEFAULT '',
  `sign` varchar(255) DEFAULT '',
  `solvedList` varchar(255) DEFAULT '',
  `attemptList` varchar(255) DEFAULT '',
  `solvedNum` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1000, 'root', '4297f44b13955235245b2497399d7a93', 'admin', 'root@q.com1', 1, '161934499037946788062', 'http://k423.tech', 'https://github.com/teleport-10032', '「我将坠入苦痛 , 换你留在红莲」', '1000,1013', '', 2);
INSERT INTO `user` VALUES (1001, 'user1', '4297f44b13955235245b2497399d7a93', 'user', 'user1@q.com', 1, '160914538124477634192', 'about:blank', 'test', 'test', '', ' ', 0);
INSERT INTO `user` VALUES (1002, 'user2', '4297f44b13955235245b2497399d7a93', 'user', 'user2@q.com', 1, '160377279468058589669', 'about:blank', 'about:blank', '这里没有签名档', '', ' ', 0);
INSERT INTO `user` VALUES (1003, 'root2', '4297f44b13955235245b2497399d7a93', 'admin', 'root2@q.com', 1, '160871958310498213237', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
INSERT INTO `user` VALUES (1004, 'user3', '4297f44b13955235245b2497399d7a93', 'user', 'user3@qq.com', 1, '160370562155412856482', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
INSERT INTO `user` VALUES (1005, 'root3', 'f5bb0c8de146c67b44babbf4e6584cc0', 'admin', 'root3@qq.com', 1, '160704796884597835405', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
INSERT INTO `user` VALUES (1006, '123', '96e79218965eb72c92a549dd5a330112', 'user', '123@qq.com', 1, '160441634776886510589', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
INSERT INTO `user` VALUES (1007, 'root3', '4297f44b13955235245b2497399d7a93', 'admin', 'root3@qq.com', 1, '160717099931144582616', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
INSERT INTO `user` VALUES (1008, 'test', '4297f44b13955235245b2497399d7a93', 'user', 'test@qq.com', 1, '160873294480683217934', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
