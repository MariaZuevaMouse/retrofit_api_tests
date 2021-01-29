# retrofit_api_tests

### Download and run market application in IDE or throw console:
download command: 

 `git clone https://github.com/MariaZuevaMouse/mini-market-swagger.git`
 
compile command:

 `javac -csourcepath ./src -d bin src/main/java/ru/geekbrains/mini/market/MiniMarketApplication.java`
 
 
launch command:

` java -classpath ./bin main/java/ru/geekbrains/mini/market/MiniMarketApplication`

### Learn API service, documentation:
##### open mini-market ui throw browser 
http://localhost:8189/market

##### see ui documentation in browser 
http://localhost:8189/market/swagger-ui.html#/category-controller

##### console can be opened by URL:
http://localhost:8189/market/h2-console

### to test API download test project:
`git clone https://github.com/MariaZuevaMouse/retrofit_api_tests.git`

and launch test:
`mvn clean test allure:serve`

##### There is considered several endpoints testing:

POST  - create product:
 - positive test
 - create product with already exist id
 - //create product with empty fields
 - create product with nonexistent category 

PUT - modify product:

 - modify product category positive test 
 - modify product price  positive test 
 - modify product title  positive test 
 - modify product category to `null` value 
 - modify product price  to `null` value 
 - modify product title to `null` value 
 - modify product price to negative value 

GET one product

- positive product GET query
- receive nonexistent product
- thrown Null PointerException if Id is null;


DELETE product

- positive product delete
- delete nonexistent product
- thrown Null PointerException if Id is null;



GET all products:
 - positive test




