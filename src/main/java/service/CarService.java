package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllCars();
    }

    public void deleteAll() {
        new CarDao(sessionFactory.openSession()).deleteAll();
    }

    public boolean addCar(String brand, String model, String licensePlate, long price) {
        long numOfThisBrand = new CarDao(sessionFactory.openSession()).numCarsByBrand(brand);
//        List<Car> allCars = getAllCars();
//        long count = allCars.stream()
//                .filter(c -> c.getBrand().equals(brand))
//                .count();
        if (numOfThisBrand >= 10) {
            return false;
        }
        Car car = new Car(brand, model, licensePlate, price);
        new CarDao(sessionFactory.openSession()).addCar(car);
        return true;
    }

    public Car buyCar(String brand, String model, String licensePlate) {
//        List<Car> allCars = getAllCars();
//        Optional<Car> buy = allCars.stream()
//                .filter(c -> c.getBrand().equals(brand) &&
//                        c.getModel().equals(model) &&
//                        c.getLicensePlate().equals(licensePlate))
//                .findFirst();
        Car car = new CarDao(sessionFactory.openSession()).isThisCarPresent(brand, model, licensePlate);
        if (car != null) {
            new CarDao(sessionFactory.openSession()).deleteCar(car.getId());
            return car;
        }
        return null;
    }
}
