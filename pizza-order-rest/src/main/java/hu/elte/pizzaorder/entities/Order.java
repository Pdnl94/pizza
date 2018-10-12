package hu.elte.pizzaorder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    @NotNull
    private boolean delivered;     
    
    //a pizza áttervezését így a legegyszerűbb megoldani,
    //de ha valami fancybb megoldást szeretnél, akkor feel free
    @Column
    private String comment;
    
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;
    
    @ManyToMany
    @JoinTable(name="ordered_pizza")
    private List<Pizza> pizzas;
}
