package game;

import game.general.Background;
import game.players.*;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {
    private Background background;
    private Player player1;
    private Player2 player2;
    private Rectangle attack;
    private Picture healthBar;
    private int blockCount;

    Sound sound = new Sound();

    public Game() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
    }

    public void setGame(Player player1, Player2 player2) {
        background = new Background();
        this.player1 = player1;
        this.player2 = player2;
        Handler handler = new Handler(player1);
        Handler2 handler2 = new Handler2(player2);
        healthBar = new Picture(10, 10, "background/finalgame/backgrounds/HEALTHBAR.png");
        player2.playerStart(this, this.player2, this.player1);
        player1.playerStart(this, this.player1, this.player2);
        sound.PlayRound1();
        healthBar.draw();
    }
    public void resetGame(){
        if(player1.lifes==0 || player2.lifes==0){
            System.exit(1);
        }
        player1.hp=100;
        player2.hp=100;
        player1.deathGround=false;
        player2.deathGround=false;
        player2.isDead=false;
        player1.isDead=false;
        player1.specialUsed=false;
        player2.specialUsed=false;
        player1.health.delete();
        player1.specialBar.delete();
        player1.health = new Rectangle(160, 92, 450, 36);
        player1.specialBar = new Rectangle(200, 134, 0, 20);
        player2.health.delete();
        player2.specialBar.delete();
        player2.specialBar = new Rectangle(1095, 134, 0, 20);
        player2.health = new Rectangle(685, 92, 450, 36);
        player1.specialBar.setColor(Color.CYAN);
        player2.specialBar.setColor(Color.CYAN);
        player1.health.setColor(Color.RED);
        player2.health.setColor(Color.RED);
        player2.specialBar.fill();
        player2.health.fill();
        player1.specialBar.fill();
        player1.health.fill();
        healthBar.delete();
        healthBar.load("background/finalgame/backgrounds/HEALTHBAR.png");
        healthBar.draw();
    }

    public void setAttack(Rectangle attack) {
        if (player2.getPlayerHitBox().getX() > attack.getX() + 100) {
        } else if (!player2.getBlocking() || player2.getBlockCount() > 1) {
            player2.takeHp(10);
            if (player2.getHp() >= 50) {

                player1.getSpecialBar().grow(25, 0);
                player1.getSpecialBar().translate(25, 0);
            }
            if (player2.getHp() <= 0) {
                player2.setDeath();
            }
            if (!(player2 instanceof Rolo)) {
                player2.getHealth().grow(-23, 0);
                player2.getHealth().translate(-23, 0);
            }
        } else if (player2.getBlocking()) {
            player2.setBlockCount();
        }
        if (player1.getPlayerHitBox().getX() < attack.getX() - 100) {
        } else if (!player1.getBlocking() || player1.getBlockCount() > 1) {
            player1.takeHp(10);
            if (player1.getHp() >= 50) {
                player2.getSpecialBar().grow(25, 0);
                player2.getSpecialBar().translate(-25, 0);
            }
            if (player1.getHp() <= 0) {
                player1.setDeath();
            }
            if (!(player1 instanceof Nuno)) {
                player1.getHealth().grow(-23, 0);
                player1.getHealth().translate(-23, 0);
            }

        } else if (player1.getBlocking()) {
            player1.setBlockCount();
        }
    }

    public void start() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //playerRefresh();
        //player1.moveStreakReset()
        while (true) {
            executorService.execute(() -> {
                try {
                    player1.refresh();
                    //player1.healthRefresh();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            executorService.execute(() -> {
                try {
                    player2.refresh();

                    // player2.healthRefresh();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


            // You can add a sleep here if needed to avoid high CPU usage
            try {
                Thread.sleep(200); // Adjust the sleep time as needed

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

