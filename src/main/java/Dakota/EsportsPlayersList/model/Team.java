package Dakota.EsportsPlayersList.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private int id;
    
    @Column(name = "teamName")
    private String teamName;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListPlayer> players = new ArrayList<>();

    public Team() {

    }

    public Team(String teamName, String city) {
        this.teamName = teamName;
        this.city = city;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ListPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<ListPlayer> players) {
        this.players = players;
    }

    public void addPlayer(ListPlayer player) {
        players.add(player);
        player.setTeam(this);
    }

    public void removePlayer(ListPlayer player) {
        players.remove(player);
        player.setTeam(null);
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", name=" + teamName + ", city=" + city + "]";
    }
}
