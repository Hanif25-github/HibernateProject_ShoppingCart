package com.jsp.hibernate.shopping_cart;

import java.util.Scanner;

import com.jsp.hibernate.shopping_cart.dao.ShoppingCartDao;

public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        
        ShoppingCartDao shop = new ShoppingCartDao();
        
        int choice;
    	boolean exit = true;
    	
    	while(exit) {
    		System.out.println("1) Add Product   2) Add user with cart");
    		System.out.println("3) Add product to user cart");
			System.out.println("4) Remove Product from users cart");
			System.out.println("5) Find  all Product By Id");
			System.out.println("6) Exit");
			
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				shop.addProduct();
				break;
				
			case 2:
				shop.addUserWithCart();
				break;
				
			case 3:
				shop.addProductToUserCart();
				break;
				
			case 4:
				shop.removeProductFromCart();
				break;
				
			case 5:
				shop.findAllProductInCart();
				break;
				
			case 6:
				System.out.println("Exited Thank you");
				exit=false;
				break;

			default:
				System.out.println("invalid Option");
			}
				
    	
    }
    	sc.close();
}
}
