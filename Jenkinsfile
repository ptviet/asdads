pipeline {
    agent { dockerfile true }
    stages {
        stage('Test') {
            steps {
                sh '''
                   echo "PATH = ${PATH}"
                   mvn --version
                   '''
            }
        }
    }
}
