package ludum.dare.components.upgradeComponents;

import com.bitdecay.jump.geom.Projectable;

/**
 * Created by jake on 12/12/2015.
 */
public class ProjectileComponent {
    public int cost = 0;
    public boolean projLVL1 = false;
    public boolean isProjLVL2 = false;
    public boolean fireProj = false;
    public boolean webProj = false;
    public boolean iceProj = false;
    public boolean poisonProj = false;

    public ProjectileComponent(ProjectileComponent proj, String projLVL2Name){
        if(!proj.projLVL1 && !proj.isProjLVL2){
            this.projLVL1 = true;
        } else if (proj.projLVL1 && !proj.isProjLVL2){
            this.isProjLVL2 = true;
            if(projLVL2Name == "FIRE_PROJECTILE"){
                fireProj = true;
            } else if (projLVL2Name == "WEB_PROJECTILE"){
                webProj = true;
            } else if (projLVL2Name == "ICE_PROJECTILE"){
                iceProj = true;
            }else if (projLVL2Name == "POISON_PROJECTILE"){
                poisonProj = true;
            }
        }
    }

}
