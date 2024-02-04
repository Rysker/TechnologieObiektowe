import java.lang.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.*;

import Population.*;
import Memento.*;


public class SimulationManager extends JFrame
{
    static int windowX = 800;
    static int windowY = 800;
    static int worldX = 10;
    static int worldY = 10;
    static int current_tick = 0;
    static boolean isRunning = false;
    static PopulationManager specmng= new PopulationManager(100, worldX, worldY, true);
    static SimulationManager sim = new SimulationManager();
    static ViewManager view = new ViewManager(windowX, windowY, worldX, worldY);
    static CareTaker careTaker = new CareTaker(specmng);
    static JButton stopButton;
    static JButton startButton;
    static JButton saveButton;
    static JButton loadButton;
    static JComboBox<String> savesCombo;
    public static void main(String[] args)
    {
        initializeButtons(view.getOptionPanel());
        viewTick(specmng.getList(), current_tick);
        while(true)
        {
            if(isRunning)
            {
                sim.nextTick();
                viewTick(specmng.getList(), current_tick);
            }

            try
            {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void nextTick()
    {
        specmng.simulatePopulation(this.current_tick);
        this.current_tick += 1;
    }

    private static void initializeButtons(JPanel jpanel)
    {
        startButton = new JButton("Start");
        startButton.setBounds(800, 40, 150, 40);
        startButton.addActionListener(e ->
        {
            isRunning = true;
        });

        stopButton = new JButton("Stop");
        stopButton.setBounds(800, 120, 150, 40);
        stopButton.addActionListener(e ->
        {
            isRunning = false;
        });

        saveButton = new JButton("Save");
        saveButton.setBounds(800, 200, 150, 40);
        saveButton.addActionListener(e ->
        {
            isRunning = false;
            careTaker.create(current_tick);
            addToCombo(careTaker.getKeys(), savesCombo);
        });

        loadButton = new JButton("Load");
        loadButton.setBounds(800, 280, 150, 40);
        loadButton.addActionListener(e ->
        {
            isRunning = false;
            String selectedKey = (String) savesCombo.getSelectedItem();
            if (selectedKey != null)
            {
                loadMemento(careTaker.restore(selectedKey));
                revertTimeTick(selectedKey);
                viewTick(specmng.getList(), current_tick);
            }
        });

        savesCombo = new JComboBox<String>();
        savesCombo.setBounds(800, 360, 150, 40);

        jpanel.add(startButton);
        jpanel.add(stopButton);
        jpanel.add(saveButton);
        jpanel.add(loadButton);
        jpanel.add(savesCombo);
    }

    public static void viewTick(ArrayList<Person> persons, int tick)
    {
        view.setPersons(persons);
        view.newTickScene(tick);
    }

    private static void addToCombo(String[] keys, JComboBox<String> combo)
    {

        combo.removeAllItems();
        Arrays.sort(keys);
        for (String key : keys)
            combo.addItem(key);
    }

    private static void revertTimeTick(String time)
    {
        SimpleDateFormat sdfDate = new SimpleDateFormat("mm:ss.SSS");
        Date parsedDate = null;
        try {
            parsedDate = sdfDate.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long originalMilliseconds = parsedDate.getTime();
        int originalTick = (int) (originalMilliseconds / 40L);
        current_tick = originalTick;
    }

    private static void loadMemento(Memento memento)
    {
        specmng.swapPopulation(memento.population);
    }

}

