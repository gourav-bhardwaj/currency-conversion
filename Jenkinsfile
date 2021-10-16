pipeline {
    agent any
    tools {
        maven "Maven"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: "${BRANCH_NAME}"]], extensions: [], userRemoteConfigs: [[credentialsId: 'GIT_CREDS', url: 'https://github.com/gourav-bhardwaj/currency-conversion.git']]])
            }
        }
       
    }
}
