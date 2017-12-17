pipeline {
  agent {
    docker {
      image 'maven:3.5-alpine'
      args '-v /root/.m2:/root/.m2'
    }
    
  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }
  }
  environment {
    DOCKERHUB_USERNAME = 'vitimfk7'
    DOCKERHUB_PASSWORD = 'pk79c1'
    DATABASE_NAME = 'mariadb'
    APPLICATION_NAME = 'course-suggestion'
  }
}