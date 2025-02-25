pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean install'  // Adjust based on your build tool
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
                script {
                    // Ensure the .jar file exists
                    bat 'dir target'
                    
                    // Debugging - print the current working directory
                    bat 'echo Current directory: %cd%'
                    
                    // Verify Java version (optional)
                    bat 'java -version'
                    
                    // Run the application
                    echo 'Running the application...'
                    bat 'java -jar target/demo-0.0.1-SNAPSHOT.jar'
                }
            }
        }
    }
}
