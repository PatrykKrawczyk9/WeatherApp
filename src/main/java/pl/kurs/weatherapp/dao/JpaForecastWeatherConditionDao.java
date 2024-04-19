package pl.kurs.weatherapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import pl.kurs.weatherapp.models.ForecastWeatherCondition;

public class JpaForecastWeatherConditionDao implements IForecastWeatherConditionDao{

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public JpaForecastWeatherConditionDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }


    @Override
    public void save(ForecastWeatherCondition weatherCondition) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(weatherCondition);
        tx.commit();
    }
}
