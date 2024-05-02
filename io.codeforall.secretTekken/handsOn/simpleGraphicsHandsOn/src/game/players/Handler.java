package game.players;

import game.players.Player;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.security.Key;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Handler implements KeyboardHandler {

    public Keyboard keyboard;
    private final ExecutorService executorService;
    private Player player;

    public Handler(Player player) {
        executorService = Executors.newFixedThreadPool(1);
        keyboard = new Keyboard(this);
        this.player = player;
        createKeyboardEvents();
    }

    public void createKeyboardEvents() {
        KeyboardEvent keyboardEventLeft = new KeyboardEvent();
        keyboardEventLeft.setKey(KeyboardEvent.KEY_A);
        keyboardEventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventLeft);

        KeyboardEvent keyboardEventRight = new KeyboardEvent();
        keyboardEventRight.setKey(KeyboardEvent.KEY_D);
        keyboardEventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventRight);

        KeyboardEvent keyboardEventUp = new KeyboardEvent();
        keyboardEventUp.setKey(KeyboardEvent.KEY_W);
        keyboardEventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventUp);

        KeyboardEvent keyboardEventDown = new KeyboardEvent();
        keyboardEventDown.setKey(KeyboardEvent.KEY_3);
        keyboardEventDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventDown);

        KeyboardEvent keyboardEventEnter = new KeyboardEvent();
        keyboardEventEnter.setKey(KeyboardEvent.KEY_1);
        keyboardEventEnter.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventEnter);


    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        executorService.execute(() -> {
            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_D:
                    player.moveRight();
                   player.setBlocking(false);
                    break;
                case KeyboardEvent.KEY_A:
                    player.moveLeft();
                    player.setBlocking(false);
                    break;
                case KeyboardEvent.KEY_W:
                    player.setBlocking(true);
                    break;
                case KeyboardEvent.KEY_3:
                    player.attackSpecial();
                    break;
                ///////////////////////////////
                case KeyboardEvent.KEY_1:
                    player.attackUp();
                    player.setBlocking(false);
                    break;
            }
        });
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
