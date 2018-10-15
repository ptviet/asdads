pipeline {
    agent { dockerfile true }
    stages {
        stage('Initialize') {
            steps {
                sh '''
                   echo "PATH = ${PATH}"
                   mvn --version
                   '''
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
          steps {
          sh 'mvn test'
          }
        }
        stage('Deliver') {
          steps {
            sh 'bash ./deliver.sh'
          }
        }
    }
}
