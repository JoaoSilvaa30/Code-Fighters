package game;


import game.players.Menu;
import game.players.Player;
import game.players.Player2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
    Menu menu= new Menu();
        while(true){
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            //playerRefresh();
            //player1.moveStreakReset()
            if(menu.getPlaying()){
            while (true) {
                executorService.execute(() -> {
                    try {
                        menu.player().refresh();
                        //player1.healthRefresh();
                    } catch (InterruptedException e) {
                       // e.printStackTrace();
                    }
                });
                executorService.execute(() -> {
                    try {
                        menu.player2().refresh();
                            //player2.stanceReset();
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

        }}}
    }

