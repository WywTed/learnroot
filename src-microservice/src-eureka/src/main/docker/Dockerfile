# https://www.cnblogs.com/lighten/p/6900556.html
FROM 192.168.1.52/tenx_containers/oraclejdk:8u172

ENV PROFILE       default

ADD eureka-server.jar /app/app.jar

WORKDIR "/app"

EXPOSE 8761

CMD ["java", "-Xms256m", "-Xmx1g", \
     "-XX:+UnlockExperimentalVMOptions", \
     "-XX:+UseCGroupMemoryLimitForHeap", \
     "-Dspring.profiles.active=${PROFILE}", \
     "-Dspring.config.location=/app/config", \
     "-jar", "/app/app.jar"]
