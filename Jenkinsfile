pipeline {
    agent any

    environment {
        DB_URL = 'jdbc:oracle:thin:@localhost:1521/XEPDB1' // Overrides global DB_URL
        DB_USERNAME = 'EARN_WHILE_TRAVEL' // Overrides global DB_USERNAME
        DB_PASSWORD = 'root' // Overrides global DB_PASSWORD
    }

    stages {
        stage('Print Environment Variables') {
            steps {
                bat '''
                echo DB_URL is: %DB_URL%
                echo DB_USERNAME is: %DB_USERNAME%
                echo DB_PASSWORD is: %DB_PASSWORD%
                '''
            }
        }

        stage('Run Application') {
            steps {
                bat '''
                echo DB_URL is: %DB_URL%
                echo DB_USERNAME is: %DB_USERNAME%
                echo DB_PASSWORD is: %DB_PASSWORD%
                java -jar target/demo-0.0.1-SNAPSHOT.jar
                '''
            }
        }
    }
}