file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/FunOraculo.scala
### java.lang.AssertionError: assertion failed

occurred in the presentation compiler.

action parameters:
uri: file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/FunOraculo.scala
text:
```scala
package taller4
import scala.util.Random

class FunOraculo {
  val Random = new Random
  val a = new SolucionesFunc

  def generarCadena(n:Int): Seq[Char] = {
    Seq.fill(n)(a.alfabeto(Random.nextInt(a.alfabeto.size)))
  }

  def generarOraculo(cadena: Seq[Char]): a.Oraculo = {
    subcadena => cadena.containsSlice(subcadena)
  }
}

```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:11)
	dotty.tools.dotc.core.Scopes$MutableScope.enterInHash(Scopes.scala:263)
	dotty.tools.dotc.core.Scopes$MutableScope.enterAllInHash(Scopes.scala:301)
	dotty.tools.dotc.core.Scopes$MutableScope.enterAllInHash(Scopes.scala:300)
	dotty.tools.dotc.core.Scopes$MutableScope.enterAllInHash(Scopes.scala:300)
	dotty.tools.dotc.core.Scopes$MutableScope.enterAllInHash(Scopes.scala:300)
	dotty.tools.dotc.core.Scopes$MutableScope.enterAllInHash(Scopes.scala:300)
	dotty.tools.dotc.core.Scopes$MutableScope.createHash(Scopes.scala:293)
	dotty.tools.dotc.core.Scopes$MutableScope.ensureCapacity(Scopes.scala:287)
	dotty.tools.dotc.core.Scopes$MutableScope.newScopeEntry(Scopes.scala:250)
	dotty.tools.dotc.core.SymbolLoaders$PackageLoader$PackageScope.newScopeEntry(SymbolLoaders.scala:223)
	dotty.tools.dotc.core.Scopes$MutableScope.enter(Scopes.scala:276)
	dotty.tools.dotc.core.Scopes$MutableScope.enter(Scopes.scala:272)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.enterNoReplace(SymDenotations.scala:2042)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.enter(SymDenotations.scala:2033)
	dotty.tools.dotc.core.SymbolLoaders$.enterNew(SymbolLoaders.scala:43)
	dotty.tools.dotc.core.SymbolLoaders$.enterModule(SymbolLoaders.scala:65)
	dotty.tools.dotc.core.SymbolLoaders$.enterClassAndModule(SymbolLoaders.scala:109)
	dotty.tools.dotc.core.SymbolLoaders$.initializeFromClassPath(SymbolLoaders.scala:198)
	dotty.tools.dotc.core.SymbolLoaders$PackageLoader.enterClasses$$anonfun$1(SymbolLoaders.scala:271)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.Vector.foreach(Vector.scala:1895)
	dotty.tools.dotc.core.SymbolLoaders$PackageLoader.enterClasses(SymbolLoaders.scala:271)
	dotty.tools.dotc.core.SymbolLoaders$PackageLoader.doComplete(SymbolLoaders.scala:292)
	dotty.tools.dotc.core.SymbolLoader.complete(SymbolLoaders.scala:341)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:174)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeOnce(SymDenotations.scala:385)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.isAbsent(SymDenotations.scala:615)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.isAccessibleFrom(SymDenotations.scala:928)
	dotty.tools.dotc.typer.ProtoTypes$SelectionProto.qualifies$1(ProtoTypes.scala:208)
	dotty.tools.dotc.typer.ProtoTypes$SelectionProto.liftedTree1$1(ProtoTypes.scala:215)
	dotty.tools.dotc.typer.ProtoTypes$SelectionProto.isMatchedBy(ProtoTypes.scala:228)
	dotty.tools.dotc.core.TypeComparer.isMatchedByProto(TypeComparer.scala:2055)
	dotty.tools.dotc.core.TypeComparer.firstTry$1(TypeComparer.scala:339)
	dotty.tools.dotc.core.TypeComparer.recur(TypeComparer.scala:1469)
	dotty.tools.dotc.core.TypeComparer.isSubType(TypeComparer.scala:208)
	dotty.tools.dotc.core.TypeComparer.isSubType(TypeComparer.scala:218)
	dotty.tools.dotc.core.TypeComparer.topLevelSubType(TypeComparer.scala:128)
	dotty.tools.dotc.core.TypeComparer.testSubType(TypeComparer.scala:144)
	dotty.tools.dotc.core.TypeComparer$.testSubType(TypeComparer.scala:2955)
	dotty.tools.dotc.typer.Typer.adaptNoArgsOther$1(Typer.scala:3980)
	dotty.tools.dotc.typer.Typer.adaptNoArgs$1(Typer.scala:4062)
	dotty.tools.dotc.typer.Typer.adapt1(Typer.scala:4268)
	dotty.tools.dotc.typer.Typer.adapt(Typer.scala:3587)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3300)
	dotty.tools.dotc.typer.Typer.typeSelectOnTerm$1(Typer.scala:752)
	dotty.tools.dotc.typer.Typer.typedSelect(Typer.scala:790)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3017)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3111)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3300)
	dotty.tools.dotc.typer.Namer.typedAheadExpr$$anonfun$1(Namer.scala:1653)
	dotty.tools.dotc.typer.Namer.typedAhead(Namer.scala:1643)
	dotty.tools.dotc.typer.Namer.typedAheadExpr(Namer.scala:1653)
	dotty.tools.dotc.typer.Namer$Completer.$anonfun$15(Namer.scala:794)
	dotty.tools.dotc.typer.Typer.typedImportQualifier(Typer.scala:2784)
	dotty.tools.dotc.typer.Namer$Completer.typeSig(Namer.scala:794)
	dotty.tools.dotc.typer.Namer$Completer.completeInCreationContext(Namer.scala:934)
	dotty.tools.dotc.typer.Namer$Completer.complete(Namer.scala:814)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:174)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:187)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:189)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.ensureCompleted(SymDenotations.scala:393)
	dotty.tools.dotc.typer.Typer.retrieveSym(Typer.scala:2989)
	dotty.tools.dotc.typer.Typer.typedImport(Typer.scala:2787)
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
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:45)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:90)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:99)
```
#### Short summary: 

java.lang.AssertionError: assertion failed