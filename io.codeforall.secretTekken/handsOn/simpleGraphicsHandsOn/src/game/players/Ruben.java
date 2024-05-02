package game.players;

import game.Game;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Ruben extends Player2 {
    public void playerStart(Game game, Player2 player2, Player player) {
        this.player2 = player2;
        this.player = player;
        this.game = game;
        this.playerPic = new Picture(500, 260, "background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_IDLE01.png");
        this.health = new Rectangle(685, 92, 450, 36);
        this.specialBar = new Rectangle(1095, 134, 0, 20);
        this.playerHitBox = new Rectangle(780, 400, 70, 140);
        specialBar.setColor(Color.YELLOW);
        specialBar.fill();
        health.setColor(Color.RED);
        health.fill();
        playerPic.draw();

    }

    public void moveLeft() {
        if (!isDead) {
            if (player.getPlayerHitBox().getX() + 70 <= playerHitBox.getX()) {
                previousKey = KeyboardEvent.KEY_LEFT;
                playerPic.load("background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_IDLE01.png");
                playerHitBox.translate(-50, 0);
                playerPic.translate(-50, 0);
                previousAction = 0;
            }
        }

    }

    public void moveRight() {
        if (!isDead) {
            if (playerHitBox.getX() < 1100) {
                previousKey = KeyboardEvent.KEY_RIGHT;
                playerPic.load("background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_IDLE02.png");
                playerHitBox.translate(50, 0);
                playerPic.translate(50, 0);
                previousAction = 0;
            }
        }
    }

    public void attackUp() {
        if (!isDead) {
            if (previousKey != KeyboardEvent.KEY_SPACE) {
                playerPic.load("background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_ATTACK1.png");
                game.setAttack(new Rectangle(playerHitBox.getX() - 150, playerHitBox.getY() / 2 + 190, 100, 100));
                setPreviousKey(KeyboardEvent.KEY_SPACE);
                blockCount = 0;
            }
        }
    }

    public void attackSpecial() {
        if (!isDead) {
            if (!specialUsed && (player.getHp() <= 50 || player.getHp() <= 950 && player instanceof Nuno)) {
                specialUsing = true;
                special = new Picture(10, 10, "background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_SPECIAL01.png");
                special.draw();
                if (player.getHp() <= 50 || (player.getHp() <= 950 && player instanceof Nuno)) {
                    specialUsed = true;
                    specialBar.delete();
                    setPreviousKey(KeyboardEvent.KEY_RIGHT);
                    if (player.getBlocking()) {
                        player.takeHp(20);
                        if (!(player instanceof Nuno)) {
                            player.getHealth().grow(-46, 0);
                            player.getHealth().translate(46, 0);
                        }

                    } else {
                        player.takeHp(40);
                        if (!(player instanceof Nuno)) {
                            player.getHealth().grow(-92, 0);
                            player.getHealth().translate(92, 0);
                        }
                    }
                }
                if (player.getHp() <= 0) {
                    player.setDeath();
                }
            }
        }
    }

    public void refresh() throws InterruptedException {
        if (deathGround) {
            playerPic.load("background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_DEATH05.png");
        } else if (isDead) {
            deathGround = true;
            String[] deathAnimation = {"background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_DEATH01.png", "background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_DEATH02.png", "background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_DEATH03.png", "background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_DEATH04.png", "background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_DEATH05.png"};
            for (String s : deathAnimation) {
                Thread.sleep(delay);
                playerPic.load(s);
            }
            super.lifes--;
        } else if (specialUsing) {
            //COLOCAR IMAGEM ESPECIAL
            specialUsing = false;
            String[] specialAnimation = {"background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_SPECIAL01.png", "background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_SPECIAL02.png"};
            for (String s : specialAnimation) {
                Thread.sleep(delay);
                special.load(s);
            }
            special.delete();

        } else if (blocking) {
            if (blockCount > 1) {
                String[] idle = {"background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_IDLE01.png", "background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_IDLE02.png"};
                for (String s : idle) {
                    Thread.sleep(delay);
                    playerPic.load(s);
                }
            } else {
                String[] blockingAnimation = {"background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_BLOCK01.png"};
                for (String s : blockingAnimation) {
                    Thread.sleep(delay);
                    playerPic.load(s);
                }
            }
        } else if (previousKey == KeyboardEvent.KEY_RIGHT || previousKey == KeyboardEvent.KEY_LEFT || blockCount > 1) {
            String[] idle = {"background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_IDLE01.png", "background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_IDLE02.png"};
            for (String s : idle) {
                Thread.sleep(delay);
                playerPic.load(s);
            }
        }//METER AQUI ARRAY CORRESPONDENTE AO
        else if (previousKey == KeyboardEvent.KEY_SPACE) {
            if (previousAction == KeyboardEvent.KEY_SPACE) {
                String[] idle = {"background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_IDLE01.png", "background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_IDLE02.png"};
                for (String s : idle) {
                    Thread.sleep(delay);
                    playerPic.load(s);
                }
            } else {
                previousAction = KeyboardEvent.KEY_SPACE;
                String[] thisAttack = {"background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_ATTACK1.png", "background/finalgame/backgrounds/characters/DIREITA/RUBENFINAL/RUBEN_ATTACK2.png"};
                for (String s : thisAttack) {
                    Thread.sleep(delay);
                    playerPic.load(s);
                    // previousKey=KeyboardEvent.KEY_RIGHT;
                }
            }
        }


    }
}
