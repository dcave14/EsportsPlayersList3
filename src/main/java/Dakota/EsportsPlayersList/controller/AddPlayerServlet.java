package Dakota.EsportsPlayersList.controller;

import Dakota.EsportsPlayersList.model.ListPlayer;
import Dakota.EsportsPlayersList.model.Team;
import Dakota.EsportsPlayersList.utils.TeamUtils;
import Dakota.EsportsPlayersList.controller.ListPlayerHelper;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addPlayerServlet")
public class AddPlayerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddPlayerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Team> teams = TeamUtils.getTeams();
        request.setAttribute("teams", teams);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/add-player.jsp");
        dispatcher.forward(request, response);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            String state = request.getParameter("state");
            String teamName = request.getParameter("teamName");
            
            if(name == null || role == null || state == null || teamName == null) {
                throw new IllegalArgumentException("Required parameters are missing or incorrect.");
            }

            Team team = new Team();
            team.setTeamName(teamName);
            
            ListPlayer player = new ListPlayer(name, team, role, state);
            ListPlayerHelper lph = new ListPlayerHelper();
            lph.insertPlayer(player);
            response.sendRedirect("viewPlayersServlet");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid team ID format.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/add-player.jsp");
            dispatcher.forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/add-player.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error-page.jsp");
            dispatcher.forward(request, response);
        }
    }
}