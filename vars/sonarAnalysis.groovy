#!/usr/bin/env groovy

def call() {
    echo "Running SonarQube Analysis"
    withSonarQubeEnv('SonarQube') {
        sh 'mvn sonar:sonar'
    }
}
