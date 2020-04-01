FROM tomcat:9.0.14-jre8
COPY ./testCopy.txt /usr/local/tomcat/testCopy.txt
RUN mkdir -p /usr/local/tomcat/webapps
RUN rm -rf /usr/local/tomcat/webapps/*
RUN ls -l /usr/local/tomcat/webapps
RUN pwd
COPY . /usr/local/tomcat/webapps/autodoc-webservice.war
#COPY ./troparo-technical/src/main/resources/* /usr/app/resources/
RUN ls -l /usr/local/tomcat/webapps
CMD ["catalina.sh","run"]
