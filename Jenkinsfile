pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'Java21'
    }

    stages {

        stage('Clone') {
            steps {
                git https://github.com/iitm23f2001146/Devops_Lab_Project.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('API Test') {
            steps {
                echo 'Run API tests here (Postman/Newman)'
            }
        }
    }

    post {
        success {
            mail to: 'sejal.chavan@cumminscollege.in',
                 subject: 'Build Success',
                 body: 'Your Jenkins build passed successfully!'
        }
        failure {
            mail to: 'sejal.chavan@cumminscollege.in',
                 subject: 'Build Failed',
                 body: 'Your Jenkins build failed!'
        }
    }
}
