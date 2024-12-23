pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Test') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/satspai/avactis.git'

                // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean packag"

                // To run Maven on a Windows agent, use
                bat "mvn clean test"
            }
        }
    }
}
