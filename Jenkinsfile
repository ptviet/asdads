node {
        stage('Checkout') {
            checkout scm
        }

        stage('Build') {
            sh 'mvn clean install'
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Image') {
                def app = docker.build "ptviet/payslip"
                app.push()
        }

        stage ('Run') {
            docker.image("ptviet/payslip").run('-p 8088:8088 --name payslip')
        }

}