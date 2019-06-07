# 基于哪个镜像
FROM java:latest

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 复制文件到容器
ADD micro-service-discovery.jar app.jar
RUN bash -c 'touch /app.jar'

# 配置容器启动后执行的命令
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

# 声明暴露的端口
EXPOSE 8761

# 时间同步
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


#ADD ./target/micro-service-discovery.jar /app/
#CMD ["java", "-Xmx256m", "-jar", "/app/micro-service-discovery.jar"]