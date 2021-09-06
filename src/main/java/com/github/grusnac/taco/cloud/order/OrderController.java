package com.github.grusnac.taco.cloud.order;

import com.github.grusnac.taco.cloud.user.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final OrderRepository orderRepository;
    private final ConversionService conversionService;

    public OrderController(OrderRepository orderRepository, ConversionService conversionService) {
        this.orderRepository = orderRepository;
        this.conversionService = conversionService;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "order-form";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order") OrderView orderView, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal UserEntity user) {
        if (errors.hasErrors()) {
            return "order-form";
        }
        LOGGER.info("Processing order: {}", orderView);
        final OrderEntity orderEntity = conversionService.convert(orderView, OrderEntity.class);
        assert orderEntity != null;
        orderEntity.setUser(user);
        orderRepository.save(orderEntity);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}