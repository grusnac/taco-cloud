package com.github.grusnac.taco.cloud.design;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.github.grusnac.taco.cloud.design.IngredientEntity.Type;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(TacoController.class)
public class TacoControllerTest {

    public static final String FLTO = "FLTO";
    public static final String GRBF = "GRBF";
    public static final String CARN = "CARN";
    public static final String TMTO = "TMTO";
    public static final String CHED = "CHED";
    public static final String SRCR = "SRCR";
    public static final String COTO = "COTO";
    public static final String LETC = "LETC";
    public static final String JACK = "JACK";
    public static final String SLSA = "SLSA";

    final List<IngredientEntity> ingredientEntities;
    final List<IngredientView> ingredientViews;
    final TacoView design;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private TacoRepository tacoRepository;

    public TacoControllerTest() {
        this.ingredientEntities = Arrays.asList(
                new IngredientEntity(FLTO, "Flour Tortilla", Type.WRAP),
                new IngredientEntity(COTO, "Corn Tortilla", Type.WRAP),
                new IngredientEntity(GRBF, "Ground Beef", Type.PROTEIN),
                new IngredientEntity(CARN, "Carnitas", Type.PROTEIN),
                new IngredientEntity(TMTO, "Diced Tomatoes", Type.VEGGIES),
                new IngredientEntity(LETC, "Lettuce", Type.VEGGIES),
                new IngredientEntity(CHED, "Cheddar", Type.CHEESE),
                new IngredientEntity(JACK, "Monterrey Jack", Type.CHEESE),
                new IngredientEntity(SLSA, "Salsa", Type.SAUCE),
                new IngredientEntity(SRCR, "Sour Cream", Type.SAUCE)
        );

        this.ingredientViews = Arrays.asList(
                new IngredientView(FLTO, "Flour Tortilla", Type.WRAP.name()),
                new IngredientView(COTO, "Corn Tortilla", Type.WRAP.name()),
                new IngredientView(GRBF, "Ground Beef", Type.PROTEIN.name()),
                new IngredientView(CARN, "Carnitas", Type.PROTEIN.name()),
                new IngredientView(TMTO, "Diced Tomatoes", Type.VEGGIES.name()),
                new IngredientView(LETC, "Lettuce", Type.VEGGIES.name()),
                new IngredientView(CHED, "Cheddar", Type.CHEESE.name()),
                new IngredientView(JACK, "Monterrey Jack", Type.CHEESE.name()),
                new IngredientView(SLSA, "Salsa", Type.SAUCE.name()),
                new IngredientView(SRCR, "Sour Cream", Type.SAUCE.name())
        );

        this.design = new TacoView();
        this.design.name = "Meat Explosion";
        this.design.ingredients = List.of(FLTO, GRBF, CARN, TMTO, CHED, SRCR);
    }

    @BeforeEach
    void setUp() {
        when(ingredientRepository.findAll()).thenReturn(ingredientEntities);
        when(ingredientRepository.findById(FLTO))
                .thenReturn(Optional.of(new IngredientEntity(FLTO, "Flour Tortilla", Type.WRAP)));
        when(ingredientRepository.findById(GRBF))
                .thenReturn(Optional.of(new IngredientEntity(GRBF, "Ground Beef", Type.PROTEIN)));
        when(ingredientRepository.findById(CARN))
                .thenReturn(Optional.of(new IngredientEntity(CARN, "Carnitas", Type.PROTEIN)));
        when(ingredientRepository.findById(TMTO))
                .thenReturn(Optional.of(new IngredientEntity(TMTO, "Diced Tomatoes", Type.VEGGIES)));
        when(ingredientRepository.findById(CHED))
                .thenReturn(Optional.of(new IngredientEntity(CHED, "Cheddar", Type.CHEESE)));
        when(ingredientRepository.findById(SRCR))
                .thenReturn(Optional.of(new IngredientEntity(SRCR, "Sour Cream", Type.SAUCE)));
    }

    @Test
    void shouldDesignForm() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(model().attribute("wrap", ingredientViews.subList(0, 2)))
                .andExpect(model().attribute("protein", ingredientViews.subList(2, 4)))
                .andExpect(model().attribute("veggies", ingredientViews.subList(4, 6)))
                .andExpect(model().attribute("cheese", ingredientViews.subList(6, 8)))
                .andExpect(model().attribute("sauce", ingredientViews.subList(8, 10)));
    }

    @Test
    void shouldProcessDesign() throws Exception {
        when(tacoRepository.save(any(TacoEntity.class))).then(invocationOnMock -> invocationOnMock.getArgument(0));
        mockMvc.perform(post("/design")
                .content("name="
                        .concat(design.name.replace(' ', '+'))
                        .concat("&ingredients=")
                        .concat(String.join(",", design.ingredients)))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location", "/orders/current"));
    }
}
