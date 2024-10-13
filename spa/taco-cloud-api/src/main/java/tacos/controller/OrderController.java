package tacos.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.Order;
import tacos.OrderRepository;
import tacos.Taco;
import tacos.configuration.OrderProps;
import tacos.UserRepository;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/orders")
@SessionAttributes("order")
@RequiredArgsConstructor
public class OrderController {

//    private tacos.OrderRepository orderRepo;
    private OrderRepository orderRepo;
    private UserRepository userRepository;
    private OrderProps orderProps;

    @PutMapping("/{orderId}")
    public Order putOrder(@RequestBody Order order) {
        return orderRepo.save(order);
    }

    @PatchMapping(value = "/{orderId}", consumes = APPLICATION_JSON_VALUE)
    public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
        var order = orderRepo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }

        return orderRepo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepo.deleteById(orderId);
        } catch (EmptyResultDataAccessException ignore) {}
    }

//    @GetMapping
//    public String ordersForUser( @AuthenticationPrincipal User user, Model model) {
//        var pageable = PageRequest.of(0, orderProps.getPageSize());
//        model.addAttribute("orders",
//                orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
//        return "orderList";
//    }
//
//    @GetMapping("/current")
//    public String orderForm(Model model){
//        model.addAttribute("order", new Order());
//        return "orderForm";
//    }
//
//    @PostMapping
//    public String processOrder(@Valid Order order, Errors errors,
//                               SessionStatus sessionStatus,
////                               Principal principal,
////                               Authentication authentication,
//                               @AuthenticationPrincipal User user) {
//        if (errors.hasErrors())
//            return "orderForm";
//
////        var user = userRepository.findByUsername(principal.getName());
////        user = (tacos.User) authentication.getPrincipal();
////        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
////        user = (tacos.User) authentication1.getPrincipal();
//        order.setUser(user);
//
//        orderRepo.save(order);
//        log.info("tacos.Order submitted: {}", order);
//        sessionStatus.setComplete();
//        return "redirect:/";
//    }
}
