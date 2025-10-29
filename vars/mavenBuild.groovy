#!/usr/bin/env groovy

def call{
    echo "Building with Maven: ${mavenGoals}"
    sh "mvn clean install"
}
