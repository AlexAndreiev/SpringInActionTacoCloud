package tacos.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.server.EntityLinks;
import tacos.Taco;
import tacos.TacoRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/design", consumes = APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DesignTacoController {

//    private final tacos.IngredientJPARepository ingredientRepo;
//    private final tacos.TacoJPARepository designRepo;
//    private final UserRepository userRepo;
    private TacoRepository tacoRepository;
    private EntityLinks entityLinks;

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        var page = PageRequest.of(0, 12, Sort.by("createAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        var optTaco = tacoRepository.findById(id);
        return optTaco.map(t -> new ResponseEntity<>(t, HttpStatus.OK))
            .orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }


//    @GetMapping
//    public String showDesignForm(Model model, Principal principal){
//        populateIngredients(model);
//        populateUser(principal, model);
//        model.addAttribute("design", new tacos.Taco());
//        return "design";
//    }
//
//    @ModelAttribute(name = "order")
//    public tacos.Order order() {
//        return new tacos.Order();
//    }
//
//    @PostMapping
//    public String processDesign(@Valid @ModelAttribute(name = "design") tacos.Taco design, BindingResult bindingResult, @ModelAttribute tacos.Order order, Model model, Principal principal) {
//        if (bindingResult.hasErrors()) {
//            populateIngredients(model);
//            populateUser(principal, model);
//            return "design";
//        }
//
//        tacos.Taco saved = designRepo.save(design);
//        order.addDesign(saved);
//        log.info("Processing design: " + design);
//        return "redirect:/orders/current";
//    }
//
//    private List<tacos.Ingredient> filterByType(List<tacos.Ingredient> ingredients, Type type)
//    {
//        return ingredients.stream()
//                .filter(x -> x.getType().equals(type))
//                .collect(Collectors.toList());
//    }
//
//    private void populateIngredients(Model model) {
//        var ingredients = new ArrayList<tacos.Ingredient>();
//        ingredientRepo.findAll().forEach(ingredients::add);
//        var types = tacos.Ingredient.Type.values();
//        for (var type: types) {
//            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
//        }
//    }
//
//    private void populateUser(Principal principal, Model model) {
//        var user = userRepo.findByUsername(principal.getName()).get();
//        model.addAttribute("user", user);
//    }
}
