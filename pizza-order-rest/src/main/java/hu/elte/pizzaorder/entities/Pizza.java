package hu.elte.pizzaorder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    @NotNull
    private String name;
    
    @Column
    @NotNull
    private int price;
    
    @ManyToMany(mappedBy = "pizzas")
    @JsonIgnore
    private List<Order> orders;
    
    @ManyToMany
    @JoinTable(name="pizza_topping")
    private List<Topping> toppings;
    
    @ManyToMany(mappedBy = "pizzas")
    @JsonIgnore
    private List<User> users;
}
