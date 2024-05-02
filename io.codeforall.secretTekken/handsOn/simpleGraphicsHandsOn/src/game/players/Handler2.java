package game.players;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Handler2 implements KeyboardHandler {
    public Keyboard keyboard;
    private final ExecutorService executorService;
    private Player2 player2;

    public Handler2(Player2 player2) {
        executorService = Executors.newFixedThreadPool(1);
        keyboard = new Keyboard(this);
        this.player2 = player2;
        createKeyboardEvents();
    }

    public void createKeyboardEvents() {
        KeyboardEvent keyboardEventLeft = new KeyboardEvent();
        keyboardEventLeft.setKey(KeyboardEvent.KEY_LEFT);
        keyboardEventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventLeft);

        KeyboardEvent keyboardEventRight = new KeyboardEvent();
        keyboardEventRight.setKey(KeyboardEvent.KEY_RIGHT);
        keyboardEventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventRight);

        KeyboardEvent keyboardEventUp = new KeyboardEvent();
        keyboardEventUp.setKey(KeyboardEvent.KEY_UP);
        keyboardEventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventUp);

        KeyboardEvent keyboardEventBlock = new KeyboardEvent();
        keyboardEventBlock.setKey(KeyboardEvent.KEY_M);
        keyboardEventBlock.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventBlock);

        KeyboardEvent keyboardEventEnter = new KeyboardEvent();
        keyboardEventEnter.setKey(KeyboardEvent.KEY_SPACE);
        keyboardEventEnter.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventEnter);


    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        executorService.execute(() -> {

            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_RIGHT:
                    player2.moveRight();
                    player2.setBlocking(false);
                    break;
                case KeyboardEvent.KEY_LEFT:
                    player2.moveLeft();
                    player2.setBlocking(false);
                    break;
                case KeyboardEvent.KEY_UP:
                    player2.setBlocking(true);
                    break;
                case KeyboardEvent.KEY_M:
                    player2.attackSpecial();
                    break;
                case KeyboardEvent.KEY_SPACE:
                    player2.attackUp();
                    player2.setBlocking(false);

                    break;
            }
        });
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
