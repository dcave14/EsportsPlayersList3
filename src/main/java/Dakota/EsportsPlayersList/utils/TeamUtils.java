    package Dakota.EsportsPlayersList.utils;
    
    import Dakota.EsportsPlayersList.model.Team;
    import java.util.ArrayList;
    import java.util.List;
    
    public class TeamUtils {
        public static String[] teams = {
            "ATLANTA FAZE", "BOSTON BREACH", "CAROLINA ROYAL RAVENS",
            "LOS ANGELES GUERRILLAS", "LOS ANGELES THIEVES", "MIAMI HERETICS",
            "MINNESOTA RØKKR", "NEW YORK SUBLINERS", "SEATTLE SURGE",
            "TEXAS OPTIC", "TORONTO ULTRA", "VEGAS LEGION"
        };
    
        public static List<Team> getTeams() {
            List<Team> teamList = new ArrayList<>();
            for (String teamName : teams) {
                Team team = new Team();
                team.setTeamName(teamName);
                teamList.add(team);
            }
            return teamList;
        }
    }