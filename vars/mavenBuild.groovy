#!/usr/bin/env groovy

def call(String mavenGoals = 'clean install') {
    echo "Building with Maven: ${mavenGoals}"
    sh "mvn ${mavenGoals}"
}
