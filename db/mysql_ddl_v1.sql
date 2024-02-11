DROP TABLE IF EXISTS `mirageledger_user`;
CREATE TABLE `mirageledger_user`  (
                                      `ID_` varchar(64)   NOT NULL COMMENT 'ID',
                                      `NAME_` varchar(255)   NOT NULL COMMENT '用户名称',
                                      `CODE_` varchar(64)   NOT NULL COMMENT '登录账号',
                                      `DESC_` varchar(255)   NULL DEFAULT NULL COMMENT '用户描述',
                                      `PASSWORD_` varchar(64)   NOT NULL COMMENT '账号密码',
                                      `CREATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `UPDATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT = '用户信息表';
INSERT INTO `mirageledger_user` (`ID_`, `NAME_`, `CODE_`, `DESC_`, `PASSWORD_`, `CREATE_TIME`, `UPDATE_TIME`) VALUES ('admin', '教务处管理员', 'admin', '管理账户', 'admin123', '2024-02-01 16:03:40', '2024-02-01 16:03:45');

DROP TABLE IF EXISTS `mirageledger_course`;
CREATE TABLE `mirageledger_course`  (
                                        `ID_` varchar(64)   NOT NULL COMMENT 'ID',
                                        `NAME_` varchar(255)   NOT NULL COMMENT '课程名称',
                                        `USER_ID` varchar(64)   NOT NULL COMMENT '用户ID',
                                        `TERM_` varchar(64)   NOT NULL COMMENT '学期',
                                        `DESC_` varchar(255)   NULL DEFAULT NULL COMMENT '课程描述',
                                        `CREATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `UPDATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT = '课程表';

DROP TABLE IF EXISTS `mirageledger_course_evaluation`;
CREATE TABLE `mirageledger_course_evaluation`  (
                                                   `ID_` varchar(64)   NOT NULL COMMENT 'ID',
                                                   `COURSE_ID` varchar(64)   NOT NULL  COMMENT '课程ID',
                                                   `TYPE_` varchar(16)   NOT NULL  COMMENT '评价类型',
                                                   `SCORE_` DOUBLE NULL DEFAULT NULL COMMENT '课程评价分',
                                                   `CREATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                                   `UPDATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT = '课程评分表';