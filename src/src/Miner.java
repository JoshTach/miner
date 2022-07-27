package src;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;
import src.tasks.MiningTask;
import src.tasks.BankTask;
import org.dreambot.api.methods.map.Area;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

@ScriptManifest(category = Category.MINING, name = "pablo", description = "Mines anything, and drops inventory when full. NOW INCLUDING EPIC IRON AND EAST MINE UPDATEOOO!!!!", author = "juan", version = 2.0)
public class Miner extends TaskScript {

    MiningAreas MiningAreaObject = new MiningAreas();

    private int someValue;
    private boolean isRunning;
    public static String rockName;
    public static String miningPlace;


   public static Map<String, Integer> rnrc = new HashMap<String, Integer>();
   public static Map<String, Area> MiningAreaHashMap = new HashMap<String, Area>();

   public void addValues(){
        rnrc.put("Tin", 53);
        rnrc.put("Clay", 6705);
        rnrc.put("Iron",2576);
        MiningAreaHashMap.put("West Mine", MiningAreaObject.VarrockWestMiningArea);
        MiningAreaHashMap.put("East Mine", MiningAreaObject.VarrockEastMiningArea);
   }


    @Override
    public void onStart() {
        SkillTracker.start(Skill.MINING);
        addValues();
        createGUI();
        addNodes(new MiningTask(), new BankTask());
    }

    private void createGUI() {


        JFrame frame = new JFrame();
        frame.setTitle("Pablo's epic miner!");


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Closes the actual GUI when clicking X

        frame.setLocationRelativeTo(getClient().getInstance().getCanvas());
        // sets GUI in the middle of screen I guess

        frame.setPreferredSize(new Dimension(300,170));
        //size

        frame.getContentPane().setLayout(new BorderLayout());
        //idk

        frame.pack(); //makes things visible?
        frame.setVisible(true); //sets GUI visible


        JPanel settingsPanel = new JPanel();

        settingsPanel.setLayout(new GridLayout(0,2));


        JTextField rockNameTextField = new JTextField();
        settingsPanel.add(rockNameTextField);

        JComboBox<String> areaComboBox = new JComboBox<>(new String[]{
            "West Mine", "East Mine"});
        settingsPanel.add(areaComboBox);


        JComboBox<String> rockComboBox = new JComboBox<>(new String[]{
                "Copper", "Tin", "Clay", "Iron", "Coal"});
        settingsPanel.add(rockComboBox);



        frame.getContentPane().add(settingsPanel, BorderLayout.CENTER);
        //end of upper GUI

        //start of lower GUI
        JPanel buttonPanel = new JPanel(); //button panel
        buttonPanel.setLayout(new FlowLayout());

        JButton button = new JButton();        //new button
        button.setText("Start Script");


//sack

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rockName = rockComboBox.getSelectedItem().toString();
                miningPlace = areaComboBox.getSelectedItem().toString();


                if (rnrc.containsKey(rockName)){
                rnrc.get(rockName);
                }

                if (MiningAreaHashMap.containsKey(miningPlace)){
                    MiningAreaHashMap.get(miningPlace);
                }


//                if (!rockNameTextField.getText().equals(""))
//                    rockNameLabel.setText(rockNameTextField.getText());
//                else
//                    rockNameLabel.setText("Please write something in the edit box");
//
                isRunning = true; //script wll loop code
                frame.dispose(); //closes gui
            }
        });

        buttonPanel.add(button);

        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        //end of lower gui


        frame.pack();
        frame.setVisible(true);
        MethodProvider.log("gui loaded XD!!!8=D");

    }

    @Override
    public void onPaint(Graphics g) {

        String experienceGainedText = String.format(
                "Mining Experience: %d (%d per hour)",
                SkillTracker.getGainedExperience(Skill.MINING),
                SkillTracker.getGainedExperiencePerHour(Skill.MINING)
        );
        g.drawString(experienceGainedText, 5, 35);
    }

}

