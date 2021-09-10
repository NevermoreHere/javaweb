FROM openjdk:8-jdk-alpine

VOLUME /tmp

RUN echo 'Asia/Shanghai' >/etc/timezone

ADD build/libs/*SNAPSHOT.jar /opt/javaweb.jar


WORKDIR /opt

EXPOSE 30502

ENV JAVA_OPTS="\
-Dcom.sun.management.jmxremote.port=51001 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Xmx1G \
-Xms512m \
-XX:SurvivorRatio=8 \
-XX:MetaspaceSize=256m \
-XX:MaxMetaspaceSize=256m \
-XX:+PrintGCDetails \
-XX:+PrintGCTimeStamps \
-Dlog.level=INFO"

ENTRYPOINT java ${JAVA_OPTS} -jar javaweb.jar
