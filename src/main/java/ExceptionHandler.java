import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<html><head><title>Exception/Error Details</title></head><body>");
        if (statusCode != 500) {
            out.write("<div style=\"background-color: red;width: 20%;text-align: center; \">");
            out.write("<h3>Error Details</h3>");
            out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
            out.write("<strong>Requested URI</strong>:" + requestUri);
            out.write("</div>");
        } else {
            out.write("<div style=\"background-color: red;width: 20%;text-align: center; \">");
            out.write("<h3>Exception Details</h3>");
            out.write("<ul><li>Servlet Name:" + servletName + "</li>");
            out.write("<li>Exception Name:" + throwable.getClass().getName() + "</li>");
            out.write("<li>Requested URI:" + requestUri + "</li>");
            out.write("<li>Exception Message:" + throwable.getMessage() + "</li>");
            out.write("</ul>");
            out.write("</div>");
        }
        out.write("<br><br>");
        out.write("<a href=\"index.html\">Home Page</a>");
        out.write("</body></html>");
    }
}