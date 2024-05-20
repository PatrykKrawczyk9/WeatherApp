package pl.kurs.weatherapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kurs.weatherapp.models.ForecastWeatherCondition;

@Repository
public class JpaForecastWeatherConditionDao implements IForecastWeatherConditionDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(ForecastWeatherCondition weatherCondition) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(weatherCondition);
        tx.commit();
    }
}
