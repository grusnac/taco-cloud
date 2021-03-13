package com.github.grusnac.taco.cloud.design;

import com.github.grusnac.taco.cloud.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/design")
@SessionAttributes(value = "order")
public class DesignTacoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DesignTacoController.class);

    private final TacoRepository tacoRepository;
    private final IngredientRepository ingredientRepository;
    private final ConversionService conversionService;

    public DesignTacoController(TacoRepository tacoRepository, IngredientRepository ingredientRepository,
                                ConversionService conversionService) {
        this.tacoRepository = tacoRepository;
        this.ingredientRepository = ingredientRepository;
        this.conversionService = conversionService;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Taco design() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        LOGGER.debug("Getting the design page");
        final Iterable<IngredientEntity> ingredientEntities = ingredientRepository.findAll();
        final Map<IngredientEntity.Type, List<IngredientEntity>> ingredientsByType = new HashMap<>();
        for (IngredientEntity ingredientEntity : ingredientEntities) {
            ingredientsByType.compute(ingredientEntity.getType(), (type, ingredients) -> {
                if (ingredients == null) {
                    ingredients = new LinkedList<>();
                }
                ingredients.add(ingredientEntity);
                return ingredients;
            });
        }
        for (IngredientEntity.Type type : IngredientEntity.Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), ingredientsByType.get(type));
        }
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        LOGGER.info("Processing design: " + design);
        TacoEntity tacoEntity = conversionService.convert(design, TacoEntity.class);
        tacoRepository.save(tacoEntity);
        order.addDesign(design);
        return "redirect:/orders/current";
    }
}
