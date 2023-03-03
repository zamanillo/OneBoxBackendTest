package com.onebox.zamanillo.backendTest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.springframework.stereotype.Service;

import com.onebox.zamanillo.backendTest.domain.Cart;
import com.onebox.zamanillo.backendTest.domain.Product;
import com.onebox.zamanillo.backendTest.helper.CartExpirationTimerTask;

@Service
public class CartService {

	public static List<Cart> cartList = new ArrayList<>();//publico para poder acceder desde otras clases
	private static final int EXPIRATION_TIME =10 * 60 * 1000; //tiempo de 10 minutos

	public Cart getCart(int cartId) {

		return cartList.stream().filter(c -> c.getId() == cartId).findFirst().orElse(null);
	}

	//Crear carrito de la compra
	public Cart createCart(Product product) {

		Cart cart = new Cart();
		List<Product> products = cart.getProductList();
		if (product.getId() != 0) { //Si no t
			products.add(product);
			cart.setProductList(products);
		}
		cartList.add(cart);
		
		Timer timer = new Timer();
		CartExpirationTimerTask cartExpired=new CartExpirationTimerTask();
		cartExpired.setCart(cart);
        timer.scheduleAtFixedRate(cartExpired, EXPIRATION_TIME, EXPIRATION_TIME);

		return cart;
	}

	public Cart updateCart(int cartId, Product product) {

		Cart cart = cartList.stream().filter(c -> c.getId() == cartId).findFirst().orElse(null);

		if (cart != null) {

			Product existingProduct = cart.getProductList().stream().filter(p -> p.getId() == product.getId())
					.findFirst().orElse(null);

			if (existingProduct != null) {

				existingProduct.setAmount(existingProduct.getAmount() + product.getAmount());

			} else {

				List<Product> products = cart.getProductList();
				
				if (product.getId() != 0) {
					products.add(product);
					cart.setProductList(products);
				}
			}
		}

		return cart;
	}

	public void deleteCart(int cartId) {

		cartList.removeIf(cart -> cart.getId() == (cartId));
	}
}
