# use jdk to build
FROM openjdk:8-jdk as BUILD
COPY src/ /code/
WORKDIR /code
RUN javac MemoryEater.java

# only use jre to run
FROM openjdk:8-jre
WORKDIR /app
COPY --from=BUILD /code/MemoryEater.class /app
ENV JAVA_OPTS ""
CMD java $JAVA_OPTS MemoryEater