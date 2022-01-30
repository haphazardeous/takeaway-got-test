# Takeaway.com Candidate Test Solution by Vedat Coskun
Firstly, thanks very much for allowing me to take this test. Below are some notes with regard to the design decisions, technology used and how to run the solution.

## Solution Decisions
Despite being a multiplayer game, I opted for a simple solution where two Spring Boot apps play with each other in half-duplex mode and user inputs are provided automatically. The communication is established via HTTP calls.

The architecture is somewhat event driven. And I utilised DDD and SOLID principles writing the code.

## Technologies Used
 * Spring Boot 2.6.3
 * Java 17
 * Maven 3


## Building, Testing and Running the Solution
To package the app after running all tests, please run  ```mvn clean package```

There are two profiles available for this solution ```host``` and ```opponent```. The host runs on port ```8090``` and the opponent runs on ```8091```, please make sure both ports available on the local machine.

### Running the solution
To get two nodes play with each other, two terminals, both in the source code folder, are needed. And then; 
* in the first terminal, run ```mvn spring-boot:run``` and wait for the app to boot up.
* then in the second terminal, run ```mvn spring-boot:run -Popponent``` and wait for app to boot up.

A game can be started on both nodes by simply running (provided curl is available in your system)
```curl "http://localhost:8090/start"``` which will pick a random number and start the game or ```curl "http://localhost:8090/start?firstNumber=56"``` if you want to start the game with a number. 
