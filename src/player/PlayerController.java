package player;

import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.player.advanced.AdvancedPlayer;

enum Statuses { PLAYING, PAUSED, STOPPED }

public class PlayerController implements IPlayer, Runnable {
  private Playlist playlist;
  private AdvancedPlayer player;
  private Thread playerThread;
  private PauseController pc;
  private Statuses status;

  public PlayerController(Playlist a_playlist) {
    playlist = a_playlist;
    status = Statuses.STOPPED;
    playerThread = null;
    pc = new PauseController();
  }

  public void run() {
    createPlayer();
  }
  
  public boolean isPlaying() {
    return status == Statuses.PLAYING;
  }

  public void next() {
    if(status == Statuses.PLAYING || status == Statuses.PAUSED) {
      if (!playlist.isEmpty()){
        int current = playlist.getCurrentSong();
        status = playlist.setCurrentSong(++current) ? Statuses.PLAYING : Statuses.STOPPED;
      }
    }
  }

  public void previous() {
    if(status == Statuses.PLAYING || status == Statuses.PAUSED) {
      if (!playlist.isEmpty()){
        int current = playlist.getCurrentSong();
        status = playlist.setCurrentSong(--current)? Statuses.PLAYING: Statuses.STOPPED;
      }
    }
  }

  public void play(){
    if (status != Statuses.PLAYING) {
      if (!playlist.isEmpty())
        if (playlist.getCurrentSong() == -1 )
          playlist.setCurrentSong(0);
      createPlayThread();
    }
  }

  public void pause() {
    if (status == Statuses.PLAYING) {
      player.stop();
      status = Statuses.PAUSED;
    }
  }      
  
  public void stop() {
    player.close();
    playerThread.interrupt();
    playerThread = null;
    player = null;
    playlist.clear();
    status = Statuses.STOPPED;
  }

  private void createPlayThread() {
    try {
      playerThread = new Thread(this);
      playerThread.start();
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
  
  private void createPlayer() {
    try {
      String path = playlist.getCurrentSongName();
      InputStream fis = new FileInputStream(path);
      player = new AdvancedPlayer(fis);
      player.setPlayBackListener(pc);
      fis = null; 
      if (status == Statuses.STOPPED) {
          status = Statuses.PLAYING;
          player.play();
      } else {
          status = Statuses.PLAYING;
          int aa = pc.getEvent().getFrame();
          player.play(aa, aa+ 1000 );
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}