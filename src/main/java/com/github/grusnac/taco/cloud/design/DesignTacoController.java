package com.github.grusnac.taco.cloud.design;

import com.github.grusnac.taco.cloud.order.OrderView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
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

    @GetMapping
    public String showDesignForm(Model model) {
        LOGGER.debug("Getting the design page");
        Collection<IngredientEntity> ingredientEntities = ingredientRepository.findAll();
        final Map<IngredientEntity.Type, List<IngredientEntity>> ingredientsByType = ingredientEntities.stream()
                .collect(groupingBy(IngredientEntity::getType));
        for (IngredientEntity.Type type : IngredientEntity.Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), ingredientsByType.get(type));
        }
        model.addAttribute("design", new TacoView());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") TacoView design, Errors errors, @ModelAttribute OrderView order) {
        if (errors.hasErrors()) {
            return "redirect:/design";
        }
        LOGGER.info("Processing design: " + design);
        TacoEntity tacoEntity = conversionService.convert(design, TacoEntity.class);
        tacoEntity = tacoRepository.save(tacoEntity);
        order.addDesign(design);
        return "redirect:/orders/current";
    }

    @ModelAttribute(name = "order")
    public OrderView order() {
        return new OrderView();
    }

    @ModelAttribute(name = "taco")
    public TacoView taco() {
        return new TacoView();
    }
}
