set classpath=lib/opencv-249.jar;lib/opencv_ffmpeg249_64.dll;lib/miglayout-3.6-swing.jar;lib/jcommon-1.0.16.jar;lib/jfreechart-1.0.13-experimental.jar;lib/jfreechart-1.0.13-swt.jar;lib/jfreechart-1.0.13.jar;.;
javac -d . *.java
java -Djava.library.path="lib" com.Main
pause