package servlet;

import com.google.gson.Gson;
import service.DailyReportService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DailyReportServletAll extends HttpServlet {
    private final DailyReportService dailyReportService = DailyReportService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            Gson gson = new Gson();
            String json = gson.toJson(dailyReportService.getAllDailyReports());
            resp.getWriter().write(json);
    }
}
