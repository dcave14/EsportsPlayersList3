package Dakota.EsportsPlayersList.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dakota.EsportsPlayersList.model.ListPlayer;

/**
 * Servlet implementation class DeletePlayerServlet
 */
@WebServlet("/deletePlayerServlet")
public class DeletePlayerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeletePlayerServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        ListPlayerHelper lph = new ListPlayerHelper();
        ListPlayer player = lph.getPlayerById(playerId);

        lph.deletePlayer(player);

        response.sendRedirect("viewPlayersServlet");
    }
}