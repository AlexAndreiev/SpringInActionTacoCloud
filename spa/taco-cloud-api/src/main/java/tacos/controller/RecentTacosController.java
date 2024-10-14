package tacos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import tacos.TacoRepository;
import tacos.hateos.TacoModel;
import tacos.hateos.TacoModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
@RequiredArgsConstructor
public class RecentTacosController {
    private final TacoRepository tacoRepository;

    @GetMapping(path = "/tacos/recent")
    public ResponseEntity<CollectionModel<TacoModel>> recentTacos() {
        var page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        var tacos = tacoRepository.findAll(page).getContent();
        var collectionModel = new TacoModelAssembler().toCollectionModel(tacos);
        collectionModel.add(linkTo(methodOn(DesignTacoController.class).recentTacos())
            .withRel("recent")
        );
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }
}
