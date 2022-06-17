package beerprojectspring.Webshop.model;

import beerprojectspring.Beer.model.Beer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "webshops")
public class Webshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "email_address")
    private String emailAddress;
    @ManyToMany(mappedBy = "webshops")/*(*//*mappedBy = "webshops", *//*fetch = FetchType.EAGER)*//*(fetch = FetchType.EAGER)*/
//    @JoinTable(name = "webshop_beers")
    private List<Beer> beers = new ArrayList<>();

    public Webshop(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }
}
