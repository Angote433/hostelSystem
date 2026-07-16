# Hostel System

A console-based hostel/dormitory management system written in Java, backed by MySQL.
It's a learning project built to practice core Java, JDBC, and layered application
design (menu -> service -> DAO), with an eye towards eventually growing into a full
web application.

## What it does

There are two kinds of users: **students** and **admins**.

**Students** can:
- Create an account (name, registration number, username, email, phone, gender, password)
- Log in
- Apply for a hostel/room allocation (if they don't have a bed yet)
- Request a room transfer (if they already have a bed and want a different one)

**Admins** can:
- Register hostels (name + gender type, since hostels are gender-segregated)
- Register rooms within a hostel
- Add beds to a room
- Review pending student requests and approve or reject them
- Handle allocations: for an approved request, pick a hostel matching the student's
  gender, then an available bed, and assign it — freeing the student's previous bed
  first if it's a transfer

## Tech stack

- Java 25 (Maven, `pom.xml`)
- MySQL (via `mysql-connector-j`)
- [jBCrypt](https://www.mindrot.org/projects/jBCrypt/) for password hashing
- Plain JDBC (no ORM) with a simple DAO -> Service -> Menu layering

## Project structure

```
src/main/java/com/ejaisoft/
  HostelSystem.java      entry point
  Utils/                 SystemSettings (DB connection), PasswordUtil, Validator
  model/                 Student, Admin, Hostel, Room, Bed, Request, and enums
  dao/                   JDBC data access, one class per table
  service/               business rules and validation, sits between menus and DAOs
  menu/                  console UI (MainMenu, LoginMenu, AdminMenu, StudentMenu)

src/main/resources/
  db.properties.example  copy to db.properties (gitignored) and fill in your credentials
  sql/                   schema notes/fixes for tables not covered by an ORM migration tool

src/test/java/
  SeedAdmin.java         one-off utility to create the first admin account
  DbMapTest.java         ad hoc manual test scratchpad
```

## Running it

1. Have MySQL running with a `hostel_system` database containing `student`, `hostel`,
   `room`, `bed`, `admin`, and `requests` tables (see `src/main/resources/sql/` for the
   admin table and a couple of schema fixes that were needed).
2. Copy `src/main/resources/db.properties.example` to `db.properties` in the same
   folder and fill in your MySQL URL/username/password.
3. Seed an admin account (one-time):
   ```
   mvn test-compile exec:java -Dexec.mainClass=SeedAdmin
   ```
   Creates username `admin` / password `ChangeMe123!` — log in with that once, then
   change it (see Future Improvements).
4. Run the app:
   ```
   mvn compile exec:java
   ```
   or run `HostelSystem.java` directly from your IDE.

## Future improvements

Roughly in order of what would matter most next:

- **Web version** — swap the console `menu` layer for a web framework (Spring Boot is
  the natural fit given the existing service/DAO layering barely changes) with proper
  sessions instead of passing objects between menu calls.
- **Change/reset password** — there's currently no way to change a password after
  account creation, for students or admins.
- **View my allocation** — students can apply/transfer but can't currently see their
  own current hostel/room/bed from the menu; it's only visible via the DB.
- **Hostel change requests** — `RequestType.HOSTEL_CHANGE` exists in the model/schema
  but isn't wired to any menu option yet.
- **Admin-side room/bed editing** — updating or removing a room/bed once created isn't
  supported yet, only adding.
- **Pagination/filtering** for listings (hostels, rooms, requests) once the data set
  grows past what fits on one screen.
- **Automated tests** — there's no test suite yet beyond manual/scripted runs; the
  service layer is the natural place to start (it's already decoupled from the console
  I/O).
- **Migration tool** (e.g. Flyway) instead of the plain `.sql` files in
  `src/main/resources/sql/`, once the schema starts changing more often.
