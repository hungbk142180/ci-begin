package base;

import base.physics.Physics;
import base.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
//    BufferedImage image;
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    public static ArrayList<GameObject> newGameObjects = new ArrayList<>();

    public static BufferedImage backBuffer = new BufferedImage(Settings.SCREEN_WITDH,
                                                Settings.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    public static Graphics backBufferGraphics = backBuffer.createGraphics();

    //    public static Enemy createEnemy() {
//        Enemy newEnemy = new Enemy();
//        gameObjects.add(newEnemy);
//        return newEnemy;
//    }

    //create(classname) >> instance classname
    public static <E extends GameObject> E create(Class<E> childClass) {
        //Class E = new Class(GameObject)
        try {
            GameObject newGameObject = childClass.newInstance();
            newGameObjects.add(newGameObject);
            return (E)newGameObject;
        } catch (Exception e) {
            return null;
        }
    }

    public static <E extends GameObject> E recycle(Class<E> childClass) {
        //1. Kiểm tra có gameObject thỏa mãn yêu cầu (go.isActive == false && go instanceOf childClass)ko
        //  - Có: thì dùng lại
        //  - Ko có: thì tạo mới
        //2. Return gameObject
//        for(int i = 0; i < gameObjects.size(); i++) {
//            GameObject go = gameObjects.get(i);
        for(GameObject go: gameObjects) {
            if(!go.isActive && go.getClass().isAssignableFrom(childClass)) {
                go.isActive = true;
                return (E)go;
            }
        }
        return create(childClass);
    }

    public static <E extends GameObject> E intersect(Class<E> childClass, Physics physics) {
//        for(int i = 0; i < gameObjects.size(); i++) {
//            GameObject go = gameObjects.get(i);
        for(GameObject go : gameObjects) {
            if(go.isActive && childClass.isAssignableFrom(go.getClass()) && go instanceof Physics) {
                Physics physicsGo = (Physics) go;
                boolean intersected = physics.getBoxCollider().intersect(physicsGo, (GameObject) physics );
                if (intersected) {
                    return (E) physicsGo;
                }
            }
        }
        return null;
    }

    public static void runAll() {
//        for(int i = 0; i < gameObjects.size(); i++) {
//            GameObject go = gameObjects.get(i);
        for(GameObject go: gameObjects) /*For perfomance purpose only*/ {
            if(go.isActive) {
                go.run();
            }
        }

        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();

        System.out.println(gameObjects.size());
    }

//    public static void renderAll(Graphics g) {
////        for(int i = 0; i < gameObjects.size(); i++) {
////            GameObject go = gameObjects.get(i);
//        for(GameObject go: gameObjects) {
//            if(go.isActive) {
//                go.render(g);
//            }
//        }
//        gameObjects.addAll(newGameObjects);
//        newGameObjects.clear();
//    }

    public static void renderAllToBackBuffer() {
        for (GameObject go: gameObjects) {
            if(go.isActive) {
                go.render(backBufferGraphics);
            }
        }
    }

    public static void renderBackBufferToGame(Graphics g) {
        g.drawImage(backBuffer, 0,0, null);
    }

    public Renderer renderer;
    public Vector2D position;
    public boolean isActive;
    public Vector2D anchor;

    public GameObject() {
        this.isActive = true;
        this.anchor = new Vector2D(0.5f, 0.5f);
    }

    public void destroy() {
        this.isActive = false;
    }

    public void run() {

    }

    public void render(Graphics g) {
        if(this.renderer != null) {
            this.renderer.render(g, this);
        }
//        g.drawImage(this.image, (int)this.position.x, (int)this.position.y, null);
    }
}
