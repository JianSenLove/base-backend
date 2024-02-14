# 使用Java官方提供的基础镜像
FROM openjdk:8-jdk-alpine

# 指定容器内应用的工作目录
WORKDIR /app

# 将打包好的jar文件复制到镜像中
COPY mirageLedger-runner-1.0.0.0.jar app.jar

# 暴露端口
EXPOSE 8081

# 运行jar文件
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
