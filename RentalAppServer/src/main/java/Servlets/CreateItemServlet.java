package Servlets;

import Beans.Item;
import SQL.ItemSQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-item")
public class CreateItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        String imgUrl = req.getParameter("imgUrl");

        Item item = new Item();
        item.setDescription(description);
        item.setTitle(title);
        item.setUserId(userId);
        item.setPrice(price);
        item.setImgUrl(imgUrl);

        ItemSQL itemSQL = new ItemSQL();
        itemSQL.create(item);
        resp.getWriter().print("Success");
    }
}
