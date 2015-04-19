/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.builder.model;

import java.awt.Rectangle;

/**
 *
 * @author Nikola
 */
public class SlidingWindowState {
    
    private final Segment seg;
    
    private final int width, height;
    
    private int incrementStep;
    
    private Rectangle currentState;
    
    public SlidingWindowState(Segment seg, int incrementStep) {
        
        this.seg = seg;
        
        Rectangle segCoord = seg.getCoords();
        
        this.width = segCoord.height;
        this.height = segCoord.height;
        
        this.incrementStep = incrementStep;
        
        currentState = new Rectangle(segCoord.x, segCoord.y, this.width, this.height);

    }

    public boolean hasNext() {
        if(currentState.x + currentState.width >= seg.getCoords().width) {
            return false;
        }
        return true;
    }

    public Segment next() {
        
        if(currentState.x <= seg.getCoords().getWidth()) {
            currentState.x += getIncrementStep();
        }
        
        Segment currentSegment = new Segment((Rectangle)currentState.clone());
        
        return currentSegment;
        
    }
    
    public Segment previous() {
        
        if(currentState.x >= 0) {
            currentState.x -= getIncrementStep();
        }
        
        Segment currentSegment = new Segment((Rectangle)currentState.clone());
        
        return currentSegment;
    }
    
    public Segment current() {
        
        return new Segment((Rectangle)currentState.clone());
        
    }
    
    /**
     * @return the incrementStep
     */
    public int getIncrementStep() {
        return incrementStep;
    }

    /**
     * @param incrementStep the incrementStep to set
     */
    public void setIncrementStep(int incrementStep) {
        this.incrementStep = incrementStep;
    }

   
    
}
