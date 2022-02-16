package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealToDAOimpl;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private final MealToDAOimpl mealToDAOimpl = new MealToDAOimpl();
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        String action = request.getParameter("action");

        if(action != null) {
            if (action.equalsIgnoreCase("delete")) {
                int mealToId = Integer.parseInt(request.getParameter("Id"));
                mealToDAOimpl.delete(mealToDAOimpl.getById(mealToId));
                request.setAttribute("mealTo", mealToDAOimpl.allMealTo());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
            }
            if (action.equalsIgnoreCase("edit")){
                int mealToId = Integer.parseInt(request.getParameter("Id"));
                MealTo mealTo = mealToDAOimpl.getById(mealToId);
                request.setAttribute("mealTo", mealTo);
                request.getRequestDispatcher("/addMeal.jsp").forward(request, response);
            }
        }
        else {
            request.setAttribute("mealTo", mealToDAOimpl.allMealTo());
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
//        response.sendRedirect("meals.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        MealTo mealTo = new MealTo();
        mealTo.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
        mealTo.setDescription(request.getParameter("description"));
        mealTo.setCalories(Integer.parseInt(request.getParameter("calories")));
        String id = request.getParameter("Id");
        if(id == null || id.isEmpty())
        mealToDAOimpl.add(mealTo);
        else {
            mealTo.setId(Integer.parseInt(id));
            mealToDAOimpl.edit(mealTo);
        }
        request.setAttribute("mealTo",mealToDAOimpl.allMealTo());
        request.getRequestDispatcher("meals.jsp").forward(request,response);
    }
}
