package ch.bbbaden.visualisation.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DrawingCanvas extends JComponent {
    private int[] data;
    private int selectedIndex = 0;
    private int gap = 0;
    private boolean end = false;
    private String text = "Welcome";

    private int windowWidth;
    private int windowHeight;
    private int barWidth;
    private int barUnitHeight;

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0, 0, windowWidth, windowHeight);

        try {
            draw(graphics2D);
        } catch (Exception e) {
        }
    }

    private void draw(Graphics2D graphics2D) {
        barWidth = (windowWidth / data.length)-gap;
        barUnitHeight = windowHeight / data.length;


        int widthOffset = windowWidth % data.length;
        int x = 0;

        graphics2D.setColor(Color.white);
        graphics2D.setFont(new Font("Arial", Font.BOLD, 20));
        graphics2D.drawString(text, 10, 30);

        for (int i = 0; i < data.length; i++) {
            //Change Color
            if (i == selectedIndex) {
                graphics2D.setColor(Color.RED);

            } else if (end && i < selectedIndex){
                graphics2D.setColor(Color.GREEN);
            }
            else {
                graphics2D.setColor(Color.white);
            }

            /*
                Nicht jede Breite ist durch die länge des Arrays teilbar, deswegen werden einige Balken vergrössert.

                Idea: Zähler zählt jeden Abstand, sodass die nächsten Balken wissen wie ihre x-position ist.
                Dadurch könnte nur jeder zweite Balken verbreitert werden und das Bild sähe schöner aus.

            {
                //System.out.println(i);
                System.out.println(widthOffset);
                //System.out.println(i % widthOffset == 0);

                //int heightOffset = windowHeight % data.length;        To be implemented

                if(widthOffset == 0){
                    graphics2D.fillRect(x, windowHeight-(barUnitHeight * (i + 1)), barWidth, barUnitHeight * (i + 1));
                    x += barWidth;
                }
                else{
                    System.out.println((float)data.length/(float)widthOffset);
                    int indexup = (int)Math.ceil((float)data.length/(float)widthOffset);
                    System.out.println(indexup);
                    System.out.println((data.length/widthOffset));

                    double split = ((float)data.length/(float)widthOffset)-(data.length/widthOffset);
                    System.out.println(split);
                    System.out.println(data.length/split);

                    if(i < data.length*split){
                        if (i % indexup == 0){
                            graphics2D.fillRect(x, windowHeight-(barUnitHeight * (i + 1)), barWidth + 1, barUnitHeight * (i + 1));
                            x += barWidth + 1;
                        }else{
                            graphics2D.fillRect(x, windowHeight-(barUnitHeight * (i + 1)), barWidth, barUnitHeight * (i + 1));
                            x += barWidth;
                        }
                    }
                    else{
                        if (i % (data.length/widthOffset) == 0){
                            graphics2D.fillRect(x, windowHeight-(barUnitHeight * (i + 1)), barWidth + 1, barUnitHeight * (i + 1));
                            x += barWidth + 1;
                        }else{
                            graphics2D.fillRect(x, windowHeight-(barUnitHeight * (i + 1)), barWidth, barUnitHeight * (i + 1));
                            x += barWidth;
                        }
                    }
                }
                */


            if (widthOffset >= i) {
                graphics2D.fillRect((barWidth + 1 + gap) * i, windowHeight - (barUnitHeight * data[i]), barWidth + 1, barUnitHeight * data[i]);
            } else {
                graphics2D.fillRect(((barWidth + gap) * i) + widthOffset, windowHeight - (barUnitHeight * data[i]), barWidth, barUnitHeight * data[i]);
            }
        }
    }



    public void updateData(int[] data, int selectedIndex, int gap, boolean end, String text) {
        this.text = text;
        this.end = end;
        this.gap = gap;
        this.selectedIndex = selectedIndex;
        this.data = data;
        this.repaint();
    }

    public void updateData(int[] data, int selectedIndex, int gap, boolean end) {
        this.end = end;
        this.gap = gap;
        this.selectedIndex = selectedIndex;
        this.data = data;
        this.repaint();
    }


    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }
}
