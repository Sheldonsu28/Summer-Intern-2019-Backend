FROM ccr.ccs.tencentyun.com/meimengmeng/myjava:8-jre
MAINTAINER hann <376544221@qq.com>
ADD ./target/weixin-commodity-0.0.1-SNAPSHOT.jar \
	/app/weixin-commodity.jar
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
CMD ["java", "-Xmx200m", "-jar", "/app/weixin-commodity.jar","--spring.profiles.active=prod"]
EXPOSE 8400