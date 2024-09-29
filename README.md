# TaxService

TaxService is a microservice designed to return tax rates for products based on the product category and customer address. It supports tax rate determination for Germany, the European Union (EU), the United States (using an external service), and other regions.

## Features

- One endpoint to fetch the tax rate.
- Tax rate determination based on:
    - **Germany**: 7% for eBooks, 19% for subscriptions.
    - **EU**: 10% fixed tax rate.
    - **US**: Tax rates fetched from Avalara API.
    - **Other Regions**: 0% tax rate.

## Technologies Used

- Java 21 (LTS)
- Spring Boot
- Spring Web (REST)
- Mockito (for unit tests)
- Maven (build automation tool)

## Getting Started

### Prerequisites

To run the project, you need the following tools installed:

- [Java 21+](https://openjdk.java.net/)
- [Maven 3+](https://maven.apache.org/)

### Project Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/ibrahim-dogan/statista-study-case.git
   cd statista-study-case
   ```

2. Build the project using Maven:

   ```bash
   mvn clean install
   ```

3. Run the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

4. The application will be available at `http://localhost:8080`.

### Docker Support

To run the application in a Docker container, follow these steps:

1. **Build the Docker image**:

   ```bash
   docker build -t taxservice:latest .
   ```

2. **Run the Docker container**:

   ```bash
   docker run -p 8080:8080 taxservice:latest
   ```

3. The application will be available at `http://localhost:8080` in your browser or through API tools like Postman.


### Configuration

#### Avalara API Integration

The service integrates with MockAPI (instead of Avalara) to get tax rates for US addresses. You can modify the API URL in the `AvalaraClient.java` class or externalize it to `application.properties`:

### API Endpoints

#### Get Tax Rate

- **URL**: `/tax/rate`
- **Method**: `POST`
- **Content-Type**: `application/json`

##### Request Body Example:

```json
{
  "productCategory": "ebook",
  "address": {
    "country": "Germany"
  }
}
```

```json
{
  "productCategory": "ebook",
  "address": {
    "country": "US",
    "region": "Virginia"
  }
}
```

##### Response Example:

```json
{
  "taxRate": 0.07
}
```

### Folder Structure

```plaintext
src
├── main
│   └── java
│       └── com.statista.taxservice
│           ├── client       # External service integration (Avalara)
│           ├── config       # Configuration for RestTemplate
│           ├── controller   # API controllers
│           ├── domain       # Business logic for tax calculation
│           ├── model        # Request/Response models
│           └── service      # Service layer
├── resources
│   └── application.properties  # Application configuration
└── test
    └── com.statista.taxservice  # Unit tests
```

### Running Tests

To run the tests, you can use the following Maven command:

```bash
mvn test
```

This will run all unit tests, including tests for tax rate calculations for different regions and mock Avalara API interactions.

### Example Tests

The project uses **Mockito** to mock dependencies and ensure unit tests are isolated. Example test classes include:

- `TaxServiceTest`: Tests the main business logic for fetching tax rates.
- `USTaxCalculatorTest`: Tests the tax calculation logic for US addresses using the Avalara service.

```java
@Test
void testCalculateTaxRate() {
    Address address = new Address();
    address.setCountry("US");
    address.setRegion("California");

    TaxRequest request = new TaxRequest();
    request.setAddress(address);

    double expectedTaxRate = 0.07;
    Mockito.when(avalaraClient.getUSTaxRate(address)).thenReturn(expectedTaxRate);

    double actualTaxRate = usTaxCalculator.calculateTaxRate(request);

    assertEquals(expectedTaxRate, actualTaxRate);
}
```