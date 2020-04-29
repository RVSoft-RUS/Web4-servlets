package DAO;

import model.Car;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public void deleteAll() {
        session.createQuery("DELETE From Car").executeUpdate();
        session.close();
    }

    public List<Car> getAllCars() {
        List<Car> cars = session.createQuery("FROM Car").list();
        session.close();
        return cars;
    }

    public Car isThisCarPresent(String brand, String model, String licensePlate) {
        Query query = session.createQuery(
                "from Car where brand = :br and model = :md and licensePlate = :lp");
        query.setParameter("br", brand);
        query.setParameter("md", model);
        query.setParameter("lp", licensePlate);
        Car car = (Car) query.uniqueResult();

//        Criteria criteria = session.createCriteria(Car.class);
//        Car aCar = (Car) criteria
//                .add(Restrictions.eq("brand", brand))
//                .add(Restrictions.eq("model", model))
//                .add(Restrictions.eq("licensePlate", licensePlate))
//                .uniqueResult();
        session.close();
        if (car == null) {
            return null;
        }
        return car;
    }

    public void addCar(Car car) {
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }

    public long numCarsByBrand(String brand) {
//        Criteria criteria = session.createCriteria(Car.class);
//        return criteria
//                .add(Restrictions.eq("brand", brand))
//                .list()
//                .size();

        Query query = session.createQuery(
                "select count(*) from Car where brand = :brand");
        query.setParameter("brand", brand);
        Long count = (Long)query.uniqueResult();
        session.close();
        return count;
    }

    public void deleteCar(long id) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE Car WHERE id= :id")
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
        session.close();
    }
}
