package Servlets;

import Beans.User;
import SQL.UserSQL;
import Util.PasswordHashUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").toLowerCase(Locale.ROOT);
        String password = req.getParameter("password");
        password = PasswordHashUtil.getMd5(password);

        UserSQL userSQL = new UserSQL();
        User user = userSQL.readByEmailAndPassword(email, password);

        if (user != null) {
            Gson gson = new Gson();
            resp.getWriter().print(gson.toJson(user));
        } else {
            resp.getWriter().print("Incorrect credentials. Try again.");
        }
    }
}
