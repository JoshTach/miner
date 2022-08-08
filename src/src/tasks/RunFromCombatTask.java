package src.tasks;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;
import src.MiningAreas;

import static org.dreambot.api.methods.input.Camera.mouseRotateToEntity;
import static org.dreambot.api.methods.walking.impl.Walking.walkExact;
import static src.Miner.*;
import java.util.Random;
import src.AntiBan;

public class RunFromCombatTask extends TaskNode {
    MiningAreas MiningAreaObject = new MiningAreas();
    public Area safeSpot;


    @Override
    public boolean accept() {
        return getLocalPlayer().isHealthBarVisible();
    }
    @Override
    public int execute() {
        MethodProvider.log("hey the rats on me! gotta blast");
       // if (MiningAreaHashMap.get(miningPlace).contains(getLocalPlayer().getTile())){
            safeSpot = MiningSafeSpot.get(MiningAreaHashMap.get(miningPlace));
            do {

                walkExact(MiningAreaObject.VarrockEastMineSafe.getRandomTile());
                //walkExact(safeSpot.getRandomTile());
                sleep(Calculations.random(1000,1500));
            }
            while(!safeSpot.contains(getLocalPlayer().getTile()));
            //works kinda
        //}
        return Calculations.random(10000, 15000);
    }













}


