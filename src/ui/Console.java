package ui;


import java.util.Scanner;

import player.IPlayer;
import player.PlayerController;
import player.Playlist;

public class Console implements IUserInterface{
  private IPlayer player;

  public Console() {
    Playlist list  = new Playlist();
    list.readFrom("audio-source");
    this.player = new PlayerController(list);
  }

  public void run() {
    keyboardInput();
  }
  
  private void keyboardInput(){
    String userInput = new String();
    Scanner keyboard = new Scanner(System.in);
    do {
      userInput = keyboard.nextLine();
      userInput = userInput.toUpperCase();  
    } while (playerActions(userInput));
    keyboard.close();
    keyboard = null;
  }

  private boolean playerActions(String a_userInput) {
    switch (a_userInput) {
      case "PLAY":
        this.player.play();
        break;
      case "PAUSE":
        this.player.pause();
        break;
      case "NEXT":
        System.out.println("next song");
        break;
      case "PREVIOUS":
        System.out.println("previous song");
        break;
      case "STOP":
        this.player.stop();
        break;
      case "Q":
        System.out.println("exit...");
        return false;
      default:
        System.out.println("command not found");
        break;
    }
    return true;
  }
}
