# Video Filter

This repository was forked from ddobervich/VideoFilter to figure out why the code wouldn't work for mac. This code works with showing the webcam, and showing images.

When trying to show a video from the disk (such as .mov or .mp4) a <code>NullPointerException</code> gets thrown. The error is shown below:

<code>java.lang.NullPointerException</code>\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at processing.video.Movie.initSink(Unknown Source)</code>\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at processing.video.Movie.initGStreamer(Unknown Source)</code>\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at processing.video.Movie.<init>(Unknown Source)</code>\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at DisplayWindow.displayVideoSourceChoiceDialog(DisplayWindow.java:82)</code>\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at DisplayWindow.initializeImageSource(DisplayWindow.java:47)</code>\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at DisplayWindow.settings(DisplayWindow.java:38)</code>\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at processing.core.PApplet.handleSettings(PApplet.java:951)</code>\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at processing.core.PApplet.runSketch(PApplet.java:10742)</code>\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at processing.core.PApplet.main(PApplet.java:10504)</code>\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at DisplayWindow.getInputInteractively(DisplayWindow.java:297)</code>\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at RunMe.main(RunMe.java:7)</code>

The code from the master branch works correctly on Windows, so the issue is most likely in the <code>lib</code> folder.

# What I Have Tried So Far

1. Downloaded Processing 4.0a3 and downloaded video library. Then copied <code>macosx</code>, <code>gst1-java-core-1.2.0.jar</code>, <code>video.jar</code>, and <code>jna.jar</code> files to <code>lib</code> in this project. No results.
2. Downloaded Video Library of Processing 2.0 beta 5 and copied the same file to <code>lib</code>. No results.
3. checked for updates on the Video Library in Processing 4.0a3, but found no new updates.