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

Requests can be made to perform the following actions:

- Get Crypto
- Get Crypto Time Series (days: 1, 7, 14, 30, 90, 180, 365, max)

### Get Crypto

#### Request

```
curl --location --request GET 'localhost:8080/api/v1/crypto/get-crypto' \
--header 'Content-Type: application/json' \
--data '{
    "name": "bitcoin"
}'
```

#### Response

```
{
    "price": 103145.49312179499,
    "marketCap": 2043195974511.9758,
    "volume": 150847790218.57977,
    "change": -1.5582451620468336,
    "lastUpdatedAt": 1737409217
}
```

### Get Crypto Time Series

#### Request

```
curl --location --request GET 'localhost:8080/api/v1/crypto/get-crypto-ts' \
--header 'Content-Type: application/json' \
--data '{
    "name": "bitcoin",
    "days": 365
}'
```

#### Response

```
[
    [
        "1705881600000",
        "41661.368099231084",
        "41842.672928405475",
        "41513.193991957734",
        "41541.89945706261"
    ],
    [
        "1706227200000",
        "41545.548425536675",
        "41644.54846272758",
        "38520.372972443714",
        "39938.286441558776"
    ],
    [
        "1706572800000",
        "39917.164425029805",
        "43286.6392695487",
        "39826.881954984616",
        "43267.609276387666"
    ],
    ...
]
```
