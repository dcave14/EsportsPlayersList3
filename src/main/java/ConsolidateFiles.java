import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ConsolidateFiles {

    public static void main(String[] args) {
        // Update these paths to match your environment
        String sourceDirectory = "F:\\COLLEGE\\SEMESTER 5\\JAVA II\\WEEK 5\\LAB"; // Your project directory
        String outputFile = "F:\\COLLEGE\\SEMESTER 5\\JAVA II\\WEEK 5\\LAB\\consolidated_files.txt"; // Path for the output file
        Set<String> targetFiles = new HashSet<>(Arrays.asList("AddPlayerServlet.java", "AddTeamServlet.java", "DeletePlayerServlet.java","DeleteTeamServlet.java","EditPlayerServlet.java","EditTeamServlet.java", "ListPlayerHelper.java","listTeamsServlet.java","TeamHelper.java","ViewPlayersByTeamServlet.java","ViewPlayersServlet.java","ViewTeamsServlet.java","ListPlayer.java","Team.java","TeamUtils.java","Persistence.xml", "web.xml", "add-player.jsp","add-team.jsp","edit-player.jsp","edit-team.jsp","errorPage.jsp","index.html","list-teams.jsp","view-players.jsp","view-players-by-team.jsp","view-teams.jsp","pom.xml"));

        try {
            Files.walk(Paths.get(sourceDirectory))
                .filter(Files::isRegularFile)
                .filter(path -> targetFiles.contains(path.getFileName().toString())) // Check if it's a targeted file
                .forEach(path -> appendFileContentsToFile(path, outputFile));
            System.out.println("Target text files have been consolidated into " + outputFile);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static void appendFileContentsToFile(Path sourceFilePath, String outputFile) {
        try {
            List<String> lines = Files.readAllLines(sourceFilePath);
            Files.write(Paths.get(outputFile), ("\n/* Start of " + sourceFilePath + " */\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(Paths.get(outputFile), lines, StandardOpenOption.APPEND);
            Files.write(Paths.get(outputFile), ("\n/* End of " + sourceFilePath + " */\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Could not write file: " + sourceFilePath + " to " + outputFile + " due to " + e.getMessage());
        }
    }
}