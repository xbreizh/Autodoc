FROM tomcat:9.0.14-jre8
RUN rm -rf /usr/local/tomcat/webapps/*

COPY /web/build/libs/web-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/web-1.0-SNAPSHOT.war
CMD ["catalina.sh","run"]