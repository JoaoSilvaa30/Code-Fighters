package game.players;

import game.Game;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player2 {
    public Picture playerPic;
    public Rectangle playerHitBox;
    public Player2 player2;
    public Player player;
    public Game game;
    public int hp = 100;
    public Rectangle health;
    public Rectangle specialBar;
    public int previousKey = KeyboardEvent.KEY_LEFT;
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

    public void playerStart(Game game, Player2 player2, Player player){
    }
    public boolean getSpecialCharacter(){
        return specialCharacter;
    }

    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount() {
        blockCount++;
    }

    public Rectangle getPlayerHitBox() {
        return playerHitBox;
    }

    public void takeHp(int hitPoints) {
        this.hp -= hitPoints;
        //load image rectangulo
    }

    public Rectangle getHealth() {
        return health;
    }

    public void setDeath() {
        isDead = true;
    }

    public int getHp() {
        return hp;
    }


    public void moveLeft() {


    }

    public void moveRight() {

    }

    public boolean getBlocking() {
        return blocking;
    }

    public void setBlocking(boolean set) {
        blocking = set;
    }


    public void attackUp() {

    }

    public void attackSpecial() {

    }


    public void setPreviousKey(int key) {
        previousKey = key;
    }

    public Rectangle getSpecialBar() {
        return specialBar;
    }


    public void refresh() throws InterruptedException {

    }
}
