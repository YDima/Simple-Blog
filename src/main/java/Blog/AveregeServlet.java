package Blog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("hello")
public class AveregeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        var writer = resp.getWriter();
        writer.println("Hello World.");

        if (req.getParameterMap().containsKey("average")) {
            String average = req.getParameter("average");

            String[] array = average.split(",");

            double sum = 0;
            for (int i = 0; i < array.length; i++) {
                sum = sum + Integer.parseInt(array[i]);
            }
            double average1 = sum / array.length;


            resp.setContentType("text/plain");
            writer.println("Average of " + average + " is " + average1);
        }
    }
}
