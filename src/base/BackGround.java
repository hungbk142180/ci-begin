package base;

import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class BackGround extends GameObject {
    public BackGround() {
        super();
        // Vẫn cần load image vì có sử dụng thuộc tính getHeight của image.
        BufferedImage image = SpriteUtils.loadImage("assets/images/background/0.png");
//        this.image = image;
        this.renderer = new SingleImageRenderer("assets/images/background/0.png");
        this.position = new Vector2D(0, -(image.getHeight() - Settings.SCREEN_HEIGHT) /*SCREEN_HEIGHT*/);
        this.anchor.set(0, 0);
    }

    @Override
    public void run() {
        if (this.position.y >= 0) {
            return;
        } else {
            this.position.y += 10 /*SPEED*/;
        }
    }


}
