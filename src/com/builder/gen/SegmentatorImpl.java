package com.builder.gen;

import com.builder.model.Segment;
import com.builder.model.SegmentSet;
import com.builder.work.Action;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class SegmentatorImpl implements Segmentator {

    protected int threshold;
    protected Dimension imgDim;

    public SegmentatorImpl(int threshold, Dimension imageDim) {

        this.threshold = threshold;
        this.imgDim = imageDim;

    }

    @Override
    public SegmentSet generateSegments(Histogram h, Action a) {

        SegmentSet segSet = new SegmentSet();

        int currentCutStart = 0;
        boolean inSegment = false;

        for (int i = 0; i < h.getLength(); i++) {

            int currentValue = h.getValueAt(i);

            if (!inSegment && currentValue > threshold) {

                currentCutStart = i;
                inSegment = true;

            }
            if (inSegment && currentValue < threshold) {

                //cuts.add(currentCutStart);
                //cuts.add(i);
                
                inSegment = false;
                
                if(i - currentCutStart < 5) continue;
                
                segSet.add(new Segment(new Rectangle(0, currentCutStart, (int)imgDim.getWidth(), i - currentCutStart)));
                
                

            }
            
            a.exec(new Object[]{ i, h.getLength() });
            
        }

        return segSet;
    }

}
