package tacos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "taco_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name = "delivery_name")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    @Column(name = "delivery_street")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    @Column(name = "delivery_city")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    @Column(name = "delivery_state")
    private String deliveryState;

    @NotBlank(message = "Zip is required")
    @Column(name = "delivery_zip")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    @Column(name = "cc_cvv")
    private String ccCVV;

    @Column(name = "placed_at")
    private Date placedAt;
    @PrePersist
    void placeAt(){
        this.placedAt = new Date();
    }

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void addDesign(Taco taco) {
        tacos.add(taco);
    }
}
