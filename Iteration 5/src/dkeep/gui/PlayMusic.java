package dkeep.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayMusic {
	private MediaPlayer mediaPlayer;
	
	PlayMusic(Media media){
		@SuppressWarnings("unused")
		JFXPanel fxPanel = new JFXPanel();
		
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.20);
	}
	
	void play(){
		mediaPlayer.play();
	}
	
}
