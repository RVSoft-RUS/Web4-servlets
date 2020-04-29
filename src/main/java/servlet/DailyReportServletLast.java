package servlet;

import com.google.gson.Gson;
import model.DailyReport;
import service.DailyReportService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DailyReportServletLast extends HttpServlet {
    private final DailyReportService dailyReportService = DailyReportService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        DailyReport dailyReport = dailyReportService.getLastReport();
        String json = gson.toJson(dailyReport);
        resp.getWriter().write(json);
    }
}
