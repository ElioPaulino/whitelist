# Builder
FROM azul/zulu-openjdk:21 AS builder
COPY  . /root/app/
WORKDIR /root/app
RUN chmod +x ./mvnw && ./mvnw clean install -B -s settings.xml -Dmaven.test.skip

FROM amazoncorretto:21-alpine-jdk AS application
RUN addgroup --gid 1001 app \
    && adduser --ingroup app --shell /bin/false --disabled-password --uid 1001 app \
    && chown app:app .
USER app
COPY --from=builder /root/app/target/*.jar /home/app/
WORKDIR /home/app
RUN chmod 0777 /home/app
EXPOSE 8080
ENTRYPOINT java -jar $JAVA_OPTIONS *.jar $APP_ARGS