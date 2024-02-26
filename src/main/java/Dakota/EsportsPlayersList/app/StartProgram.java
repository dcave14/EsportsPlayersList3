package Dakota.EsportsPlayersList.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Dakota.EsportsPlayersList.controller.ListPlayerHelper;
import Dakota.EsportsPlayersList.model.ListPlayer;
import Dakota.EsportsPlayersList.utils.TeamUtils;

public class StartProgram {
    static Scanner in = new Scanner(System.in);
    static ListPlayerHelper lph = new ListPlayerHelper();

    //method to view roster by player name or team name
    private static void viewRoster() {
        System.out.println("View roster by:");
        System.out.println("1 : Player Name");
        System.out.println("2 : Team Name");
        int choice = in.nextInt();
        in.nextLine(); //consume newline left-over

        if (choice == 1) {
            //view roster by player name
            System.out.print("Enter the player name: ");
            String playerName = in.nextLine();
            List<ListPlayer> players = lph.searchForPlayerByName(playerName);
            if (players.isEmpty()) {
                System.out.println("No players found with the name: " + playerName);
            } else {
                //assuming first player found is valid player to find team
                String team = players.get(0).getTeam();
                System.out.println("Player found in team: " + team);
                System.out.println("Fetching full roster for team: " + team);
                //fetch and display player roster for team
                displayPlayerRoster(team);
            }
        } else if (choice == 2) {
            //view roster by team name
            System.out.println("Choose a team:");
            String team = TeamUtils.showTeams(in); // Use TeamUtils to get the team
            //fetch and display player roster for selected team
            displayPlayerRoster(team);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    //method to display the roster for specific team
    private static void displayPlayerRoster(int team) {
        List<ListPlayer> players = lph.searchForPlayerByTeam(team);
        if (players.isEmpty()) {
            System.out.println("No players found in team: " + team);
        } else {
            System.out.println("Team: " + team);
            System.out.println("Player Roster:");

            //separate players into active players, substitutes, and coaches
            List<ListPlayer> activePlayers = new ArrayList<>();
            List<ListPlayer> substitutes = new ArrayList<>();
            List<ListPlayer> coaches = new ArrayList<>();

            for (ListPlayer player : players) {
                if (player.getRole().equals("Coach")) {
                    coaches.add(player); //add coaches to coaches list
                } else if (player.getState().equals("Active")) {
                    activePlayers.add(player); //add active players to activePlayers list
                } else if (player.getState().equals("Substitute")) {
                    substitutes.add(player); //add substitutes to substitutes list
                }
            }

            //display active players
            int playerCount = 1;
            for (ListPlayer player : activePlayers) {
                System.out.println("----Player " + playerCount + ":");
                System.out.println("--------Name: " + player.getName());
                System.out.println("----------Role: " + player.getRole());
                System.out.println("----------State: " + player.getState());
                playerCount++;
            }

            //display subs
            for (ListPlayer player : substitutes) {
                System.out.println("----Player " + playerCount + ":");
                System.out.println("--------Name: " + player.getName());
                System.out.println("----------Role: " + player.getRole());
                System.out.println("----------State: " + player.getState());
                playerCount++;
            }

            //display coaches
            System.out.println("Coaches:");
            if (coaches.isEmpty()) {
                System.out.println("--Roster:");
                System.out.println("No coaches found in team: " + team);
            } else {
                System.out.println("--Roster:");
                int coachCount = 1;
                for (ListPlayer coach : coaches) {
                    System.out.println("----Coach " + coachCount + ":");
                    System.out.println("--------Name: " + coach.getName());
                    coachCount++;
                }
            }
        }
    }

    //method to add new player to roster
    private static void addPlayer() {
        System.out.println("Choose a team:");
        String team = TeamUtils.showTeams(in); //use TeamUtils to get team

        System.out.println("Enter a player name: ");
        String playerName = in.nextLine();
        System.out.println("Choose Role: ");
        System.out.println("1. SMG, 2. AR, 3. Flex, 4. Coach");
        int roleChoice = in.nextInt();
        in.nextLine(); 

        String role;
        switch (roleChoice) {
            case 1:
                role = "SMG";
                break;
            case 2:
                role = "AR";
                break;
            case 3:
                role = "Flex";
                break;
            case 4:
                role = "Coach";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to 'Flex'.");
                role = "Flex";
        }

        System.out.println("Enter Player State: ");
        System.out.println("1. Active, 2. Substitute, 3. Coach");
        int stateChoice = in.nextInt();
        in.nextLine(); 

        String state;
        switch (stateChoice) {
            case 1:
                state = "Active";
                break;
            case 2:
                state = "Substitute";
                break;
            case 3:
                state = "Coach";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to 'Active'.");
                state = "Active";
        }

        ListPlayer toAdd = new ListPlayer(playerName, team, role, state);
        lph.insertPlayer(toAdd);
        System.out.println("Player added successfully.");
    }

    //method to view details of specific player by name
    private static void viewPlayer() {
        System.out.print("Enter the player name: ");
        String playerName = in.nextLine();
        List<ListPlayer> players = lph.searchForPlayerByName(playerName);
        if (players.isEmpty()) {
            System.out.println("No players found with the name: " + playerName);
        } else {
            for (ListPlayer player : players) {
                System.out.println(player.returnPlayerDetails());
            }
        }
    }

    //method to edit player's details
    private static void editPlayer() {
        System.out.println("How would you like to search? ");
        System.out.println("1 : Search by Team");
        System.out.println("2 : Search by Player");
        int searchBy = in.nextInt();
        in.nextLine(); // Consume newline left-over

        List<ListPlayer> foundPlayers = null;

        if (searchBy == 1) {
            //search by team name
            String teamName = TeamUtils.showTeams(in);
            foundPlayers = lph.searchForPlayerByTeam(teamName);
        } else if (searchBy == 2) {
            //search by player name
            System.out.print("Enter the player name: ");
            String playerName = in.nextLine();
            foundPlayers = lph.searchForPlayerByName(playerName);
        } else {
            System.out.println("Invalid selection.");
            return; //ealry return to avoid proceeding further
        }

        if (foundPlayers != null && !foundPlayers.isEmpty()) {
            //if players found, process search results
            processSearchResults(foundPlayers);
        } else {
            System.out.println("No results found.");
        }
    }

    //method to delete player from roster
    private static void deletePlayer() {
        System.out.println("Delete player by:");
        System.out.println("1 : Team Name");
        System.out.println("2 : Player Name");
        int choice = in.nextInt();
        in.nextLine(); 

        List<ListPlayer> foundPlayers = null;

        if (choice == 1) {
            //delete by team name
            System.out.println("Choose a team:");
            String team = TeamUtils.showTeams(in);
            foundPlayers = lph.searchForPlayerByTeam(team);
        } else if (choice == 2) {
            //delete by player name
            System.out.print("Enter the player name: ");
            String playerName = in.nextLine();
            foundPlayers = lph.searchForPlayerByName(playerName);
        }

        if (foundPlayers != null && !foundPlayers.isEmpty()) {
            //if players found, display them and ask for ID of player to delete
            System.out.println("Found Players and Coaches:");
            for (ListPlayer player : foundPlayers) {
                System.out.println(
                        "ID: " + player.getId() + ", Team: " + player.getTeam() + ", " + player.getRole() + ": "
                                + player.getName());
            }
            System.out.print("Enter the ID of the player/coach you want to delete: ");
            if (in.hasNextInt()) {
                int id = in.nextInt();
                in.nextLine(); 

                ListPlayer toDelete = lph.searchForPlayerById(id);

                if (toDelete != null) {
                    //if player/coach found, show details and ask for confirmation
                    System.out.println("You are about to delete the following player/coach:");
                    System.out.println(
                            "ID: " + toDelete.getId() + ", Team: " + toDelete.getTeam() + ", " + toDelete.getRole()
                                    + ": " + toDelete.getName());
                    System.out.print("Confirm deletion (yes/no): ");
                    String confirmation = in.nextLine().trim().toLowerCase();

                    if (confirmation.equals("yes")) {
                        //if confirmed, delete player/coach from list
                        lph.deletePlayer(toDelete);
                        System.out.println("Player/coach deleted successfully.");
                    } else {
                        System.out.println("Deletion cancelled.");
                    }
                } else {
                    System.out.println("No player/coach found with ID: " + id);
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } else {
            System.out.println("No players/coaches found.");
        }
    }

    //method to process search results and edit player in list
    private static void processSearchResults(List<ListPlayer> foundPlayers) {
        //display each found player's ID and details
        for (int i = 0; i < foundPlayers.size(); i++) {
            ListPlayer p = foundPlayers.get(i);
            System.out.println("[" + (i + 1) + "]: ID: " + p.getId() + ", Team: " + p.getTeam() + ", Player: "
                    + p.getName() + ", Role: " + p.getRole());
        }

        System.out.print("Select the number corresponding to the player you wish to edit: ");
        int selection = in.nextInt() - 1; //adjust for 0-index
        in.nextLine(); 

        if (selection >= 0 && selection < foundPlayers.size()) {
            ListPlayer toEdit = foundPlayers.get(selection);
            System.out.println("Selected: " + toEdit.getName() + " from " + toEdit.getTeam());
            System.out.println("Which field do you want to update? (Select 1-3):");
            System.out.println("1. Name\n2. Team\n3. Role");
            int fieldToUpdate = in.nextInt();
            in.nextLine(); 

            editPlayerDetails(toEdit, fieldToUpdate);
        } else {
            System.out.println("Invalid selection.");
        }
    }

    //method to edit specific details of player
    private static void editPlayerDetails(ListPlayer player, int field) {
        switch (field) {
            case 1:
                //edit player name
                System.out.print("Enter new name: ");
                String newName = in.nextLine();
                player.setName(newName);
                break;
            case 2:
                //edit player team
                System.out.print("Enter new team: ");
                String newTeam = in.nextLine();
                player.setTeam(newTeam);
                break;
            case 3:
                //edit player role
                System.out.println("Select new role:");
                System.out.println("1. SMG\n2. AR\n3. Flex\n4. Coach");
                int roleChoice = in.nextInt();
                String newRole = "";
                switch (roleChoice) {
                    case 1:
                        newRole = "SMG";
                        break;
                    case 2:
                        newRole = "AR";
                        break;
                    case 3:
                        newRole = "Flex";
                        break;
                    case 4:
                        newRole = "Coach";
                        break;
                    default:
                        System.out.println("Invalid role choice. Keeping the existing role.");
                }
                if (!newRole.isEmpty()) {
                    player.setRole(newRole);
                }
                break;
            default:
                System.out.println("Invalid field selection.");
                return;
        }
        lph.updatePlayer(player);
        System.out.println("Player updated successfully.");
    }

    //method to display menu options
    private static void showMenu() {
        System.out.println("\n1. Add a player");
        System.out.println("2. Edit a player");
        System.out.println("3. Delete a player");
        System.out.println("4. View Roster");
        System.out.println("5. View Player");
        System.out.println("6. Exit");
        System.out.print("Your choice: ");
    }

    //entry point of program
    public static void main(String[] args) {
        boolean goAgain = true;

        //display menu and perform actions based on user's choice
        while (goAgain) {
            showMenu();
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    addPlayer();
                    break;
                case 2:
                    editPlayer();
                    break;
                case 3:
                    deletePlayer();
                    break;
                case 4:
                    viewRoster(); //call viewRoster to view by team or player name
                    break;
                case 5:
                    viewPlayer(); //call viewPlayer to view a specific player
                    break;
                case 6:
                    goAgain = false;
                    lph.cleanUp();
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }

        //close scanner
        in.close();
    }
}