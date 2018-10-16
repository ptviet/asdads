node {

    withMaven(maven:'maven') {

        stage('Checkout') {
            checkout scm
        }

        stage('Build') {
            sh 'mvn clean install'

            def pom = readMavenPom file:'pom.xml'
            print pom.version
            env.version = pom.version
        }

        stage('Image') {
                def app = docker.build "localhost:5000/payslip:${env.version}"
                app.push()
        }

        stage ('Run') {
            docker.image("localhost:5000/payslip:${env.version}").run('-p 8088:8088 --name payslip')
        }

    }

}