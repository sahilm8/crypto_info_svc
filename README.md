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
- Spring Validation
- Jakarta Validation
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
- Crypto Time Series (interval: 1min, 5min, 15min, 30min, 60min)
