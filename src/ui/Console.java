package ui;


import java.util.Scanner;

import player.IPlayer;
import player.PlayerController;
import player.Playlist;
import java.util.List;

public class Console implements IUserInterface{
  private IPlayer player;
  private Playlist list;
  private String message;

  public Console() {
    list  = new Playlist();
    message = "";
  }

  public void run() {
    Scanner keyboard = new Scanner(System.in);
    String path = new String();
    System.out.print("|-----------------------| zx player |-----------------------|\npath:");
    path = keyboard.nextLine();
    
    list.readFrom(path);
    player = new PlayerController(list);
    
    do {
      draw(list);
      message = "";
    } while (keyboardInput(keyboard));
    
    keyboard.close();
    keyboard = null;
  }
  
  private boolean keyboardInput(Scanner keyboard) {
    String userInput = new String();
    userInput = keyboard.nextLine();
    userInput = userInput.toUpperCase();  
    return playerActions(userInput);
  }

  private boolean playerActions(String a_userInput) {
    switch (a_userInput) {
      case "PLAY":
        player.play();
        break;
      case "PAUSE":
        player.pause();
        break;
      case "NEXT":
        player.next();
        break;
      case "PREVIOUS":
        player.previous();
        break;
      case "STOP":
        player.stop();
        break;
      case "Q":
         message = "exit...";
        return false;
      default:
        message = "command not found";
        break;
    }
    return true;
  }

  private void draw(Playlist playlist) {
    //comand to clear screen
    System.out.print("\033[H\033[2J");
    System.out.println("|-----------------------| zx player |-----------------------|");
    List<String> list =  playlist.getAllSongNames();
    int current = playlist.getCurrentSong();
    for(int i = 0; i < list.size(); i++) {
      if (i == current) 
        System.out.print("> ");
      System.out.println(list.get(i));
    }

    System.out.print(": ");
    if (message != "")
      System.out.print("     "+message);
  }
}
