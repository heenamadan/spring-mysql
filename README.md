# spring-mysql

Please unzip this project and import in eclipse.

prerequisites:

Java 8
Maven
Tomcat 9
Mysql 5.7.22

Please run below steps for database:
1) create database robo_auto_process;
2) use robo_auto_process;

3)

CREATE TABLE IF NOT EXISTS users (

    username varchar(50) NOT NULL,
      password varchar(100) NOT NULL,
      full_name varchar(100) NOT NULL,
      role varchar(50) NOT NULL,
      country varchar(100) NOT NULL,
      enabled tinyint(1) NOT NULL,
      PRIMARY KEY (username)) ENGINE=InnoDB DEFAULT CHARSET=latin1;

4) INSERT INTO users (username, password, full_name, role, country, enabled) VALUES
    ('mukesh', '$2a$10$N0eqNiuikWCy9ETQ1rdau.XEELcyEO7kukkfoiNISk/9F7gw6eB0W', 'Mukesh Sharma', 'ROLE_ADMIN', 'India', 1),
    ('tarun', '$2a$10$QifQnP.XqXDW0Lc4hSqEg.GhTqZHoN2Y52/hoWr4I5ePxK7D2Pi8q', 'Tarun Singh', 'ROLE_USER', 'India', 1);
5)
CREATE TABLE IF NOT EXISTS demo_articles (
      article_id int(5) NOT NULL AUTO_INCREMENT,
      quantity varchar(200) NOT NULL,
      category varchar(100) NOT NULL,
      PRIMARY KEY (article_id)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


1) Open project at terminal
2) run below command:
mvn clean install
3) mvn spring-boot:run
4) open http://localhost:8080/app/login to any of your browser. (Google chrome preferred)
5) login with below credentials:
user name: mukesh
password: m123
