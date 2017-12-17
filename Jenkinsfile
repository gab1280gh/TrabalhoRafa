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
        sh 'mvn clean install'
      }
    }
    stage('Dockerhub') {
      steps {
        sh 'mvn clean install'
        sh 'docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD'
      }
    }
    stage('Mariadb denp') {
      steps {
        sh 'mvn clean install'
        sh '''touch $JENKINS_HOME/Dockerfiles/Mariadb/my.cnf
touch $JENKINS_HOME/Dockerfiles/Mariadb/start.sh
'''
      }
    }
    stage('Imagem aplicação') {
      parallel {
        stage('Imagem aplicação') {
          steps {
            sh 'mvn clean install'
            sh '''cd $JENKINS_HOME/Dockerfiles/Tomcat
docker build -t $DOCKERHUB_USERNAME/$APPLICATION_NAME  .
'''
          }
        }
        stage('Imagem mariadb') {
          steps {
            sh 'mvn clean install'
            sh '''cd $JENKINS_HOME/Dockerfiles/Mariadb
docker build -t $DOCKERHUB_USERNAME/$DATABASE_NAME  .
'''
          }
        }
      }
    }
    stage('Upload images') {
      parallel {
        stage('Upload image application') {
          steps {
            sh 'mvn clean install'
            sh 'docker push $DOCKERHUB_USERNAME/$APPLICATION_NAME'
          }
        }
        stage('Upload image mariadb') {
          steps {
            sh 'mvn clean install'
            sh 'docker push $DOCKERHUB_USERNAME/$DATABASE_NAME'
          }
        }
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