node {
       stage('Checkout'){
          checkout scm
       }

        stage('Build') {
                sh 'sh gradlew clean build -x test'
        }
        stage('Test') {
                sh 'sh gradlew test'
        }
        stage('Jacoco Report') {
               sh 'sh gradlew jacocoTestReport'
        }

        stage('Execute Sonar') {
            sh 'sh run-sonar.sh'
        }

        stage('Build Dev Docker Image') {
            sh 'docker build . -t service-base'
        }

}
