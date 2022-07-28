package src.tasks;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;
import src.MiningAreas;
import static org.dreambot.api.methods.walking.impl.Walking.walkExact;
import static src.Miner.rnrc;
import static src.Miner.rockName;
import static src.Miner.MiningAreaHashMap;
import static src.Miner.miningPlace;

public class MiningTask extends TaskNode {
    MiningAreas MiningAreaObject = new MiningAreas();
    public boolean isWalking = true;

    public void runFromTarget(){

    if (getLocalPlayer().isHealthBarVisible() && getLocalPlayer().isInCombat()){
        if (MiningAreaHashMap.get(miningPlace).contains(getLocalPlayer().getTile())){
            //empty will fill later

        }




    }




    }



    @Override
    public boolean accept() {
        // If our inventory isn't full and we're not mining, we should start

        return !Inventory.isFull() && !isMining();

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

    @Override
    public int execute() {


        if (isWalking == true){
            walkToArea();
        }

        GameObject rock = getClosestRock();
            MethodProvider.log("hey just passin by");


            // If there aren't any available rocks near us, we should just wait until one's available
            if (rock == null) return Calculations.random(500, 1000);
            MethodProvider.log("NO ROCK OH NO");

            if (rock.interact("Mine")) { // If we successfully click on the rock

                sleepUntil(this::isMining, 2500); // Wait until we're mining, with a max wait time of 2,500ms (2.5 seconds)
                MethodProvider.log("trying to mine XD!!!!");
            }


        return Calculations.random(500, 1000);
    }
    private GameObject getClosestRock() {
        return GameObjects.closest(object -> object.getName().equalsIgnoreCase("Rocks") && object.hasAction("Mine") && object.getModelColors() != null
                && object.getModelColors()[0] == rnrc.get(rockName).shortValue());
    }
    /**
     * For part 1, we'll consider our player doing any animation as mining
     * This will be improved/changed in a future part
     *
     * @return true if the player is mining, otherwise false
     */
    private boolean isMining() {
        return getLocalPlayer().isAnimating();
    }

}