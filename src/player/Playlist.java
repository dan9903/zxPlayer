package player;

import java.util.ArrayList;

public class Playlist {
  private ArrayList<String> songsLists;
  private int currentSong;

  public Playlist() {
    this.songsLists = new ArrayList<String>();
    this.currentSong = -1;
  }

  public String getCurrentSongName() {
    if (this.currentSong != -1)
      return this.songsLists.get(this.currentSong);
    return "";
  }
  
  public ArrayList<String> getAllSongNames() {
    return this.songsLists;
  }
  
  public int getCurrentSong() {
    return this.currentSong;
  }

  public boolean setCurrentSong(int a_song) {
    if ( (this.songsLists.size() >= a_song) && ( -1 <= a_song)) {
      this.currentSong = a_song;
      return true;
    }
    this.currentSong = -1;
    return false;
  }

  public void add(String a_song) {
    this.songsLists.add(a_song);
  }

  public void remove(String a_song) {
    this.songsLists.remove(a_song);
  }
}