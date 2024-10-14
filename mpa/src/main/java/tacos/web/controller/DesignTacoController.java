package tacos.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.data.IngredientJPARepository;
import tacos.data.TacoJPARepository;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.security.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@RequiredArgsConstructor
public class DesignTacoController {

    private final IngredientJPARepository ingredientRepo;
    private final TacoJPARepository designRepo;
    private final UserRepository userRepo;


    @GetMapping
    public String showDesignForm(Model model, Principal principal){
        populateIngredients(model);
        populateUser(principal, model);
        model.addAttribute("design", new Taco());
        return "design";
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute(name = "design") Taco design, BindingResult bindingResult, @ModelAttribute Order order, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            populateIngredients(model);
            populateUser(principal, model);
            return "design";
        }

        Taco saved = designRepo.save(design);
        order.addDesign(saved);
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type)
    {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    private void populateIngredients(Model model) {
        var ingredients = new ArrayList<Ingredient>();
        ingredientRepo.findAll().forEach(ingredients::add);
        var types = Ingredient.Type.values();
        for (var type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private void populateUser(Principal principal, Model model) {
        var user = userRepo.findByUsername(principal.getName()).get();
        model.addAttribute("user", user);
    }
}
