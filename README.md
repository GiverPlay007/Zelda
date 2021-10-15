## :video_game: Zelda

Jogo estilo Top-down baseado no [Zelda](https://www.zelda.com/), desenvolvido em [Java](https://www.java.com/).  
Este é um projeto do [Curso de Desenvolvimento de Jogos](https://cursos.dankicode.com/curso-dev-games) da [Danki Code](http://cursos.dankicode.com/) e não tem nenhuma conexão com o jogo original.

## :gear: Importando e configurando

O jogo foi desenvolvido na versão 16 do Java, é necessário ter a JDK 16 instalada no sistema caso não faça alterações no script de compilação.
  
Primeiro, clonamos nosso repositório:
```shell
# Clone o repositório
git clone https://github.com/GiverPlay007/Zelda.git

# Navegue até a pasta do projeto
cd Zelda
```

### Importar na IntelliJ IDEA
Você pode importar diretamente a tela inicial, selecionando a opção "Get from Version Control" e colocando o link do repositório. Caso não queira fazer isso, basta seguir os seguintes passos:

- Na tela inicial, seleciona "Open or Import".
- Navegue até a pasta do repositório clonado.
- Selecione o arquivo "build.gradle" com um click duplo.
- Clique em "Open as project".
- Pronto!

### :hammer: Build
Se você estiver utilizando IntelliJ IDEA, não é necessário usar linhas de comando, pode compilar pela aba do Gradle dentro da IDE, consulte a [documentação](https://www.jetbrains.com/help/idea/getting-started-with-gradle.html#run_terminal) caso não saiba fazer isso. Se prefira o terminal, fique a vontade.

Linux ou Windows (PowerShell):
```batch
./gradlew build
```
