FROM openjdk:22-oraclelinux8
RUN microdnf install -y nmap-ncat && \
    microdnf clean all
WORKDIR /app
COPY target/blog-post-service-0.0.1-SNAPSHOT.jar app.jar
COPY wait_script.sh wait_script.sh
EXPOSE 8080
RUN chmod +x wait_script.sh
ENTRYPOINT [ "./wait_script.sh" ]
