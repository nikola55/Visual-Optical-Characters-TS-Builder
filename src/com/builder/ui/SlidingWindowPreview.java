/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.builder.ui;

import com.builder.model.SlidingWindowState;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Nikola
 */
public class SlidingWindowPreview extends javax.swing.JPanel {

    /**
     * Creates new form SlidingWindowPreview
     */
    
    private int thisWidth = 200, thisHeight = 200;
    
    private BufferedImage currentImage;
    
    public SlidingWindowPreview() {
        initComponents();
    }
    
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        drawImage((Graphics2D)g);
        
    }
    
    private void drawImage(Graphics2D g) {
        
        if(currentImage==null) return;
        
        double sf = (double)getWidth() / (double)currentImage.getWidth();
        
        AffineTransform scale = AffineTransform.getScaleInstance(sf, sf);
        
        g.drawImage(currentImage, scale, null);
        g.setColor(Color.red);
        g.drawLine(thisWidth/2, 0, thisWidth/2, thisHeight);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @return the currentImage
     */
    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    /**
     * @param currentImage the currentImage to set
     */
    public void setCurrentImage(BufferedImage currentImage) {
        this.currentImage = currentImage;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * @return the lineState
     */
    
}