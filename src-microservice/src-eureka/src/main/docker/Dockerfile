# https://www.cnblogs.com/lighten/p/6900556.html
FROM 192.168.1.52/tenx_containers/oraclejdk:8u172

ENV PROFILE        default
ENV EUREKA_HOST    spring-cloud-discovery

ADD eureka-server.jar /app/app.jar

WORKDIR "/app"

EXPOSE 9100

CMD ["java", "-Xms256m", "-Xmx1g", \
     "-XX:+UnlockExperimentalVMOptions", \
     "-XX:+UseCGroupMemoryLimitForHeap", \
     "-Dspring.profiles.active=${PROFILE}", \
     "-Deureka.host=${EUREKA_HOST}", \
     "-Dspring.config.location=/app/config", \
     "-jar", "/app/app.jar"]
