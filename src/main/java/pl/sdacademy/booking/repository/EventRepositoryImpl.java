package pl.sdacademy.booking.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import pl.sdacademy.booking.data.EventEntity;
import pl.sdacademy.booking.util.DatabaseUtil;

import java.util.List;

public class EventRepositoryImpl implements EventRepository{
    private EntityManager entityManager;

    public EventRepositoryImpl() {
        entityManager = DatabaseUtil.getEntityManager();
    }

    @Override
    public List<EventEntity> findAll() {
        TypedQuery<EventEntity> events = entityManager
                .createQuery("select * FROM EventEntity"  //równoznaczne zapytanie z select event FROM EventEntity event, gdzie event jest aliasem
                        , EventEntity.class);
        return events.getResultList();
    }

    @Override
    public void addEvent(EventEntity event) {
        entityManager.getTransaction().begin();
        entityManager.persist(event);
        entityManager.getTransaction().commit();
    }
}
