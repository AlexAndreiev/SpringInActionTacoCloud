package tacos;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@RestResource(rel = "tacos", path = "tacos")
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull(message = "You must choose at least 1 ingredient")
    @Size(min=1, message = "You must choose at least 1 ingredient")
    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(name = "taco_ingredients",
        joinColumns = @JoinColumn(name = "taco_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    @Column(name = "created_at")
    private Date createdAt;
    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
