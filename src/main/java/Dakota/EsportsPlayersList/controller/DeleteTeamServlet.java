package Dakota.EsportsPlayersList.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dakota.EsportsPlayersList.model.Team;

@WebServlet("/deleteTeamServlet")
public class DeleteTeamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId = Integer.parseInt(request.getParameter("teamId"));

        TeamHelper teamHelper = new TeamHelper();
        Team teamToDelete = teamHelper.findTeam(teamId);
        if (teamToDelete != null) {
            teamHelper.deleteTeam(teamToDelete);
        }

        response.sendRedirect("viewTeamsServlet");
    }
}
