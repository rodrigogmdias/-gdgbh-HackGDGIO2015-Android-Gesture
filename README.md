# [gdgbh] HackGDGIO2015 Android Gesture

![AndroidGesture](https://raw.githubusercontent.com/rodrigodias-us/-gdgbh-HackGDGIO2015-Android-Gesture/master/DesktopApp/imgs/AndroidGesture.gif)

Capaz de executar hotkeys pré definidos pelo usuário, o Android Gesture deve ser capaz de reconhecer o movimento feito pelo usuario e atribuir a um comando.

## Aplicação

A aplicação desse modulo serve tanto para apresentar um slide como para comandos mais avançados de alguma outra aplicação.

## Como funciona?

Após abrir o app no Android Wear, o aplicativo faz conexão com a aplicação Desktop que roda um server websocket para escutar em realtime as informações que são repassadas pelo Sensor -> Acelerometer.

Na Aplicação Desktop o usuario faz o cadastro de movimentos utilizando o Android Wear para serem comparados posteriormente. Ao encontrar um padrão cadastrado o App Desktop dispara o Hotkey cadastrado.

## Instalação Desktop App

O app do desktop foi feito em nodeWebKit por isso vc precisa ter instalado em sua maquina o NodeJS.

Após instalar o Node abra o terminal e execute:

	npm install
	
Despois de instalado as dependecias rode o projeto:

	gulp run

## Grato pela oportunidade

Gostaria de deixar aqui o meu muito obrigado ao Google Developers Group de BH e o pessoal do MinasDev.