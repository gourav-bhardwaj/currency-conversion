IS_CODE_CHANGE = getGitChanges()
pipeline {
    agent any
	tools {
		maven 'Maven'
	}
    stages {
		stage('Build') {
			when {
				expression {
					IS_CODE_CHANGE == true
				}
			}
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
