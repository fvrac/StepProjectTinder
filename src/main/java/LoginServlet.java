import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginServlet extends HttpServlet {
    private final Auth auth;

    public LoginServlet(Auth auth) {
        this.auth = auth;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Path path = Paths.get("./templates/login.html");
        ServletOutputStream os = resp.getOutputStream();
        Files.copy(path, os);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputEmail = req.getParameter("inputEmail");
        String inputPassword = req.getParameter("inputPassword");
        boolean checked = auth.check(inputEmail, inputPassword);

        Cookie c = new Cookie("%CALC%", inputEmail);
        c.setPath("/");
        resp.addCookie(c);

        try (PrintWriter w = resp.getWriter()) {
            w.printf("%s %s\n",inputEmail, checked ? "logged in successfully" : "login failed");
        }
    }
}
