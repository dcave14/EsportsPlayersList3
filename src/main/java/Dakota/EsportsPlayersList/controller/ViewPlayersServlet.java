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

/**
 * Servlet implementation class ViewPlayersServlet
 */
@WebServlet("/viewPlayersServlet")
public class ViewPlayersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public ViewPlayersServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListPlayerHelper lph = new ListPlayerHelper();
        List<ListPlayer> players = lph.showAllPlayers();
        request.setAttribute("players", players);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view-players.jsp");
        dispatcher.forward(request, response);
    }
}
