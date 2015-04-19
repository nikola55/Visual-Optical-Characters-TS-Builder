package com.builder.gen;

public interface Histogram {

	public static enum HistogramType {

		VERTICAL, HORIZONTAL

	}
	
	int getValueAt(int index);
	
	int getMinimum();
	
	int getMaximum();
	
	int getLength();

	HistogramType getType();

}
