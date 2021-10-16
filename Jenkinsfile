pipeline {
    agent any
	tools {
		maven 'Maven'
	}
    environment {
        CLUSTER_NAME_PROD = 'my-cluster'          
    }
    stages {
		stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
		stage('Test') {
            steps {
				echo 'Testing started dear gourav kumar ----------------------------------------------------------------'
                sh 'mvn clean test'
            }
        }
	stage('Docker build & push') {
		steps {
			withCredentials([
			usernamePassword(credentialsId: 'DOCKER_CRED', usernameVariable: 'USER', passwordVariable: 'PWD')
			]) {
				sh "mvn clean compile jib:build -Djib.to.auth.username=$USER -Djib.to.auth.password=$PWD"
			}
		}
        }
	    stage('Connect GKE Cluster') {
		    steps {
			    sh "gcloud container clusters get-credentials ${CLUSTER_NAME_PROD} --zone us-central1-c"
		    }
	    }
    }
}
