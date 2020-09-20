package zxPlayer.main;

import zxPlayer.ui.IUserInterface;
//import zxPlayer.ui.Console;
import zxPlayer.ui.Window;

public class App {

  public static void main(String[] args) {
    IUserInterface ui =  new Window();
    	//new Console();
    ui.run();
  }
}
