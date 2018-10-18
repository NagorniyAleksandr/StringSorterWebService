# String Sorter WebService

Java-based RESTful web service that reads a collection of strings from request payload, sort them by provided comparison algorithm and return top _N_ results.

###In the current version(1.0.0) this service provides the next sorting algorithm**:
+ input strings are sorted by the longest word contained in the string.
+ if there are the same length of the longest word, will be selected "biggest by lexicographical comparison" longest word.

## Requirements (if not using docker/docker-compose)
1. java v.8.*
2. maven v.3.* (if not using docker)
3. docker (if using docker)

## How to:
## Run application using docker
1. Clone project from repository to your local machine(this location will be used in the documentation as variable _${PROJECT DIR}_)
2. Override ENVs provided in dockerfile if needed
3. Open terminal in your ${PROJECT DIR} and run the following commands:
* ```docker build -t string-sorter-service . ```
* ```docker run --name string-sorter-service -d -p 8090:8080 string-sorter-service```

\* to stop docker container run command:
```docker stop string-sorter-service```

## Run application without docker:
1. Clone project from repository to your local machine.
2. Check application configuration in `${PROJECT DIR}/src/main/resources/application.yml`
3. Build application (it will create "target" folder in project director):
* ```cd ${PROJECT DIR}```\
* ```mvn clean package```
4. Run
with Default Configuration (application.yml):
        
        
        java -jar ${PROJECT DIR}/target/string-sorter-service-*.jar
Run with Custom Configuration (replace 'some.property.key' and 'custom_value' with your property to override):

       java -jar ${PROJECT DIR}/target/string-sorter-service-*.jar --some.property.key=custom_value


### Change application configuration
Set your property to ```${PROJECT DIR}/src/main/resources/application.yaml``` if needed