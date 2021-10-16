pipeline {
    agent any
	tools {
		maven 'Maven'
	}
    stages {
		stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
		stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
		stage('Docker build & push') {
            steps {
                sh 'mvn clean compile jib:build'
            }
        }
    }
}
