package DAO;

import model.DailyReport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    private Long getLastId() {
        Query query = session.createQuery(
                "select max(id) from DailyReport");
        return  (Long) query.uniqueResult();
    }

    public List<DailyReport> getAllDailyReport() {
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        session.close();
        return dailyReports;
    }

    public void deleteAll() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE From DailyReport").executeUpdate();
        transaction.commit();
        session.close();
    }

    public void newDay() {
        Transaction transaction = session.beginTransaction();
        session.save(new DailyReport(0L, 0L));
        transaction.commit();
        session.close();
    }

    public DailyReport getCurrentReport() {
        Query query = session.createQuery(
                "from DailyReport r where r.id = (select max(r.id) from DailyReport)");
        DailyReport lastDailyReport = (DailyReport) query.uniqueResult();
        session.close();
        return lastDailyReport;
    }

    public DailyReport getLastReport() {
        Query query = session.createQuery(
                "select max(id) from DailyReport");
        long lastId = (long) query.uniqueResult();

        query = session.createQuery("from DailyReport where id = :id");
        query.setParameter("id", lastId - 1);
        DailyReport lastDailyReport = (DailyReport) query.uniqueResult();
        session.close();
        return lastDailyReport;
    }

    public void updateReport(long id, long earnings, long soldCars) {
        String hql = "Update DailyReport SET earnings = :earningsParam , soldCars = :soldCarsParam " +
                "WHERE Id = :idParam";
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("earningsParam"  , earnings);
        query.setParameter("soldCarsParam"  , soldCars);
        query.setParameter("idParam", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
