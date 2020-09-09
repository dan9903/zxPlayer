import ui.IUserInterface;
import ui.Console;

public class App{

  public static void main(String[] args) {
    IUserInterface ui = new Console();
    ui.run();
  }
}