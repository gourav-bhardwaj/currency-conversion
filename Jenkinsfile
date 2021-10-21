pipeline {
    agent any
    environment {
        CLUSTER_NAME = 'my-cluster'
        SERVER_URL = 'https://35.194.38.235'
        CREDENTIALS_ID = 'kubecred'
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
        stage('Deploy to GKE') {
            steps{
                withKubeConfig([credentialsId: env.CREDENTIALS_ID,
                    serverUrl: env.SERVER_URL,
                    clusterName: env.CLUSTER_NAME
                    ]) {
					sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.20.5/bin/linux/amd64/kubectl"'  
					sh 'chmod u+x ./kubectl'  
					sh './kubectl delete deploy,svc -l app=currency-conversion'
					sh './kubectl apply -f manifest.yaml'
				}
            }
        }
    }
}
