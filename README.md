# Crypto Info API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)

![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

API to fetch and return crypto data. Based on the [CoinGecko API](https://www.coingecko.com).

## Stack

- Java 21
- Maven
- Spring Boot
- Spring Web
- Spring Webflux
- Reactor Core
- Spring Dotenv
- Lombok
- Spring Test
- MacOS DNS Resolver

## Setup

- Install dependencies:
```
./mvnw clean install
```
- Start the application:
```
./mvnw spring-boot:run
```

## Endpoints

Requests can be made to get the following resources:

- Crypto
    - Name
    - Price
    - Market Cap
    - 24 Hour Volume
    - 24 Hour Change
    - Last Updated At
- Crypto Time Series
    - OHLC
    - Days: 1, 7, 14, 30, 90, 180, 365

### Requests

- GET /:
```
curl -i -X GET http://localhost:8080/api/v1/crypto/
```

- GET /get-price:
```
curl -i -X GET http://localhost:8080/api/v1/crypto/get-price?name=bitcoin
```

- GET /get-ohlc:
```
curl -i -X GET http://localhost:8080/api/v1/crypto/get-ohlc?name=bitcoin&days=365
```
