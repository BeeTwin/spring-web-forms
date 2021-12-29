FROM maven:latest
RUN git clone https://github.com/BeeTwin/spring-web-forms.git
WORKDIR spring-web-forms
RUN mvn package
ENTRYPOINT ["/bin/bash"]
CMD ["run.sh"]
