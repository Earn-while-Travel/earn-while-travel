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
                echo "DB_URL is: ${env.DB_URL}"
                echo "DB_USERNAME is: ${env.DB_USERNAME}"
                echo "DB_PASSWORD is: ${env.DB_PASSWORD}"
            }
        }

        stage('Run Application') {
            steps {
                // Run the application after confirming the environment variables
                echo "Running the application with the environment variables..."
                bat '''
                echo "DB_URL is: %DB_URL%"
                echo "DB_USERNAME is: %DB_USERNAME%"
                echo "DB_PASSWORD is: %DB_PASSWORD%"
                java -jar target/demo-0.0.1-SNAPSHOT.jar
                '''
            }
        }
    }
}
