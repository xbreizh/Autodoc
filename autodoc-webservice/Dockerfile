FROM tomcat:9.0.14-jre8
RUN mkdir -p /usr/local/tomcat/webapps/webservice
RUN rm -rf /usr/local/tomcat/webapps/webservice/*

COPY ./autodoc-webservice/web/build/libs/autodoc-webservice.war /usr/local/tomcat/webapps/webservice/autodoc-webservice.war

CMD ["catalina.sh","run"]
