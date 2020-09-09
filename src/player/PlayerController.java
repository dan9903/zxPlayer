package player;

import javazoom.jl.player.Player;
import java.io.InputStream;
import java.io.FileInputStream;

public class PlayerController {
  private Playlist playlist;
  private boolean playing;
  private Player player;

  public PlayerController(Playlist a_playlist) {
    this.playlist = a_playlist;
    this.playing = false;
  }

  public boolean isPlaying() {
    return this.playing;
  }

  public boolean next() {
    int current = this.playlist.getCurrentSong();
    this.playing = this.playlist.setCurrentSong(++current);
    return this.playing;
  }

  public boolean previous() {
    int current = this.playlist.getCurrentSong();
    this.playing =  this.playlist.setCurrentSong(--current);
    return this.playing;
  }

  public void play(){
    this.playing = true;
    if (this.playlist.getCurrentSong() == -1)
      this.playing = this.playlist.setCurrentSong(0);
      try {
        InputStream stream = new FileInputStream("/home/han/Workspace/CARREIRA_COMECA_AQUI/zxPlayer/audio-source/LUCKHAOS.mp3");
        this.player = new Player(stream);
        this.player.play();
      } catch (Exception e) {
        System.out.println( e.getMessage() );
      }
  }

  public void pause() {
    this.playing = false;
  }

}