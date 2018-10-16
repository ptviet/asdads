node {

   stage('Clone Repository') {
        // Get some code from a GitHub repository
        git 'https://github.com/ptviet/payslip.git'
    
   }
   stage('Build Maven Image') {
        docker.build("maven-build")
   }
   
   stage('Run Maven Container') {

        //Run maven image
        sh "docker run --rm --name maven-build-container maven-build"
   }
   
   stage('Deploy Spring Boot Application') {
       
        sh "docker run --name java-deploy-container --volumes-from maven-build-container -d -p 8088:8088 ptviet/payslip"
   }

}