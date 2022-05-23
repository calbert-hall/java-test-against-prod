# Applitools Autozone: Java

### READ FIRST: Autozone Structure 

The purpose of this repo is to show how you can establish an immediate baseline from the production environment, and then 
compare pre-prod to it. This uses Applitools urls, but the principle is identical. 

## Prerequisites:

1. Java is installed on your machine.
   
   * [Install JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
   
2. Maven is installed on your machine, JAVA_HOME environment variable is created, added to PATH, path to Maven is added to PATH too

   * [Install Maven, configure enviroment variables](https://maven.apache.org/install.html)

3. Chrome Webdriver is on your machine and is in the PATH. Here are some resources from the internet that'll help you.

   * https://splinter.readthedocs.io/en/0.1/setup-chrome.html
   * https://stackoverflow.com/questions/38081021/using-selenium-on-mac-chrome
   * https://www.youtube.com/watch?time_continue=182&v=dz59GsdvUF8

4. IntelliJ IDEA (or Eclipse) is installed on your machine if you want to run example from IDE.

   * [Install IntelliJ IDEA](https://www.jetbrains.com/idea/download/)  or [Install Eclipse](https://www.eclipse.org/downloads/)
     

### Running the first example

1. `mvn install`
