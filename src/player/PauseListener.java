package player;

import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class PauseListener extends PlaybackListener {
  public PlaybackEvent pauseEvent;

  public void playbackFinished(PlaybackEvent event) {
    pauseEvent = event;
  }
  
  public PlaybackEvent getEvent() {
    return pauseEvent;
  }
}