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

 Date: 04/11/2020 17:13:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `title` varchar(40) NOT NULL,
  `content` varchar(5005) NOT NULL,
  `authorId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of announcement
-- ----------------------------
BEGIN;
INSERT INTO `announcement` VALUES (1, '2020-09-28 15:37:10', '如何将默认语言改为中文', '本来就是中文qaq', 1);
INSERT INTO `announcement` VALUES (2, '2020-09-28 15:38:43', '山东建筑大学第八届ICPC校赛参赛通知', '明天就是校赛了，大家今晚好好休息！明天一定超常发挥！\n比赛地点是：信息楼207，209\n明天上午10点开始签到（签到时间很长，不用着急），10点半开始热身赛，11点开始正式赛，下午四点比赛结束，大家可以早一点过来找到自己的对应的座号，调试机器。然后呢，可以食物入场（毕竟得吃午饭对吧，但是不要带味儿大的，不然可能被赶出去，建议带面包）\n然后，参加校赛的同学可以去pu口袋上报名，签到成功后据说有0.08分！！！！\n校赛不允许携带任何电子设备哦，要是被发现携带电子设备，可能会被我们当场锤死，当然，你们可以自行准备纸质资料，这个我们很提倡。\n好！大家今晚好好休息吧！', 1);
INSERT INTO `announcement` VALUES (3, '2020-09-28 15:38:42', '山东建筑大学第九届ICPC校赛参赛通知', '明天就是校赛了，大家今晚好好休息！明天一定超常发挥！\n比赛地点是：信息楼207，209\n明天上午10点开始签到（签到时间很长，不用着急），10点半开始热身赛，11点开始正式赛，下午四点比赛结束，大家可以早一点过来找到自己的对应的座号，调试机器。然后呢，可以食物入场（毕竟得吃午饭对吧，但是不要带味儿大的，不然可能被赶出去，建议带面包）\n然后，参加校赛的同学可以去pu口袋上报名，签到成功后据说有0.08分！！！！\n校赛不允许携带任何电子设备哦，要是被发现携带电子设备，可能会被我们当场锤死，当然，你们可以自行准备纸质资料，这个我们很提倡。\n好！大家今晚好好休息吧！', 1);
INSERT INTO `announcement` VALUES (4, '2020-09-28 15:36:23', '2020-IPCP新生群须知', '请修改一下备注\n格式:年级-专业-姓名\n我们学校的OJ地址\nhttp://724vector.cn:82/\n2019ACM-ICPC宣传兼比赛报名网站：\nhttp://acm.724vector.cn\n第七届山东建筑大学ACM程序设计竞赛于2019年11月或12月举行，届时将选拔新队员。在校赛前，我们每周会有新生训练赛，详情关注群公告和OJ，希望大家准时参加，赛后补题，慢慢提升。校赛考查内容远多于学校课程进度，希望大家自学。\n校赛考查范围：\n1.基础语法与数据结构\n分支、循环、递归、数组、字符串、结构体、简单STL\n2.基础排序、模拟、贪心\n3.简单数学与思维题\n/* 不考查但学会很牛逼系列：STL进阶、搜索、分治*/\n推荐学习书籍及网站：\n书籍\n算法导论（？？？）、啊哈算法、信息学竞赛一本通、挑战程序设计竞赛、算法竞赛入门指导（一、二版）\n网站\nB站（知名学习网站）\n洛谷（https://www.luogu.org/ 吊打大学生的小学生都在这里）\nHDU（http://acm.hdu.edu.cn/ 杭电OJ）\nPOJ（http://poj.org/ 北大OJ）\nCodeforces（https://codeforces.com/ 难度较高，毛子横行）\n预祝大家校赛拔得头筹', 1);
INSERT INTO `announcement` VALUES (5, '2020-09-28 15:37:50', '休伯利安执勤规定Beta3', '德丽莎不要随便拿十字架打人', 1);
INSERT INTO `announcement` VALUES (6, '2020-09-28 15:39:24', 'onlineJudge网站变更通知', '由于维护人员变更，我校OnlineJudge(OJ)网址改为 oj.k423.tech (不会影响数据)，造成的不便请大家谅解', 1);
INSERT INTO `announcement` VALUES (7, '2020-09-28 15:40:28', '校赛奖品领取通知', '第七届ACM-ICPC 校赛奖品已经到位，请凭有效证件或获奖证书到信息楼315西领取\n获奖名单见群文件\n奖项如下： \n一等奖：键盘 / 小米手环4二选一\n二等奖：鼠标 / 耳机 二选一\n三等奖：充电宝 / U盘 二选一\n一血：纪念书签\n参与奖：凡是参与本次比赛的大一新生，且 \n在此次比赛中解出一道以上题目的同 \n学，均可以领取鼠标垫一张', 1);
INSERT INTO `announcement` VALUES (8, '2020-09-28 15:40:45', '奖状领取须知', '因为校团委的老师比较忙，奖状盖章延后，领取奖状时间延后，奖状盖好章后会在群里通知。\n', 1);
INSERT INTO `announcement` VALUES (9, '2020-09-28 15:40:54', '校赛榜单', '校赛榜单在http://724vector.cn:82/比赛列表中“第七届校赛”查看，另外闭幕式短片在这看：https://www.bilibili.com/video/av77653838/', 1);
INSERT INTO `announcement` VALUES (10, '2020-10-15 09:02:21', '校赛榜单', '明天信息楼206有考试，大家来的时候安静一点，不要打扰到考试的人。\n', 1);
INSERT INTO `announcement` VALUES (11, '2020-09-28 15:41:38', '关于gets()', '使用C/C++答题的同学请注意，使用gets()函数会导致编译错误，请使用其他替代方法，例如c++中的cin.get()，C中的fgets()', 1);
INSERT INTO `announcement` VALUES (12, '2020-09-28 15:41:44', '赛事通告', '我们的国庆新生训练即将开始，题目已经就绪，希望同学们多多参与，这个对编程学习有帮助。\n做题中碰到的问题，都可以找管理询问。\n希望同学们可以在班里啥的帮着宣传一下哈，争取更多人的参与。\n祝同学们做题顺利，假期快乐。\n(附此次训练赛网址：\nhttp://724vector.cn:82/contest/45\n)', 1);
COMMIT;

-- ----------------------------
-- Table structure for contest
-- ----------------------------
DROP TABLE IF EXISTS `contest`;
CREATE TABLE `contest` (
  `id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `problemList` varchar(255) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of contest
-- ----------------------------
BEGIN;
INSERT INTO `contest` VALUES (1, '第十六届山东建筑大学ICPC校赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2019-10-07 09:00:00', '2019-10-07 14:00:00');
INSERT INTO `contest` VALUES (2, '第十七届山东建筑大学ICPC校赛热身赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2020-10-10 09:00:00', '2020-11-12 14:00:00');
INSERT INTO `contest` VALUES (3, '第十七届山东建筑大学ICPC校赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2020-11-07 09:00:00', '2020-12-07 14:00:00');
INSERT INTO `contest` VALUES (4, '第十八届山东建筑大学ICPC校赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2020-10-07 09:00:00', '2021-10-07 14:00:00');
INSERT INTO `contest` VALUES (5, '第十九届山东建筑大学ICPC校赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2020-10-07 09:00:00', '2020-10-07 14:00:00');
INSERT INTO `contest` VALUES (6, '第二十届山东建筑大学ICPC校赛热身赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2020-10-07 09:00:00', '2020-10-07 14:00:00');
INSERT INTO `contest` VALUES (7, '第二十届山东建筑大学ICPC校赛', '1.比赛不能访问除比赛之外的网站，不能用手机，不能和其他人交流\n\n2.比赛可以使用任何纸质版材料，不可以使用电子版\n\n3.需要安装比赛环境请于http://10.17.18.20:81/自取（热身赛应该已经准备好了吧）\n\n4.比赛将于结束前一小时封榜，比赛结束后揭榜\n\n5.还是那句话，有什么问题随时使唤工作人员\n\n（提示：比赛期间以前自己注册的账号是无法登录的哦）', NULL, '2020-10-07 09:00:00', '2022-10-07 14:00:00');
COMMIT;

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem` (
  `id` int(11) NOT NULL,
  `title` varchar(105) DEFAULT NULL,
  `des` varchar(1005) DEFAULT NULL,
  `input` varchar(255) DEFAULT NULL,
  `ouput` varchar(255) DEFAULT NULL,
  `inputExample` varchar(255) DEFAULT NULL,
  `outputExample` varchar(255) DEFAULT NULL,
  `hint` varchar(1005) DEFAULT NULL,
  `acSubmit` int(11) DEFAULT NULL,
  `totalSubmit` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `authorName` varchar(255) DEFAULT NULL,
  `visible` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of problem
-- ----------------------------
BEGIN;
INSERT INTO `problem` VALUES (1, '奶牛排队', '奶牛排队', '这里是输入', '这里是输出', '1', '2', '没有提示', 10, 20, '2020-11-04 13:58:32', 'root', 1);
INSERT INTO `problem` VALUES (2, 'A+B for Input-Output Practice (1)', 'our task is to Calculate a + b. Too easy?! Of course! I specially designed the problem for acm beginners. You must have found that some problems have the same titles with this one, yes, all these problems were designed for the same aim', 'he input will consist of a series of pairs of integers a and b, separated by a space, one pair of integers per line.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '1 5\n10 20', '6\n30', '这是一个求两数之和的题目，输入多对用空格分开的两个数a b，输出a+b的和，每一对数据的和占一行。', 100, 100, '2020-11-04 13:58:32', 'root', 1);
INSERT INTO `problem` VALUES (3, 'A+B for Input-Output Practice (1)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20', '6\n30', '（google翻译？看数据猜题目？）', 40, 45, '2020-11-04 13:58:32', 'root', 1);
INSERT INTO `problem` VALUES (4, '帽子戏法', '三个人，竖着站成一排。有五个帽子，三个蓝色，两个红色，每人带一个，各自不准看自己的颜色。\n\n然后问第一个人带的什么颜色的帽子，他说不知道，然后又问第二个人带的什么颜色的帽子，同样说不知道，又问第三个人带的是什么颜色的帽子，他说我知道。\n\n问第三个人带的是什么色帽子?\n\n（第一个人站在排的最后,他可以看见前二个人的帽子的颜色)', '无', '如果红色输出“red”，如果蓝色输出“blue”', '无', '输出样例不见了\n', '无', 12, 100, '2020-11-04 13:58:32', 'root', 1);
INSERT INTO `problem` VALUES (5, 'A+B for Input-Output Practice (1)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20', '无\n30', '（google翻译？看数据猜题目？）', 40, 45, '2020-11-04 13:58:35', 'root2', 1);
INSERT INTO `problem` VALUES (6, 'A+B for Input-Output Practice (4)', '三个人，竖着站成一排。有五个帽子，三个蓝色，两个红色，每人带一个，各自不准看自己的颜色。\n\n然后问第一个人带的什么颜色的帽子，他说不知道，然后又问第二个人带的什么颜色的帽子，同样说不知道，又问第三个人带的是什么颜色的帽子，他说我知道。\n\n问第三个人带的是什么色帽子?\n\n（第一个人站在排的最后,他可以看见前二个人的帽子的颜色)', '无', '如果红色输出“red”，如果蓝色输出“blue”', '无', '输出样例不见了\n', '无', 12, 1100, '2020-11-04 13:58:32', 'root', 1);
INSERT INTO `problem` VALUES (7, 'A+B for Input-Output Practice (5)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20', '6\n30', '（google翻译？看数据猜题目？）', 40, 45, '2020-11-04 13:58:32', 'root', 1);
INSERT INTO `problem` VALUES (8, 'A+B for Input-Output Practice (6)', '三个人，竖着站成一排。有五个帽子，三个蓝色，两个红色，每人带一个，各自不准看自己的颜色。\n\n然后问第一个人带的什么颜色的帽子，他说不知道，然后又问第二个人带的什么颜色的帽子，同样说不知道，又问第三个人带的是什么颜色的帽子，他说我知道。\n\n问第三个人带的是什么色帽子?\n\n（第一个人站在排的最后,他可以看见前二个人的帽子的颜色)', '无', '如果红色输出“red”，如果蓝色输出“blue”', '无', '输出样例不见了\n', '无', 12, 221, '2020-11-04 13:58:32', 'root2', 1);
INSERT INTO `problem` VALUES (9, 'A+B for Input-Output Practice (7)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20', '6\n30', '（google翻译？看数据猜题目？）', 40, 45, '2020-11-04 13:58:32', 'root', 0);
INSERT INTO `problem` VALUES (10, 'A+B for Input-Output Practice (8)', '三个人，竖着站成一排。有五个帽子，三个蓝色，两个红色，每人带一个，各自不准看自己的颜色。\n\n然后问第一个人带的什么颜色的帽子，他说不知道，然后又问第二个人带的什么颜色的帽子，同样说不知道，又问第三个人带的是什么颜色的帽子，他说我知道。\n\n问第三个人带的是什么色帽子?\n\n（第一个人站在排的最后,他可以看见前二个人的帽子的颜色)', '无', '如果红色输出“red”，如果蓝色输出“blue”', '无', '输出样例不见了\n', '无', 12, 311, '2020-11-04 13:58:32', 'root', 0);
INSERT INTO `problem` VALUES (11, 'A+B for Input-Output Practice (9)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20', '6\n30', '（google翻译？看数据猜题目？）', 40, 45, '2020-11-04 13:58:32', 'root', 0);
INSERT INTO `problem` VALUES (12, 'A+B for Input-Output Practice (10)', '三个人，竖着站成一排。有五个帽子，三个蓝色，两个红色，每人带一个，各自不准看自己的颜色。\n\n然后问第一个人带的什么颜色的帽子，他说不知道，然后又问第二个人带的什么颜色的帽子，同样说不知道，又问第三个人带的是什么颜色的帽子，他说我知道。\n\n问第三个人带的是什么色帽子?\n\n（第一个人站在排的最后,他可以看见前二个人的帽子的颜色)', '无', '如果红色输出“red”，如果蓝色输出“blue”', '无', '输出样例不见了\n', '无', 12, 41, '2020-11-04 13:58:21', 'root', 0);
INSERT INTO `problem` VALUES (13, 'A+B for Input-Output Practice (11)', 'The first line integer means the number of input integer a and b. Your task is to Calculate a + b', 'Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.', 'For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.', '2\n1 5\n10 20', '6\n30', '（google翻译？看数据猜题目？）', 40, 45, '2020-11-04 13:58:32', 'root', 0);
INSERT INTO `problem` VALUES (14, 'A+B for Input-Output Practice (12)', '三个人，竖着站成一排。有五个帽子，三个蓝色，两个红色，每人带一个，各自不准看自己的颜色。\n\n然后问第一个人带的什么颜色的帽子，他说不知道，然后又问第二个人带的什么颜色的帽子，同样说不知道，又问第三个人带的是什么颜色的帽子，他说我知道。\n\n问第三个人带的是什么色帽子?\n\n（第一个人站在排的最后,他可以看见前二个人的帽子的颜色)', '无', '如果红色输出“red”，如果蓝色输出“blue”', '无', '输出样例不见了\n', '无', 12, 43, '2020-11-04 13:58:32', 'root', 1);
COMMIT;

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `problemId` int(11) DEFAULT NULL,
  `authorId` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `memoryCost` int(11) DEFAULT NULL,
  `timeCost` int(11) DEFAULT NULL,
  `code` varchar(5005) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1036 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of state
-- ----------------------------
BEGIN;
INSERT INTO `state` VALUES (1000, 1, 1, '2020-10-15 09:27:29', 'C++', 'Pending', 22, 33, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1001, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1002, 1, 1, '2020-10-15 09:34:07', 'C++', 'Wrong Answer', 44, 55, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1003, 1, 1, '2020-10-15 09:40:24', 'C++', 'Compile Error', 0, 0, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1004, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1005, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1006, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1007, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1008, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1009, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1010, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1011, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1012, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1013, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1014, 1, 1, '2020-10-15 09:33:24', 'C++', 'Accept', 33, 22, '#include <iostream>\n\nusing namespace std;\n\nint main()\n{\n  char a[10007] = {0};\n  int i, j, n, ans = 0, s, e, d;\n  cin >> d;\n  if(!d)\n    return 0;\n  cin >> n;\n  while(n--)\n  {\n    cin >> s >> e;\n    for(i = s; i <= e; i ++)\n      a[i] = 1;\n  }\n  for(i = 0; i <= d; i ++)\n    if(!a[i])\n      ans ++;\n  cout << ans << endl;\n  return 0;\n}');
INSERT INTO `state` VALUES (1015, 1, 3, '2020-10-27 12:59:07', 'C++', 'Pending', 0, 0, 'myCode');
INSERT INTO `state` VALUES (1016, 1, 3, '2020-10-27 13:08:19', 'C++', 'Pending', 0, 0, 'testCode1');
INSERT INTO `state` VALUES (1017, 1, 3, '2020-10-27 13:09:19', 'C++', 'Pending', 0, 0, 'testCode2');
INSERT INTO `state` VALUES (1018, 1, 3, '2020-10-27 13:09:51', 'C++', 'Pending', 0, 0, 'testCode3');
INSERT INTO `state` VALUES (1019, 1, 3, '2020-10-27 13:10:39', 'C++', 'Pending', 0, 0, 'testCode4');
INSERT INTO `state` VALUES (1020, 1, 3, '2020-10-27 13:11:06', 'C++', 'Pending', 0, 0, 'testCode5');
INSERT INTO `state` VALUES (1021, 1, 3, '2020-10-27 13:11:44', 'C++', 'Pending', 0, 0, 'testCode6');
INSERT INTO `state` VALUES (1035, 2, 1, '2020-10-27 16:17:40', 'C++', 'Pending', 0, 0, '#include \"iostream\"\n#include \"string.h\"\n#include \"stdio.h\"\n#include \"algorithm\"\n#include \"vector\"\n#include \"queue\"\n#include \"stack\"\n#include \"set\"\n#include \"map\"\nusing namespace std;\n#define maxn 30005\n#define dbg printf(\"!\\n\")\n\nset<long long> s;\n//lazy标记\nlong long lazy[maxn << 2];\nstruct node\n{\n    //该节点的值\n    //区间左右端点\n    long long value;\n    long long left, right;\n}tree[maxn << 2];\n//root是根节点 l和r为左右区间\nvoid build(long long root, long long l, long long r)\n{\n    tree[root].left = l;\n    tree[root].right = r;\n    lazy[root] = 0;\n    //当找到l和r相等的节点 如[2,2],赋值\n    if (tree[root].left == tree[root].right)\n    {\n        tree[root].value = -123;\n        return;\n    }\n    long long mid = (tree[root].left + tree[root].right) >> 1;\n    //二分查找儿子\n    build(root << 1, l, mid);\n    build(root << 1 | 1, mid + 1, r);\n    //将儿子节点值的和赋给父节点\n    tree[root].value = tree[root << 1].value + tree[root << 1 | 1].value;\n}\n//往下传递lazy标记 m是当前区间的长度\nvoid PushDown(long long rt,long long m)\n{\n    if (lazy[rt] != 0)\n    {\n        lazy[rt << 1] = lazy[rt];\n        lazy[rt << 1 | 1] = lazy[rt];\n        tree[rt << 1].value = lazy[rt] * (m - (m >> 1));\n        tree[rt << 1 | 1].value = lazy[rt] * (m >> 1);\n        lazy[rt] = 0;\n    }\n}\n//root为根节点 l r 分别为要更新的左右区间，v为要加上的值\nvoid update(long long root, long long l , long long r , long long v)\n{\n    //当定位到一个区间时 更新该区间的value,同时加上lazy标记\n    if (tree[root].left == l && tree[root].right == r)\n    {\n        tree[root].value =  v * (r - l + 1);\n        lazy[root] = v;\n        return;\n    }\n    if (tree[root].left == tree[root].right)\n        return ;\n    long long mid = (tree[root].left + tree[root].right) >> 1;\n    PushDown(root , tree[root].right - tree[root].left + 1);\n    //二分查找儿子\n    if (r <= mid)\n        update(root << 1 , l , r, v);\n    else if(l > mid)\n        update(root << 1 | 1, l, r , v);\n    else\n    {\n        update(root << 1, l , mid, v);\n        update(root << 1 | 1, mid + 1 , r , v);\n    }\n    tree[root].value = tree[root << 1].value + tree[root << 1 | 1].value;\n}\n//查询某个区间的和\nlong long query(long long root, long long l, long long r)\n{\n    //当被查询的区间完全包含当前节点的区间时\n    if (l <= tree[root].left && r >= tree[root].right)\n        return tree[root].value;\n    PushDown(root , tree[root].right - tree[root].left + 1);\n    long long mid = (tree[root].left + tree[root].right) >> 1;\n    long long ans = 0;\n    if (l <= mid)\n        ans = ans + query(root << 1, l, r);\n    if (r > mid)\n        ans = ans + query(root << 1 | 1, l, r);\n    return ans;\n}\n\nvoid bls(long long root, long long l, long long r)\n{\n    if (tree[root].left == tree[root].right)\n    {\n     //   printf(\"%lld \",tree[root].value);\n        s.insert(tree[root].value);\n        return;\n    }\n    PushDown(root , tree[root].right - tree[root].left + 1);\n    long long mid = (tree[root].left + tree[root].right) >> 1;\n    //二分查找儿子\n    bls(root << 1, l, mid);\n    bls(root << 1 | 1, mid + 1, r);\n}\nint main()\n{\n    int t , n ;\n    scanf(\"%d\" ,&t);\n    int a[10005];\n    int b[10005];\n    while(t --)\n    {\n        scanf(\"%d\",&n);\n        s.clear();\n        for(int i = 1 ; i <= n ; i++)\n        {\n            scanf(\"%d %d\" , &a[i] , &b[i]);\n            s.insert(a[i]) ; s.insert(b[i]);\n        }\n        long long cc = 0;\n        long long arr[maxn];\n        for(set<long long>::iterator it = s.begin() ; it != s.end() ; it++)\n            arr[cc++] = *it;\n        /*\n        for(int i = 0 ; i < cc ; i ++)\n            cout << arr[i] << \" \" ;\n        dbg;\n         */\n        build(1, 1, n*2+5);\n        for(int i = 1 ; i <= n; i ++)\n        {\n            long long l = lower_bound(arr, arr+cc, a[i]) - arr;\n            long long r = lower_bound(arr, arr+cc, b[i]) - arr;\n            l ++ ; r ++;\n            //cout << l << \" \" << r << \" \" << i << endl;\n            update(1, l, r, i);\n        }\n        s.clear();\n        bls(1, 1, n*2+5);\n        //cout << endl;\n        long long te = s.size() - 1;\n        printf(\"%lld\\n\",te);\n    }\n    return 0;\n}\n/*\n 1 2 3 4 5 6 7 8 9 10 11\n  1 2 3 4 5 6 7 8 9 10\n*/');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `available` int(1) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'root', '4297f44b13955235245b2497399d7a93', 'admin123123', NULL, 'admin', '1@q.com', 0, '160445304156476845616');
INSERT INTO `user` VALUES (2, 'user1', '4297f44b13955235245b2497399d7a93', '这是一个测试账户(user1)。', NULL, 'user', '2@q.com', 1, '160380829449933542545');
INSERT INTO `user` VALUES (3, 'user2', '4297f44b13955235245b2497399d7a93', '这是一个测试账户(user2)。11', NULL, 'user', '3@q.com', 1, '160377279468058589669');
INSERT INTO `user` VALUES (4, 'rootasd', '4297f44b13955235245b2497399d7a93', 'user', NULL, 'user', '1@q.com11', 1, NULL);
INSERT INTO `user` VALUES (5, 'root1', '4297f44b13955235245b2497399d7a93', 'TEST', NULL, 'admin', '1@q11.com', 1, '160441441244553581751');
INSERT INTO `user` VALUES (6, '123123', '4297f44b13955235245b2497399d7a93', '这是一个测试账户(user2)。', NULL, 'user', '1asd@qq.com', 1, '160370562155412856482');
INSERT INTO `user` VALUES (7, 'rrrqweq', '4297f44b13955235245b2497399d7a93', 'user', NULL, 'user', '1@qqq.ocm', 1, '160370564928966001306');
INSERT INTO `user` VALUES (8, 'root2', '4297f44b13955235245b2497399d7a93', '这是一个测试账户(user2)。', NULL, 'admin', 'asdklj@qq.com', 1, '160445303023652199367');
INSERT INTO `user` VALUES (15, '123', '96e79218965eb72c92a549dd5a330112', '签名', NULL, 'user', '123@q.com', 1, '160441634776886510589');
INSERT INTO `user` VALUES (16, '1231231', 'f5bb0c8de146c67b44babbf4e6584cc0', NULL, NULL, 'user', '1jl@q.com', 1, NULL);
INSERT INTO `user` VALUES (17, '1231', '4297f44b13955235245b2497399d7a93', NULL, NULL, 'user', '11@qq.com', 1, NULL);
INSERT INTO `user` VALUES (18, 'lkhjads', 'f5bb0c8de146c67b44babbf4e6584cc0', NULL, NULL, 'user', 'asd@q.fom', 1, NULL);
INSERT INTO `user` VALUES (19, 'rootroot', '4297f44b13955235245b2497399d7a93', 'JKLKJLKJLKJLKJLKJL;IKJLK', NULL, 'user', '2@q.com123', 1, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
