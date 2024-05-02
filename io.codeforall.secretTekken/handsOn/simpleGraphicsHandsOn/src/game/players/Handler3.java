package game.players;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Handler3 implements KeyboardHandler {
    Menu menu;
    public Keyboard keyboard;

    public Handler3(Menu menu) {
        this.menu = menu;
        keyboard = new Keyboard(this);
        createKeyboardEvents();

    }

    public void createKeyboardEvents() {
        KeyboardEvent keyboardEventPlay = new KeyboardEvent();
        keyboardEventPlay.setKey(KeyboardEvent.KEY_P);
        keyboardEventPlay.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventPlay);

        KeyboardEvent keyboardEventControls = new KeyboardEvent();
        keyboardEventControls.setKey(KeyboardEvent.KEY_O);
        keyboardEventControls.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventControls);

        KeyboardEvent keyboardEventQuit = new KeyboardEvent();
        keyboardEventQuit.setKey(KeyboardEvent.KEY_I);
        keyboardEventQuit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventQuit);

        KeyboardEvent keyboardEventPlayerChoose = new KeyboardEvent();
        keyboardEventPlayerChoose.setKey(KeyboardEvent.KEY_E);
        keyboardEventPlayerChoose.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventPlayerChoose);

        KeyboardEvent keyboardEventPlayer2Choose = new KeyboardEvent();
        keyboardEventPlayer2Choose.setKey(KeyboardEvent.KEY_L);
        keyboardEventPlayer2Choose.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventPlayer2Choose);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_P:
                if (menu.startingScreen) {
                    menu.menu = true;
                    menu.startingScreen = false;
                    menu.background.load("background/finalgame/backgrounds/MENU.png");
                } else if (menu.menu) {
                    menu.menu = false;
                    menu.characters = new Picture(10, 10, "background/finalgame/backgrounds/PLAYERCHOOSEfinal.png");

                    menu.characters.draw();
                    menu.sound.menu1.stop();
                    menu.sound.PlayMenu2();
                    //RECTANGULO AQUI DE CHOOSE PLAYER
                    //player inicial para  cada um
                    menu.playerChoose = true;
                    menu.playerChooseSquare.setColor(Color.CYAN);
                    menu.playerChooseSquare.draw();
                    menu.player2ChooseSquare.setColor(Color.RED);
                    menu.player2ChooseSquare.draw();
                    return;

                } else if (menu.playerChoose) {
                    menu.background.delete();
                    menu.sound.menu2.stop();
                    menu.game.setGame(menu.player, menu.player2);
                    menu.playing = true;
                    menu.playerChoose = false;
                    return;

                } else if (menu.playing) {
                    if (menu.player.isDead || menu.player2.isDead) {
                        if(menu.victoryScreen){
                            System.exit(1);
                        }
                        if(menu.player.lifes<=0){
                         menu.menuVictory = new Picture(10,10,"background/finalgame/backgrounds/PLAYER2WON.png");
                         menu.menuVictory.draw();
                         menu.victoryScreen=true;
                        }
                        else if(menu.player2.lifes<=0){
                            menu.menuVictory = new Picture(10,10,"background/finalgame/backgrounds/PLAYER1WON.png");
                            menu.menuVictory.draw();
                            menu.victoryScreen=true;
                        }
                        else {
                            menu.game.resetGame();
                        }
                    }
                }
                break;
            case KeyboardEvent.KEY_O:
                if (menu.menu) {
                    menu.background.load("background/finalgame/backgrounds/CONTROLS.png");
                    menu.isControls = true;
                    menu.menu = false;
                    break;
                }
            case KeyboardEvent.KEY_I:
                if (menu.isControls) {
                    menu.isControls = false;
                    menu.menu = true;
                    menu.background.load("background/finalgame/backgrounds/MENU.png");

                } else {
                    menu.sound.menu1.stop();
                    menu.sound.menu2.stop();
                    menu.sound.round1.stop();
                    System.exit(1);
                }
                break;
            case KeyboardEvent.KEY_E:
                if (menu.getPlaying()) {
                    return;
                }
                if (menu.playerChoose) {
                    if (menu.playerChooseSquare.getX() > 350 && menu.playerChooseSquare.getY() < 290) {
                        menu.playerChooseSquare.translate(-100, 100);
                    } else if (menu.playerChooseSquare.getX() > 440 && menu.playerChooseSquare.getY() > 350) {
                        menu.playerChooseSquare.translate(-200, -100);
                    } else {
                        menu.playerChooseSquare.translate(100, 0);
                    }
                }
                //tambem aqui a picture load +delete
                for (int i = 0; i < menu.players.length; i++) {
                    if (menu.players[i] == menu.player) {
                        if (i == menu.players.length - 1) {
                            menu.player = menu.players[0];
                            return;
                        }
                        menu.player = menu.players[i + 1];
                        return;
                    }

                }
                break;

            case KeyboardEvent.KEY_L:
                if (menu.getPlaying()) {
                    return;
                }
                if (menu.playerChoose) {
                    if (menu.player2ChooseSquare.getX() < 880 && menu.player2ChooseSquare.getY() < 290) {
                        menu.player2ChooseSquare.translate(100, 100);
                    } else if (menu.player2ChooseSquare.getX() < 770 && menu.player2ChooseSquare.getY() == 385) {
                        menu.player2ChooseSquare.translate(200, -100);
                    } else {
                        menu.player2ChooseSquare.translate(-100, 0);
                    }
                }
                for (int i = 0; i < menu.players2.length; i++) {
                    if (menu.players2[i] == menu.player2) {
                        if (i == menu.players2.length - 1) {
                            menu.player2 = menu.players2[0];
                            return;
                        }
                        menu.player2 = menu.players2[i + 1];
                        return;
                    }
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
