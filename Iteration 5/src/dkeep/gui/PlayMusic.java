package dkeep.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class PlayMusic {
	public MediaPlayer mediaPlayer;
	/**
	 * processamento de ficheiros de som
	 * @param media
	 */
	public PlayMusic(Media media){
		@SuppressWarnings("unused")
		JFXPanel fxPanel = new JFXPanel();
		
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.20);
	}
	/**
	 * altera condicao de play das musicas
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
	 * play das musicas
	 */
	public void playSound(){
		mediaPlayer.play();
	}
}
