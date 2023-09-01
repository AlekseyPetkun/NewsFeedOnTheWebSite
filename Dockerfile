FROM maven:3.6.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

FROM openjdk:17-oracle
WORKDIR /app
COPY --from=build /app/target/newsFeedApp-0.0.1-SNAPSHOT.jar newsFeedApp.jar
EXPOSE 8080
CMD ["java", "-jar", "newsFeedApp.jar"]



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