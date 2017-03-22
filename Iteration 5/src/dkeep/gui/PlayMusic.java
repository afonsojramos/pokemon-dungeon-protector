package dkeep.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class PlayMusic {
	private MediaPlayer mediaPlayer;
	
	PlayMusic(Media media){
		@SuppressWarnings("unused")
		JFXPanel fxPanel = new JFXPanel();
		
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.20);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		    	   mediaPlayer.seek(Duration.ZERO);
		       }
		   });
	}
	
	void play(){
		mediaPlayer.play();
	}
}
