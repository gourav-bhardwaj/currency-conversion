pipeline {
    agent any
    environment {
        PROJECT_ID = 'spcurrencyk8sproject'
        CLUSTER_NAME = 'gov-app-cluster'
        LOCATION = 'us-central1-c'
        CREDENTIALS_ID = 'gke'
    }
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
				usernamePassword(credentialsId: 'DOCKER_CRED', usernameVariable: 'USER', passwordVariable: 'PWD')
				]) {
					sh "mvn clean compile jib:build -Djib.to.auth.username=$USER -Djib.to.auth.password=$PWD"
				}
			}
        }
        stage('Deploy on GKE Cluster') {
            steps {
                step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'deployment.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
            }
        }
    }
}
