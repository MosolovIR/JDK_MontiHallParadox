import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MontiHallSimulation {
    private static final int TOTAL_ATTEMPTS = 1000;
    private final Random random = new Random();
    private final Map<Integer, Boolean> keepChoiceResults = new HashMap();
    private final Map<Integer, Boolean> switchChoiceResults = new HashMap();

    public void startGame() {
        for (int i = 0; i < TOTAL_ATTEMPTS; ++i) {
            boolean winWithKeep = playGame(false);
            boolean winWithSwitch = playGame(true);
            keepChoiceResults.put(i, winWithKeep);
            switchChoiceResults.put(i, winWithSwitch);
        }

        printResults();
    }

    private boolean playGame(boolean switchChoice) {
        int winDoor = random.nextInt(3);
        int playerChoice = random.nextInt(3);

        int hostChoice;
        do {
            do {
                hostChoice = random.nextInt(3);
            } while (hostChoice == winDoor);
        } while (hostChoice == playerChoice);

        if (switchChoice) {
            playerChoice = 3 - playerChoice - hostChoice;
        }

        return playerChoice == winDoor;
    }

    private void printResults() {
        long keepWins = keepChoiceResults.values().stream().filter((win) -> win).count();
        long switchWins = switchChoiceResults.values().stream().filter((win) -> win).count();
        System.out.println("Стратегия без смены выбора: Победы = " + keepWins + ", Поражения = " + (1000L - keepWins));
        System.out.println("Стратегия со сменой выбора: Победы = " + switchWins + ", Поражения = " + (1000L - switchWins));
    }
}
