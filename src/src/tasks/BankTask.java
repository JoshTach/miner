package src.tasks;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.script.TaskNode;
import src.MiningAreas;
import static org.dreambot.api.methods.walking.impl.Walking.walk;
import static org.dreambot.api.methods.walking.impl.Walking.walkExact;

public class BankTask extends TaskNode {
    MiningAreas MiningAreaObject = new MiningAreas();



    @Override
    public boolean accept() {
        // If our inventory is full, we should execute this task
        return Inventory.isFull();
    }


    @Override
    public int execute() {

        if (Bank.openClosest()) {
            // If this returns true, that means the bank is open and ready
            // You can see other things you can do now below

            Bank.depositAllItems();     // This will deposit everything in your inventory

            MethodProvider.log("opening bank or depo");

        } else {
            // If this returns false, that means the client is still walking
            // or interacting with the bank

            MethodProvider.log("intreacting with bank/still walking to bank");
            return 500;

        }

        do {

            walkExact(MiningAreaObject.VarrockEastMiningArea.getRandomTile());
            MethodProvider.log("trying to find a walkxd");

            sleep(Calculations.random(3000, 4000));

        } while(!MiningAreaObject.VarrockEastMiningArea.contains(getLocalPlayer().getTile()));

        return 500;

    }

}