package com.github.grusnac.taco.cloud.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final OrderRepository orderRepository;
    private final ConversionService conversionService;

    public OrderController(OrderRepository orderRepository, ConversionService conversionService) {
        this.orderRepository = orderRepository;
        this.conversionService = conversionService;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new OrderView());
        return "order";
    }

    @PostMapping
    public String processOrder(@Valid OrderView order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "order";
        }
        LOGGER.info("Processing order: {}", order);
        orderRepository.save(conversionService.convert(order, OrderEntity.class));
        sessionStatus.setComplete();
        return "redirect:/";
    }
}