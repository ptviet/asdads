node {

   stage('Clone Repository') {
        // Get some code from a GitHub repository
        git 'https://github.com/ptviet/payslip.git'
    
   }
   stage('Build Maven Image') {
        // docker.build("maven-build")
        sh "docker build -t ptviet/payslip ."
   }

   stage('Deploy Spring Boot Application') {
       
        sh "docker run -p 8088:8088 ptviet/payslip"
   }

}