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

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").toLowerCase(Locale.ROOT);
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        password = PasswordHashUtil.getMd5(password);

        UserSQL userSQL = new UserSQL();

        User user = userSQL.readByEmailAndPassword(email, password);

        Gson gson = new Gson();

        if (user != null) { //User already exists with those credentials so sign in the user
            resp.getWriter().print(gson.toJson(user));

        } else if (userSQL.readByEmail(email) != null) { //User already exists with that email
            resp.getWriter().print("User already exists with those credentials.");
        } else { //User does not exist so sign up
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setId(userSQL.create(user));
            resp.getWriter().print(gson.toJson(user));
        }
    }
}
