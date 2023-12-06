# Pokemon
Bienvenido a la Batalla Pokemon

## Descripción del juego
La batalla pokémon es un juego en el que dos jugadores seleccionan sus Pokémon, con habilidades. Además, cuentan con una veridad de items que pueden utilizar cada jugador en su turno. El sistema climático agrega un toque adicional de estrategia, ya que puede beneficiar o afectar a los Pokémon de diferentes maneras.


# Dependencias
Para poder instalar correctamente el juego, debes instalar las siguientes dependencias:

- [org.jetbrains.annotations](https://github.com/org.realityforge/org.jetbrains.annotations) - 1.7.0
- [underscore](https://github.com/javadev/underscore) - 1.87
- [junit-jupiter-api](https://github.com/junit-team/junit5) - 5.9.2
- [consoleui](https://github.com/codeshelf/consoleui) - 0.0.13
- [mockito-inline](https://github.com/mockito/mockito) - 5.2.0
- [mockito-junit-jupiter](https://github.com/mockito/mockito) - 5.2.0
- [jackson-databind](https://github.com/FasterXML/jackson-databind) - 2.15.1
- [jline-terminal](https://github.com/jline/jline-terminal) - 3.23.0
- [jline](https://github.com/jline/jline) - 3.23.0
- [javatuples](https://github.com/javatuples/javatuples) - 1.2

También debes tener en cuenta la versión de Java utilizada: Java 21.

## Instrucciones de instalación
**NOTA:** Si no deseas clonar el repositorio, puedes ejecutar el juego usando JAR como está aclarado más adelante. 


_Clonando el repositorio_

1. Clona este repositorio en tu máquina local utilizando:
   git clone <>
2. Busca la carpeta del repositorio clonado y ubícate en donde encuentre el archivo Main.java
3. Antes de compilar el juego, aseguráte de haber instalado las dependencias.
3. Abra la consola (cmd - powershell)
4. Compila el juego utilizando el siguiente comando: javac Main.java
5. Para ejecutar el juego, escribe nuevamente en la consola: java Main.


## Ejecución del juego

#### Desde tu IDE 

  Corriendo el archivo PokemonApp.class que encontrarás en el siguiente path: `src/main/java/org/fiuba/algoritmos3/PokemonApp.java `


#### Desde la terminal con JAR

1. Debes tener instalado [Java Runtime Environment](https://www.oracle.com/java/technologies/downloads/) en tu sistema (Si no lo tenes, click en el enlace)
2. Descargar el archivo JAR [Pokemon.jar]() en este repositorio
3. Abre una terminal y ejecuta el programa utilizando el siguiente comando:
``java -jar Pokemon.jar``


## Instrucciones de juego

1. **Inicio del juego**
    * Al iniciar el juego, se le pedirá a cada jugador su nombre y seleccionar el Pokemon con el cual iniciar a jugar.
2. **Desarrollo del juego**
    * El juego continuará con turnos alternados entre los jugadores.
    * En cada turno, podrás seleccionar una acción para tu Pokémon, como atacar, usar un item, o cambiar de Pokémon
    * El objetivo del juego es derrotar a los Pokémon del oponente antes de que todos tus Pokémon sean derrotados.
4. **Final del juego**
    * El juego termina cuando ocurre uno de los siguientes escenarios:
        * Todos los Pokémon de un jugador son derrotados, en ese caso el otro jugador gana.
        * Uno de los jugadores se rinde, el otro jugador gana automáticamente.