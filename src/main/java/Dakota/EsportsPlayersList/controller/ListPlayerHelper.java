package Dakota.EsportsPlayersList.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import Dakota.EsportsPlayersList.model.ListPlayer;

public class ListPlayerHelper {
    static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("EsportsPlayersList");

    //method to insert new player into database
    public void insertPlayer(ListPlayer lp) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(lp);
        em.getTransaction().commit();
        em.close();
    }

    //method to retrieve all players from database
    public List<ListPlayer> showAllPlayers() {
        EntityManager em = emfactory.createEntityManager();
        TypedQuery<ListPlayer> typedQuery = em.createQuery("select lp from ListPlayer lp", ListPlayer.class);
        List<ListPlayer> allPlayers = typedQuery.getResultList();
        em.close();
        return allPlayers;
    }
    
 // method to search for player by ID
    public ListPlayer getPlayerById(int id) {
        EntityManager em = emfactory.createEntityManager();
        ListPlayer player = em.find(ListPlayer.class, id);
        em.close();
        return player;
    }

    //method to search for player by ID
    public ListPlayer searchForPlayerById(int idToEdit) {
        EntityManager em = emfactory.createEntityManager();
        ListPlayer found = em.find(ListPlayer.class, idToEdit);
        em.close();
        return found; // This could be null if no player is found
    }

    public List<ListPlayer> searchForPlayerByTeam(int teamId) {
        EntityManager em = emfactory.createEntityManager();
        try {
            TypedQuery<ListPlayer> typedQuery = em.createQuery(
                "SELECT lp FROM ListPlayer lp WHERE lp.team.id = :teamId", ListPlayer.class);
            typedQuery.setParameter("teamId", teamId);
            List<ListPlayer> foundPlayers = typedQuery.getResultList();
            return foundPlayers;
        } finally {
            em.close();
        }
    }

    //method to search for players by name
    public List<ListPlayer> searchForPlayerByName(String playerName) {
        EntityManager em = emfactory.createEntityManager();
        TypedQuery<ListPlayer> typedQuery = em.createQuery(
                "select lp from ListPlayer lp where lp.name = :playerName", ListPlayer.class);
        typedQuery.setParameter("playerName", playerName);

        List<ListPlayer> foundPlayers = typedQuery.getResultList();
        em.close();
        return foundPlayers;
    }

    //method to search for coaches by team name
    public List<ListPlayer> searchForCoachByTeam(String teamName) {
        EntityManager em = emfactory.createEntityManager();
        TypedQuery<ListPlayer> typedQuery = em.createQuery(
                "select lp from ListPlayer lp where lp.team = :teamName and lp.role = 'Coach'", ListPlayer.class);
        typedQuery.setParameter("teamName", teamName);
    
        List<ListPlayer> foundCoaches = typedQuery.getResultList();
        em.close();
        return foundCoaches;
    }

    //method to update player's details in database
    public void updatePlayer(ListPlayer toEdit) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(toEdit);
        em.getTransaction().commit();
        em.close();
    }

    //method to delete player from database
    public void deletePlayer(ListPlayer toDelete) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<ListPlayer> typedQuery = em.createQuery(
                "select lp from ListPlayer lp where lp.id = :selectedId", ListPlayer.class);
        typedQuery.setParameter("selectedId", toDelete.getId());
        typedQuery.setMaxResults(1);
        ListPlayer result = typedQuery.getSingleResult();
        em.remove(result);
        em.getTransaction().commit();
        em.close();
    }

    //method to clean up EntityManagerFactory
    public void cleanUp() {
        emfactory.close();
    }
}