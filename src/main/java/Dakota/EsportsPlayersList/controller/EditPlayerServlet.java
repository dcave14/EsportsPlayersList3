package Dakota.EsportsPlayersList.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dakota.EsportsPlayersList.model.ListPlayer;
import Dakota.EsportsPlayersList.model.Team;

/**
 * Servlet implementation class EditPlayerServlet
 */
@WebServlet("/editPlayerServlet")
public class EditPlayerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditPlayerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playerId = Integer.parseInt(request.getParameter("playerId"));

        ListPlayerHelper lph = new ListPlayerHelper();
        ListPlayer player = lph.getPlayerById(playerId);
        request.setAttribute("player", player);

        TeamHelper teamHelper = new TeamHelper();
        List<Team> teams = teamHelper.showAllTeams();
        request.setAttribute("teams", teams);

        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-player.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        String name = request.getParameter("name");
        int teamId = Integer.parseInt(request.getParameter("team"));
        String role = request.getParameter("role");
        String state = request.getParameter("state");

        TeamHelper teamHelper = new TeamHelper();
        Team team = teamHelper.findTeam(teamId);

        ListPlayerHelper lph = new ListPlayerHelper();
        ListPlayer player = lph.getPlayerById(playerId);

        player.setName(name);
        player.setTeam(team);
        player.setRole(role);
        player.setState(state);

        lph.updatePlayer(player);

        response.sendRedirect("viewPlayersServlet");
    }
}
