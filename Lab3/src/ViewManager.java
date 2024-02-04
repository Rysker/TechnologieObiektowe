import Population.Person;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ViewManager extends JFrame
{
    private int WINDOW_WIDTH_PX;
    private int WINDOW_HEIGHT_PX;
    private double WINDOW_WIDTH_M;
    private double WINDOW_HEIGHT_M;
    private double scale_x;
    private double scale_y;
    private ArrayList<Person> persons;
    private JLabel timeLabel;
    private JPanel canvasPanel;
    private JPanel optionPanel;

    public ViewManager(int width, int height, double xm, double ym)
    {
        this.WINDOW_HEIGHT_M = ym;
        this.WINDOW_WIDTH_M = xm;
        this.WINDOW_HEIGHT_PX = height;
        this.WINDOW_WIDTH_PX = width;
        this.scale_x = this.WINDOW_HEIGHT_PX / this.WINDOW_HEIGHT_M;
        this.scale_y = this.WINDOW_WIDTH_PX / this.WINDOW_WIDTH_M;
        JFrame frame = new JFrame("Simulation");

        canvasPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g)
            {
            super.paintComponent(g);
            drawPersons(g);
            }
        };
        canvasPanel.setLayout(null);
        canvasPanel.setBounds(50, 50, 610, 610);
        canvasPanel.setBackground(Color.WHITE);

        timeLabel = new JLabel("Time:");
        timeLabel.setBounds(330, 10, 150, 40);

        optionPanel = new JPanel();
        optionPanel.setLayout(null);
        optionPanel.setBounds(700, 100, 30, 70);
        optionPanel.setBackground(Color.ORANGE);
        optionPanel.add(timeLabel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(canvasPanel);
        frame.add(optionPanel);
        frame.setSize(1000, 800);
        frame.setVisible(true);
    }

    public void refresh()
    {
        repaint();
    }

    public void updateTimeLabel(int tick)
    {
        long milliseconds = tick * 40L;
        Date date = new Date(milliseconds);
        SimpleDateFormat sdfDate = new SimpleDateFormat("mm:ss.SSS");
        String tmp = sdfDate.format(date);
        timeLabel.setText("Time: " + tmp);
    }

    public void newTickScene(int tick)
    {
        updateTimeLabel(tick);
        canvasPanel.repaint();
        refresh();
    }

    private void drawPersons(Graphics g)
    {
        if(persons == null)
            return;
        for (Person person : persons)
        {
            int radius = 10;
            int xdraw_beg = (int) (person.getX() * scale_x);
            int ydraw_beg = (int) (person.getY() * scale_y);

            Color personColor = person.getState().getColor();
            g.setColor(personColor);
            g.fillOval(xdraw_beg, ydraw_beg, radius, radius);
        }
    }

    public void setPersons(ArrayList<Person> x)
    {
        this.persons = x;
    }


    public JPanel getOptionPanel()
    {
        return this.optionPanel;
    }

}


