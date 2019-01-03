#!/usr/bin/env groovy

//noinspection GroovyUnusedAssignment
@Library(['eng-build-utils']) _

String[] jobNameItems = (env.JOB_NAME).split('/')
String   buildType = jobNameItems[jobNameItems.length - 2].take(2)

def stg = ""

def params = [
    [
		BASE_URL: "https://dev.getchute.com",
		ENV: "qa"
	], 
    [
		BASE_URL: "https://staging.getchute.com",
		ENV: "staging"
	], 
    [
		BASE_URL: "https://api.getchute.com", 
		ENV: "prod"
	] 
]

pipeline {
  agent {
    label "chute-jenkins-android-sdk"
  }
  parameters {
    string(name: 'APP_ID', defaultValue: 'XXXXXXXXX', description: 'App ID')
    string(name: 'APP_SECRET', defaultValue: 'XXXXXXXXX', description: 'App secret')
  }
  options {
    timestamps()
    skipDefaultCheckout true
  }
  stages {
    stage("Checkout") {
      steps {
        checkout([
            $class                           : 'GitSCM',
            branches                         : scm.branches,
            doGenerateSubmoduleConfigurations: false,
            userRemoteConfigs                : scm.userRemoteConfigs,
            extensions                       : scm.extensions
        ])
      }
    }
    stage("Install dependencies") {
      steps {
        script {
          sh "apt-get update"
		  sh "apt-get install maven -y"
        }
      }
    }
    stage("Running unit tests") {
      steps {
		sh "./gradlew clean"
        sh "./gradlew :sdk:test"
        junit 'sdk/build/test-results/**/*.xml'
      }
    }
    stage("Generate apk and publish") {
      when {
        expression {
          return buildType == 'IB' || buildType == 'RC'
        }
      }
      steps {
		script {
			params.each { param ->
				script {
					stg = param['ENV']
					sh "export BASE_URL=${param['BASE_URL']}"
                    sh "echo BASE_URL=${param['BASE_URL']}"
			        sh "./gradlew :sdk:build"
				}
				withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'service.tc.nexus', usernameVariable: 'tcUser', passwordVariable: 'tcPassword']]) {
              		sh "mvn deploy:deploy-file -DgroupId=com.getchute.android.sdk.v1 -DartifactId=library -Dclassifier=${stg} -Dversion=1.0.${BUILD_NUMBER}  -DgeneratePom=true -Dpackaging=aar -DrepositoryId=chute-apk -Durl=https://${tcUser}:${tcPassword}@nexus.devfactory.com/repository/chute-apk -Dfile=sdk/build/outputs/aar/sdk-release.aar"
				}
      		}
    	}
	  }
	}
  }
  post {
    always {
      script {
        cleanWs()
      }
    }
  }
}
