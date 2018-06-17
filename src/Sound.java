package src;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    public void s_effect(int n) {

        File win = new File("./sound/win.wav");
        File lose = new File("./sound/lose.wav");
        File skill_w = new File("./sound/skill_w.wav");
        File skill_b = new File("./sound/skill_b.wav");
        File capture = new File("./sound/capture.wav");
        File level = new File("./sound/level_up.wav");
        File item = new File("./sound/item.wav");

        switch (n) {

            case 1:
                try {
                    AudioInputStream stream = AudioSystem.getAudioInputStream(win);
                    Clip clip = AudioSystem.getClip();
                    clip.open(stream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 2:
                try {
                    AudioInputStream stream = AudioSystem.getAudioInputStream(lose);
                    Clip clip = AudioSystem.getClip();
                    clip.open(stream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 3:
                try {
                    AudioInputStream stream = AudioSystem.getAudioInputStream(skill_w);
                    Clip clip = AudioSystem.getClip();
                    clip.open(stream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 4:
                try {
                    AudioInputStream stream = AudioSystem.getAudioInputStream(skill_b);
                    Clip clip = AudioSystem.getClip();
                    clip.open(stream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 5:
                try {
                    AudioInputStream stream = AudioSystem.getAudioInputStream(capture);
                    Clip clip = AudioSystem.getClip();
                    clip.open(stream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 6:
                try {
                    AudioInputStream stream = AudioSystem.getAudioInputStream(level);
                    Clip clip = AudioSystem.getClip();
                    clip.open(stream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 7:
                try {
                    AudioInputStream stream = AudioSystem.getAudioInputStream(item);
                    Clip clip = AudioSystem.getClip();
                    clip.open(stream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }

}
