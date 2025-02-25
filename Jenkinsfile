pipeline {
    agent any
    environment {
        RUN_LOCALLY = 'true'  // Set to 'true' to run locally, 'false' or not set to skip
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean install'
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
                    
                    // Check if we are running locally
                    if (env.RUN_LOCALLY == 'true') {
                        echo 'Running the application locally...'

                        // Ensure that DB_URL, DB_USERNAME, and DB_PASSWORD are set locally before running
                        echo 'Setting up environment variables for local run:'

                        // Set local environment variables for DB connection
                        bat 'set DB_URL=jdbc:oracle:thin:@localhost:1521/XEPDB1'
                        bat 'set DB_USERNAME=EARN_WHILE_TRAVEL'
                        bat 'set DB_PASSWORD=root'

                        // Run the application
                        
                    } else {
                        echo 'Skipping application run in Jenkins pipeline.'
                        bat 'java -jar target/demo-0.0.1-SNAPSHOT.jar'
                    }
                }
            }
        }
    }
}
