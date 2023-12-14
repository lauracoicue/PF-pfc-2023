file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/SolucionesFunc.scala
### java.lang.AssertionError: assertion failed: denotation object SolucionesFunc invalid in run 6. ValidFor: Period(1..5, run = 7)

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
      if (n == 0){
        Seq(cadena)
      } else {
        val nCadena = alfabeto.flatMap(s => generarCadena(n - 1, cadena :+ s))
        println(nCadena.size)
        nCadena
      }
    }
    generarCadena(n).find(o).getOrElse(Seq())
  }

  def reconstruirCadenaMejorado(n: Int, o: Oraculo): Seq[Char] = {
    def generarSecuencias(k: Int, secuencias: Seq[Set[Seq[Char]]]): Seq[Set[Seq[Char]]] = {
        if (k > n) secuencias
        else {
          println(secuencias.size)
          val nSecuencias = secuencias(k-1).flatMap(s => alfabeto.map(c => s :+ c)).filter(o)
          generarSecuencias(k+1, secuencias :+ nSecuencias)
        }
      }
      val secuencias = generarSecuencias(1, Seq(Set(Seq())))
      secuencias(n).find(_.length == n).getOrElse(Seq())
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
	scala.meta.internal.pc.SemanticdbTextDocumentProvider.textDocument(SemanticdbTextDocumentProvider.scala:33)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticdbTextDocument$$anonfun$1(ScalaPresentationCompiler.scala:191)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: denotation object SolucionesFunc invalid in run 6. ValidFor: Period(1..5, run = 7)