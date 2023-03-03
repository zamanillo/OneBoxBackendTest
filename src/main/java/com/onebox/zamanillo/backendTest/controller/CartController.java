package com.onebox.zamanillo.backendTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.zamanillo.backendTest.domain.Cart;
import com.onebox.zamanillo.backendTest.domain.Product;
import com.onebox.zamanillo.backendTest.service.CartService;

@RestController
@RequestMapping("/backendTest/api/v1")
public class CartController {

	@Autowired
	CartService cartService;

	@GetMapping("/test")
	public String test() {

		return "Hello from Controller!!!";
	}

	@GetMapping("/cart/{cart_id}")
	public ResponseEntity<Cart> getCart(@PathVariable("cart_id") int cartId) {

		ResponseEntity<Cart> responseEntity;

		try {
			Cart cart = cartService.getCart(cartId);

			if (cart == null)
				responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				responseEntity = new ResponseEntity<>(cart, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
	
	@PostMapping("/cart")
	public ResponseEntity<Cart> createCart(@RequestBody (required=false) Product product  ) {

		ResponseEntity<Cart> responseEntity;

		try {

			Cart cart = cartService.createCart(product);
			responseEntity = new ResponseEntity<>(cart, HttpStatus.CREATED);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
	
	@PutMapping("/cart/{cart_id}")
	public ResponseEntity<Cart> updateCart(@PathVariable("cart_id") int cartId, @RequestBody Product product){

		ResponseEntity<Cart> responseEntity;

		if (cartService.getCart(cartId) == null)
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			try {
				Cart cart = cartService.updateCart(cartId, product);
				responseEntity = new ResponseEntity<>(cart, HttpStatus.OK);
			} catch (Exception e) {
				responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return responseEntity;
	}

	@DeleteMapping("/cart/{cart_id}")
	public ResponseEntity<HttpStatus> deleteCart(@PathVariable("cart_id") int cartId) {

		ResponseEntity<HttpStatus> responseEntity;

		try {
			cartService.deleteCart(cartId);
			responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
}
