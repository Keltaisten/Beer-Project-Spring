//package beerprojectspring.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "ingredients_one_line")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class IngredientOneLineInDb {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private double salt;
//    private double sugar;
//    private double barley;
//    private double wheat;
//    private double corn;
//
//    @OneToOne
//    @JoinColumn(name = "beer_object_id")
//    private Beer beer;
//
//
//}
