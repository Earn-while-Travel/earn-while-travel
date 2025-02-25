pipeline {
    agent any

    environment {
        DB_URL = 'jdbc:oracle:thin:@localhost:1521/XEPDB1'
        DB_USERNAME = 'EARN_WHILE_TRAVEL'
        DB_PASSWORD = 'root'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Print Environment Variables') {
            steps {
                withEnv(["DB_URL=${env.DB_URL}", "DB_USERNAME=${env.DB_USERNAME}", "DB_PASSWORD=${env.DB_PASSWORD}"]) {
                    bat '''
                    echo DB_URL is: %DB_URL%
                    echo DB_USERNAME is: %DB_USERNAME%
                    echo DB_PASSWORD is: %DB_PASSWORD%
                    '''
                }
            }
        }

        stage('Run Application') {
            steps {
                withEnv(["DB_URL=${env.DB_URL}", "DB_USERNAME=${env.DB_USERNAME}", "DB_PASSWORD=${env.DB_PASSWORD}"]) {
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
}