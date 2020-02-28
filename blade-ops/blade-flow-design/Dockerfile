FROM bladex/alpine-java:8_server-jre_cn_unlimited

MAINTAINER smallchill@163.com

RUN mkdir -p /blade/flowdesign

WORKDIR /blade/flowdesign

EXPOSE 9999

ADD ./target/blade-flow-design.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=test"]
