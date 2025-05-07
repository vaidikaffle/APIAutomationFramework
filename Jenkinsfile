pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Start JSON Server') {
            steps {
                sh '''#!/bin/bash
                echo "Starting JSON Server..."
                cd /var/lib/jenkins/workspace/${JOB_NAME}
                json-server --watch db.json --port 3000 &
                SERVER_PID=$!
                echo "JSON Server started with PID: $SERVER_PID"
                sleep 5
                '''
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Stop JSON Server') {
            steps {
                sh 'kill $SERVER_PID'
            }
        }
    }
}