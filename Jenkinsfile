pipeline {

  agent any

  stages {

    stage('Checkout Source') {
      steps {
        git url:'https://github.com/raju1982/selenium-docker-test.git', branch:'main'
      }
    }

      stage("Build image") {
            steps {
                script {
                    myapp = docker.build("rkandpal/selenium-docker-test:${env.BUILD_ID}")
                }
            }
      }

      stage("Push image") {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                            myapp.push("latest")
                            myapp.push("${env.BUILD_ID}")
                    }
                }
            }
      }


    stage("Execute Test") {
          steps {
            sh """
              docker network create grid

              docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub:4.0.0-rc-2-prerelease-20210908

              docker run -d --net grid --name chrome-node -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
                  selenium/node-chrome:4.0.0-rc-2-prerelease-20210908
            """

            sleep time: 5, unit: 'SECONDS'

            sh """
               docker run --net grid -d --name test9001 rkandpal/selenium-docker-test

               docker exec test9001 /bin/sh -c "cd /home/app; mvn -Dmaven.test.failure.ignore=false clean test"

               docker rm -f  test9001 chrome-node selenium-hub

               docker network rm grid

               docker rmi rkandpal/selenium-docker-test
               """
          }
    }
  }

  post {
    always {
              echo 'Deleting docker containers and images.'
              sh """
              docker rm -f  test9001 chrome-node selenium-hub
              docker network rm grid
              docker rmi rkandpal/selenium-docker-test
              """
          }
  }

}