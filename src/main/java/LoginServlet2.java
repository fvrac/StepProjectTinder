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

public class LoginServlet2 extends HttpServlet {
    private final Auth auth;

    public LoginServlet2(Auth auth) {
        this.auth = auth;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Path path = Paths.get("./content/login.html");
        ServletOutputStream os = resp.getOutputStream();
        Files.copy(path, os);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_name = req.getParameter("user_name");
        String user_paswd = req.getParameter("user_paswd");
        boolean checked = auth.check(user_name, user_paswd);

        Cookie c = new Cookie("%CALC%", user_name);
        c.setPath("/");
        resp.addCookie(c);

        try (PrintWriter w = resp.getWriter()) {
            w.printf("%s %s\n",user_name, checked ? "logged in successfully" : "login failed");
        }
    }
}
