FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD ssc-website-2.0.jar /app.jar
#RUN sh -c 'touch /app.jar'
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar