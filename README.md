o# CRM System - Customer Relationship Management

Professional CRM system built with Spring Boot, PostgreSQL, and Docker.

## 🚀 Features

- **Customer Management**: CRUD operations for managing customers
- **Deal Management**: Track sales deals and opportunities
- **Task Management**: Organize tasks and to-dos
- **RESTful API**: Well-structured REST endpoints
- **Swagger Documentation**: Interactive API documentation
- **Docker Support**: Containerized application
- **PostgreSQL Database**: Reliable data storage

## 📋 Technologies

- **Backend**: Spring Boot 3.3.5
- **Database**: PostgreSQL 15
- **ORM**: Spring Data JPA / Hibernate
- **Documentation**: Swagger/OpenAPI 3
- **Build Tool**: Gradle
- **Containerization**: Docker & Docker Compose
- **Java Version**: 17

## 🏗️ Project Structure
```
crm-system/
├── src/main/java/com/example/crm/
│   ├── config/          # Configuration classes
│   ├── controller/      # REST Controllers
│   ├── dto/             # Data Transfer Objects
│   ├── exception/       # Custom exceptions
│   ├── model/           # Entity classes
│   ├── repository/      # JPA Repositories
│   └── service/         # Business logic
├── src/main/resources/
│   └── application.properties
├── Dockerfile
├── docker-compose.yml
└── build.gradle
```

## 🔧 Installation & Setup

### Prerequisites

- Java 17 or higher
- Docker Desktop
- PostgreSQL (or use Docker)
- IntelliJ IDEA (recommended)

### Steps

1. **Clone the repository**
```bash
   git clone <your-repo-url>
   cd crm-system
```

2. **Start PostgreSQL with Docker**
```bash
   docker-compose up -d postgres
```

3. **Run the application**
```bash
   ./gradlew bootRun
```
Or run from IntelliJ IDEA

4. **Access Swagger UI**
```
   http://localhost:8080/swagger-ui.html
```

## 📡 API Endpoints

### Customer Endpoints
- `POST /api/customers` - Create customer
- `GET /api/customers` - Get all customers
- `GET /api/customers/{id}` - Get customer by ID
- `PUT /api/customers/{id}` - Update customer
- `DELETE /api/customers/{id}` - Delete customer
- `GET /api/customers/status/{status}` - Filter by status
- `GET /api/customers/search?name=` - Search by name

### Deal Endpoints
- `POST /api/deals` - Create deal
- `GET /api/deals` - Get all deals
- `GET /api/deals/{id}` - Get deal by ID
- `PUT /api/deals/{id}` - Update deal
- `DELETE /api/deals/{id}` - Delete deal
- `GET /api/deals/stage/{stage}` - Filter by stage
- `GET /api/deals/customer/{customerId}` - Get customer deals
- `GET /api/deals/total/{stage}` - Get total amount by stage

### Task Endpoints
- `POST /api/tasks` - Create task
- `GET /api/tasks` - Get all tasks
- `GET /api/tasks/{id}` - Get task by ID
- `PUT /api/tasks/{id}` - Update task
- `DELETE /api/tasks/{id}` - Delete task
- `GET /api/tasks/status/{status}` - Filter by status
- `GET /api/tasks/priority/{priority}` - Filter by priority
- `GET /api/tasks/overdue` - Get overdue tasks

## 🐳 Docker Commands
```bash
# Start all services
docker-compose up -d

# Start only PostgreSQL
docker-compose up -d postgres

# Stop all services
docker-compose down

# Stop and remove volumes
docker-compose down -v

# View logs
docker-compose logs -f

# Rebuild and start
docker-compose up -d --build
```

## 📊 Database Schema

### Customers Table
- id, firstName, lastName, email, phone
- company, address, status
- createdAt, updatedAt

### Deals Table
- id, title, description, amount, stage
- expectedCloseDate, actualCloseDate
- customer_id (FK), createdAt, updatedAt

### Tasks Table
- id, title, description, status, priority
- dueDate, customer_id (FK)
- createdAt, updatedAt

## 🔐 Environment Variables
```properties
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/crm_db
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=root
```

## 🌐 Deployment

### Deploy to Render.com (Free)

1. Create account on render.com
2. Create new Web Service
3. Connect GitHub repository
4. Configure:
    - Build Command: `./gradlew build`
    - Start Command: `java -jar build/libs/*.jar`
5. Add PostgreSQL database
6. Set environment variables

### Deploy to Railway.app (Free)

1. Create account on railway.app
2. New Project → Deploy from GitHub
3. Add PostgreSQL plugin
4. Auto-deploy enabled

## 📝 Sample API Requests

### Create Customer
```json
POST /api/customers
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phone": "+998901234567",
  "company": "Tech Corp",
  "address": "Tashkent, Uzbekistan",
  "status": "ACTIVE"
}
```

### Create Deal
```json
POST /api/deals
{
  "title": "New Software Project",
  "description": "CRM development deal",
  "amount": 50000.00,
  "stage": "PROPOSAL",
  "expectedCloseDate": "2025-12-31",
  "customerId": 1
}
```

### Create Task
```json
POST /api/tasks
{
  "title": "Follow up call",
  "description": "Call customer about proposal",
  "status": "TODO",
  "priority": "HIGH",
  "dueDate": "2025-10-25T14:00:00",
  "customerId": 1
}
```

## 🤝 Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository
2. Create feature branch
3. Commit changes
4. Push to branch
5. Open pull request

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

Menace - Tashkent, Uzbekistan

## 📞 Support

For issues and questions, please open an issue on GitHub.
