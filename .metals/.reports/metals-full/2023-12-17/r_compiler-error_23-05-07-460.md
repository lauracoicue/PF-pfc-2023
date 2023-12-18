file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/ProyectoF.scala
### java.lang.AssertionError: assertion failed: denotation object language invalid in run 4. ValidFor: Period(1..2, run = 6)

occurred in the presentation compiler.

action parameters:
offset: 4144
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

  val nuevoTrie = d.adicionar(Seq('a', 't'@@), t)
  println(nuevoTrie)

  }
 }

```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.core.Denotations$SingleDenotation.updateValidity(Denotations.scala:717)
	dotty.tools.dotc.core.Denotations$SingleDenotation.bringForward(Denotations.scala:742)
	dotty.tools.dotc.core.Denotations$SingleDenotation.toNewRun$1(Denotations.scala:799)
	dotty.tools.dotc.core.Denotations$SingleDenotation.current(Denotations.scala:870)
	dotty.tools.dotc.core.Symbols$Symbol.recomputeDenot(Symbols.scala:120)
	dotty.tools.dotc.core.Symbols$Symbol.computeDenot(Symbols.scala:114)
	dotty.tools.dotc.core.Symbols$Symbol.denot(Symbols.scala:107)
	dotty.tools.dotc.core.Symbols$.toDenot(Symbols.scala:494)
	dotty.tools.dotc.typer.Checking.checkLegalImportPath(Checking.scala:938)
	dotty.tools.dotc.typer.Checking.checkLegalImportPath$(Checking.scala:809)
	dotty.tools.dotc.typer.Typer.checkLegalImportPath(Typer.scala:116)
	dotty.tools.dotc.typer.Typer.typedImport(Typer.scala:2789)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3060)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3112)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3200)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3256)
	dotty.tools.dotc.typer.Typer.typedPackageDef(Typer.scala:2812)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3081)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3112)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3300)
	dotty.tools.dotc.typer.TyperPhase.typeCheck$$anonfun$1(TyperPhase.scala:44)
	dotty.tools.dotc.typer.TyperPhase.typeCheck$$anonfun$adapted$1(TyperPhase.scala:54)
	scala.Function0.apply$mcV$sp(Function0.scala:42)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:440)
	dotty.tools.dotc.typer.TyperPhase.typeCheck(TyperPhase.scala:54)
	dotty.tools.dotc.typer.TyperPhase.runOn$$anonfun$3(TyperPhase.scala:88)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.typer.TyperPhase.runOn(TyperPhase.scala:88)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:246)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1321)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:262)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:270)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:279)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:67)
	dotty.tools.dotc.Run.compileUnits(Run.scala:279)
	dotty.tools.dotc.Run.compileSources(Run.scala:194)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:165)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.HoverProvider$.hover(HoverProvider.scala:34)
	scala.meta.internal.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:342)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: denotation object language invalid in run 4. ValidFor: Period(1..2, run = 6)