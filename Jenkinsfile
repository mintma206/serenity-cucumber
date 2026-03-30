pipeline {
    agent any

    tools {
        jdk 'jdk17'           // 你刚才配置的 JDK Name
        maven 'maven3.9'     // 你刚才配置的 Maven Name
    }

    stages {
        stage('Checkout Code') {
            steps {
               // 临时禁用 SSL 验证，解决 GnuTLS 问题
                withEnv(['GIT_SSL_NO_VERIFY=true']) {
                    git branch: 'main',                    // 如果你的默认分支是 master，请改成 'master'
                        credentialsId: 'github-pat',
                        url: 'https://github.com/mintma206/serenity-cucumber.git'
                }
            }
        }

        stage('Run Serenity Tests') {
            steps {
                sh '''


                    mvn clean verify serenity:aggregate \
                    -Dfile.encoding=UTF-8 \
                    -Dmaven.test.skip=false \
                    -Dmaven.repo.local=/tmp/m2-repo \
                    --no-transfer-progress \
                    -Dmaven.wagon.http.ssl.insecure=true \
                    -Dmaven.wagon.http.ssl.allowall=true \
                    -Dmaven.wagon.http.ssl.ignore.validity.dates=true \
                    -Denvironment=ci
                '''
            }
        }
    }

    post {
        always {
            // 显示 Serenity 完整报告（推荐）
            publishHTML (
                target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: "Serenity BDD Report",
                    reportTitles: "Serenity Report"
                ]
            )

            // 归档完整报告（支持下载）
            archiveArtifacts artifacts: 'target/site/serenity/**',
                           allowEmptyArchive: true,
                           fingerprint: false


        }

        success {
            echo '🎉 Serenity 测试执行成功！'
        }

        failure {
            echo '❌ Serenity 测试执行失败！'
        }
    }
}