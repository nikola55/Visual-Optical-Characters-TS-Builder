package com.builder.model;

import java.awt.Rectangle;

public class Segment {
    
    private Rectangle coords;
    
    private boolean isSelected;
    
    public Segment(Rectangle coords) {
        
        this.coords = coords;
        this.isSelected = false;
    }

    /**
     * @return the coords
     */
    public Rectangle getCoords() {
        return coords;
    }
    
    @Override
    public String toString() {
        return String.format("x=%d, y=%d, w=%d, h=%d", coords.x, coords.y, coords.width, coords.height);
    }
    
    public boolean isSelected() {
        
        return isSelected;
        
    }
    
    public void setIsSelected(boolean isSelectd) {
        
        this.isSelected = isSelectd;
        
    }
    
}
