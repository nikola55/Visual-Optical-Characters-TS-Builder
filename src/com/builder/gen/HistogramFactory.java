package com.builder.gen;

import java.awt.image.BufferedImage;

import com.builder.gen.Histogram.HistogramType;
import com.builder.work.Action;

public class HistogramFactory {

	public static Histogram createHistogram(BufferedImage image,
			final HistogramType type, Action a) {

		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

		int histogramRange, valueRange;

		if (type.equals(HistogramType.VERTICAL)) {

			histogramRange = image.getHeight();
			valueRange = image.getWidth();

		} else if (type.equals(HistogramType.HORIZONTAL)) {

			histogramRange = image.getWidth();
			valueRange = image.getWidth();

		} else {

			throw new IllegalArgumentException();

		}

		final int histogram[] = new int[histogramRange];

		for (int i = 0; i < histogram.length; i++) {

			histogram[i] = 0;

			for (int j = 0; j < valueRange; j++) {

				int x = type.equals(HistogramType.VERTICAL) ? j : i;
				int y = type.equals(HistogramType.VERTICAL) ? i : j;

				int color = image.getRGB(x, y);

				histogram[i] += (0xff - (color & 0xff))
						+ (0xff - ((color >> 8) & 0xff))
						+ (0xff - ((color >> 16) & 0xff));

			}
                        
                        a.exec(new Object[]{ i, histogram.length });
                        
			histogram[i] = histogram[i] / 3;

			if (histogram[i] > max) {
				max = histogram[i];
			}
			if (histogram[i] < min) {
				min = histogram[i];
			}
		}

		final int histogramMaximum = max;
		final int histogramMinimum = min;

		return new Histogram() {

			@Override
			public HistogramType getType() {

				return type;

			}

			@Override
			public int getValueAt(int index) {

				if (index >= histogram.length)
					throw new IndexOutOfBoundsException();

				return histogram[index];
			}

			@Override
			public int getLength() {

				return histogram.length;

			}

			@Override
			public int getMinimum() {

				return histogramMinimum;
			}

			@Override
			public int getMaximum() {

				return histogramMaximum;
			}

		};

	}

}
