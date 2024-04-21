rem This command checks the version of the Java compiler
javac --version

rem Pause the script execution to allow the user to view the output
pause

rem This command checks the version of Node.js
node -v

rem Pause the script execution to allow the user to view the output
pause

rem This command installs the packages listed in the package.json file
rem It downloads and installs the required dependencies for the project
npm install

rem Pause the script execution to allow the user to view the output
pause

:: Start the Node.js application in a separate command prompt window
echo Starting Node.js application in a new command prompt...
start cmd /c "node app.js"

:: Wait for a few seconds to let the Node.js application start (adjust the delay as needed)
timeout /t 5 > nul

:: Set the classpath for the Java application
set classpath=lib/opencv-249.jar;lib/opencv_ffmpeg249_64.dll;lib/miglayout-3.6-swing.jar;lib/jcommon-1.0.16.jar;lib/jfreechart-1.0.13-experimental.jar;lib/jfreechart-1.0.13-swt.jar;lib/jfreechart-1.0.13.jar;.;

:: Compile the Java program
javac -d . *.java


:: Run the Java program to save frames to the "frames" folder
java -Djava.library.path="lib" com.Main

:: Optional pause to allow user to review output
pause
