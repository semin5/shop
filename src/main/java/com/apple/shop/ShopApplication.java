package com.apple.shop;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {

		SpringApplication.run(ShopApplication.class, args);

		Friend friend = new Friend("lee");
		System.out.println(friend.name);
	}

}

class Friend{
	String name;
	int age = 20;
	Friend(String a){
		this.name = a;
	}
}
