package com.builder.gen;

import com.builder.model.SegmentSet;
import com.builder.work.Action;



public interface Segmentator {
	
	public SegmentSet generateSegments(Histogram h, Action a);
	
}
