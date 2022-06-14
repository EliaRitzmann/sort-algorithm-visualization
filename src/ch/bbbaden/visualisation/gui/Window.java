package ch.bbbaden.visualisation.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Window {
    private JFrame window = new JFrame();
    private DrawingCanvas canvas;
    private int width = 1000;
    private int height = 1000;

    //Wieso bruch ich öberhaupt no Java :(
    private final int WINDOW_WIDTH_OFFSET = 18; //18
    private final int WINDOW_HEIGHT_OFFSET = 47; //47

    public Window(DrawingCanvas drawingCanvas){
        this.canvas = drawingCanvas;
        window.setSize(width+WINDOW_WIDTH_OFFSET, height+WINDOW_HEIGHT_OFFSET);
        window.setTitle("Sort visualisation");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        //Event Listener
        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //super.componentResized(e);
                width = window.getWidth()-WINDOW_WIDTH_OFFSET;
                height = window.getHeight()-WINDOW_HEIGHT_OFFSET;
                resizeCanvas();
            }
        });

        window.add(canvas);
        window.setVisible(true);
        resizeCanvas();

    }

    private void resizeCanvas(){
        canvas.setWindowWidth(width);
        canvas.setWindowHeight(height);
        canvas.repaint();
    }

    public void setCanvas(DrawingCanvas canvas) {
        this.canvas = canvas;
        window.add(this.canvas);

        //why set visible ????
        window.setVisible(true);

        resizeCanvas();
    }

}
