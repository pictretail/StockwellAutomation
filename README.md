# Stockwell webapp automation

This repository contains an automation framework for testing stockwell web application using TestNG and Gradle. This document provides instructions for setting up, configuring, and running the framework.

## Prerequisites

- **Java JDK**: Ensure you have JDK 8 or later installed. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or use OpenJDK.

  - Verify installation: `java -version`

- **Gradle**: Install Gradle 7.5.1. You can download it from [the official Gradle website](https://gradle.org/install/).

  - Verify installation: `gradle -v`

- **IDE**: Use an Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or Visual Studio Code for Java development.

## Getting Started

### Clone the Repository

First, clone the repository to your local machine:

```bash

### Open your terminal or command prompt:

Navigate to the directory where you want to clone the project:
cd /path/to/your/desired/directory
Replace /path/to/your/desired/directory with the actual path where you want to clone the repository.

Clone the repository using the following command:
git clone https://github.com/pictretail/StockwellAutomation.git

Navigate to the cloned project directory:
cd StockwellAutomation

### Running Tests from an IDE

To run the test cases from your IDE, follow these steps based on your chosen IDE:

- **Eclipse**
 -Open the Project: 
    Launch Eclipse and import the project.
    Go to File > Import... > Gradle > Existing Gradle Project and follow the prompts to import the project.

-Configure TestNG:
    Ensure TestNG is installed in Eclipse (you can install it via the Eclipse Marketplace if necessary).
    Right-click on the project and select Properties.
    Go to Java Build Path and ensure the JDK is properly configured.

-Run Tests:
    Open the test class you want to run.
    Right-click inside the test class or method and select Run As > TestNG Test.
    To run all tests, right-click on the src/test/java folder and select Run As > TestNG Test.


- **Visual Studio Code**
 -Open the Project: 
    Launch Visual Studio Code and open the cloned StockwellAutomation folder.

 -Install Extensions:
    Install the "Java Extension Pack" and "Gradle for Java" extensions from the Extensions view (Ctrl+Shift+X).
    Import Gradle Project:

 -Run Tests:
    Use the built-in terminal
    To run a specific test, you need to use the terminal command: gradle test 


- **IntelliJ IDEA**
 -Open the Project: 
    Launch IntelliJ IDEA and open the cloned StockwellAutomation project.

-Import Gradle Project:
    If prompted, select "Import Gradle Project" and follow the wizard to import the project.
    Alternatively, you can manually import the Gradle project by right-clicking on the build.gradle file in the Project view and selecting "Import Gradle Project."

-Configure TestNG:
    Go to File > Project Structure > Modules.
    Make sure the module SDK is set to the correct JDK version (JDK 8 or later).
    Click OK to save the configuration.

-Run Tests:
    Open the test class you want to run.
    Right-click inside the test class or method and select Run 'TestName'.
    Alternatively, you can run all tests by right-clicking on the src/test/java directory and selecting Run 'All Tests'.




