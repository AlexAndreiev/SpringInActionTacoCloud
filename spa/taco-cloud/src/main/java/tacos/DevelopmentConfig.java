package tacos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.Ingredient.Type;

import java.util.Arrays;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {
    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo,
                                        UserRepository userRepo, PasswordEncoder encoder, TacoRepository tacoRepo) { // user repo for ease of testing with a built-in user
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                var flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
                var cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
                var groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
                var carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
                var tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
                var lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
                var cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
                var jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
                var salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
                var sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
                repo.save(flourTortilla);
                repo.save(cornTortilla);
                repo.save(groundBeef);
                repo.save(carnitas);
                repo.save(tomatoes);
                repo.save(lettuce);
                repo.save(cheddar);
                repo.save(jack);
                repo.save(salsa);
                repo.save(sourCream);


                userRepo.save(new User("habuma", encoder.encode("password"),
                    "Craig Walls", "123 North Street", "Cross Roads", "TX",
                    "76227", "123-123-1234"));

                Taco taco1 = new Taco();
                taco1.setName("Carnivore");
                taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
                tacoRepo.save(taco1);

                Taco taco2 = new Taco();
                taco2.setName("Bovine Bounty");
                taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
                tacoRepo.save(taco2);

                Taco taco3 = new Taco();
                taco3.setName("Veg-Out");
                taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
                tacoRepo.save(taco3);

            }
        };
    }
}
