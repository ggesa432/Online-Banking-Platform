# Online Banking Platform

A full‑featured web banking application that supports customers, branch staff, and administrators.  
Customers manage their accounts and perform transactions; staff open and maintain customer accounts; administrators oversee branches, roles, and audit activity.

---

## Features

| Actor          | Key Capabilities |
| -------------- | ---------------- |
| **Customer**   | • View balances & transactions  <br>• Deposit / withdraw / transfer funds  <br>• Self‑service profile updates |
| **Staff**      | • Create / update customer records  <br>• Open new accounts  <br>• Resolve service requests |
| **Administrator** | • Manage branches & staff  <br>• Assign roles  <br>• System‑wide dashboards & audit logs |

---

## Tech Stack

* **Backend**  | Java 17, Spring Boot 3 (MVC, Data JPA, Security), Hibernate 6, JdbcTemplate  
* **Frontend** | JSP + JSTL, HTML 5, Bootstrap 5, JavaScript  
* **Database** | MySQL 8 (on AWS RDS)  
* **Storage**  | AWS S3 for profile images / docs  
* **Security** | BCrypt hashing, role‑based access, custom success & access‑denied handlers  
* **Testing**  | JUnit 5, Mockito, Postman  
* **Build / Deploy** | Maven, Tomcat 10 (embedded), AWS

---

## Getting Started

```bash
# 1  Clone
git clone https://github.com/your‑username/online‑banking‑platform.git
cd online‑banking‑platform

# 2  Configure DB + AWS creds in src/main/resources/application.properties
#    (create schema 'onlinebankingdb' in MySQL)

# 3  Build
mvn clean install

# 4  Run
mvn spring‑boot:run    # → http://localhost:8081/
```

Initial admin credentials:

| Username | Password |
| -------- | -------- |
| `admin`  | `admin123` |

---

## Project Structure

```
src
 └─ main
    ├─ java/com/synergisticit
    │   ├─ controller
    │   ├─ service
    │   ├─ repository
    │   └─ model
    ├─ resources
    │   ├─ application.properties
    │   └─ static
    └─ webapp/WEB-INF/views   (JSP)
```

---

## Implementation Notes

* **Seed data** – `CommandLineRunner` creates default *Admin* role & user.  
* **Transactional logic** – deposits, withdrawals, transfers recorded in `BankTransaction` table via service layer.  
* **Branch DAO** – uses raw `JdbcTemplate` for fine‑grained SQL.  
* **Role‑aware pages** – JSTL `<c:choose>` hides / disables fields for non‑privileged users.  
* **Server‑side sorting** – user & role lists returned as `ORDER BY username / role_name ASC`.

---

## Screenshots

> _Add UI screenshots or a short demo gif here_

---

## Contributing

1. Fork the repo  
2. Create your feature branch (`git checkout -b feat/AmazingFeature`)  
3. Commit your changes (`git commit -m 'feat: add AmazingFeature'`)  
4. Push to the branch (`git push origin feat/AmazingFeature`)  
5. Open a Pull Request

---

## License

This project is released for educational purposes.  
Contact the author for production licensing.

---

_Built with Spring Boot & ☕ by **Gesangzeren**_  

