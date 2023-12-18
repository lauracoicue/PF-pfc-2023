file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/ProyectoF.scala
### java.lang.AssertionError: assertion failed: phase parser has already been used once; cannot be reused

occurred in the presentation compiler.

action parameters:
uri: file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/ProyectoF.scala
text:
```scala
/**
  * Proyecto Final - Reconstrucción de cadenas
  * Autores: <Laura Tatiana Coicue Poquiguegue - 2276652-3743 
  *           Laura Sofía Peñaloza López - 2259485-3743
  *           Esmeralda Rivas Guzmán - 2259580-3743>
  * Profesor: Carlos A Delgado
  */
package proyectoF

import org.scalameter.measure
import org.scalameter.withWarmer
import org.scalameter.Warmer

object ProyectoF{

  def saludo() = "Proyecto Final 2023-II"

  def main(args: Array[String]): Unit = {

    println(saludo())
    println(
      withWarmer(new Warmer.Default) measure {
        (1 to 100000000).toArray
      }
    )

    val a = new SolucionesFunc
    val b = new FunOraculo
    val c= new SolucionesFuncPar
    val benchmark = new Benchmark();

  /*  

    //INGENUA
    for {
      i <- 1 to 6
      m1 = math.pow(2, i).toInt
      val cadena = b.generarCadena(m1)
      m2 = b.generarOraculo(cadena)
    } yield {
      println(s"****************************************")
      println(s"Algoritmo ingenuo con cadenas de tamaño ${i}");
      val (t1, t2, aceleracion) = benchmark.compararAlgoritmos(
        new SolucionesFuncPar().reconstruirCadenaIngenuoPar(1),
        new SolucionesFunc().reconstruirCadenaIngenuo
      )(m1, m2);
      println(s"Paralelizada=   $t1");
      println(s"Normal=   $t2");
      println(s"Aceleración=   $aceleracion");

    }*/
/*
    //MEJORADA
    for {
      i <- 1 to 10
      m1 = math.pow(2, i).toInt
      val cadena = b.generarCadena(m1)
      m2 = b.generarOraculo(cadena)
    } yield {
      println(s"****************************************")
      println(s"Probando cadenas de ${math.pow(2, i).toInt}x${math.pow(2, i).toInt}");
      val (t1, t2, aceleracion) = benchmark.compararAlgoritmos(
        new SolucionesFuncPar().reconstruirCadenaTurboMejoradaPar(8),
        new SolucionesFunc().reconstruirCadenaTurboMejorada
      )(m1, m2);
      println(s"Paralelizada=   $t1");
      println(s"Normal=   $t2");
      println(s"Aceleración=   $aceleracion");

    }
*/
/*
    //TURBO
    for {
      i <- 1 to 10
      m1 = math.pow(2, i).toInt
      val cadena = b.generarCadena(m1)
      m2 = b.generarOraculo(cadena)
    } yield {
      println(s"****************************************")
      println(s"Algotirmo Turbo ${i}")
      val (t1, t2, aceleracion) = benchmark.compararAlgoritmos(
        new SolucionesFuncPar().reconstruirCadenaTurboPar(8),
        new SolucionesFunc().reconstruirCadenaTurbo
      )(m1, m2);
      println(s"Paralelizada=   $t1");
      println(s"Normal=   $t2");
      println(s"Aceleración=   $aceleracion");

    }
    */
/*
    //TURBOMEJORADA
    for {
      i <- 1 to 10
      m1 = math.pow(2, i).toInt
      val cadena = b.generarCadena(m1)
      m2 = b.generarOraculo(cadena)
    } yield {
      println(s"****************************************")
      println(s"Probando cadenas de ${math.pow(2, i).toInt}");
      val (t1, t2, aceleracion) = benchmark.compararAlgoritmos(
        new SolucionesFuncPar().reconstruirCadenaTurboMejoradaPar(8),
        new SolucionesFunc().reconstruirCadenaTurboMejorada
      )(m1, m2);
      println(s"Paralelizada=   $t1");
      println(s"Normal=   $t2");
      println(s"Aceleración=   $aceleracion");

    }

*/

    /*val n = 4
    val cadena = b.generarCadena(n)
    val oraculo = b.generarOraculo(cadena)
    val resultado = a.reconstruirCadenaTurboMejorado(n, oraculo) 
    val resultado1 = a.reconstruirCadenaTurbo(n, oraculo)
    println(cadena)
    println(resultado)
    println(resultado1)*/

  val d = new Arbol

  val t = d.Nodo(' ', false, List(
    d.Nodo('a', false, List(
      d.Nodo('c', true, List(
        d.Nodo('a', false, List(
          d.Hoja('c', true)
        )),
        d.Hoja('t', true)
      ))
    )),
    d.Nodo('c', true, List(
      d.Nodo('a', false, List(
        d.Nodo('c', true, List(
          d.Hoja('t', true)
        ))
      )),
      d.Hoja('t', true)
    )),
    d.Hoja('t', true)
  ))

  val nuevoTrie = d.adicionar(Seq('t', 'a'), t)
  println(nuevoTrie)

  }
 }

```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.core.Phases$Phase.init(Phases.scala:406)
	dotty.tools.dotc.core.Phases$Phase.init(Phases.scala:420)
	dotty.tools.dotc.core.Phases$PhasesBase.usePhases(Phases.scala:168)
	dotty.tools.dotc.core.Phases$PhasesBase.usePhases$(Phases.scala:37)
	dotty.tools.dotc.core.Contexts$ContextBase.usePhases(Contexts.scala:842)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:231)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:279)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:67)
	dotty.tools.dotc.Run.compileUnits(Run.scala:279)
	dotty.tools.dotc.Run.compileSources(Run.scala:194)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:165)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:45)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:90)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:99)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: phase parser has already been used once; cannot be reused