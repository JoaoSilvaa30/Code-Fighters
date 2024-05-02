package game.players;

import game.Game;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Player {
    public Picture playerPic;
    public Rectangle playerHitBox;
    public Player player;
    public Player2 player2;
    public Game game;
    public int hp = 100;
    public  Rectangle health;
    public Rectangle specialBar;
    public int previousKey = KeyboardEvent.KEY_D;
    public int previousAction;
    public boolean blocking;
    public boolean isDead;
    public int delay = 100;
    public int lifes = 3;
    public boolean deathGround;
    public int blockCount = 0;
    public boolean specialUsed = false;
    public Picture special;
    public boolean specialUsing = false;
    public boolean specialCharacter = false;

    public void playerStart(Game game, Player player, Player2 player2) {
    }
    public boolean getSpecialCharacter(){
        return specialCharacter;
    }

    public void moveLeft() {
    }

    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount() {
        blockCount++;
    }

    public void moveRight() {

    }

    public void attackUp() {

    }

    public void attackSpecial() {
    }

    public void setPlayer2(Player2 player2) {
        this.player2 = player2;
    }


    public void setPreviousKey(int key) {
        previousKey = key;
    }


    public Rectangle getPlayerHitBox() {
        return playerHitBox;
    }

    public void takeHp(int hp) {
        this.hp -= hp;
    }

    public int getHp() {
        return this.hp;
    }

    public boolean getBlocking() {
        return blocking;
    }

    public void refresh() throws InterruptedException {
    }

    public void setBlocking(boolean set) {
        blocking = set;
    }

    public void setDeath() {
        isDead = true;
    }

    public Rectangle getHealth() {
        return health;
    }

    public Rectangle getSpecialBar() {
        return specialBar;
    }
}
