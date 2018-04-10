pipeline {
	agent any

	parameters {
		string(name: 'tomcat_staging', defaultValue: '13.58.245.250', description: 'Staging Server')
		string(name: 'tomcat_production', defaultValue: '13.58.61.247', description: 'Production Server')
	}

	triggers {
		pollSCM('* * * * *')
	}

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

		stage('Deployments') {

			parallel {

				stage('Deploy to Staging') {
					steps {
						sh "scp -i /tmp/tomcat-demo.pem **/target/*.war ec2-user@${params.tomcat_staging}:/var/lib/tomcat/webapps/"
					}
				}

				stage('Deploy to Production') {
					steps {
						timeout(time:5, unit:'DAYS') {
							input message: 'Approve PRODUCTION Deployment?'
						}
						sh "scp -i /tmp/tomcat-demo.pem **/target/*.war ec2-user@${params.tomcat_production}:/var/lib/tomcat/webapps/"
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
	}
}
