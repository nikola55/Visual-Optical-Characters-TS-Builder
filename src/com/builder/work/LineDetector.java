/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.builder.work;

import com.builder.gen.Histogram;
import com.builder.gen.HistogramFactory;
import com.builder.gen.Segmentator;
import com.builder.gen.SegmentatorImpl;
import com.builder.model.Project;
import com.builder.model.SegmentSet;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

/**
 *
 * @author Nikola
 */
public class LineDetector implements Runnable {

    private final Project proj;
    private final JProgressBar pb;
    private final JDialog parentDialog;

    public LineDetector(Project proj, JProgressBar pb, JDialog parentDialog) {

        this.proj = proj;
        this.pb = pb;
        this.parentDialog = parentDialog;

    }

    @Override
    public void run() {

        BufferedImage binary = proj.getBinary();

        Histogram hist = HistogramFactory.createHistogram(binary, Histogram.HistogramType.VERTICAL, new Action() {

            @Override
            public void exec(Object[] params) {
                
                double prog = (int) params[0];
                double total = (int) params[1];
                
                synchronized (pb) {
                    pb.setValue(pb.getValue() + (int) (total / prog * 50.0));
                }

            }

        });

        proj.setHist(hist);

        Segmentator s = new SegmentatorImpl(255 * 5, new Dimension(binary.getWidth(), binary.getHeight()));
        SegmentSet segs = s.generateSegments(hist, new Action() {

            @Override
            public void exec(Object[] params) {
                
                double prog = (int) params[0];
                double total = (int) params[1];
                
                synchronized (pb) {
                    pb.setValue(pb.getValue() + (int) (total / prog * 50.0));
                }

            }
        });

        proj.setLineSegments(segs);

        synchronized (parentDialog) {
            parentDialog.setVisible(false);
        }
    }

}
