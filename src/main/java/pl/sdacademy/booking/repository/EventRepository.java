package pl.sdacademy.booking.repository;

// todo zaimplementowac repository EntityRepository z metoda findAll - zwraca EventEntity

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import pl.sdacademy.booking.data.EventEntity;
import pl.sdacademy.booking.util.DatabaseUtil;

import java.util.List;

public class EventRepository {
    private EntityManager entityManager;
    public EventRepository() { //bezparametrowy konstruktor
        entityManager = DatabaseUtil.getEntityManager();
    }

//    @Override
//    public List<ItemEntity> findItems() {
//        TypedQuery<ItemEntity> items = entityManager
//                .createQuery("select item from ItemEntity item "
//                        , ItemEntity.class);
//        return items.getResultList();
//    }
    public List<EventEntity> findAll(){
        TypedQuery<EventEntity> events=entityManager
                .createQuery("select event from EventEntity event ",
        EventEntity.class);
        return events.getResultList();
    }
}
