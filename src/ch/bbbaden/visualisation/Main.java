package ch.bbbaden.visualisation;

import ch.bbbaden.visualisation.algorithms.BubbleSort;
import ch.bbbaden.visualisation.algorithms.SortAlgorithms;
import ch.bbbaden.visualisation.gui.DrawingCanvas;
import ch.bbbaden.visualisation.gui.OptionsWindow;
import ch.bbbaden.visualisation.gui.Window;

import java.util.*;

public class Main{
    private Window window;
    private OptionsWindow optionsWindow;
    private DrawingCanvas drawingCanvas = new DrawingCanvas();
    List<SortAlgorithms> sortAlgorithms = new ArrayList<>();

    public void setup() {
        window = new Window(drawingCanvas);

        sortAlgorithms.add(new BubbleSort());

        optionsWindow = new OptionsWindow(sortAlgorithms, drawingCanvas);

    }




}
