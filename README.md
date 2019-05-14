
Your task is to implement a specific feature as a REST service that uses some 3rd party API.
A service should make an aggregated search of trees (plants, not binary trees) in the circle.
Input:
  - X and Y of the cartesian center point
  - A search radius in meters

Output:
  - Aggregation by "common name" (please see in the documentation on the same page above which field to refer to)

Example of the expected output:
```json
{
    "red maple": 30,
    "American linden": 1,
    "London planetree": 3
}
```

The service should use the data from the 3rd party API (https://data.cityofnewyork.us/Environment/2015-Street-Tree-Census-Tree-Data/uvpi-gqnh): `https://data.cityofnewyork.us/resource/nwxe-4ae8.json`



1. Build
  - From command line navigate to the project root folder
  - Execute command: $mvn clean install

2. Execute
  - From project root folder execute command: $java -jar target/interview.assignment-1.0-SNAPSHOT.jar

3. Testing
  - Open a browser and access: http://localhost:8080/swagger-ui.html
  - To search for trees, navigate to Tree section, click on POST line and after that click on "Try it out" button
  - Specify the request body and click on "Execute" button