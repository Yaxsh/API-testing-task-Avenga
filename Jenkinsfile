pipeline {
    agent any

    tools {
        maven 'Maven_3.9'
        jdk 'JDK_17'
    }

    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Yaxsh/API-testing-task-Avenga.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: "${env.ALLURE_RESULTS_DIR}"]]
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/**/*.xml', allowEmptyArchive: true
        }
    }
}
