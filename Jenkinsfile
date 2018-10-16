node {
        stage('Checkout') {
            checkout scm
        }

        stage('Build') {
            sh 'mvn clean install'
        }

        stage('Test') {
             sh 'mvn test'
        }

        stage('Image') {
                def customImage = docker.build("ptviet/payslip:${env.BUILD_ID}")
                customImage.push()
        }

        stage ('Run') {
            docker.image("ptviet/payslip:${env.BUILD_ID}").run('-p 8088:8088 --name payslip')
        }

}