import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Cookie[] cookies = req.getCookies(); // NULL
    int id = -1; // no user
    if (cookies != null) {
      for (Cookie c: cookies) {
        if (c.getName().equals("%CALC%")) {
          c.setMaxAge(0);
          resp.addCookie(c);
        }
      }
    }

  }
}
