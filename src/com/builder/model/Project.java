/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.builder.model;

import com.builder.gen.Histogram;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nikola
 */
public class Project {
    
    private File posExDir;
    
    private File negExDir;
    private File imageFile;
    
    private BufferedImage image;
    private BufferedImage binary;
    
    private Histogram hist;
    
    private SegmentSet lineSegments;
    
    private SlidingWindowState slidingWindowState;
    
    public Project(File posExDir, File negExDir, File imageFile) {
        this.posExDir = posExDir;
        this.negExDir = negExDir;
        this.imageFile = imageFile;
        loadImage();
    }
    
    private void loadImage() {
     
        try {
         
            this.image = ImageIO.read(this.imageFile);
            
        } catch (IOException e) {
            
            e.printStackTrace();
            
        }
        
        this.binary = clone(this.image);
        
    }
    
    public BufferedImage getOriginalImage() {
        
        return this.image;
        
    }
    
    /**
     * @return the posExDir
     */
    public File getPosExDir() {
        return posExDir;
    }

    /**
     * @return the negExDir
     */
    public File getNegExDir() {
        return negExDir;
    }

    /**
     * @return the imageFile
     */
    public File getImageFile() {
        return imageFile;
    }

    /**
     * @return the image
     */
    public BufferedImage getImage() {
        this.binary = clone(this.image);
        return this.binary;
    }
    
    public BufferedImage getDrawable(boolean overlaySegments) {
        
        if(overlaySegments) {
            
            Graphics2D imgg = (Graphics2D)this.binary.createGraphics();
            
            for(Segment s : lineSegments) {
                
                Rectangle rect = s.getCoords();
                
                if(s.isSelected()) {
                
                    imgg.setColor(Color.yellow);
                
                } else {
                    
                    imgg.setColor(Color.red);
                    
                }
                
                imgg.drawRect(rect.x, rect.y, rect.width, rect.height);
                
                imgg.setColor(Color.red);
            }
            
            imgg.dispose();
        }    
        
        return this.binary;
    }
    
    private BufferedImage clone(BufferedImage img){
        ColorModel cm = img.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = img.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    
    @Override
    public String toString() {
        return "Project{" + "posExDir=" + posExDir + ", negExDir=" + negExDir + ", imageFile=" + imageFile + ", image=" + image + '}';
    }

    /**
     * @return the binary
     */
    public BufferedImage getBinary() {
        return binary;
    }

    /**
     * @param binary the binary to set
     */
    public void setBinary(BufferedImage binary) {
        this.binary = binary;
    }

    /**
     * @return the hist
     */
    public Histogram getHist() {
        return hist;
    }

    /**
     * @param hist the hist to set
     */
    public void setHist(Histogram hist) {
        this.hist = hist;
    }

    /**
     * @return the lineSegments
     */
    public SegmentSet getLineSegments() {
        return lineSegments;
    }

    /**
     * @param lineSegments the lineSegments to set
     */
    public void setLineSegments(SegmentSet lineSegments) {
        this.lineSegments = lineSegments;
    }

    /**
     * @return the slidingWindowState
     */
    public SlidingWindowState getSlidingWindowState() {
        return slidingWindowState;
    }

    /**
     * @param slidingWindowState the slidingWindowState to set
     */
    public void setSlidingWindowState(SlidingWindowState slidingWindowState) {
        
        this.slidingWindowState = slidingWindowState;
    }
    
    
    
}
