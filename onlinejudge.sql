/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : onlinejudge

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 14/04/2021 21:06:39
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of announcement
-- ----------------------------
BEGIN;
INSERT INTO `announcement` VALUES (1, '2020-11-12 16:00:26', '如何将默认语言改为中文', '本来就是中文qaq', 1, 0);
INSERT INTO `announcement` VALUES (2, '2020-11-12 16:51:26', '山东建筑大学第八届ICPC校赛参赛通知', '明天就是校赛了，大家今晚好好休息！明天一定超常发挥！\n比赛地点是：信息楼207，209\n明天上午10点开始签到（签到时间很长，不用着急），10点半开始热身赛，11点开始正式赛，下午四点比赛结束，大家可以早一点过来找到自己的对应的座号，调试机器。然后呢，可以食物入场（毕竟得吃午饭对吧，但是不要带味儿大的，不然可能被赶出去，建议带面包）\n然后，参加校赛的同学可以去pu口袋上报名，签到成功后据说有0.08分！！！！\n校赛不允许携带任何电子设备哦，要是被发现携带电子设备，可能会被我们当场锤死，当然，你们可以自行准备纸质资料，这个我们很提倡。\n好！大家今晚好好休息吧！', 1, 1);
INSERT INTO `announcement` VALUES (3, '2020-11-12 16:51:31', '山东建筑大学第九届ICPC校赛参赛通知', '明天就是校赛了，大家今晚好好休息！明天一定超常发挥！\n比赛地点是：信息楼207，209\n明天上午10点开始签到（签到时间很长，不用着急），10点半开始热身赛，11点开始正式赛，下午四点比赛结束，大家可以早一点过来找到自己的对应的座号，调试机器。然后呢，可以食物入场（毕竟得吃午饭对吧，但是不要带味儿大的，不然可能被赶出去，建议带面包）\n然后，参加校赛的同学可以去pu口袋上报名，签到成功后据说有0.08分！！！！\n校赛不允许携带任何电子设备哦，要是被发现携带电子设备，可能会被我们当场锤死，当然，你们可以自行准备纸质资料，这个我们很提倡。\n好！大家今晚好好休息吧！', 1, 1);
INSERT INTO `announcement` VALUES (5, '2020-11-12 16:51:21', '休伯利安执勤规定Beta3', '德丽莎不要随便拿十字架打人', 1, 1);
INSERT INTO `announcement` VALUES (6, '2020-11-12 16:51:18', 'onlineJudge网站变更通知', '由于维护人员变更，我校OnlineJudge(OJ)网址改为 oj.k423.tech (不会影响数据)，造成的不便请大家谅解', 1, 0);
INSERT INTO `announcement` VALUES (7, '2020-11-12 16:51:20', '校赛奖品领取通知', '第七届ACM-ICPC 校赛奖品已经到位，请凭有效证件或获奖证书到信息楼315西领取\n获奖名单见群文件\n奖项如下： \n一等奖：键盘 / 小米手环4二选一\n二等奖：鼠标 / 耳机 二选一\n三等奖：充电宝 / U盘 二选一\n一血：纪念书签\n参与奖：凡是参与本次比赛的大一新生，且 \n在此次比赛中解出一道以上题目的同 \n学，均可以领取鼠标垫一张', 1, 0);
INSERT INTO `announcement` VALUES (8, '2020-11-12 16:51:19', '奖状领取须知', '因为校团委的老师比较忙，奖状盖章延后，领取奖状时间延后，奖状盖好章后会在群里通知。\n', 1, 0);
INSERT INTO `announcement` VALUES (9, '2020-12-08 10:39:07', '校赛榜单2', '校赛榜单在http://724vector.cn:82/比赛列表中“第七届校赛”查看，另外闭幕式短片在这看：https://www.bilibili.com/video/av77653838/', 1, 1);
INSERT INTO `announcement` VALUES (10, '2020-12-08 10:39:07', '参赛须知1', '明天信息楼206有考试，大家来的时候安静一点，不要打扰到考试的人。\n', 1, 0);
INSERT INTO `announcement` VALUES (11, '2020-12-08 10:39:10', '关于gets()', '使用C/C++答题的同学请注意，使用gets()函数会导致编译错误，请使用其他替代方法，例如c++中的cin.get()，C中的fgets(不是)\n', 1, 1);
INSERT INTO `announcement` VALUES (12, '2020-12-08 10:39:08', '赛事通告q', '我们的国庆新生训练即将开始，题目已经就绪，希望同学们多多参与，这个对编程学习有帮助。\n做题中碰到的问题，都可以找管理询问。\n希望同学们可以在班里啥的帮着宣传一下哈，争取更多人的参与。\n\n祝同学们做题顺利，假期快乐。\n(附此次训练赛网址：\nhttp://724vector.cn:82/contest/45\n)', 1, 1);
INSERT INTO `announcement` VALUES (15, '2020-12-08 11:32:24', 'test', 'success', 1, 1);
INSERT INTO `announcement` VALUES (16, '2020-12-23 18:45:04', 't', 'test', 0, 0);
INSERT INTO `announcement` VALUES (18, '2021-02-12 18:36:02', '111', '1111', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for contest
-- ----------------------------
DROP TABLE IF EXISTS `contest`;
CREATE TABLE `contest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT '',
  `des` varchar(255) DEFAULT '',
  `problemList` varchar(255) DEFAULT '',
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `visible` int(1) DEFAULT '0',
  `authorId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of contest
-- ----------------------------
BEGIN;
INSERT INTO `contest` VALUES (1, '第十六届山东建筑大学ICPC校赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2019-10-07 09:00:00', '2019-10-07 14:00:00', 0, 1);
INSERT INTO `contest` VALUES (2, '第十七届山东建筑大学ICPC校赛热身赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2020-10-10 09:00:00', '2020-11-12 14:00:00', 1, 4);
INSERT INTO `contest` VALUES (3, '第十七届山东建筑大学ICPC校赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2020-11-07 09:00:00', '2020-12-07 14:00:00', 1, 1);
INSERT INTO `contest` VALUES (4, '第十八届山东建筑大学ICPC校赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2020-10-07 09:00:00', '2021-10-07 14:00:00', 1, 1);
INSERT INTO `contest` VALUES (5, '第十九届山东建筑大学ICPC校赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2020-10-07 09:00:00', '2020-10-07 14:00:00', 1, 1);
INSERT INTO `contest` VALUES (6, '第二十届山东建筑大学ICPC校赛热身赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2020-10-07 09:00:00', '2020-10-07 14:00:00', 1, 1);
INSERT INTO `contest` VALUES (7, '123', '', '', '2020-11-03 00:00:00', '2020-11-12 00:00:00', 0, 4);
INSERT INTO `contest` VALUES (9, 'test', 't', '1,2', '2020-12-07 00:00:00', '2020-12-16 00:00:00', 1, 4);
INSERT INTO `contest` VALUES (10, 'test2', 's', 'aa', '2020-12-11 00:00:00', '2020-12-19 00:00:00', 1, 1);
INSERT INTO `contest` VALUES (11, 'test33', '1231', '123122', '2020-12-08 00:00:00', '2020-12-23 00:00:00', 0, 4);
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of problem
-- ----------------------------
BEGIN;
INSERT INTO `problem` VALUES (1, '奶牛排队', '奶牛排队', '这里是输入', '这里是输出', '无需输入', '这里是输出', '没有提示', 1, 5, '2020-11-04 13:58:32', 1, 1);
INSERT INTO `problem` VALUES (2, 'A+B for Input-Output Practice (1)', 'our task is to Calculate a + b. Too easy?! Of course! I specially designed the problem for acm beginners. You must have found that some problems have the same titles with this one, yes, all these problems were designed for the same aim', 'he input will consist of a series of pairs of integers a and b, separated by a space, one pair of integers per line.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '1 5\n10 20', '6\n30', '这是一个求两数之和的题目，输入多对用空格分开的两个数a b，输出a+b的和，每一对数据的和占一行。', 0, 0, '2020-11-04 13:58:32', 1, 0);
INSERT INTO `problem` VALUES (3, 'A+B for Input-Output Practice (1)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20', '6\n30', '（google翻译？看数据猜题目？）', 0, 0, '2020-11-04 13:58:32', 1, 0);
INSERT INTO `problem` VALUES (4, '帽子戏法', '三个人，竖着站成一排。有五个帽子，三个蓝色，两个红色，每人带一个，各自不准看自己的颜色。\n\n然后问第一个人带的什么颜色的帽子，他说不知道，然后又问第二个人带的什么颜色的帽子，同样说不知道，又问第三个人带的是什么颜色的帽子，他说我知道。\n\n问第三个人带的是什么色帽子?\n\n（第一个人站在排的最后,他可以看见前二个人的帽子的颜色)', '无', '如果红色输出“red”，如果蓝色输出“blue”', '无', '输出样例不见了\n', '无', 0, 0, '2020-11-04 13:58:32', 4, 0);
INSERT INTO `problem` VALUES (5, 'A+B for Input-Output Practice (1)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20', '无\n30', '（google翻译？看数据猜题目？）', 0, 0, '2020-11-04 13:58:35', 4, 0);
INSERT INTO `problem` VALUES (6, 'A+B for Input-Output Practice (4)', '三个人，竖着站成一排。有五个帽子，三个蓝色，两个红色，每人带一个，各自不准看自己的颜色。\n\n然后问第一个人带的什么颜色的帽子，他说不知道，然后又问第二个人带的什么颜色的帽子，同样说不知道，又问第三个人带的是什么颜色的帽子，他说我知道。\n\n问第三个人带的是什么色帽子?\n\n（第一个人站在排的最后,他可以看见前二个人的帽子的颜色)', '无', '如果红色输出“red”，如果蓝色输出“blue”', '无', '输出样例不见了\n', '无', 0, 0, '2020-11-04 13:58:32', 4, 0);
INSERT INTO `problem` VALUES (7, 'A+B for Input-Output Practice (5)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20', '6\n30', '（google翻译？看数据猜题目？）', 0, 0, '2020-11-04 13:58:32', 1, 0);
INSERT INTO `problem` VALUES (8, 'A+B for Input-Output Practice (6)', '三个人，竖着站成一排。有五个帽子，三个蓝色，两个红色，每人带一个，各自不准看自己的颜色。\n\n然后问第一个人带的什么颜色的帽子，他说不知道，然后又问第二个人带的什么颜色的帽子，同样说不知道，又问第三个人带的是什么颜色的帽子，他说我知道。\n\n问第三个人带的是什么色帽子?\n\n（第一个人站在排的最后,他可以看见前二个人的帽子的颜色)', 's', '这里是输出2', '无', '这里是输出2', '无', 1, 13, '2020-11-04 13:58:32', 1, 1);
INSERT INTO `problem` VALUES (9, 'A+B for Input-Output Practice (7)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20', '6\n30', '（google翻译？看数据猜题目？）', 0, 0, '2020-11-04 13:58:32', 1, 0);
INSERT INTO `problem` VALUES (10, 'A+B for Input-Output Practice (8)', '三个人，竖着站成一排。有五个帽子，三个蓝色，两个红色，每人带一个，各自不准看自己的颜色。\n\n然后问第一个人带的什么颜色的帽子，他说不知道，然后又问第二个人带的什么颜色的帽子，同样说不知道，又问第三个人带的是什么颜色的帽子，他说我知道。\n\n问第三个人带的是什么色帽子?\n\n（第一个人站在排的最后,他可以看见前二个人的帽子的颜色)', '无', '如果红色输出“red”，如果蓝色输出“blue”', '无', '输出样例不见了\n', '无1', 0, 0, '2020-11-04 13:58:32', 1, 0);
INSERT INTO `problem` VALUES (11, 'A+B for Input-Output Practice (9)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20', '6\n30', '（google翻译？看数据猜题目？）', 0, 0, '2020-11-04 13:58:32', 4, 0);
INSERT INTO `problem` VALUES (13, 'A+B for Input-Output Practice (100)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b\nThe first line integer means the number of input integer a and b. Your task is to Calculate a + b\nThe first line integer means the number of input integer a and b. Your task is to Calculate a + b\nThe first line integer means the number of input integer a and b. Your task is to Calculate a + b\nThe first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20 \ntest ', '6\n30', '（google翻译？看数据猜题目？）', 0, 0, '2020-11-04 13:58:32', 4, 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=1091 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of state
-- ----------------------------
BEGIN;
INSERT INTO `state` VALUES (1072, 1, 1, '2021-02-02 12:48:26', 'C++', 'ce', '/judger/run/119e402d6aab4f0486b1e7142299790f/main.c:1:1: error: \'http\' does not name a type\n http://localhost:8080/problemList\n ^~~~', 0, 0, 'http://localhost:8080/problemList');
INSERT INTO `state` VALUES (1073, 1, 1, '2021-02-02 12:49:11', 'C++', 'wa', '', 3379200, 1, '#include <iostream>\nusing namespace std;\nint main()\n{\n//    int a,b;\n//    while(cin >> a >> b)\n//        cout << a+b << endl;\n    cout << \"这里是输出2\";\n    return 0;\n}\n');
INSERT INTO `state` VALUES (1074, 1, 1, '2021-02-02 12:49:35', 'C++', 'ac', '', 3391488, 0, '#include <iostream>\nusing namespace std;\nint main()\n{\n//    int a,b;\n//    while(cin >> a >> b)\n//        cout << a+b << endl;\n    cout << \"这里是输出\";\n    return 0;\n}\n');
INSERT INTO `state` VALUES (1075, 1, 1, '2021-02-02 12:50:00', 'C++', 'ac', '', 3497984, 1, '#include <iostream>\nusing namespace std;\nint main()\n{\n//    int a,b;\n//    while(cin >> a >> b)\n//        cout << a+b << endl;\n    cout << \"这里是输出\";\n    return 0;\n}\n');
INSERT INTO `state` VALUES (1076, 8, 1, '2021-02-02 12:50:23', 'C++', 'ac', '', 3407872, 0, '#include <iostream>\nusing namespace std;\nint main()\n{\n//    int a,b;\n//    while(cin >> a >> b)\n//        cout << a+b << endl;\n    cout << \"这里是输出2\";\n    return 0;\n}\n');
INSERT INTO `state` VALUES (1077, 8, 1, '2021-02-02 12:50:47', 'C++', 'wa', '', 3440640, 2, '#include <iostream>\nusing namespace std;\nint main()\n{\n//    int a,b;\n//    while(cin >> a >> b)\n//        cout << a+b asdasd<< endl;\n    cout << \"这里是输出\";\n    return 0;\n}\n');
INSERT INTO `state` VALUES (1078, 8, 1, '2021-02-02 14:08:32', 'C++', 'se', 'failed to create runtime dir', 0, 0, '\n\n#include <iostream>\nusing namespace std;\nint main()\n{\n// int a,b;\n// while(cin >> a >> b)\n// cout << a+b asdasd<< endl;\ncout << \"这里是输出\";\nreturn 0;\n}\n\n');
INSERT INTO `state` VALUES (1079, 8, 1, '2021-02-02 14:09:40', 'C++', 'wa', '', 3289088, 1, '\n\n#include <iostream>\nusing namespace std;\nint main()\n{\n// int a,b;\n// while(cin >> a >> b)\n// cout << a+b asdasd<< endl;\ncout << \"这里是输出\";\nreturn 0;\n}\n\n');
INSERT INTO `state` VALUES (1080, 8, 1, '2021-02-02 14:10:09', 'C++', 'wa', '', 3440640, 0, '\n\n#include <iostream>\nusing namespace std;\nint main()\n{\n// int a,b;\n// while(cin >> a >> b)\n// cout << a+b asdasd<< endl;\ncout << \"这里是输出\";\nreturn 0;\n}\n\n');
INSERT INTO `state` VALUES (1081, 8, 1, '2021-02-12 10:13:51', 'C++', 'ce', '/judger/run/28b57f88219a4a7aaa9d5ef2c7a7c561/main.c:1:1: error: expected unqualified-id before numeric constant\n 111\n ^~~', 0, 0, '111');
INSERT INTO `state` VALUES (1082, 8, 1, '2021-02-12 10:21:56', 'C++', 'ce', '/judger/run/7c7ca998225f467d9737130e361421c8/main.c:1:1: error: expected unqualified-id before numeric constant\n 111\n ^~~', 0, 0, '111');
INSERT INTO `state` VALUES (1083, 8, 1, '2021-02-12 10:23:07', 'C++', 'ce', '/judger/run/7c4c8ac4cd2a4a2b8e2bfa289822eae3/main.c:1:1: error: \'asdasd\' does not name a type\n asdasd\n ^~~~~~', 0, 0, 'asdasd');
INSERT INTO `state` VALUES (1084, 1, 1, '2021-02-12 10:23:52', 'C++', 'ce', '/judger/run/3f6bcd571348464db81823f0cb46f914/main.c:1:1: error: expected unqualified-id before numeric constant\n 111\n ^~~', 0, 0, '111');
INSERT INTO `state` VALUES (1085, 8, 1, '2021-02-12 10:25:00', 'C++', 'ce', '/judger/run/055cab3161034b3995bf41c05d56db1e/main.c:1:1: error: \'asdas\' does not name a type\n asdas\n ^~~~~', 0, 0, 'asdas');
INSERT INTO `state` VALUES (1086, 1, 1, '2021-02-12 10:25:22', 'C++', 'ce', '/judger/run/8fd380bb2a854fa9b9e15b482e4c36dc/main.c:1:1: error: \'asdasd\' does not name a type\n asdasd\n ^~~~~~', 0, 0, 'asdasd');
INSERT INTO `state` VALUES (1087, 8, 1, '2021-02-12 10:28:02', 'C++', 'ce', '/judger/run/288a3eefee9f4a74b584cb7c9c156d12/main.c:1:1: error: expected unqualified-id before numeric constant\n 123123\n ^~~~~~', 0, 0, '123123');
INSERT INTO `state` VALUES (1088, 8, 1, '2021-02-12 18:31:07', 'C++', 'ce', '/judger/run/fee86ebe76de46b2bdc842af631a20ec/main.c:1:1: error: \'asdasd\' does not name a type\n asdasd\n ^~~~~~', 0, 0, 'asdasd');
INSERT INTO `state` VALUES (1089, 8, 1, '2021-02-12 18:32:10', 'C++', 'ce', '/judger/run/b673f3ad31fc4304bbac0e61ea639204/main.c:1:1: error: \'aa\' does not name a type\n aa\n ^~', 0, 0, 'aa');
INSERT INTO `state` VALUES (1090, 8, 1, '2021-02-12 18:41:41', 'C++', 'ce', '/judger/run/3f9af3da25064da4bf6da8d6ad4b3172/main.c:1:1: error: \'test\' does not name a type\n test\n ^~~~', 0, 0, 'test');
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'root', '4297f44b13955235245b2497399d7a93', 'admin', '1@q.com1', 1, '161147191231547153352', 'http://k423.tech', 'https://github.com/teleport-10032', '「我将坠入苦痛 , 换你留在红莲」', '1,8', '1,8', 2);
INSERT INTO `user` VALUES (2, 'user1', '4297f44b13955235245b2497399d7a93', 'user', '2@q.com', 1, '160914538124477634192', 'about:blank', 'test', 'test', '', ' ', 0);
INSERT INTO `user` VALUES (3, 'user2', '4297f44b13955235245b2497399d7a93', 'user', '3@q.com', 1, '160377279468058589669', 'about:blank', 'about:blank', '这里没有签名档', '', ' ', 0);
INSERT INTO `user` VALUES (4, 'root1', '4297f44b13955235245b2497399d7a93', 'admin', '1111@q.com', 1, '160871958310498213237', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
INSERT INTO `user` VALUES (6, '123123', '4297f44b13955235245b2497399d7a93', 'user', '1asd@qq.com', 1, '160370562155412856482', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
INSERT INTO `user` VALUES (8, 'root2', 'f5bb0c8de146c67b44babbf4e6584cc0', 'admin', 'asdklj@qq.com', 1, '160704796884597835405', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
INSERT INTO `user` VALUES (15, '123', '96e79218965eb72c92a549dd5a330112', 'user', '123@q.com', 1, '160441634776886510589', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
INSERT INTO `user` VALUES (21, 'root3', '4297f44b13955235245b2497399d7a93', 'admin', '1021822981@QQ.COM', 1, '160717099931144582616', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
INSERT INTO `user` VALUES (22, 'test', '4297f44b13955235245b2497399d7a93', 'user', 'test@qq.com', 1, '160873294480683217934', 'about:blank', 'about:blank', '这里没有签名档', '', '', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
