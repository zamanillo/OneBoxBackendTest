package com.onebox.zamanillo.backendTest.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {

    private static int idcount;//para poder autoincrementar sin necesidad de @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int id;
    private List<Product> productList;
    
    public Cart () {
    	
    	id=idcount++;
    	productList=new ArrayList<>();
    }
}
