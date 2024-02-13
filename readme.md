# mirageLedge

# 教务管理系统(教师部分模块)

## 项目介绍
此项目主要实现了教务系统的部分模块功能，主要涉及教师课程的查看以及教师课程的评价

## 项目技术栈
- SpringBoot
- MyBatisPlus
- MySQL

项目结构主要分四个目录
common  通用模块目录
course 课程模块
runner  项目启动模块
user  用户模块(教师模块)

用户登录以及信息的获取采用jwt令牌机制
## 项目启动模块
### 项目启动模块主要负责
1.项目的启动
2.mapper文件和bean的扫描路径配置

## 通用模块
### 通用模块主要内容
1.jwt令牌机制的实现和身份验证
2.全局异常处理
3.mybatisplus的分页和自动注入配置

## 课程模块
### 课程模块主要内容
1.课程的增删改查(每个教师只能管理自己所开设的课程以及课程评价，管理员可以管理所有课程)

## 用户模块
### 用户模块主要内容
1.用户信息的增删改查
2.用户的登录注册





## 云服务器部署

以下部署都基于docker(centos7)
没有安装docker见
[docker-ce镜像_docker-ce下载地址_docker-ce安装教程-阿里巴巴开源镜像站 (aliyun.com)](https://developer.aliyun.com/mirror/docker-ce?spm=a2c6h.13651102.0.0.3e221b11jUhVMy)



1.部署mysql

```shell
docker run --name=mysql8 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.0.28
```

2.创建数据库表

新建数据库，名为evalution

sql文件见根目录下db文件,运行sql文件

3.部署项目后端

4.部署nginx和项目前端