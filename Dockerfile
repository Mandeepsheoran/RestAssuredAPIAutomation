FROM maven:3.6.3-jdk-8

COPY src home/apiframework/src

COPY index.html home/apiframework/index.html

COPY pom.xml home/apiframework/pom.xml


COPY testng.xml home/apiframework/testng.xml

WORKDIR home/apiframework
#Running the actual command
RUN mvn clean test
