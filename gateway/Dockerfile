FROM openjdk:8-jdk-alpine
EXPOSE 8765
WORKDIR /app
ADD target/app.jar /app.jar 
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait
CMD /wait && java -jar /app.jar
