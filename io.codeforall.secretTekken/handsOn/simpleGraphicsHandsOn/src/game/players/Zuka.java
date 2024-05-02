package game.players;

import game.Game;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Zuka extends Player{
    public void playerStart(Game game, Player player, Player2 player2) {
        this.player2=player2;
        this.player=player;
        this.game = game;
        this.playerPic = new Picture(300, 260, "background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_IDLE01.png");
        this.playerHitBox = new Rectangle(370, 400, 70, 140);
        this.health = new Rectangle(160, 92, 450, 36);
        this.specialBar = new Rectangle(200, 134, 0, 20);
        specialBar.setColor(Color.YELLOW);
        specialBar.fill();
        health.setColor(Color.RED);
        health.fill();
        playerPic.draw();
    }

    public void moveLeft() {
        if (!isDead) {
            if (playerHitBox.getX() > 120) {
                previousKey = KeyboardEvent.KEY_A;
                playerPic.load("background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_IDLE02.png");
                playerHitBox.translate(-50, 0);
                playerPic.translate(-50, 0);
                previousAction = 0;
            }
        }
    }
    public void moveRight() {
        if (!isDead) {
            if (player2.getPlayerHitBox().getX() >= playerHitBox.getX() + 70) {
                previousKey = KeyboardEvent.KEY_D;
                playerPic.load("background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_IDLE01.png");
                playerHitBox.translate(50, 0);
                playerPic.translate(50, 0);

                previousAction = 0;
            }
        }
    }
    public void attackUp() {
        if (!isDead) {
            if (previousAction != KeyboardEvent.KEY_1) {
                playerPic.load("background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_ATTACK01.png");
                game.setAttack(new Rectangle(playerPic.getMaxX() - 300, playerPic.getMaxY() / 2 + 100, 100, 100));
                setPreviousKey(KeyboardEvent.KEY_1);
                blockCount = 0;
            }
        }
    }

    public void attackSpecial() {
        if (!isDead) {
            if (!specialUsed && (player2.getHp()<=50|| player2.getHp() <= 950 && player2 instanceof Rolo)) { //FALTAVA ISTO
                specialUsing = true;
                special = new Picture(10, 10, "background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_SPECIAL01.png");
                special.draw();
                if (player2.getHp() <= 50 ||  (player2.getHp() <= 950 && player2 instanceof Rolo)) {
                    specialUsed = true;
                    specialBar.delete();
                    setPreviousKey(KeyboardEvent.KEY_D);
                    if (player2.getBlocking()) {
                        player2.takeHp(20);
                        if (!(player2 instanceof Rolo)) {
                            player2.getHealth().translate(-46, 0);
                            player2.getHealth().grow(-46, 0);
                        }
                    } else {
                        player2.takeHp(40);
                        if (!(player2 instanceof Rolo)) {
                        player2.getHealth().translate(-92, 0);
                        player2.getHealth().grow(-92, 0);}
                    }
                }
                if (player2.getHp() <= 0) {
                    player2.setDeath();
                }
            }
        }
    }
    public void refresh() throws InterruptedException {
        if (deathGround) {
            playerPic.load("background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_DEATH04.png");
        } else if (isDead) {
            deathGround = true;
            String[] deathAnimation = {"background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_DEATH01.png", "background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_DEATH02.png", "background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_DEATH03.png", "background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_DEATH04.png"};
            for (String s : deathAnimation) {
                Thread.sleep(delay);
                playerPic.load(s);
            }
            super.lifes--;
        } else if (specialUsing) {
            specialUsing = false;
            String[] specialAnimation = {"background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_SPECIAL01.png", "background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_SPECIAL02.png"};
            for (String s : specialAnimation) {
                Thread.sleep(delay);
                special.load(s);
            }
            special.delete();
        } else if (blocking) {
            if (blockCount > 1) {
                String[] idle = {"background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_IDLE01.png", "background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_IDLE02.png"};
                for (String s : idle) {
                    Thread.sleep(delay);
                    playerPic.load(s);
                }
            } else {
                String[] blockingAnimation = {"background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_BLOCK01.png", "background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_BLOCK02.png"};
                for (String s : blockingAnimation) {
                    Thread.sleep(delay);
                    playerPic.load(s);
                }
            }
        } else if (previousKey == KeyboardEvent.KEY_D || previousKey == KeyboardEvent.KEY_A) {
            String[] idle = {"background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_IDLE01.png", "background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_IDLE02.png"};
            for (String s : idle) {
                Thread.sleep(delay);
                playerPic.load(s);
            }
        }
        //METER AQUI ARRAY CORRESPONDENTE AO
        else if (previousKey == KeyboardEvent.KEY_1) {
            if (previousAction == KeyboardEvent.KEY_1) {
                String[] idle = {"background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_IDLE01.png", "background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_IDLE02.png"};
                for (String s : idle) {
                    Thread.sleep(delay);
                    playerPic.load(s);
                }
            } else {
                previousAction = KeyboardEvent.KEY_1;
                String[] thisAttack = {"background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_ATTACK01.png", "background/finalgame/backgrounds/characters/ESQUERDA/ZUCAFINAL/ZUCA_ATTACK02.png"};
                for (String s : thisAttack) {
                    Thread.sleep(delay);
                    playerPic.load(s);
                    // previousKey=KeyboardEvent.KEY_A;
                }
            }
        }
    }

}
