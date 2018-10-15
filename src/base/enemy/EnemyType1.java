package base.enemy;

import base.physics.BoxCollider;
import base.renderer.AnimationRenderer;

public class EnemyType1 extends Enemy {
    public EnemyType1() {
        super();
        this.renderer = new AnimationRenderer("assets/images/enemies/level0/pink/0.png",
                "assets/images/enemies/level0/pink/1.png",
                "assets/images/enemies/level0/pink/2.png",
                "assets/images/enemies/level0/pink/3.png");
        this.collider = new BoxCollider(28,28);
    }

    @Override
    public void takeDamage(int damage) {
        if(damage > 0) {
            this.destroy();
        }
    }
}
