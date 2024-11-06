package com.andreiev.clientservice;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ClientServiceReactive {

    private final WebClient.Builder wvBuilder;

    public ClientServiceReactive(@LoadBalanced WebClient.Builder wvBuilder) {
        this.wvBuilder = wvBuilder;
        var executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(
            () -> getIngredientById("someId")
                .subscribe(i -> System.out.println("Reactive: " + i)),
            5, 10, TimeUnit.SECONDS );
    }

    public Mono<Ingredient> getIngredientById(String ingredientId) {
        return wvBuilder.build()
            .get()
            .uri("http://ingredient-service/ingredients/{id}", ingredientId)
            .retrieve()
            .bodyToMono(Ingredient.class);

    }

}
