package zxPlayer.player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Playlist {
  private List<String> songsList;
  private int currentSong;

  public Playlist() {
    this.songsList = null;
    this.currentSong = -1;
  }

  public String getCurrentSongName() {
    if (this.currentSong != -1)
      return this.songsList.get(currentSong);
    return null;
  }
  
  public List<String> getAllSongNames() {
    return this.songsList;
  }
  
  public int getCurrentSong() {
    return this.currentSong;
  }

  public boolean isEmpty() {
    return this.songsList.isEmpty();
  }

  public boolean setCurrentSong(int a_song_index) {
    if ( (this.songsList.size() >= a_song_index) && ( -1 <= a_song_index)) {
      this.currentSong = a_song_index;
      return true;
    }
    this.currentSong = -1;
    return false;
  }

  public void add(String a_path_song) {
    // this.songsList.add(a_song);
  }

  public void remove(String a_path_song) {
    //this.songsLists.remove(a_song);
  }

  public void clear() {
    this.songsList.clear();
    this.currentSong = -1;
  }
  public void readFrom(String directory) {
    try(Stream<Path> walk = Files.walk(Paths.get(directory))) {
      this.songsList = walk
        .map(x -> x.toFile().toString())
        .filter(x -> x.toString().endsWith(".mp3"))
        .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}