package src;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.randoms.RandomEvent;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.Entity;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.Menu;
import org.dreambot.api.wrappers.widgets.message.Message;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.input.Mouse;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import static org.dreambot.api.input.Mouse.moveMouseOutsideScreen;
import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.tabs.Tabs.*;

public class AntiBan {

    public final Skill[] skillsToCheck = new Skill[]{

            Skill.MINING,
            Skill.AGILITY,
            Skill.ATTACK,
            Skill.COOKING,
            Skill.FISHING,
            Skill.FLETCHING,
            Skill.MAGIC,
            Skill.SLAYER,
            Skill.RANGED,
            Skill.FISHING,
            Skill.CRAFTING,
            Skill.CONSTRUCTION,
            Skill.FARMING,
            Skill.HUNTER,
            Skill.SMITHING,
            Skill.HERBLORE,
            Skill.PRAYER,
            Skill.DEFENCE,
            Skill.WOODCUTTING,
            Skill.RUNECRAFTING,
            Skill.STRENGTH,
            Skill.HITPOINTS,
            Skill.THIEVING,
            Skill.FIREMAKING,

    };

    private static Skill getRandom(Skill[] array){
        int rng = new Random().nextInt(array.length);
        return array[rng];
    }
    public void checkSkills(){

            Skills.open();
            Skills.hoverSkill(getRandom(skillsToCheck));
            sleep(Calculations.random(3000,6000));
            if(getOpen() != Tab.INVENTORY) {
                 open(Tab.INVENTORY);
            }
    }

    public void moveMouseOff(){
        moveMouseOutsideScreen();
        sleep(Calculations.random(20000,40000));
        //return Calculations.random(600000,1800000);





    }



}
