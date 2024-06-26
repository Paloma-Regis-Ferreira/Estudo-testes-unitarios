pipeline {
    agent any

    environment {
        // Defina a variável MVN para o diretório onde o Maven está montado no contêiner do Jenkins
        MVN = tool 'Maven'
    }

    stages {
        stage('Setup Maven') {
            steps {
                // Configura o Maven no contêiner do Jenkins
                script {
                    // Define a ferramenta Maven para o Jenkins
                    env.PATH = "${MVN}/bin:${env.PATH}"
                }
            }
        }
// etapa desnecessaria pois na configuração do jenkins ele ja diz onte esta o projeto para ser baixado
//         stage('Checkout') {
//             steps {
//                 // Clona o repositório do GitHub e verifica a branch main
//                 script {
//                     git branch: 'main', url: 'https://github.com/Paloma-Regis-Ferreira/Estudo-testes-unitarios.git'
//                 }
//             }
//         }

        stage('Build') {
            steps {
                // Compila o projeto Maven
                sh 'mvn clean package -DskipTests=true'
            }
        }

//no passo acima ja usou o clean. Nao usar mais o "clean" e trabalhar com o mesmo binario gerado no passo anterior para os proximos stages
        stage('Test and Coverage') {
            failFast true
            steps {
                // Executa os testes com JaCoCo e gera relatórios de cobertura
                sh 'mvn test jacoco:report'
            }
        }

        stage('SonarQube Analysis') { //sem as variaveis definidas no jenkins
            steps {
                script {
                    // Endereço IP do contêiner do SonarQube
                    def sonarqubeIP = '172.19.0.3'
                    // Executa a análise do SonarQube
                    withSonarQubeEnv() {
                        sh "${MVN}/bin/mvn verify sonar:sonar -Dsonar.projectKey=Estudo-testes-unitarios -Dsonar.projectName='Estudo-testes-unitarios'"
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    sleep(60)
                    timeout(time: 2, unit: 'MINUTES') {
                        def qgResult = waitForQualityGate abortPipeline: true
                        echo "Status do Quality Gate: ${qgResult}"
                    }
                }
            }
        }

        stage('Print Message') {
            steps {
                echo 'Esta é uma mensagem de exemplo'
            }
        }
    }
}