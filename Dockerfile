ARG DOCKER_REGISTRY=""
ARG DOCKER_BASE_IMAGE="library/openjdk"
ARG DOCKER_BASE_IMAGE_TAG="17.0.2-jdk"
ARG APPNAME="app"

FROM ${DOCKER_REGISTRY}${DOCKER_BASE_IMAGE}:${DOCKER_BASE_IMAGE_TAG}

###
ENV CFG_PATH /opt/config
ENV LOG_PATH /opt/logs

VOLUME /opt/config
VOLUME /opt/logs

ENV JAVA_DEFAULT_OPTS "-Djava.net.preferIPv4Stack=true -Dnetworkaddress.cache.ttl=0 \
    -XX:+UnlockExperimentalVMOptions \
    -XX:+UseContainerSupport \
    -XX:MinRAMPercentage=25.0 \
    -XX:MaxRAMPercentage=75.0 \
    -XX:InitialRAMPercentage=25.0 \
    -Dnetworkaddress.cache.negative.ttl=0 \
    -Xdebug -Xnoagent \
    -Dcom.sun.management.jmxremote \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Dcom.sun.management.jmxremote.ssl=false \
    -Dcom.sun.management.jmxremote.local.only=false \
    -Djava.rmi.server.hostname=127.0.0.1 \
    -Djava.security.egd=file:/dev/./urandom"

ENV JDWP_PORT 8940
ENV JMXREMOTE_PORT 8950

ENV JAVA_OPTS ""

ENV JAR_OPTS ""
ENV ENV "docker"

COPY ./build/libs/habitio-backend-0.0.1-SNAPSHOT.jar /opt/app.jar

WORKDIR /opt/

CMD exec java \
    ${JAVA_DEFAULT_OPTS} \
    -XX:OnOutOfMemoryError="kill -2 %p" \
    --enable-preview \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=${JDWP_PORT} \
    -Dcom.sun.management.jmxremote.port=${JMXREMOTE_PORT} \
    -Dcom.sun.management.jmxremote.rmi.port=${JMXREMOTE_PORT} \
    -Dspring.profiles.active=${ENV} \
    ${JAVA_OPTS} \
    -jar /opt/app.jar \
    ${JAR_OPTS}
