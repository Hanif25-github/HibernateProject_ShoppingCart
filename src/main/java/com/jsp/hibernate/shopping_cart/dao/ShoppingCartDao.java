package com.jsp.hibernate.shopping_cart.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jsp.hibernate.shopping_cart.entity.Cart;
import com.jsp.hibernate.shopping_cart.entity.Product;
import com.jsp.hibernate.shopping_cart.entity.Users;

public class ShoppingCartDao {
	Scanner sc = new Scanner(System.in);

	Configuration cfg = new Configuration().configure().addAnnotatedClass(Users.class).addAnnotatedClass(Cart.class)
			.addAnnotatedClass(Product.class);
	SessionFactory sf = cfg.buildSessionFactory();

	public void addProduct() {

		// Product Object creation
		Product p = new Product();

		System.out.println("Enter the product ID:");
		p.setProductId(sc.nextInt());

		System.out.println("Enter the Product Name");
		p.setProductName(sc.next());

		System.out.println("Enter the Brand");
		p.setBrand(sc.next());

		System.out.println("enter the Price");
		p.setPrice(sc.nextInt());

		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		session.save(p);
		System.out.println("Product is Added");

		tran.commit();
		session.close();

	}

	public void addUserWithCart() {

		// User Object Creation
		Users u = new Users();

		System.out.println("Enter the User Id");
		u.setUserID(sc.nextInt());

		System.out.println("Enter the User Name");
		u.setUserName(sc.next());

		System.out.println("Enter the email");
		u.setEmail(sc.next());

		System.out.println("Enter the Mobile No");
		u.setMobile(sc.nextLong());

		System.out.println("Enter the Location");
		u.setLocation(sc.next());

		// Cart Object Creation
		Cart c = new Cart();

		System.out.println("Enter the Cart Id");
		c.setCartId(sc.nextInt());

		// adding the Cart into User
		u.setCart(c);

		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		session.save(u);
		session.save(c);

		System.out.println("User Added with cart");

		tran.commit();
		session.close();

	}

	public void addProductToUserCart() {

		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		// finding the Product id
		System.out.println("enter the Product ID");
		int productId = sc.nextInt();
		Product product = session.get(Product.class, productId);
		if (product != null) {

			// Finding the User Id

			System.out.println("enter the User Id");
			int userId = sc.nextInt();
			Users user = session.get(Users.class, userId);
			if (user != null) {

				Cart usercart = user.getCart();
				List<Product> plst = usercart.getProduct();

				plst.add(product);

//				usercart.setProduct(plst);  not required

				session.update(usercart);

				System.out.println("Product is Added");

			} else {
				System.out.println("User not find");
			}

		} else {
			System.out.println("Product Not found");
		}

		tran.commit();
		session.close();
	}

	public void removeProductFromCart() {

		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		// Remove the Product id
		System.out.println("enter the User Id");
		int userId = sc.nextInt();
		Users user = session.get(Users.class, userId);
		if (user != null) {

			Cart usercart = user.getCart();
			List<Product> plst = usercart.getProduct();
			
			for (Product product : plst) {
				System.out.println(product);
			}
			
			System.out.println("enter the Product Id that you want to remove");
			int productId = sc.nextInt();
			
			Product product = session.get(Product.class, productId);
			
			plst.remove(product);
			
			
			session.update(usercart);
			
			
			tran.commit();
			session.close();

			System.out.println("Product is Removed");

		} else {
			System.out.println("User not find");
		}

		
	}

	public void findAllProductInCart() {

		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();

		System.out.println("Enter the User ID");
		int userId = sc.nextInt();
		Users user = session.get(Users.class, userId);

		if (user != null) {

			Cart usercart = user.getCart();

			List<Product> plst = usercart.getProduct();

			for (Product product : plst) {

				System.out.println(product);

			}
		} else {
			System.out.println("User not found");
		}

		tran.commit();
		session.clear();

	}
}
