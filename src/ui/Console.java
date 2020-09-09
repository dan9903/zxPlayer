package ui;
import player.Playlist;
import player.PlayerController;

public class Console implements IUserInterface{
  public void run() {
    testMethod();
  }

  public void testMethod() {
    Playlist list = new Playlist();
    list.add("monkey wrench");
    list.add("breakout");
    list.add("the pretender");
    list.add("walk");

    PlayerController p = new PlayerController(list);

    p.play();

    }
}
