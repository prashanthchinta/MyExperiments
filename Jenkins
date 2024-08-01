pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/prashanthchinta/MyExperiments.git'
                bat 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                bat 'mvn deploy'
            }
        }
    }

    triggers {
        githubPush()
    }


