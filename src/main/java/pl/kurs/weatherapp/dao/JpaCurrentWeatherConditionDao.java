package pl.kurs.weatherapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kurs.weatherapp.models.CurrentWeatherCondition;

@Repository
public class JpaCurrentWeatherConditionDao implements ICurrentWeatherConditionDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void save(CurrentWeatherCondition weatherCondition) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(weatherCondition);
        tx.commit();
    }
}
