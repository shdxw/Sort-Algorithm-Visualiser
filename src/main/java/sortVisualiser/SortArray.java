package sortVisualiser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;
import static util.Sleep.sleepFor;

/**
 * The array that can be sorted
 * @author mhops
 */
public class SortArray extends JPanel {
    public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 720;
    private static final int BAR_WIDTH = 8;
    private static final int NUM_BARS = WIN_WIDTH / BAR_WIDTH;
    private int[] array;
    private int[] accessColourMod;
    
    public SortArray() {
        setBackground(Color.darkGray);
        array = new int[NUM_BARS];
        accessColourMod = new int[NUM_BARS];
        for (int i = 0; i < NUM_BARS; i++) {
            array[i] = i;
            accessColourMod[i] = 0;
        }
    }
    
    public int arraySize() {
        return array.length;
    }
    
    public int getValue(int index) {
        return array[index];
    }
    
    public void swapUpdate(int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        
        accessColourMod[firstIndex] = 100;
        accessColourMod[secondIndex] = 100;
        repaint();
        sleepFor(10000);
    }
    
    /**
     * Gets the canvas size
     * @return size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIN_WIDTH, WIN_HEIGHT);
    }
    
    public void resetColours() {
        for (int i = 0; i < NUM_BARS; i++) {
            accessColourMod[i] = 0;
        }
        repaint();
    }
    
    /**
     * Draws the array
     * @param g The graphics device for drawing
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D)g;
        super.paintComponent(graphics);
       
        graphics.setColor(Color.white);
        for (int x = 0; x < NUM_BARS; x++) {
            int height = getValue(x) * 3;
            int xBegin = x + (BAR_WIDTH - 1) * x;
            int yBegin = WIN_HEIGHT - height;
            
            int val = accessColourMod[x] * 2;
            graphics.setColor(new Color(255, 255 - val, 255 - val));
            graphics.fillRect(xBegin, yBegin, BAR_WIDTH, height);
            if (accessColourMod[x] > 0) {
                accessColourMod[x]-= 5;
            }
        }
    }
}
