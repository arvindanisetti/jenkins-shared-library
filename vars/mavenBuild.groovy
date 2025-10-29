#!/usr/bin/env groovy

def call(String mavenGoals = 'mvn clean') {
    echo "Building with Maven: ${mavenGoals}"
    sh "mvn ${mavenGoals}"
}
