package dkeep.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
/**
 * PlayMusic.java - Class that deals with sounds
 */
public class PlayMusic {
	public MediaPlayer mediaPlayer;
	/**
	 * Processing of sound files
	 * @param media
	 */
	public PlayMusic(Media media){
		@SuppressWarnings("unused")
		JFXPanel fxPanel = new JFXPanel();
		
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.20);
	}
	/**
	 * Sets sound to play continuously
	 */
	public void playContinuous(){
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		    	   mediaPlayer.seek(Duration.ZERO);
		       }
		   });
		mediaPlayer.play();
	}
	/**
	 * Sets sound to only play once
	 */
	public void playSound(){
		mediaPlayer.play();
	}
}
