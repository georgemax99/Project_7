package Servlets;

import Beans.Item;
import SQL.ItemSQL;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/get-items")
public class GetItemsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemSQL itemSQL = new ItemSQL();
        List<Item> itemList = itemSQL.readAll();

        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(itemList));
    }
}
