package com.example.aerodromeApp.Model;

public class SoundModle {
    private  String soound_img,sound_sound,sound_title;

    public SoundModle(){

    }

    public SoundModle(String soound_img, String sound_sound, String sound_title) {
        this.soound_img = soound_img;
        this.sound_sound = sound_sound;
        this.sound_title = sound_title;
    }

    public String getSoound_img() {
        return soound_img;
    }

    public void setSoound_img(String soound_img) {
        this.soound_img = soound_img;
    }

    public String getSound_sound() {
        return sound_sound;
    }

    public void setSound_sound(String sound_sound) {
        this.sound_sound = sound_sound;
    }

    public String getSound_title() {
        return sound_title;
    }

    public void setSound_title(String sound_title) {
        this.sound_title = sound_title;
    }
}
