package com.builder.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractListModel;

public class SegmentSet extends AbstractListModel<Segment> implements Iterable<Segment> {

    private List<Segment> segments;

    public SegmentSet() {
        segments = new ArrayList<>();
    }

    @Override
    public Iterator<Segment> iterator() {

        return new Iterator<Segment>() {

            List<Segment> segs = segments;
            private int currentSegment = 0;

            @Override
            public boolean hasNext() {

                if (currentSegment < segs.size()) {
                    return true;
                }

                return false;
            }

            @Override
            public Segment next() {

                return segs.get(currentSegment++);

            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            
            
        };
    }

    public boolean add(Segment e) {

        return segments.add(e);
    }

    @Override
    public int getSize() {

        return segments.size();
    }

    @Override
    public Segment getElementAt(int index) {
        if (index >= segments.size()) {
            throw new IndexOutOfBoundsException();
        }
        return segments.get(index);
    }

    

    
}
