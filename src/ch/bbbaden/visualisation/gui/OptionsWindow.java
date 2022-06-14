package ch.bbbaden.visualisation.gui;

import ch.bbbaden.visualisation.algorithms.SortAlgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OptionsWindow extends JFrame implements ActionListener, Runnable{
    private int arraySize = 100;
    private int gap = 0;
    private long delay = 5;

    private int indexThread;

    private List<SortAlgorithms> sortAlgorithms;
    private DrawingCanvas drawingCanvas;
    private int[] data = new int[arraySize];
    private Thread thread;

    private JLabel lbl_arraySize;
    private JSlider sld_ArraySize;
    private JLabel lbl_gap;
    private JSlider sld_gap;
    private JLabel lbl_delay;
    private JSlider sld_delay;
    private JButton btn_shuffle;
    private JButton btn_abort;


    private List<JButton> buttons = new ArrayList<>();


    private Font font = new Font(Font.SERIF, Font.PLAIN, 20);

    public OptionsWindow(List<SortAlgorithms> sortAlgorithms, DrawingCanvas drawingCanvas){
        this.sortAlgorithms = sortAlgorithms;
        this.drawingCanvas = drawingCanvas;

        this.setTitle("Options");
        this.setResizable(false);
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));


        createData();
        updateCanvas();
        setupData();
        setupGUI();


    }

    private void setupData(){
        for (int i = 0; i < sortAlgorithms.size(); i++) {
            buttons.add(new JButton());
            buttons.get(i).setText(sortAlgorithms.get(i).toString());
            buttons.get(i).addActionListener(this);
        }
    }

    private void setupGUI(){

        lbl_arraySize = new JLabel();
        lbl_arraySize.setText("Array size: " + arraySize);
        lbl_arraySize.setFont(font);


        sld_ArraySize = new JSlider(JSlider.HORIZONTAL, 0, 500, arraySize);
        sld_ArraySize.setMinorTickSpacing(50);
        sld_ArraySize.setMajorTickSpacing(100);
        sld_ArraySize.setPaintTicks(true);
        sld_ArraySize.addChangeListener(e -> {
            if(sld_ArraySize.getValue() < 10){
                sld_ArraySize.setValue(10);
            }
            arraySize = sld_ArraySize.getValue();
            lbl_arraySize .setText("Array size: " + arraySize);
            createData();
            updateCanvas();
        });

        lbl_gap = new JLabel();
        lbl_gap.setText("Gap size: " + gap);
        lbl_gap.setFont(font);

        sld_gap = new JSlider(JSlider.HORIZONTAL, 0, 5, gap);
        sld_gap.setMajorTickSpacing(1);
        sld_gap.setPaintTicks(true);
        sld_gap.addChangeListener(e -> {
            gap = sld_gap.getValue();
            lbl_gap.setText("Gap size: " + gap);
            updateCanvas();
        });

        lbl_delay = new JLabel();
        lbl_delay.setText("Delay: " + delay + "ms");
        lbl_delay.setFont(font);

        sld_delay = new JSlider(JSlider.HORIZONTAL, 1, 20, (int)delay);
        sld_delay.setMajorTickSpacing(1);
        sld_delay.setPaintTicks(true);
        sld_delay.addChangeListener(e -> {
            delay = sld_delay.getValue();
            lbl_delay.setText("Delay: " + delay + "ms");
        });

        btn_shuffle = new JButton();
        btn_shuffle.setText("Shuffle Data");
        btn_shuffle.addActionListener(this);

        btn_abort = new JButton();
        btn_abort.setText("Abort");
        btn_abort.addActionListener(this);
        btn_abort.setVisible(false);

        this.add(lbl_arraySize);
        this.add(sld_ArraySize);
        this.add(lbl_gap);
        this.add(sld_gap);
        this.add(lbl_delay);
        this.add(sld_delay);
        this.add(btn_shuffle);

        for (JButton button: buttons) {
            this.add(button);
        }

        this.add(btn_abort);

    }

    private void updateCanvas(){
        drawingCanvas.updateData(data, 0, gap, false);
    }

    private void createData(){
        data = new int[arraySize];
        for (int i = 0; i < data.length; i++) {
            data[i] = i+1;
        }
    }

    private void shuffleData(){
        Random rd = new Random();

        // Starting from the last element and swapping one by one.
        for (int i = data.length-1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = rd.nextInt(i+1);

            // Swap array[i] with the element at random index
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }

        updateCanvas();
    }

    private void isRunning(boolean enable){
        btn_abort.setVisible(!enable);
        btn_shuffle.setEnabled(enable);
        for (JButton button : buttons) {
            button.setEnabled(enable);
        }
        sld_ArraySize.setEnabled(enable);
        sld_gap.setEnabled(enable);
        sld_delay.setEnabled(enable);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()== btn_shuffle){
            shuffleData();
        }else if(e.getSource()==btn_abort){
            sortAlgorithms.get(indexThread).setAbort(true);
        }else{
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i)==e.getSource()){

                    indexThread = i;
                    thread = new Thread(this);
                    thread.start();
                }
            }
        }

    }

    @Override
    public void run() {
        isRunning(false);
        sortAlgorithms.get(indexThread).run(drawingCanvas, delay, data, gap);
        isRunning(true);
    }
}
