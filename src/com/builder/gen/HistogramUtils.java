package com.builder.gen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.builder.gen.Histogram.HistogramType;

public class HistogramUtils {
	
	private static final int WHITE = 0xffffffff;
	private static final int BLACK = 0x0;
	
	public static BufferedImage asImage(Histogram hist) {
		
		int imageWidth,
			imageHeight;
		
		if(hist.getType().equals(HistogramType.VERTICAL)) {
			
			imageWidth = hist.getMaximum() / 255;
			imageHeight = hist.getLength();
			
		} else if (hist.getType().equals(HistogramType.HORIZONTAL)) {
			
			imageWidth = hist.getLength();
			imageHeight = hist.getMaximum() / 255;
			
		} else {
			
			throw new IllegalArgumentException();
			
		}
		
		BufferedImage img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = img.createGraphics();
		graphics.setPaint(new Color(WHITE));
		graphics.fillRect ( 0, 0, img.getWidth(), img.getHeight() );
		graphics.dispose();
		
		if(hist.getType().equals(HistogramType.VERTICAL)) {
			
			for (int i = 0; i < imageHeight; i++) {
				
				int maxX = hist.getValueAt(i) / 255;
				
				for (int j = 0; j < maxX; j++) {
					
					img.setRGB(j, i, BLACK);
					
				}
				
			}
			
		} else if (hist.getType().equals(HistogramType.HORIZONTAL)) {
			
			for (int i = 0; i < imageWidth; i++) {
				
				int maxY = hist.getValueAt(i) / 255;
				
				for (int j = 0; j < maxY; j++) {
					
					img.setRGB(i, j, BLACK);
					
				}
				
			}
			
		}
		
		return img;
	}
	
}
