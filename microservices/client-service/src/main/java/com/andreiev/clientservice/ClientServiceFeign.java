package com.andreiev.clientservice;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ClientServiceFeign {

    private final FeignIngredientClient feignIngredientClient;

    public ClientServiceFeign(FeignIngredientClient feignIngredientClient) {
        this.feignIngredientClient = feignIngredientClient;
        var executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(
            () -> System.out.println("Feign: " + getIngredientById("someId")),
            5, 10, TimeUnit.SECONDS );
    }

    public Ingredient getIngredientById(String ingredientId) {
        return feignIngredientClient.getIngredient(ingredientId);
    }

}
