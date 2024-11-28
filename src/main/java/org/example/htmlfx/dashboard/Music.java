package org.example.htmlfx.dashboard;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
    private MediaPlayer mediaPlayer;

    public Music() {
        Media media = new Media("https://www.youtube.com/watch?v=TGHp6LQzvPY");
        this.mediaPlayer = new MediaPlayer(media);
    }

    public void play() {
        mediaPlayer.play();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public double getVolume() {
        return mediaPlayer.getVolume();
    }

    public boolean isPlaying() {
        return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

    public void dispose() {
        mediaPlayer.dispose();
    }
}

