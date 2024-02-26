package Dakota.EsportsPlayersList.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dakota.EsportsPlayersList.model.ListPlayer;
import Dakota.EsportsPlayersList.model.Team;

@WebServlet("/viewPlayersByTeamServlet")
public class ViewPlayersByTeamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewPlayersByTeamServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeamHelper teamHelper = new TeamHelper();
        List<Team> teams = teamHelper.showAllTeams();
        request.setAttribute("teams", teams);
        
        String teamIdString = request.getParameter("teamId");
        if (teamIdString != null && !teamIdString.isEmpty()) {
            int teamId = Integer.parseInt(teamIdString);
            ListPlayerHelper listPlayerHelper = new ListPlayerHelper();
            List<ListPlayer> playersByTeam = listPlayerHelper.searchForPlayerByTeam(teamId);
            request.setAttribute("playersByTeam", playersByTeam);
            
            Team selectedTeam = teamHelper.findTeam(teamId);
            if (selectedTeam != null) {
                request.setAttribute("teamName", selectedTeam.getTeamName());
            }
        }

        request.getRequestDispatcher("/view-players-by-team.jsp").forward(request, response);
    }
}
