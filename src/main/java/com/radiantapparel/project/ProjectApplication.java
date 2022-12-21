package com.radiantapparel.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import java.nio.file.Paths;

@SpringBootApplication
public class ProjectApplication {
    private static Gson gson = new Gson();

    static class CreatePayment {
        @SerializedName("items")
        Object[] items;

        public Object[] getItems() {
        return items;
        }
    }

    static class CreatePaymentResponse {
        private String clientSecret;
        public CreatePaymentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
        }
    }

    static int calculateOrderAmount(Object[] items) {
        // Replace this constant with a calculation of the order's amount
        // Calculate the order total on the server to prevent
        // people from directly manipulating the amount on the client
        return 1400;
    }


	public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
        // staticFiles.externalLocation(Paths.get("public").toAbsolutePath().toString());
        // John's key
        // Stripe.apiKey = "sk_test_51MFlFOA7FBolyCg75096WXsZfqQ3uAOZSoKUizxtzP2miYf9BYZH0EhtNd8gENoleymHrTmN8GHAYBkeBbq8peZi001eGcYboT";
        // Ciaran's key
        Stripe.apiKey = "sk_test_51MFpnALrPQlCjFmwmbp9TYyuUnyJl5KRKH9aY92qVUgEnioVB4zwrSP1yS6vSW2tZtWKYYRGCO4wGB55E9eK2NmR00ugMxu2aY";
        // Ben's key
        // Stripe.apiKey = "sk_test_51MG1wTL9pzCvrE7mkvdoxzPp8ZA7iUx8uKgSqIU8s7JD7Fwh27h0Ax4L7l8IIw12fneoWK5MHBMTLB9c4DpMsP9I00C4HX7tmk";
	}
}