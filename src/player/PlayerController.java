package player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.player.advanced.AdvancedPlayer;

enum Statuses { PLAYING, PAUSED, STOPPED }

public class PlayerController implements IPlayer, Runnable {
  private final Playlist playlist;
  private AdvancedPlayer player;
  private Thread playerThread;
  private final PauseListener pl;
  private Statuses status;

  public PlayerController(final Playlist a_playlist) {
    playlist = a_playlist;
    status = Statuses.STOPPED;
    playerThread = null;
    pl = new PauseListener();
  }

  public void run() {
    try {
      String path = playlist.getCurrentSongName();
      InputStream fis = new FileInputStream(path);
      player = new AdvancedPlayer(fis);
      player.setPlayBackListener(pl);
      
      if (status == Statuses.STOPPED) {
        status = Statuses.PLAYING;
        player.play();
      } else {
        status = Statuses.PLAYING;
        int start  = pl.getEvent().getFrame();
        int end = fis.available();
        player.play(start, end);
      }
      fis = null;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean isPlaying() {
    return status == Statuses.PLAYING;
  }

  public void next() {
    // to try change the song to forward
    changeSong(true);
  }

  public void previous() {
    // to change the song to backward
    changeSong(false);
  }

  public void play() {
    if (status != Statuses.PLAYING) {
      if (!playlist.isEmpty())
        if (playlist.getCurrentSong() == -1)
          playlist.setCurrentSong(0);
      createPlayThread();
    }
  }

  public void pause() {
    if (status == Statuses.PLAYING) {
      deletePlayerThread();
      status = Statuses.PAUSED;
    }
  }

  public void stop() {
    deletePlayerThread();
    playlist.clear();
    status = Statuses.STOPPED;
  }

  private void changeSong(final boolean forward) {
    if (status == Statuses.STOPPED && playlist.isEmpty())
      return;

    final int current = forward ? playlist.getCurrentSong() + 1 : playlist.getCurrentSong() - 1;
    if (playlist.setCurrentSong(current)) {
      deletePlayerThread();
      status = Statuses.STOPPED;
      play();
    }
  }

  private void deletePlayerThread() {
    player.stop();
    player.close();
    playerThread.interrupt();
    playerThread = null;
    player = null;

  }

  private void createPlayThread() {
    try {
      playerThread = new Thread(this);
      playerThread.start();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }
}