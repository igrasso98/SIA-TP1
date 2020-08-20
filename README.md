# SIA-TP
_En este repositorio se encontraran los diferentes trabajos prácticos que iremos realizando a lo largo del cuatrimestre de la materia Sistemas de Inteligencia Artificial._

## TP1: Metodos de búsqueda informados y no informados
_Aplicación de diferentes metodos de búsqueda para encontrar la solución más optima a diferentes niveles del juego [Sokoban](http://www.game-sokoban.com/)_

### Pre-requisitos
Al ejecutable se le necesita pasar como parámetro por linea de comando un archivo json con las configuraciones. Dicho archivo debe cumplir con el siguiente formato:

```
{
  "config": {
    "timelimit": 10.0,
    "algorithm-name": "IDASTAR",
    "heuristic": "Manhattan",
    "limit": 100,
    "step": 100,
    "level-number": 8
  }
}
```
En donde
* **timelimit** : hace referencia al tiempo máximo de ejecución de un metodo de búsqueda en formato de double. Un valor negativo indica que no hay limite de tiempo
* **algorithm-name** : Nombre del metodo de búsqueda. Los disponibles son DFS, BFS, ASTAR, IDASTAR, IDDFS, GREEDY
* **heuristic** : Heuristica que se tiene en cuenta en los métodos de búsqueda informados
    * *Boxes To Goals*: Mide la distancia de los objetivos no cumplidos a las cajas no asignadas. 
    * *Box Moved* : Mide la distancia minima de los objetivos no cumplidos a las cajas no asignadas sumado con la cantidad de movimientos de la caja
    * *Boxes To Goals Min Distance*: Sumatoria de la menor distancia entre una caja sin asignar y un objetivo sin cumplir restando los objetivos cumplidos
* **limit** : Limite base que se aplica a IDDFS e IDASTAR. Un limite negativo indica que no hay limite
* **step** : Valor con el que se incrementa el limite en IDDFS e IDASTAR
* **level-number**: Nivel elegido. El proyecto cuenta con 9 niveles con dificultad creciente. En caso de que se ingrese un numero negativo, el nivel sera elegido de forma aleatoria

## Ejecución
Correr la siguiente linea en la terminal
```
Java -jar TP1.jar [path]
```
**path**: Es el path al json con las configuraciones

## Analizando resultados
Luego de una exitosa ejecucion, le deberia aparecer algo asi junto a todos los movimientos necesarios para resolver el nivel:
```
Initial Parameters:
* Level number: 8
* Limit: 100
* Heuristic: Manhattan
* Step: 100
* Algorithm name: IDASTAR
* Timelimit: 10.0

Results:
* Status:SUCCESS
* Depth:56
* Cost:56
* Explored:1130
* Nodes left in frontier:627
* Solution coordinates:[(3,4), (2,4), (2,5), (1,5), (1,4), (1,3), (1,2), (1,3), (2,3), (2,4), (3,4), (4,4), (4,3), (5,3), (5,2), (6,2), (6,3), (6,4), (6,5), (6,4), (5,4), (4,4), (3,4), (2,4), (2,3), (1,3), (1,4), (1,5), (2,5), (2,4), (2,3), (3,3), (4,3), (5,3), (5,4), (6,4), (6,3), (6,2), (5,2), (5,1), (4,1), (3,1), (3,2), (3,1), (4,1), (5,1), (5,2), (5,3), (4,3), (4,4), (3,4), (2,4), (2,5), (2,6), (3,6), (4,6), (4,5)]
* Time to solve:0.053s
```

## Autores
* Ignacio Grasso - [igrasso](https://github.com/igrasso98)
* Bautista Blacker - [bblacker](https://github.com/bautiblacker)

