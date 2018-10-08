package game;

import base.*;
import base.enemy.Enemy;
import base.player.Player;
//import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
//import java.awt.image.BufferedImage;

public class GameCanvas extends JPanel {
    BackGround background ;
    Player player ;
//    public static ArrayList<Enemy> enemies;
//    public static ArrayList<PlayerBullet> playerBullets;

    public GameCanvas() {
        //Khởi tạo các game object
        this.background = GameObject.recycle(BackGround.class);
        this.player = GameObject.recycle(Player.class);
//        enemies = new ArrayList<>();
        Enemy enemy = GameObject.recycle(Enemy.class);
//        playerBullets = new ArrayList<>();
    }

    public void run() {
//        player.run();
//        background.run();
//        for (Enemy enemy: enemies) {
//            enemy.run();
//        }
//        for (PlayerBullet playerBullet: playerBullets) {
//            playerBullet.run();
//        }
        GameObject.runAll();
    }

    public void render(Graphics g) {
//        background.render(g);
//        player.render(g);
//        for (Enemy enemy: enemies) {
//            enemy.render(g);
//        }
//        for (PlayerBullet playerBullet: playerBullets) {
//            playerBullet.render(g);
//        }
        GameObject.renderAll(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.render(g);
    }
}
