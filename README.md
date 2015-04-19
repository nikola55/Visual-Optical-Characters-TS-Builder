# Visual-Optical-Characters-TS-Builder
Simple GUI application that speeds up a little the process of creating OCR training set from text images.

The main intention is creation of positive/negative examples for a given class. 

It is easy to use: 

1. Import the image in the application (File -> new)

2. Chose the location for positive and negative example (The two must be placed in separate folders)

3. Adjust the threshold for the image.

4. Detect lines 

5. Pick the line you want to segment and perform sliding window through it.

6. On each slide mark the example as either positive or negative  (The examples will be saved in the corresponding folder from step 2).

A sample screenshot from the UI.

![alt tag](http://s28.postimg.org/g3vfiyqfh/Untitled.png)
