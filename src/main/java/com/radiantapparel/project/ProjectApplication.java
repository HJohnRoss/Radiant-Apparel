package com.radiantapparel.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stripe.Stripe;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
        // John's key
        Stripe.apiKey = "sk_test_51MFlFOA7FBolyCg75096WXsZfqQ3uAOZSoKUizxtzP2miYf9BYZH0EhtNd8gENoleymHrTmN8GHAYBkeBbq8peZi001eGcYboT";
        // Ciaran's key
        // Stripe.apiKey = "sk_test_51MFpnALrPQlCjFmwmbp9TYyuUnyJl5KRKH9aY92qVUgEnioVB4zwrSP1yS6vSW2tZtWKYYRGCO4wGB55E9eK2NmR00ugMxu2aY";
        // Ben's key
	}
}