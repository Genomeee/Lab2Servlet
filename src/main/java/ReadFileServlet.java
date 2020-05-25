import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "ReadFileServlet",
        urlPatterns = "/readFile")
public class ReadFileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String getFile = request.getParameter("fileName");
        String filename = "files/" + getFile;
        PrintWriter pw = response.getWriter();
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String text;

        while ((text = reader.readLine()) != null) {
            pw.println("<p>" + text + "</p>");
        }
    }
}
