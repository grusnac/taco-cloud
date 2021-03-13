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
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.groupingBy;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class TacoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TacoController.class);

    private final IngredientRepository ingredientRepository;
    private final ConversionService conversionService;

    public TacoController(TacoRepository tacoRepository, IngredientRepository ingredientRepository,
                          ConversionService conversionService) {
        this.ingredientRepository = ingredientRepository;
        this.conversionService = conversionService;
    }

    @ModelAttribute(name = "order")
    public OrderView order() {
        return new OrderView();
    }

    @ModelAttribute(name = "design")
    public TacoView design() {
        return new TacoView();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        LOGGER.debug("Getting the design page");
        model.addAllAttributes(fetchIngredients());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") TacoView design, Errors errors,
                                @ModelAttribute("order") OrderView orderView, Model model) {
        if (errors.hasErrors()) {
            model.addAllAttributes(fetchIngredients());
            return "design";
        }
        LOGGER.info("Processing design: " + design);
//        TacoEntity tacoEntity = conversionService.convert(design, TacoEntity.class);
//        assert tacoEntity != null;
//        tacoRepository.save(tacoEntity);
        orderView.addDesign(design);
        return "redirect:/orders/current";
    }

    private Map<String, List<IngredientView>> fetchIngredients() {
        return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
                .map(ingredientEntity -> conversionService.convert(ingredientEntity, IngredientView.class))
                .collect(groupingBy(ingredientView -> ingredientView.getType().toLowerCase()));
    }
}
