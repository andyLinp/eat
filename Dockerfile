#使用的基础镜像
FROM openjdk:8-jre-alpine


#把当前目录下的jar包添加到镜像
ADD  ./target/eat-1.0.0-SNAPSHOT.jar  /usr/local/projects/eat.jar


#启动时运行jar包,并把日志输出到指定目录中
CMD  java -jar  /usr/local/projects/eat.jar