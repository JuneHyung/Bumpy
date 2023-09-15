pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // GitHub 프로젝트 클론 및 백엔드 브랜치로 전환
                checkout([$class: 'GitSCM', branches: [[name: 'main']],
                          doGenerateSubmoduleConfigurations: false,
                          submoduleCfg: [],
                          userRemoteConfigs: [[credentialsId: 'Patabear', url: 'git@github.com:JuneHyung/Bumpy.git']]])
            }
        }

        stage('npm build')
        {
            steps {
                dir('develop/frontend/bumpy-nuxt3') {
                    sh 'npm install'
                    sh 'npm run generate'
                }
            }
        }

        stage('Build') {
            steps {
                // Gradle을 사용하여 프로젝트 빌드
                dir('develop/backend') {
                    sh 'chmod +x gradlew'
                    sh './gradlew build'
                }
            }
        }

        stage('Copy Files to Remote Server - Frontend') {
            steps {
                // 빌드된 파일을 원격 서버로 복사
                dir('develop/frontend/bumpy-nuxt3/.output/public') {
                    sshagent(credentials: ['server1ssh']) {
                        sh 'ssh server1@172.30.1.50 "rm -rf /home/server1/nginx/dist/* && cp -r /home/server1/jenkins/jenkins_home/workspace/bumpy-front/develop/frontend/bumpy-nuxt3/.output/public/* /home/server1/nginx/dist/"'
                    }
                }
            }
        }

        stage('Copy Files to Remote Server - backend') {
            steps {
                // 빌드된 파일을 원격 서버로 복사
                dir('develop/backend/build/libs') {
                    sshagent(credentials: ['server1ssh']) {
                        sh 'ssh server1@172.30.1.50 "cp /home/server1/jenkins/jenkins_home/workspace/bumpy-back/develop/backend/build/libs/* /home/server1/bumpy/jar/"'
                    }
                }
            }
        }

        stage('Deploy with Docker Compose - Frontend') {
            steps {
                // 원격 서버에서 Docker Compose 실행
                sshagent(credentials: ['server1ssh']) {
                    sh 'ssh server1@172.30.1.50 "cd /home/server1/nginx && docker-compose down && docker-compose up -d --build"'
                }
            }
        }

        stage('Deploy with Docker Compose - backend') {
            steps {
                // 원격 서버에서 Docker Compose 실행
                sshagent(credentials: ['server1ssh']) {
                    sh 'ssh server1@172.30.1.50 "cd /home/server1/bumpy && docker-compose down && docker-compose up -d --build"'
                }
            }
        }
    }
}
