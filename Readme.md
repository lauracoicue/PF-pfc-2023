# Proyecto Final  - Fundamentos de programación funcional y concurrente

El problema de la reconstrucción de cadenas

**Autoras :**
* Laura Tatiana Coicue - 202276652
* Laura Sofía Peñaloza Lopez - 202259485
* Esmeralda Rivas Guzmán - 202259580

## Archivos 
### Informe
Este documento describe cada una de las funciones desarrolladas de manera secuencial y paralela, argumentando sobre su implementación y las mejoras observadas en cada prueba. Además, se incluyen las evaluaciones comparativas y los tests generados para cada función.

### Proyecto — Código
En PF-pfc-2023/app/src/main/scala/proyectoF/ se cuenta con 6 archivos.scala, entre los que se tienen:

* **ProyectoF.scala:** Contiene el Main del proyecto, el cual incluye el llamado de las funciones, para realizar las evaluaciones comparativas.
* **Arbol.scala:** En esta clase se hizo la implementacion de las funciones relacionadas con Trie, en este caso:
  * *pertenece*
  * *adicionar*
  * *arbolDeSufijos*
* **Benchmark.scala:** Contiene el código para producir la evaluación comparativa entre las funciones secuenciales y paralelas.
* **FunOraculo.scala:** Genera la comprobación del oraculo a partir de la función *generarOraculo*. Además, se cuenta con *generarCadena*, que permite la producción de cadenas de manera aleatoria  a partir del alfabeto definido ('a', 'c', 'g', 't'), la cadena resultante será a la que queremos llegar con la reconstrucción.
* **SolucionesFunc.scala:** En esta clase, se plantean las 5 funciones de reconstrucción de cadenas secuenciales:
  * *reconstruirCadenaIngenuo*
  * *reconstruirCadenaMejorado*
  * *reconstruirCadenaTurbo*
  * *reconstruirCadenaTurboMejorada*
  * *reconstruirCadenaTurboAcelerada*
* **SolucionesFuncPar:** Contiene las funciones paralelas de cada una de las secuenciales. Para este caso, se usó paralelismo de tareas.
  * *reconstruirCadenaIngenuoPar*
  * *reconstruirCadenaMejoradoPar*
  * *reconstruirCadenaTurboPar*
  * *reconstruirCadenaTurboMejoradaPar*
  * *reconstruirCadenaTurboAceleradaPar*

En PF-pfc-2023/app/src/test/scala/proyectoF/ se cuenta con 11 archivos.scala, estos han sido creados con el objetivo de realizar pruebas a cada una de las funciones, tanto secuenciales como paralelas. Dentro del contenido de cada archivo, se cuenta con 4 tests, que verifican que la función sea correcta para n = (4, 8, 16, 64). Exceptuando, *Test1Ingenua.scala* y *Test2IngenuaPar.scala*, debido a que solo fueron probadas para n = (2, 4, 8), por la alta ocupación de memoria que genera. Estos archivos son:
 * *Test1Ingenua.scala*
 * *Test2IngenuaPar.scala*
 * *Test3Mejorada.scala*
 * *Test4MejoradaPar.scala*
 * *Test5Turbo.scala*
 * *Test6TurboPar.scala*
 * *Test7TurboMejorada.scala*
 * *Test8TurboMejoradaPar.scala*
 * *Test9TurboAcelerada.scala*
 * *Test10TurboAceleradaPar.scala*
 * *TestProyectoF.scala:* Está creado por defecto, para verificar el Main.

## Instrucciones de ejecución 
Para compilar el proyecto, se debe tener en cuenta que se realizó a partir del lenguaje de programación *Scala*, por lo que será necesario un IDE para su correcta ejecución. 

* Para comprobar el resultado de cada una de las funciones, podrá ejeuctar los tests creados, los cuales estarán correctos si la cadena reconstruida por la función es igual a la cadena generada de manera aleatoria por *generarCadena*
* Para conocer los datos de la evaluación comparativa entre funciones secuenciales y paralelas, deberá dirigirse al archivo *ProyectoF.scala*. En este, se encuentra el código para cada una de las comparaciones, solo será necesario quitar los símbolos, para poder ejecutarlos, ya que cada bloque se encuentra comentado.
  




