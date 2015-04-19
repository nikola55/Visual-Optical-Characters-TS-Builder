/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.builder.work;

import com.builder.ui.ExamplesViewer;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JProgressBar;

/**
 *
 * @author Nikola
 */
public class Thresholder extends Thread {
    
    private BufferedImage image;
    private int value;
    private ExamplesViewer viewer;
    
    public Thresholder(BufferedImage image, int value, ExamplesViewer viewer) {
        this.image = image;
        this.value = value;
        this.viewer = viewer;
    }
    
    
    
    @Override
    public void run() {
        
        if(this.image == null) return;
        
        int imageHeight = image.getHeight();
        
        int upadetSteps = imageHeight / 10;
        
        for (int y = 0; y < imageHeight; y++) {
            
            for (int x = 0; x < image.getWidth(); x++) {
                
                Color c = new Color(image.getRGB(x, y));
                int average = (int)(1.0 / 3.0 * (c.getRed() + c.getGreen() + c.getBlue()));
                
                if(average < value) {
                    image.setRGB(x, y, 0x0);
                } else {
                    image.setRGB(x, y, 0xffffff);
                }
                
            }
            
            if(y % upadetSteps == 0 && y != 0) {
                
                viewer.repaint();
                
            }
        }
        
        viewer.repaint();
    }
    
    
    
}
