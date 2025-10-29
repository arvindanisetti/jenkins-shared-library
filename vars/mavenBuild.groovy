#!/usr/bin/env groovy

def call(String mavenGoals = 'clean clean') {
    echo "Building with Maven: ${mavenGoals}"
    sh "mvn ${mavenGoals}"
}
