import java.util.HashMap;
import java.util.Map;

public class AuthService implements Auth {
  Map<String, String> data = new HashMap<>();

  AuthService() {
    data.put("ali","123");
    data.put("alex","123");
    data.put("fred","123");
    data.put("shrek","123");
    data.put("don","123");
    data.put("admin","123");
    data.put("admin@gmail.com","123");
  }

  @Override
  public boolean check(String login, String paswd) {
    return data.containsKey(login)
        && data.get(login).equals(paswd);
  }
}
