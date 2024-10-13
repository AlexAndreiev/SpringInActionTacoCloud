package tacos.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import tacos.Taco;
import tacos.hateos.TacoModel;

@Configuration
public class HateosConfiguration {
    @Bean
    public RepresentationModelProcessor<CollectionModel<TacoModel>> tacoProcessor(EntityLinks entityLinks) {
        return new RepresentationModelProcessor<>() {
            @Override
            public CollectionModel<TacoModel> process(CollectionModel<TacoModel> model) {
                model.add(entityLinks.linkFor(Taco.class)
                    .slash("recent")
                    .withRel("recents")
                );
                return model;
            }
        };
    }
}
