package Dakota.EsportsPlayersList.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import Dakota.EsportsPlayersList.model.Team;

@WebServlet(name = "AddTeamServlet", value = "/addTeamServlet")
public class AddTeamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddTeamServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/add-team.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teamName = request.getParameter("name");
        String city = request.getParameter("city");

        if (teamName == null || teamName.isEmpty() || city == null || city.isEmpty()) {
            request.getSession().setAttribute("errorMessage", "Both team name and city are required.");
            response.sendRedirect("addTeam.jsp"); 
            return; 
        }

        try {
            TeamHelper teamHelper = new TeamHelper();
            Team team = new Team(teamName, city);
            teamHelper.insertTeam(team);

            request.getSession().setAttribute("successMessage", "Team successfully added.");
            response.sendRedirect("viewTeamsServlet"); // Use the correct URL or Servlet mapping
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with more robust logging

            request.getSession().setAttribute("errorMessage", "Error adding team. Please try again.");
            response.sendRedirect("addTeam.jsp"); // Use the correct URL or JSP name
        }
    }
}
