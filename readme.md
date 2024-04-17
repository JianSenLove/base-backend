# mirageLedge

# 商城管理系统

## 项目介绍
此项目主要实现了商城管理系统的部分模块功能

## 项目技术栈
- SpringBoot
- MyBatisPlus
- MySQL

项目结构主要分四个目录
common  通用模块目录
shop 商城模块
runner  项目启动模块
user  用户模块

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

## 商城模块
### 商城模块主要内容
1.商品信息的增删改查
2.商品分类的增删改查
3.订单的增删改查
4.购物车的增删改查

## 用户模块
### 用户模块主要内容
1.用户信息的增删改查
2.用户的登录注册





## 云服务器部署

以下部署都基于docker(centos7)
没有安装docker见
[docker-ce镜像_docker-ce下载地址_docker-ce安装教程-阿里巴巴开源镜像站 (aliyun.com)](https://developer.aliyun.com/mirror/docker-ce?spm=a2c6h.13651102.0.0.3e221b11jUhVMy)



#### 1.部署mysql

创建卷

```shell
docker volume create evaluation_mysql
```

部署mysql

```shell
docker run --name=mysql8 -p 3306:3306 -v evaluation_mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=mirage666666 -d mysql:8.0.28
docker update -m 500M --memory-reservation 500M --memory-swap 1000M mysql8
```

#### 2.创建数据库表

新建数据库，名为evalution

sql文件见根目录下db文件,运行sql文件

#### 3.部署项目后端

后端默认暴露端口为8081

配置文件中调整mysql地址和数据库后打包项目

复制后端项目根目录下DockerFile文件到云服务器某文件夹下(/home)

将Dockerfile和runner模块下打包的jar包放在同一目录
docker build -t mirageledger-runner .
docker run -p 8081:8081 --name evaluation -d mirageledger-runner

#### 4.部署nginx和项目前端

在执行打包前，在前端项目配置前端的后端地址为自己的服务器地址(/src/util/request.ts,baseURL)

##### 方法一:

先将前端项目打包

npm run build

将打包后的dist目录下的全部内容复制到云服务器的某个文件夹下(这里示例为/home/mirageLedge/nginx/html)

运行nginx

```shell
docker run --name nginx  -p 80:80 -p 443:443 \
-v /home/mirageLedge/nginx/html:/usr/share/nginx/html -d nginx
```

方法一访问不成功则执行方法二

##### 方法二:

初始化nginx

```shell
docker run --name nginx -p 80:80 -d nginx
```

复制nginx配置文件

```shell
docker cp nginx:/etc/nginx/conf.d /home/mirageLedge/nginx
docker cp nginx:/etc/nginx/nginx.conf /home/mirageLedge/nginx/nginx.conf
docker cp nginx:/usr/share/nginx/html /home/mirageLedge/nginx
```

将前端项目打包

npm run build

将打包后的dist目录下的全部内容复制到/home/mirageLedge/nginx/html目录下

最后启动nginx

```shell
docker run --name nginx  -p 80:80 -p 443:443 \
-v /home/mirageLedge/nginx/conf.d:/etc/nginx/conf.d \
-v /home/mirageLedge/nginx/nginx.conf:/etc/nginx/nginx.conf \
-v /home/mirageLedge/nginx/html:/usr/share/nginx/html -d nginx
```



至此项目部署完毕，直接访问服务器地址(默认80端口),即可访问项目