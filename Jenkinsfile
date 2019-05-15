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
            def IMAGE_BASE_NAME="dboss/doers"
            def DOCKER_IMAGE="$IMAGE_BASE_NAME:$BUILD_NUMBER"
            sh "docker build . -t $IMAGE_BASE_NAME"
            sh "docker tag $IMAGE_BASE_NAME $DOCKER_IMAGE"
            sh "docker push $IMAGE_BASE_NAME"
            sh "docker push $DOCKER_IMAGE"
            sh "docker rmi $DOCKER_IMAGE"
            sh "docker rmi $IMAGE_BASE_NAME"
        }

}
