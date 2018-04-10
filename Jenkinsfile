pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'mvn clean package'
      }
      post {
        success {
          echo 'Now Archiving...'
          archiveArtifacts artifacts: '**/target/*.war'
        }
      }
    }
    stage('Deploy to Staging') {
      steps {
        build job: 'deploy-to-staging'
      }
    }
    stage('Deploy to Production') {
      steps {
        timeout(time:5, unit:'DAYS') {
          input message: 'Approve PRODUCTION Deployment?'
        }
        build job: 'deploy-to-production'
      }
      post {
        success {
          echo 'Code deployed to PRODUCTION...'
        }
        failure {
          echo 'Code deployment FAILED...'
        }
      }
    }
  }
}
