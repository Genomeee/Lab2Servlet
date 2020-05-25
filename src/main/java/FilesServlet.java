import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "FilesServlet",
        urlPatterns = "/files")
public class FilesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse res) {

        try {
            res.setContentType("text/html");
            res.setCharacterEncoding("UTF-8");
            String folder = request.getParameter("fileToUpload") == null ? request.getParameter("fileToUpload") : "files";

            PrintWriter out = res.getWriter();
            File fileFolder = new File(folder);
            out.println("<form action=\"http://localhost:8080/readFile\" method=\"get\">");
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Select the file</h1>");
            out.println("<select name=\"fileName\">\n");
            for (File file : Objects.requireNonNull(fileFolder.listFiles())) {
                out.println("<option >" + file.getName() + "</option>\n");
            }
            out.println("</select>\n");
            out.println("<input type=\"submit\" value=\"Отправить\" />");
            out.println("</form>\n");
            out.println("</html>");
            out.println("</body>");
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

}