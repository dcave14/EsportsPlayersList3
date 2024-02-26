package Dakota.EsportsPlayersList.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dakota.EsportsPlayersList.model.Team;

@WebServlet("/viewTeamsServlet")
public class ViewTeamsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewTeamsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeamHelper teamHelper = new TeamHelper();
        List<Team> teams = teamHelper.showAllTeams();

        request.setAttribute("teams", teams); 

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view-teams.jsp");
        dispatcher.forward(request, response);
    }
}
