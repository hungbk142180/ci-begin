package base.enemy;

import base.*;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.Player;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject implements Physics {
    BoxCollider collider;
    Vector2D velocity;
    int damage;

    public EnemyBullet() {
        super();
//        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        this.renderer = new SingleImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"));
        this.position = new Vector2D(0,0);
        this.collider = new BoxCollider(14,14);
        this.velocity = new Vector2D(0,1);
        this.damage = 4;
    }

    @Override
    public void run() {
        Player player = GameObject.intersect(Player.class, this);
        if(player != null) {
            player.takeDamage(this.damage);
            this.destroy();
        }

        //Nếu viên đạn bay quá tầm màn hình thì sẽ bị destroy để sau này có thể recycle
        if(this.position.y > Settings.SCREEN_HEIGHT) {
            this.destroy();
    }
        this.position.addThis(this.velocity.x, this.velocity.y);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }

}
