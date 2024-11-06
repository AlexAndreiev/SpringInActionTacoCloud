package com.andreiev.clientservice;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ClientService {

    private final RestTemplate restTemplate;

    public ClientService(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        var executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(
            () -> System.out.println(getIngredientById("someId")),
            5, 10, TimeUnit.SECONDS );
    }

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate
            .getForObject("http://ingredient-service/ingredients/{id}", Ingredient.class, ingredientId);

    }

}
