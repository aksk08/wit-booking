package pl.sdacademy.booking.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.NonUniqueResultException;
import pl.sdacademy.booking.data.EventEntity;
import pl.sdacademy.booking.util.DatabaseUtil;

import java.time.LocalDateTime;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {
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

    @Override
    public Long findEventByDate(LocalDateTime date) {
        TypedQuery<Long> query = entityManager
                .createQuery("SELECT id FROM EventEntity event where time_from<=:dateParam and time_to>=:dateParam", Long.class);
        query.setParameter("dateParam", date);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return -1L; //bo nie będzie identyfikatorem, ale nie będzie też nullem
        }
    }
}
