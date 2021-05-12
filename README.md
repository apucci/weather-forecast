# Weather Forecast System

## Details

- To verify the planet's alignment with themselves, and with the sun, was used Pearson's linear correlation coefficient;
- The rainy days are verified by measuring the internal area of the triangle formed by the three planets, and them
  compared with the sum of the area of the triangles formed with each 2 planet, and the sun, if sum of the area is
  greater than the area of the original triangle, tha the sun is outside the triangle;
- The heavy rain days are discovered by saving each triangle perimeter in the database, and after querying it,
  searching; for the highest perimeter and updating all records that have the same value to heavy_rain;
- The solution is available in heroku: https://...

## Development

### Comments

- There is an Open API spec file, at the root, and it can be visualized at any toll that supports OAS 3;
- There is also a metrics API, that shoes metrics in Prometheus format, that can be accessed in the path /prometheus;

### Prerequisites

- Docker
- Java 11

### Running

First is necessary to run the Database

Run local database:

```
docker-compose up -d
```

After, run the application

```
./mvnw mn:run
```

### Tests

For running the tests simply run a clean install

```
./mvnw clean install
```