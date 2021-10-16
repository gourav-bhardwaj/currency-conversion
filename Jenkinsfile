pipeline {
    agent any
	tools {
		maven 'Maven'
	}
    environment {
        PROJECT_ID = 'spcurrencyk8sproject'
        LOCATION = 'us-central1-c'
        CREDENTIALS_ID = 'gke'
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
	stage('Deploy to GKE production cluster') {
            	steps {
                        step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME_PROD, location: env.LOCATION, manifestPattern: 'deployment.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
            	}
        }
    }
}
