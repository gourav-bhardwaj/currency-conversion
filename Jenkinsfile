pipeline {
    agent any
	tools {
		maven 'Maven'
	}
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'trunk']], extensions: [], userRemoteConfigs: [[credentialsId: 'GITHUB_CRED', url: 'https://github.com/gourav-bhardwaj/currency-conversion.git']]])
            }
        }
		stage('Build') {
            steps {
                sh 'mvn clean build'
            }
        }
    }
}
