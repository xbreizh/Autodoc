FROM tomcat:9.0.14-jre8
RUN rm -rf /usr/local/tomcat/webapps/*
COPY /web/libs/web.war /usr/local/tomcat/webapps/web.war
#COPY ./troparo-technical/src/main/resources/* /usr/app/resources/
CMD ["catalina.sh","run"]
