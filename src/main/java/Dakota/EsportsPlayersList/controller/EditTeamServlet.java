package Dakota.EsportsPlayersList.controller;

import Dakota.EsportsPlayersList.model.Team;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editTeamServlet")
public class EditTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId = Integer.parseInt(request.getParameter("teamId"));
        String name = request.getParameter("name");
        String city = request.getParameter("city");

        TeamHelper teamHelper = new TeamHelper();
        Team teamToUpdate = teamHelper.findTeam(teamId);

        if (teamToUpdate != null) {
            teamToUpdate.setTeamName(name);
            teamToUpdate.setCity(city);
            teamHelper.updateTeam(teamToUpdate);
        }

        response.sendRedirect("viewTeamsServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId = Integer.parseInt(request.getParameter("teamId"));
        TeamHelper teamHelper = new TeamHelper();
        Team team = teamHelper.findTeam(teamId);
        request.setAttribute("team", team);
        request.getRequestDispatcher("/edit-team.jsp").forward(request, response);
    }
}
