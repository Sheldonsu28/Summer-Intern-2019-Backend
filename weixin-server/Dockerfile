FROM ccr.ccs.tencentyun.com/meimengmeng/myjava:8-jre
MAINTAINER hann <376544221@qq.com>
ADD ./target/weixin-server-0.0.1-SNAPSHOT.jar \
	/app/weixin-server.jar
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
CMD ["java", "-Xmx200m", "-jar", "/app/weixin-server.jar","--spring.profiles.active=prod"]
EXPOSE 8000