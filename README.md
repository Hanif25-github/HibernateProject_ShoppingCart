# Shopping Cart System - Hibernate Project
  Shopping Cart Management System manages shopping database using Hibernate, ORM tool. Project is developed by performing all CRUD, search operations on database.

### hibernate.cfg.xml [link](src/main/java/hibernate.cfg.xml)
This is hibernate configuration file.It contains configuration details of Session Factory and resource of hibernate mapping file. It contains database connection details such as connection url, username, password, dialect, Driver class etc. I used Oracle Driver in this project.

### pom.xml [link](src/main/java/hibernate.cfg.xml) ///
This is Project descriptor file which contains all the dependencies and additional two dependency are
- Hibernate core (version: 5.4.33 final)
- MySQL Connector/j (version: 8.3.0)

### com.jsp.hibernate.shopping_car.entity [link](src/main/java/com/jsp/hibernate/shopping_cart/entity)
In this java package, It contains the three JavaBean Class with setter and getter methods for the properties of Users class, Cart class and Product class. Additionally, it contains hibernate mapping details of all Java objects, which need to be mapped to database tables. It mainly has:
- @Entity — mapped to database table
- @Id — mapped to the primary key of the table
- @OneToOne — establish a relationship between two entities
- @ManyToMany — establish a relationship between two entities

### SoppingCartDao.java [link](src/main/java/com/jsp/hibernate/shopping_cart/dao/ShoppingCartDao.java)
It has all methods to access database table. After creating Configuration instance build a SessionFactory. From this SessionFactory, open a session and perform opertaions on database by beginning Transaction.

1. **addProduct :** This method adds new product record. save() of hibernate pushes addition of record in to queue and once transaction is committed it will be added to database.
2. **addUserWithCart :** This method adds new user along with cart id. save() of hibernate pushes addition of record in to queue and once transaction is committed it will be added to database.
3. **addProductToUserCrt :** This method add product record to ArrayList bases on product id. It uses update() of hibernate to update saved object.
4. **removeProductFromCart :** This method delete product record from ArrayList based on product id. It uses update() of hibernate to update saved object.
5. **findAllProductInCart :** This method retrieves all product records stored in ArrayList<Product> Cart class based on Id. It uses get() of hibernate is used by sending hibernate class and id.
