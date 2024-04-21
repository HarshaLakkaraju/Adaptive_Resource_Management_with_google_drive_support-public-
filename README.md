# Video Processing and Person Detection System

This project consists of two components: a Node.js application and a Java application, working together to process video streams, detect persons in the frames, and upload the frames containing detected persons to a specified Google Drive folder.

it uses a Node.js application and a Java application to process video streams and detect persons in the frames. The java application monitors the `videos` folder  and starts processing them. When a video is detected the person , application processes the video to extract frames of the  detects persons .

Detected person frames are saved in the `frames` folder. The node application  automatically uploads the frames containing detected persons to a specified Google Drive folder whenever changes occur in the `frames` folder.

[use this  README for setting up node synchronize from frames to google drive](overall_doc_other/readme.md).

## Index

1. [Prerequisites](#prerequisites)
2. [Setup and Execution](#setup-and-execution)
3. [Additional Notes](#additional-notes)
4. [Google Drive API Setup](#google-drive-api-setup)
5. [Time Synchronization](#time-synchronization)

## Prerequisites

- **Node.js**: Ensure Node.js is installed on your system.
- **Java**: Make sure Java is installed on your system.
- **Git**: Verify Git is installed on your system.

## Setup and Execution

1. **Run the `run.bat` file**: Execute the file called `run.bat` to perform the following tasks in your terminal:

    - Install Node.js dependencies.
    - Start the Node.js application in a new command prompt.
    - Compile and run the Java program.

    Make sure you have Node.js, Java, and Git set up on your system before running the batch file.

2. **Review Output**: An optional pause at the end of `run.bat` allows you to review the output.

## Additional Notes

- **Adjust Delays**: Modify the `timeout /t` command in `run.bat` as needed to allow the Node.js application to start properly.
- **Classpath Settings**: Ensure that the `classpath` environment variable in `run.bat` is set correctly to include all necessary libraries.
- **Error Handling**: Properly handle any exceptions or errors during execution to ensure smooth operation.

## Google Drive API Setup

For instructions on setting up the Google Drive API and connecting to the Google Drive folder:

- Refer to the inner `README` file within the project directory for detailed instructions on setting up the Google Drive API.
- Follow the instructions to configure your own `credentials.json` file to establish a connection to the Google Drive folder.

## To avoid errors with the Google Drive API

 ensure your system's time is synchronized with the correct time (e.g., IST).

- Use appropriate tools or settings in your operating system to synchronize your system's clock.
- Keeping the correct time will help avoid issues when connecting to the API and interacting with the Google Drive folder.
