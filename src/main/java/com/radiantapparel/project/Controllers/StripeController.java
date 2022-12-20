package com.radiantapparel.project.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.radiantapparel.project.Models.ProductDatabase;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
public class StripeController {

    private static Gson gson = new Gson();


    static class CreatePaymentResponse {
        private String clientSecret;
        public CreatePaymentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
        }
    }
    
    @PostMapping("/create/paymentIntent")
    public static ResponseEntity createPaymentIntent(Model model, HttpSession session) throws StripeException{

        if(session.getAttribute("cart") == null){
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }
        ArrayList<Map<ProductDatabase, String>> cart = (ArrayList<Map<ProductDatabase, String>>) session.getAttribute("cart");

        Double total = 0.0;
        for(Map<ProductDatabase, String> oneProduct : cart){
            for(Entry<ProductDatabase, String> oneKey : oneProduct.entrySet()){
                total += (oneKey.getKey().getPrice().getUnitAmount() * Integer.parseInt(oneKey.getValue()));
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(total);
        for(int i = 0; i < stringBuilder.length(); i++){
            if(stringBuilder.charAt(i) == '.'){
                stringBuilder.deleteCharAt(i);
            }
        }

        List<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", Integer.parseInt(stringBuilder.toString()));
        params.put("currency", "usd");
        params.put(
        "payment_method_types",
        paymentMethodTypes
        );

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
        return  ResponseEntity.ok(gson.toJson(paymentResponse));
    }
}