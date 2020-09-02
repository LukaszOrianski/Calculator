import buffer.HistoryBuffer;
import menu.Menu;

public class Main {
    public static void main(String[] args) {
        HistoryBuffer historyBuffer = new HistoryBuffer();
        Menu menu = new Menu(historyBuffer.getCalcOperationBuffer());
        historyBuffer.start();
        menu.mainMenu();
        historyBuffer.interrupt();
    }
}