#!/usr/bin/env groovy

def call(String mavenGoals = 'clean install') {
    echo "Building with Maven..."
    sh "mvn ${mavenGoals}"
}
