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
                echo "Changing directory to the workspace..."
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
                sh '''#!/bin/bash
                echo "Stopping JSON Server..."
                if [ -f json_server.pid ]; then
                    SERVER_PID=$(cat json_server.pid)
                    if ps -p $SERVER_PID > /dev/null; then
                        kill $SERVER_PID
                        echo "JSON Server with PID $SERVER_PID stopped."
                    else
                        echo "Process $SERVER_PID not running."
                    fi
                else
                    echo "PID file not found. JSON Server may not have started properly."
                fi
                '''
            }
        }
    }

//   post {
//          always {
//              mail to: 'vaidiksoni96@gmail.com',
//                   subject: "Jenkins Build (${JOB_NAME} - ${BUILD_NUMBER})",
//                   body: """Build Status: ${currentBuild.result}
//
//                   Check console output at ${BUILD_URL} to view the results.
//                   """
//          }
//          failure {
//              mail to: 'vaidiksoni96@gmail.com',
//                   subject: "Jenkins Build Failed (${JOB_NAME} - ${BUILD_NUMBER})",
//                   body: """Build Status: FAILED
//
//                   The API automation tests have failed.
//
//                   Check console output at ${BUILD_URL} for details.
//                   """
//          }
//          success {
//              mail to: 'vaidiksoni96@gmail.com',
//                   subject: "Jenkins Build Successful (${JOB_NAME} - ${BUILD_NUMBER})",
//                   body: """Build Status: SUCCESS
//
//                   The API automation tests have passed successfully.
//
//                   Check console output at ${BUILD_URL} for details.
//                   """
//          }
//          unstable {
//              mail to: 'vaidiksoni96@gmail.com',
//                   subject: "Jenkins Build Unstable (${JOB_NAME} - ${BUILD_NUMBER})",
//                   body: """Build Status: UNSTABLE
//
//                   The API automation tests have reported some unstable results.
//
//                   Check console output at ${BUILD_URL} for details.
//                   """
//          }
//          aborted {
//              mail to: 'vaidiksoni96@gmail.com',
//                   subject: "Jenkins Build Aborted (${JOB_NAME} - ${BUILD_NUMBER})",
//                   body: """Build Status: ABORTED
//
//                   The Jenkins build was aborted.
//
//                   Check console output at ${BUILD_URL} for details.
//                   """
//          }
//      }
}