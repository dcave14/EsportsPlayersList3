package Dakota.EsportsPlayersList.controller;

import Dakota.EsportsPlayersList.model.Team;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TeamHelper {
    static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("EsportsPlayersList");

    public void insertTeam(Team team) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(team);
        em.getTransaction().commit();
        em.close();
    }

    public Team findTeam(int id) {
        EntityManager em = emfactory.createEntityManager();
        Team team = em.find(Team.class, id);
        em.close();
        return team;
    }

    public List<Team> showAllTeams() {
        EntityManager em = emfactory.createEntityManager();
        TypedQuery<Team> typedQuery = em.createQuery("select t from Team t", Team.class);
        List<Team> allTeams = typedQuery.getResultList();
        em.close();
        return allTeams;
    }

    public void updateTeam(Team toEdit) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(toEdit);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteTeam(Team toDelete) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        Team foundTeam = em.find(Team.class, toDelete.getId());
        em.remove(foundTeam);
        em.getTransaction().commit();
        em.close();
    }
    
    public void cleanUp() {
        emfactory.close();
    }
}
