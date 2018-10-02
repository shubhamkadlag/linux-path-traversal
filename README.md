# linux-path-traversal

A dummy CLI application which supports below Linux commands:
cd
ls
mkdir
rm
pwd
session clear

The application does not create real directories on the disk.
The application internally uses a custom implentation of N-ary tree for storing the directory structure.
The code is designed in accordance with Service Locator Pattern in order to provide good extensibility.
The services are Cached in an instance of Class com.demo.util.Cache in order to provide good extensibility and performance.
All the services are stored in Context: Instance of Class com.demo.util.InitialContext.
The application uses Swing for GUI interaction. 

The application can be run using below command:
java -jar application.jar

Please ensure the java version: 

The application has been compiled and built on 32-bit
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) Client VM (build 25.181-b13, mixed mode, sharing)

Please ensure there are no restrictions by java security manager for changing the STDOUT and STDERR to a different location, as this application
changes the STDOUT and STDERR to a custom Java Console created by application.

Like many of the Linux shell , backspace, Combination keys : Ctrl,Shift, Alt have been disabled. However it is not recommended to use
keyboard shortcuts for copy, paste as it may cause unexpected behaviour. If you accidently type wrong command, press enter and the application
will continue to run.






