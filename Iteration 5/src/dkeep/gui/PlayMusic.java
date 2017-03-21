/*package dkeep.gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;

public class PlayMusic {
	
	public static MediaPlayer mediaPlayer;
	
	public void playSound(Media media){
		//JFXPanel fxPanel = new JFXPanel();
	}
	
	public void playMusic(){
		//JFXPanel fxPanel = new JFXPanel();
		
		//Media theme = new Media(new File("/Music.mp3").ToURI().toString());
		//Add a scene
        Group root = new Group();
        Scene scene = new Scene(root, 500, 200);

        Media pick = new Media("Music.mp3");
        MediaPlayer player = new MediaPlayer(pick);
        player.play();

        //Add a mediaView, to display the media. Its necessary !
        //This mediaView is added to a Pane
        MediaView mediaView = new MediaView(player);
        ((Group)scene.getRoot()).getChildren().add(mediaView);
        mediaView.setVisible(false);
	}
	
}*/
