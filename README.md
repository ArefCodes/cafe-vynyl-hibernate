:coffee:**Cafe Vynyl Project - Hibernate**

This project is built with Hibernate and Postgresql. The purpose is to manage cafe menu system.

**Features:**
```
1. Uses Hibernate ORM for database management.
2. Stores menu items, vynyl, and category into tables.
3. Fully configurable with hibernate.cfg.xml.
```

✅**Prerequisites:**
Before running project, make sure you have following:
```
1. Java 23+
2. PostgreSQL installed and running
3. Maven installed
4. Git installed
```
**Project Structure:**
```
📦 cafe-vynyl-hibernate
 ┣ 📂 src
 ┃ ┣ 📂 main
 ┃ ┃ ┣ 📂 java/com/aref
 ┃ ┃ ┃ ┣ 📜 Main.java
 ┃ ┃ ┃ ┣ 📜 Menuitems.java
 ┃ ┃ ┃ ┣ 📜 Category.java
 ┃ ┃ ┃ ┣ 📜 Vynyl.java
 ┃ ┃ ┣ 📂 resources
 ┃ ┃ ┃ ┣ 📜 hibernate.cfg.xml
 ┣ 📜 README.md
 ┣ 📜 .gitignore
 ┣ 📜 LICENSE
 ┣ 📜 pom.xml
 ```
 **Database Schema:**
 Category table:
 ```
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private int id;

    @Column(name = "name", nullable = false, unique = true) //making columns of the table
   
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    
    private List<Menuitems> menuItems = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)

    private List<Vynyl> vynyl = new ArrayList<>();
}
```
Menuitems table:
```
@Entity
public class Menuitems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 3)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
```
Vynyl table:
```
@Entity
public class Vynyl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
```
**About this project:**
```
This project is part of my journy to become a professional Java Backend Developer. My next goal is to migrate this project to Spring Boot. If you have any suggestions for improvement, I will be sincerely happy if you contribute. :):
```
**Author:**
```
Aref Hamidipour
arefhamidi94@gmail.com
https://github.com/arefcodes
```