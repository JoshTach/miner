package src.tasks;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;
import src.MiningAreas;
import static org.dreambot.api.methods.walking.impl.Walking.walkExact;
import static src.Miner.*;
import java.util.Random;
import src.AntiBan;


public class MiningTask extends TaskNode {
    MiningAreas MiningAreaObject = new MiningAreas();
    AntiBan AntiBanObject = new AntiBan();
    public boolean isWalking = true;
    public Area temp;


    public void runFromTarget(){
        if (MiningAreaHashMap.get(miningPlace).contains(getLocalPlayer().getTile())){
            temp = MiningSafeSpot.get(MiningAreaHashMap.get(miningPlace));
            do {
                walkExact(temp.getRandomTile());
                sleep(Calculations.random(200,500));
            }
            while(!temp.contains(getLocalPlayer().getTile()));
            //works kinda
        }
    }

    public void walkToArea() {
        do {
            walkExact(MiningAreaHashMap.get(miningPlace).getRandomTile());
            sleep(Calculations.random(1000, 2000));
            MethodProvider.log("Walking to homedepot");
        }
        while (!MiningAreaHashMap.get(miningPlace).contains(getLocalPlayer().getTile()));
        if (MiningAreaHashMap.get(miningPlace).contains(getLocalPlayer().getTile())){
            isWalking = false;
        }
    }
    private GameObject getClosestRock() {
        return GameObjects.closest(object -> object.getName().equalsIgnoreCase("Rocks") && object.hasAction("Mine") && object.getModelColors() != null
                && object.getModelColors()[0] == rnrc.get(rockName).shortValue());
    }
    private boolean isMining() {
        return getLocalPlayer().isAnimating();
    }
    @Override
    public boolean accept() {
        // If our inventory isn't full and we're not mining, we should start
        return !Inventory.isFull() && !isMining();

    }
    @Override
    public int execute() {

        int min = 1;
        int max = 50;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);

        if (isWalking == true){
            walkToArea();
        }

        GameObject rock = getClosestRock();

        if (getLocalPlayer().isHealthBarVisible() && getLocalPlayer().isInCombat()) {
                runFromTarget();
                MethodProvider.log("running");
        }
            // If there aren't any available rocks near us, we should just wait until one's available
        if (rock == null) return Calculations.random(500, 1000);
        MethodProvider.log("NO ROCK OH NO");

        if (rock.interact("Mine")) { // If we successfully click on the rock

                sleepUntil(this::isMining, 2500); // Wait until we're mining, with a max wait time of 2,500ms (2.5 seconds)
                MethodProvider.log("trying to mine XD!!!!");
        }
                MethodProvider.log(random_int);

        if (random_int == max) {
                    AntiBanObject.checkSkills();
                    MethodProvider.log("aye man im about to open inventory!");
        }
        return Calculations.random(500, 1000);
    }
}