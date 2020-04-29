package servlet;

import com.google.gson.Gson;
import model.Car;
import service.CarService;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    private CarService carService = CarService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(carService.getAllCars());
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");

        Car buyCar = carService.buyCar(brand, model, licensePlate);

        if (buyCar != null) {
            // TODO: 21.04.2020 add to report
            //  OK!
            DailyReportService.getInstance().addCarToReport(buyCar.getPrice());
        } else {
            System.out.println("Такой машины нет в наличии!!!");
        }
    }
}
