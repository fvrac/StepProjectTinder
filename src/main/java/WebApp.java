import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WebApp {
  public static void main(String[] args) throws Exception {
    Server server = new Server(8095);
    ServletContextHandler handler = new ServletContextHandler();
    AuthService as = new AuthService();

    handler.addServlet(new ServletHolder(new LoginServlet(as)), "/login/*");
    handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout/*");
    handler.addServlet(new ServletHolder(new HelloServlet()), "/hello/*");
    handler.addServlet(new ServletHolder(new UsersServlet()), "/users/*");
    handler.addServlet(new ServletHolder(new LikedServlet()), "/liked/*");
    handler.addServlet(new ServletHolder(new MessagesServlet()), "/messages/*");
    handler.addServlet(new ServletHolder(new ServletRedirect("/login")), "/*");

    server.setHandler(handler);
    server.start();
    server.join();
  }
}
