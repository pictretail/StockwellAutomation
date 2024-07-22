# Use the base image with Selenium and Chromium
FROM selenium/standalone-chromium:latest

# Switch to the root user to perform system-level operations
USER root

# Update package lists and install necessary tools
RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        wget \
        unzip \
    && rm -rf /var/lib/apt/lists/*

# Install OpenJDK 11 (or another version you prefer)
RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        openjdk-11-jdk \
    && rm -rf /var/lib/apt/lists/*

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64

# Ensure that the JAVA_HOME/bin directory is in the PATH
ENV PATH $JAVA_HOME/bin:$PATH

# Install Gradle 7.5.1
ENV GRADLE_VERSION 7.5.1
RUN wget -q "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
    && unzip -q "gradle-${GRADLE_VERSION}-bin.zip" -d /opt \
    && rm -f "gradle-${GRADLE_VERSION}-bin.zip"
ENV GRADLE_HOME /opt/gradle-${GRADLE_VERSION}
ENV PATH $GRADLE_HOME/bin:$PATH

# Verify Gradle installation
RUN gradle --version


# Create a new folder in the user's home directory for the testing project
RUN mkdir -p /home/seluser/stockwellautomation

# Copy the entire testing project into the image
COPY . /home/seluser/stockwellautomation

# Set the working directory to the new folder
WORKDIR /home/seluser/stockwellautomation/qa-stockwell

# Set read permissions recursively for the entire project
RUN sudo chmod -R +r /home/seluser/stockwellautomation/qa-stockwell
RUN ls -lR /home/seluser/stockwellautomation/qa-stockwell

# Make gradlew executable
RUN chmod +x ./gradlew

# Run the Selenium scripts using gradle
#CMD ["sh", "-c", "gradle clean build --refresh-dependencies"]
#CMD ["sh", "-c", "gradle test"]
CMD ["./gradlew", "clean", "test", "--info"]
