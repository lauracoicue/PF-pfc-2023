file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/SolucionesFunc.scala
### java.lang.AssertionError: assertion failed: denotation object SolucionesFunc invalid in run 4. ValidFor: Period(1..5, run = 7)

occurred in the presentation compiler.

action parameters:
uri: file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/SolucionesFunc.scala
text:
```scala
package proyectoF

class SolucionesFunc {
  val alfabeto = Seq('a', 'c', 'g', 't')
  type Oraculo = Seq[Char] => Boolean 

  def reconstruirCadenaIngenuo(n: Int, o: Oraculo): Seq[Char] = {
    def generarCadena(n: Int, cadena: Seq[Char] = Seq()): Seq[Seq[Char]] = {
      if (n == 0) Seq(cadena)
      else {
        alfabeto.flatMap(s => generarCadena(n - 1, cadena :+ s))
      }
    }
    generarCadena(n).find(o).getOrElse(Seq())
  }

  def reconstruirCadenaMejorado(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Set[Seq[Char]]): Set[Seq[Char]] = {
      if (k > n) subCadena
      else {
        val nSubC = subCadena.flatMap(s1 => alfabeto.map(s2 => s1 ++ Seq(s2))).filter(o)
        generarSubC(k + 1, nSubC)
      }
    }
    val subCadena = generarSubC(1, Set(Seq()))
    subCadena.find(_.length == n).getOrElse(Seq())
  }

  


  /*def reconstruirCadenaTurbo(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Set[Seq[Char]]): Set[Seq[Char]] = {
      if (k > n) subCadena 
      else {
        val nSubC = subCadena.flatMap(s1 => subCadena.map(s2 => s1 ++ s2).filter(o))
        generarSubC(k*2, nSubC)
      }
    }
    val ISubC = alfabeto.map(Seq(_)).toSet 
    val subCadena = generarSubC(2, ISubC)
    subCadena.find(_.length == n).getOrElse(Seq())
  }*/

  def reconstruirCadenaTurboOriginal(n: Int, o: Oraculo): (Seq[Char], Int) = {
    var consultasOraculo = 0

    def generarSubC(k: Int, subCadena: Set[Seq[Char]]): Set[Seq[Char]] = {
      if (k > n) subCadena
      else {
        val nSubC = subCadena.flatMap(s1 => subCadena.map(s2 => s1 ++ s2).filter { s =>
          consultasOraculo += 1
          o(s)
        })
        generarSubC(k * 2, nSubC)
      }
    }

    val ISubC = alfabeto.map(Seq(_)).toSet
    val subCadena = generarSubC(2, ISubC)
    val resultadoFinal = subCadena.find(_.length == n).getOrElse(Seq())

    (resultadoFinal, consultasOraculo)
  }


  /*def reconstruirCadenaTurbo(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Set[Seq[Char]]): Set[Seq[Char]] = {
      if (k > n) subCadena
      else {
        val nSubC = filtrarSubCadenas(subCadena, k, o)
        println(nSubC)
        generarSubC(k * 2, nSubC)
      }
    }

    def filtrarSubCadenas(SC: Set[Seq[Char]], k: Int, o: Oraculo): Set[Seq[Char]] = {
      SC.flatMap { s1 =>
        SC.flatMap { s2 =>
          val s = s1 ++ s2
          if (s1.takeRight(k) == s2.take(k) && o(s.take(k))) Set(s)
          else Set.empty[Seq[Char]]
        }
      }
    }

    val ISubC = alfabeto.map(Seq(_)).toSet
    val subCadena = generarSubC(2, ISubC)
    subCadena.find(_.length == n).getOrElse(Seq())
  }*/


def reconstruirCadenaTurboMejorada(n: Int, o: Oraculo): (Seq[Char], Int) = {
    def generarSubC(k: Int, subCadena: Set[Seq[Char]], consultas: Int): (Set[Seq[Char]], Int) = {
      if (k > n) (subCadena, consultas)
      else {
        val (nSubC, nuevasConsultas) = filtrar(subCadena, k / 2, o)
        generarSubC(k * 2, nSubC, consultas + nuevasConsultas)
      }
    }

    def filtrar(SC: Set[Seq[Char]], k: Int, o: Oraculo): (Set[Seq[Char]], Int) = {
      var consultas = 0
      val nuevoSC = SC.flatMap { s1 =>
        SC.collect {
          case s2 if s1.take(k) == s2.takeRight(k) =>
            val s = s1 ++ s2.drop(k)
            consultas += 1
            if (o(s.take(k))) s
            else Seq.empty[Char]
        }
      }
      (nuevoSC, consultas)
    }

    val ISubC = alfabeto.map(Seq(_)).toSet
    val (subCadena, consultas) = generarSubC(2, ISubC, 0)
    val resultadoFinal = subCadena.find(_.length == n).getOrElse(Seq())

    (resultadoFinal, consultas)
  }

  
  /*def reconstruirCadenaTurboMejorado(n: Int, o: Oraculo): Seq[Char] = {
    def filtrar(subCadena: Set[Seq[Char]], k: Int): Set[Seq[Char]] = {
      subCadena.flatMap(s1 => subCadena.map(s2 => s1 ++ s2))
        .filter(s => (0 to s.length - k).forall(i => subCadena.exists(_ == s.drop(i).take(k))) && o(s))
    }

    def generarSubC(k: Int, subCadena: Set[Seq[Char]]): Set[Seq[Char]] = {
      if (k > n) subCadena 
      else {
        val nSubC = subCadena.flatMap(s1 => subCadena.map(s2 => s1 ++ s2))
        val nSubC = filtrar(nSubC, k/2)
        generarSubC(k*2, nSubC)
      }
    }

    val ISubC = alfabeto.map(Seq(_)).toSet 
    val subCadena = generarSubC(2, ISubC)
    subCadena.find(_.length == n).getOrElse(Seq())
  }*/

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
	dotty.tools.dotc.typer.Namer.addAbsentCompanions$1$$anonfun$6$$anonfun$1(Namer.scala:698)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:575)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:573)
	dotty.tools.dotc.core.Scopes$Scope$$anon$1.foreach(Scopes.scala:134)
	dotty.tools.dotc.typer.Namer.addAbsentCompanions$1$$anonfun$6(Namer.scala:704)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:575)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:573)
	scala.collection.AbstractIterable.foreach(Iterable.scala:933)
	scala.collection.IterableOps$WithFilter.foreach(Iterable.scala:903)
	dotty.tools.dotc.typer.Namer.addAbsentCompanions$1(Namer.scala:704)
	dotty.tools.dotc.typer.Namer.index(Namer.scala:712)
	dotty.tools.dotc.typer.Namer.recur$1(Namer.scala:391)
	dotty.tools.dotc.typer.Namer.indexExpanded(Namer.scala:409)
	dotty.tools.dotc.typer.Namer.index(Namer.scala:381)
	dotty.tools.dotc.typer.TyperPhase.enterSyms$$anonfun$1(TyperPhase.scala:36)
	dotty.tools.dotc.typer.TyperPhase.enterSyms$$anonfun$adapted$1(TyperPhase.scala:38)
	scala.Function0.apply$mcV$sp(Function0.scala:42)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:440)
	dotty.tools.dotc.typer.TyperPhase.enterSyms(TyperPhase.scala:38)
	dotty.tools.dotc.typer.TyperPhase.runOn$$anonfun$1(TyperPhase.scala:76)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.typer.TyperPhase.runOn(TyperPhase.scala:76)
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
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:45)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:90)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:99)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: denotation object SolucionesFunc invalid in run 4. ValidFor: Period(1..5, run = 7)