pipeline {
    agent any

    environment {
        JAR_FILE = "build/libs/*.jar"
        IMAGE_NAME = "tasker-image"
        REGISTRY_URL = "415154241470.dkr.ecr.ap-northeast-2.amazonaws.com"
        ECR_REPO = "tasker-ecr-repo"
    }

    stages {
        stage('Build') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew build'
            }
        }

        stage('Build Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'c026b6b8-e134-463a-a641-e8b40ea275e2', passwordVariable: 'JASYPT_PASSWORD', usernameVariable: '')]) {
                    script {
                        def dockerImage = docker.build("${IMAGE_NAME}:${BUILD_NUMBER}")
                        dockerImage.inside {
                            sh "cp ${JAR_FILE} app.jar"
                            sh "java -Djasypt.encryptor.password=${JASYPT_PASSWORD} -jar /app.jar"
                        }
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry("https://${REGISTRY_URL}") {
                        def image = dockerImage.push("${ECR_REPO}:${BUILD_NUMBER}")
                    }
                }
            }
        }
    }
}