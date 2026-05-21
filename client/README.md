# Лабораторна робота №8 — Клієнт-серверний застосунок

**Тема:** Система "Факультатив"  
**Стек:** Spring Boot 3 (сервер) + Vue JS 3 (клієнт)

---

## Структура проекту

```
java_labs/
├── lab7/elective/   # Spring Boot 3 — REST API
└── client/          # Vue JS 3 — веб-клієнт (цей проект)
```

---

## Серверна частина (Spring Boot)

### Вимоги
- Java 17+
- MySQL 8+

### Налаштування

У файлі `lab7/elective/src/main/resources/application.properties` встанови свій пароль MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/electivedb?serverTimezone=UTC&createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### Запуск

```bash
cd lab7/elective
./mvnw spring-boot:run
```

Сервер запуститься на `http://localhost:8080`

### REST API ендпоінти

| Метод | URL | Опис |
|-------|-----|------|
| GET | /api/students | Список студентів |
| GET | /api/students/{id} | Студент за ID |
| POST | /api/students | Створити студента |
| PUT | /api/students/{id} | Оновити студента |
| DELETE | /api/students/{id} | Видалити студента |
| GET | /api/courses | Список курсів |
| GET | /api/courses/{id} | Курс за ID |
| POST | /api/courses | Створити курс |
| PUT | /api/courses/{id} | Оновити курс |
| DELETE | /api/courses/{id} | Видалити курс |

---

## Клієнтська частина (Vue JS 3)

### Вимоги
- Node.js 20+

### Встановлення залежностей

```bash
npm install
```

### Запуск

```bash
npm run dev
```

Клієнт запуститься на `http://localhost:5173`

### Використані бібліотеки

| Бібліотека | Призначення |
|---|---|
| Vue 3 | основний фреймворк |
| vue-router 4 | навігація між сторінками |
| Pinia | управління станом |
| Axios | HTTP-запити до REST API |
| Bootstrap 5 | стилізація інтерфейсу |

### Сторінки

- `/students` — список студентів, повний CRUD
- `/courses` — список курсів, повний CRUD