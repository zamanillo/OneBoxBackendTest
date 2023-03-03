package com.onebox.zamanillo.backendTest.helper;

import java.util.TimerTask;

import com.onebox.zamanillo.backendTest.domain.Cart;
import com.onebox.zamanillo.backendTest.service.CartService;

public class CartExpirationTimerTask extends TimerTask {
	
	private Cart cart;

	@Override
	public void run() {

			deleteCart(cart.getId());
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart carts) {
		cart = carts;
	}

	private void deleteCart(int cartId) {
		// Elimina el carrito de la lista
		CartService.cartList.removeIf(cart -> cart.getId() == (cartId));
	}
}
