package pl.kurs.weatherapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import pl.kurs.weatherapp.models.CurrentWeatherCondition;

public class JpaCurrentWeatherConditionDao implements ICurrentWeatherConditionDao {

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public JpaCurrentWeatherConditionDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void save(CurrentWeatherCondition weatherCondition) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(weatherCondition);
        tx.commit();
    }
}
