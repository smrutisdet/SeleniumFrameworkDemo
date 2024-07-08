pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'build job is finished...'
            }
        }
         stage('Test') {
            steps {
               checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/smrutisdet/seleniumFrameworkDemo.git']])
               bat 'mvn clean test -Dbrowser=chrome'
            }
        }
        stage('Deploy') {
            steps {
                echo 'deploying build to stage environment'
            }
        }
    }
}
