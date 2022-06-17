//package beerprojectspring.repository;
//
//import beerprojectspring.model.Webshop;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import java.util.List;
//
//public class WebshopBeerRepository {
//
//    private EntityManagerFactory factory;
//
//    public WebshopBeerRepository(EntityManagerFactory factory) {
//        this.factory = factory;
//    }
//
//    public List<Webshop> findWebshopsWithBeers() {
//        EntityManager entityManager = factory.createEntityManager();
//        try {
//            List<Webshop> webshops = entityManager.createQuery(
////                    "select s from Webshop s join  on Webshop.id = webshop_beers.webshops_id join Beer on Beer.id = webshop_beers.beer_id",Webshop.class)
//                            "select s from Webshop s join s.beers", Webshop.class)
//                    .getResultList();
//            return webshops;
//        } finally {
//            entityManager.close();
//        }
//    }
//}
