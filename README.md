# 📚 Dynamic-Quiz-Application

A full-stack web application that dynamically generates quizzes using AI based on user input. The system allows users to create quizzes on any topic, attempt them interactively, and evaluate their performance.

## 🚀 Features
* 🎯 **Generate quizzes dynamically** using Gemini AI
* 🧠 **Supports custom prompts** (any topic & difficulty)
* 📝 **Multiple-choice questions (MCQs)** with real-time navigation
* 🔄 **Randomized question generation** via AI
* 🌐 **Web-based interface** using Thymeleaf & Bootstrap
* ⚡ **Backend powered by Spring Boot** & Spring Data JPA
* 📊 **Score calculation system** for instant results
* 📂 **Clean DTO-based data handling** for JSON parsing

## 🏗️ Tech Stack
**Frontend:**
* HTML5, CSS3 (Bootstrap)
* Thymeleaf Template Engine
* JavaScript (AJAX for dynamic updates)

**Backend:**
* Java (JDK 17+)
* Spring Boot (Web, Data JPA, DevTools)
* MySQL Database

**AI Integration:**
* Google Gemini Pro API

**Tools:**
* Maven (Dependency Management)
* Git/GitHub (Version Control)

## 📂 Project Structure
```text
Dynamic-Quiz-Application
│
├── src/main/java/com/quiz/
│   ├── controller     # Handles HTTP requests & Navigation
│   ├── service        # Business logic & Gemini AI integration
│   ├── repository     # Spring Data JPA interfaces
│   ├── model/dto      # Entity classes & Data Transfer Objects
│   └── entity         # Database entities (User, Quiz, Score)
│
├── src/main/resources/
│   ├── templates      # Thymeleaf HTML pages (Login, Dashboard, Quiz)
│   ├── static         # CSS/JS files
│   └── application.properties

```

## ⚙️ How to Run

### 1. Clone the Repository
```bash
git clone [https://github.com/SinghAmanPappu/Dynamic-Quiz-Application.git](https://github.com/SinghAmanPappu/Dynamic-Quiz-Application.git)
cd Dynamic-Quiz-Application
2. Configure Environment Variables
To keep your API key secure, set it as an environment variable on your system:

Name: SPRING_AI_API_KEY

Value: your_gemini_api_key_here

Alternatively, update src/main/resources/application.properties:

Properties
spring.ai.api.key=${SPRING_AI_API_KEY}
3. Install & Run
Bash
mvn clean install
mvn spring-boot:run
Open your browser to: http://localhost:8080

🧪 API Testing (Postman)
📌 Endpoint
POST /generate

📥 Request Example
Body (JSON):

JSON
{
  "prompt": "Java OOP concepts"
}
📤 Response Example
JSON
{
  "questions": [
    {
      "question": "What is inheritance?",
      "options": [
        "Code reuse mechanism",
        "Loop structure",
        "Database concept",
        "None"
      ],
      "answer": "Code reuse mechanism"
    }
  ]
}
