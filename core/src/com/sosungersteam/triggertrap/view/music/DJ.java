package com.sosungersteam.triggertrap.view.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class DJ {
    public static DJ dj = null;
    public HashMap<String, Music> musicMap = new HashMap<>();
    public HashMap<String, Sound> soundMap = new HashMap<>();
    public Music playingMusic;

    private DJ() {

    }

    public static DJ get() {
        if (dj == null)
            dj = new DJ();
        return dj;
    }

    public void load() {
        musicMap.put("gameChill",
                Gdx.audio.newMusic(Gdx.files.internal("music/052. Uwa!! So HEATS!!.mp3")));
        musicMap.put("menu",
                Gdx.audio.newMusic(Gdx.files.internal("music/Nitro Fun - Cheat Codes.mp3")));

    }

    public void playMusic(String musicName) {
        if (playingMusic != null)
            playingMusic.pause();
        if (musicMap.containsKey(musicName)) {
            playingMusic = musicMap.get(musicName);
            playingMusic.play();
            playingMusic.setLooping(true);
            playingMusic.setVolume(0.1f);
        }

    }

    public void playSound(String soundName) {

    }

    public void setMusicVolume(float volume) {
        playingMusic.setVolume(volume);
    }
}
