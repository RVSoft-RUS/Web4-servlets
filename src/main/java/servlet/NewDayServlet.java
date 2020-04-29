package servlet;

import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewDayServlet extends HttpServlet {
    private DailyReportService dailyReportService = DailyReportService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Gson gson = new Gson();
//        String json = gson.toJson(DailyReportService.getInstance().getLastReport());
//        resp.getWriter().write(json);
        dailyReportService.newDay();
    }
}
