package ch.bbbaden.visualisation.algorithms;

import ch.bbbaden.visualisation.gui.DrawingCanvas;

import java.util.Timer;
import java.util.TimerTask;


public class BubbleSort implements SortAlgorithms{
    private boolean abort = false;
    private Timer timer;
    int timeInMs = 0;
    int totalTime = 0;

    public void run(DrawingCanvas drawingCanvas, long delay, int[] array, int gap) {
        timer = new Timer();
        timeInMs = 0;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeInMs++;
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1);

        int smaller;
        int bigger;
        boolean run = true;
        abort = false;

        for (int i = 0; i < array.length && run && !abort; i++) {
            run = false;

            for (int y = 0; y < array.length-1 && !abort; y++) {
                if(array[y] > array[y + 1]) {
                    bigger = array[y];
                    smaller = array[y + 1];
                    array[y] = smaller;
                    array[y + 1] = bigger;
                    run = true;

                    drawingCanvas.updateData(array, y+1, gap,false, "BubbleSort | Array size: " + array.length + " | Delay: " + delay + "ms | Duration: " + (float)timeInMs/1000 + "s");
                    try{
                        Thread.sleep(delay);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        }

        totalTime = timeInMs;
        //end
        for (int i = 0; i < array.length && !abort; i++) {
            drawingCanvas.updateData(array, i, gap, true, "BubbleSort | Array size: " + array.length + " | Delay: " + delay + "ms | Completed in: " + (float)timeInMs/1000 + "s");
            try{
                Thread.sleep(delay);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    @Override
    public String toString(){
        return "BubbleSort";
    }


    public void setAbort(boolean abort) {
        this.abort = abort;
    }
}
