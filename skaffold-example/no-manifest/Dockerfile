# use jdk to build
FROM openjdk:8u151-jdk-alpine3.7 as BUILD
COPY src/ /code/
WORKDIR /code
RUN javac SimpleJavaApplication.java

# only use jre to run
FROM openjdk:8u151-jre-alpine3.7
WORKDIR /app
COPY --from=BUILD /code/SimpleJavaApplication.class /app
CMD ["java", "SimpleJavaApplication"]