package game.general;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Background {
    private Picture background;
    private String[] backgroundArena = {"background/new/Layer 1.png", "background/new/Layer 2.png", "background/new/Layer 3.png", "background/new/Layer 4.png", "background/new/Layer 5.png"};
    private int delay = 200;
    public Background(){
        this.background=new Picture(10,10,"background/finalgame/backgrounds/arena1/BACKGROUND1.png");
        background.draw();
        Canvas.limitCanvasHeight(background.getHeight());
        Canvas.limitCanvasWidth(background.getWidth());

        }
        public void refresh() throws InterruptedException{
            for(int i=0; i<backgroundArena.length;i++){
                Thread.sleep(delay);
                background.load(backgroundArena[i]);
            }
        }
    }

