package service;

import DAO.DailyReportDao;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class DailyReportService {
    private static DailyReportService dailyReportService;

    private SessionFactory sessionFactory;

    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    public List<DailyReport> getAllDailyReports() {
        return new DailyReportDao(sessionFactory.openSession()).getAllDailyReport();
    }

    public DailyReport getLastReport() {
        return new DailyReportDao(sessionFactory.openSession()).getLastReport();
    }

    public void deleteAll() {
        new DailyReportDao(sessionFactory.openSession()).deleteAll();
    }

    public void addCarToReport(long price) {
        DailyReport report = new DailyReportDao(sessionFactory.openSession()).getCurrentReport();
        if (report == null) {
            new DailyReportDao(sessionFactory.openSession()).newDay();
            report = new DailyReportDao(sessionFactory.openSession()).getCurrentReport();
        }
        long id = report.getId();
        long earlings = report.getEarnings() + price;
        long soldCars = report.getSoldCars() + 1;
        new DailyReportDao(sessionFactory.openSession()).updateReport(id, earlings, soldCars);
    }

    public void newDay() {
        new DailyReportDao(sessionFactory.openSession()).newDay();
    }
}
