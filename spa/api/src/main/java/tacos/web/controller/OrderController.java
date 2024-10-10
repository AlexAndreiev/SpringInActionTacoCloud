package tacos.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.model.Order;
import tacos.configuration.OrderProps;
import tacos.data.OrderJPARepository;
import tacos.security.User;
import tacos.security.UserRepository;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@RequiredArgsConstructor
public class OrderController {

//    private OrderRepository orderRepo;
    private OrderJPARepository orderRepo;
    private UserRepository userRepository;
    private OrderProps orderProps;


    @GetMapping
    public String ordersForUser( @AuthenticationPrincipal User user, Model model) {
        var pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders",
                orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus,
//                               Principal principal,
//                               Authentication authentication,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors())
            return "orderForm";

//        var user = userRepository.findByUsername(principal.getName());
//        user = (User) authentication.getPrincipal();
//        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//        user = (User) authentication1.getPrincipal();
        order.setUser(user);

        orderRepo.save(order);
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
