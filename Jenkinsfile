pipeline {
    agent any
    tools {
        maven "Maven"
    }
    stages {
        stage('Init') {
            steps {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
            }
        }
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
