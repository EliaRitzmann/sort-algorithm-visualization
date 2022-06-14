package ch.bbbaden.visualisation.algorithms;

import ch.bbbaden.visualisation.gui.DrawingCanvas;

public interface SortAlgorithms {
    public void run(DrawingCanvas drawingCanvas, long delay, int[] array, int gap);
    public void setAbort(boolean abort);
}
