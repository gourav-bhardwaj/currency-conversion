pipeline {
    agent{
			label 'helm'
		}

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Docker Build & Push') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'

                // Run maven build.
                sh "mvn clean compile jib:build"
            }
        }
        stage('Helm Deploy') {
            steps {
               def repo = "https://github.com/gourav-bhardwaj/gv-helm-repo"; 
               sh "helm repo add gv-helm-repo ${repo}"
            }
            steps {
              script {
					      openshift.withCluster() {
                  sh "helm upgrade --install currency-conversion-app gv-helm-repo/example-app --values example-app/currency-conversion-values.yaml --wait"
                }
              }
            }
        }
    }
}
