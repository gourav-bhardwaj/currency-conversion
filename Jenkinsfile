pipeline {
    agent any
    tools {
        maven "Maven"
    }
    stages {
        stage('Build') {
            steps {
               sh 'mvn clean build -DskipTests'
            }
        }
        stage('Test') {
            steps {
               sh 'mvn clean test'
            }
        }
    }
}
