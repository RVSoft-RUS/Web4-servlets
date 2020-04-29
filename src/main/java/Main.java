import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;

public class Main {

    public static void main(String[] args) throws Exception {
        CustomerServlet customerServlet = new CustomerServlet();
        DailyReportServlet dailyReportServlet = new DailyReportServlet();
        DailyReportServletAll dailyReportServletAll = new DailyReportServletAll();
        DailyReportServletLast dailyReportServletLast = new DailyReportServletLast();
        NewDayServlet newDayServlet = new NewDayServlet();
        ProducerServlet producerServlet = new ProducerServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(customerServlet), "/customer");
        context.addServlet(new ServletHolder(producerServlet), "/producer");
        context.addServlet(new ServletHolder(newDayServlet), "/newday");
        context.addServlet(new ServletHolder(dailyReportServlet), "/report");
        context.addServlet(new ServletHolder(dailyReportServletAll), "/report/all");
        context.addServlet(new ServletHolder(dailyReportServletLast), "/report/last");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
