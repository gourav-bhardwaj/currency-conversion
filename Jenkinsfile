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
				withCredentials([
				usernamePassword(credentials: 'DOCKER_CRED', usernameVariable: USER, passwordVariable: PWD)
				]) {
					sh "mvn clean compile jib:build -Djib.to.auth.username=${USER} -Djib.to.auth.password=${PWD}"
				}
			}
        }
    }
}
