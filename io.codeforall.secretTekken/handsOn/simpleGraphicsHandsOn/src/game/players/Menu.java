package game.players;

import game.*;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Menu {
    Picture background;
    Picture controls;
    Picture characters;
    Game game;

    boolean playerChoose = false;
    boolean startingScreen = true;
    boolean menu = false;
    boolean isControls;
    boolean playing;
    boolean victoryScreen;
    David david = new David();
    Rolo rolo = new Rolo();
    Mendanha mendanha = new Mendanha();
    Mike mike = new Mike();
    Ruben ruben = new Ruben();
    Aidos aidos = new Aidos();
    Bernard bernard = new Bernard();
    Mariana mariana = new Mariana();
    Nuno nuno = new Nuno();
    Zuka zuka = new Zuka();

    Sound sound = new Sound();
    Player[] players = {nuno, zuka, mariana, bernard, aidos};
    Player2[] players2 = {rolo, david, mike, mendanha, ruben};
    Player player = nuno;
    Player2 player2 = rolo;
    Rectangle playerChooseSquare = new Rectangle(265,285,70,70);
    Rectangle player2ChooseSquare= new Rectangle(960,285,70,70);
    Picture menuVictory;

    public Menu() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Handler3 handler = new Handler3(this);
        game = new Game();
        background = new Picture(10, 10, "background/finalgame/backgrounds/FIRSTSCREEN.png");
        background.draw();
        sound.PlayMenu1();
        sound.decreaseVolume(sound.menu1, 30.0f);
        sound.decreaseVolume(sound.menu2,30.0f);
    }

    public boolean getPlaying() {
        return playing;
    }

    public Player player() {
        return player;
    }

    public void refresh() throws InterruptedException {
        game.start();
    }

    public Player2 player2() {
        return player2;
    }


}
