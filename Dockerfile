FROM maven:3-eclipse-temurin-17 AS MAVEN
WORKDIR /build/
COPY pom.xml /build
COPY src /build/src
RUN mvn clean install -DskipTests=true

FROM eclipse-temurin:17-jdk
COPY --from=MAVEN /build/target/newsFeedApp-0.0.1-SNAPSHOT.jar /opt/newsFeedApp.jar
ENTRYPOINT java -Xms512m -Xmx1024m -Duser.timezone=UTC -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9999 -jar -Djava.security.egd=file:/dev/./urandom /opt/newsFeedApp.jar


##FROM openjdk:17-oracle
##WORKDIR /app
##COPY --from=build /app/target/newsFeedApp-0.0.1-SNAPSHOT.jar newsFeedApp.jar
##EXPOSE 8080
##CMD ["java", "-jar", "newsFeedApp.jar"]



#DRAFT

#COPY /app/target/*.jar /app/*.jar
#COPY docker-startup.sh .
#RUN chmod +x docker-startup.sh
#EXPOSE 8080
#CMD ["./docker-startup.sh"]

#AS build
#WORKDIR /app
#COPY . .
#RUN ./gradlew build

#FROM openjdk:17-oracle
#WORKDIR /app
#COPY --from=build /app/newsfeedapp-0.0.1.jar .
#COPY docker-startup.sh .
#RUN chmod +x docker-startup.sh
#EXPOSE 8080
#CMD ["./docker-startup.sh"]

#ARG JAR_FILE=target/com-example-newsFeedApp-0.0.1-SNAPSHOT.jar
#WORKDIR /opt/app
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]