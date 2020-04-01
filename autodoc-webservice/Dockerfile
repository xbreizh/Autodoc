FROM tomcat:9.0.14-jre8
RUN mkdir -p /usr/local/tomcat/webapps
RUN rm -rf /usr/local/tomcat/webapps/*
RUN ls -l /usr/local/tomcat/webapps
RUN pwd
COPY ./testCopy.txt /usr/local/tomcat/webapps/testCopy.txt
COPY autodoc-webservice.war /usr/local/tomcat/webapps/autodoc-webservice.war
#COPY ./troparo-technical/src/main/resources/* /usr/app/resources/
RUN ls -l /usr/local/tomcat/webapps
CMD ["catalina.sh","run"]
