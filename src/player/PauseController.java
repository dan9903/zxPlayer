package player;

import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class PauseController extends PlaybackListener {
  public PlaybackEvent pauseEvent;

  public void playbackStarted(PlaybackEvent event) {
    System.out.println("playbackstaart");
  }

  public void playbackFinished(PlaybackEvent event) {
    pauseEvent = event;
  }
  
  public PlaybackEvent getEvent() {
    return pauseEvent;
  }
}