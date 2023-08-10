package org.wordlers.wordle.structure;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

@SuppressWarnings("unused")
public class MusicPlayer {

    public MediaPlayer player;
    private File currentlyPlaying;

    private boolean loop = false;
    private boolean disabled = false;
    private double volume = 1;

    public void play(File file) {
        this.currentlyPlaying = file;
        if (disabled) return;

        if (file == null || file.toURI().toString() == null) return;

        stop();
        player = new MediaPlayer(new Media(
            file.toURI().toString()
        ));

        player.setVolume(volume);
        player.setCycleCount(loop ? MediaPlayer.INDEFINITE : 1);
        player.play();
    }

    public void resume() {
        if (player == null) return;
        if (player.getStatus() != MediaPlayer.Status.PAUSED) return;

        player.play();
    }

    public void pause() {
        if (player == null) return;
        if (player.getStatus() != MediaPlayer.Status.PLAYING) return;

        player.pause();
    }

    public void stop() {
        if (player == null) return;
        if (player.getStatus() == MediaPlayer.Status.STOPPED) return;

        player.stop();
    }

    public boolean loop() {
        return loop;
    }

    public MusicPlayer loop(boolean loop) {
        this.loop = loop;
        if (this.player != null)
            this.player.setCycleCount(loop ? MediaPlayer.INDEFINITE : 1);
        return this;
    }

    public boolean disabled() {
        return disabled;
    }

    public MusicPlayer disable(boolean disabled) {
        this.disabled = disabled;
        if (disabled) stop();
        else play(currentlyPlaying);
        return this;
    }

    public double volume() {
        return volume;
    }

    public MusicPlayer volume(double volume) {
        this.volume = volume;
        if (this.player != null) this.player.setVolume(volume);
        return this;
    }
}
