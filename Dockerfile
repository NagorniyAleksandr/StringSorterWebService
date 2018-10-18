### <<<------------------------------------------------------------------------------------------------------------------------------------------>>> ###
### <<<---------------------------------------------------Stage 1: Build the component----------------------------------------------------------->>> ###
### <<<------------------------------------------------------------------------------------------------------------------------------------------>>> ###

FROM alpine:3.7 AS build-stage

ENV APP_HOME="/string-sorter-service"

# Get the source code to build
COPY src ${APP_HOME}/src
COPY pom.xml ${APP_HOME}

WORKDIR ${APP_HOME}

# Install Java and Maven, and then build the stuff
RUN apk --update add openjdk8 maven procps && \
    rm -rf /var/cache/apk/* && \
    mvn dependency:resolve && \
    mvn verify && \
    mvn package

### <<<------------------------------------------------------------------------------------------------------------------------------------------>>> ###
### <<<---------------------------------------------------Stage 2: Prepare the final image------------------------------------------------------->>> ###
### <<<------------------------------------------------------------------------------------------------------------------------------------------>>> ###

FROM alpine:3.7

LABEL project="String Sorter Service" \
      version="1.0.0"

ENV APP_HOME="/string-sorter-service" \
    APP_LOG="/logs/string-sorter-service" \
    APP_PORT="8080" \
    APP_SWAGGER_ENABLED=true

RUN apk --update add openjdk8-jre

COPY --from=build-stage ${APP_HOME} ${APP_HOME}

WORKDIR ${APP_HOME}

VOLUME ${APP_LOG}

CMD java -jar target/string-sorter-service-1.0.0-SNAPSHOT.jar \
       --server.port="${APP_PORT}" \
       --application.swagger.enabled="${APP_SWAGGER_ENABLED}" \
       --logging.path="${APP_LOG}"