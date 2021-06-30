FROM tomcat:8.5-jre8

LABEL maintainer="Mohamad el cheikh ali"

ADD ./target/lib-service.war /usr/local/tomcat/webapps/
RUN mkdir -p /root/.station
ADD ./src/main/resources/properties/db.properties /root/.station

EXPOSE 8080

CMD ["catalina.sh", "run"]
