package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

//	@Bean
//	@Profile({"dev", "qa"})
//	public CommandLineRunner dataLoader(tacos.IngredientRepository repo, UserRepository userRepo,
//										PasswordEncoder encoder)
//	{
//
//	}
}
