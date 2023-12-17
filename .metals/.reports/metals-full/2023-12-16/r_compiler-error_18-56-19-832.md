file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/SolucionesFunc.scala
### java.lang.AssertionError: assertion failed: denotation class Object invalid in run 11. ValidFor: Period(1..5, run = 12)

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

  def reconstruirCadenaTurbo(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Set[Seq[Char]]): Set[Seq[Char]] = {
      if (k > n) subCadena 
      else {
        val nSubC = subCadena.flatMap(s1 => subCadena.map(s2 => s1 ++ s2).filter(o))
        println("Turbo: " + nSubC)
        generarSubC(k*2, nSubC)
      }
    }
    val ISubC = alfabeto.map(Seq(_)).toSet 
    val subCadena = generarSubC(2, ISubC)
    subCadena.find(_.length == n).getOrElse(Seq())
  }


  def reconstruirCadenaTurboMejorado(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Set[Seq[Char]]): Set[Seq[Char]] = {
      if (k > n) subCadena
      else {
        val nSubC = filtrar(subCadena, k).filter(o
        println("Mejorado : " + nSubC)
        generarSubC(k * 2, nSubC)
      }
    }

    def filtrar(subCadena: Set[Seq[Char]], k: Int): Set[Seq[Char]] = {
      subCadena.flatMap { s1 =>
        subCadena.flatMap { s2 =>
          val s = s1 ++ s2
          val subCDeS = s.sliding(k).toSet
          if (subCDeS.forall(sub => sub.length == k)) Set(s)
          else Set.empty[Seq[Char]]
        }
      }
    }

    val ISubC = alfabeto.map(Seq(_)).toSet
    val subCadena = generarSubC(2, ISubC)
    subCadena.find(_.length == n).getOrElse(Seq())
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
	dotty.tools.dotc.core.Symbols$ClassSymbol.classDenot(Symbols.scala:481)
	dotty.tools.dotc.core.Symbols$.toClassDenot(Symbols.scala:497)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.computeMemberNames$$anonfun$1(SymDenotations.scala:2324)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.computeMemberNames(SymDenotations.scala:2325)
	dotty.tools.dotc.core.SymDenotations$MemberNamesImpl.apply(SymDenotations.scala:2917)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.memberNames(SymDenotations.scala:2314)
	dotty.tools.dotc.core.Types$Type.memberNames(Types.scala:903)
	dotty.tools.dotc.core.Types$Type.memberDenots(Types.scala:920)
	dotty.tools.dotc.core.Types$Type.implicitMembers(Types.scala:988)
	dotty.tools.dotc.core.Contexts$Context.liftedTree1$1(Contexts.scala:206)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:209)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:217)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:217)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:217)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:217)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:217)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:217)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:217)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:217)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:217)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:217)
	dotty.tools.dotc.core.Contexts$Context.implicits(Contexts.scala:217)
	dotty.tools.dotc.typer.Implicits$ImplicitSearch.searchImplicit(Implicits.scala:1537)
	dotty.tools.dotc.typer.Implicits$ImplicitSearch.bestImplicit(Implicits.scala:1572)
	dotty.tools.dotc.typer.Implicits.inferImplicit(Implicits.scala:1060)
	dotty.tools.dotc.typer.Implicits.inferImplicit$(Implicits.scala:818)
	dotty.tools.dotc.typer.Typer.inferImplicit(Typer.scala:116)
	dotty.tools.dotc.typer.Implicits.inferView(Implicits.scala:856)
	dotty.tools.dotc.typer.Implicits.inferView$(Implicits.scala:818)
	dotty.tools.dotc.typer.Typer.inferView(Typer.scala:116)
	dotty.tools.dotc.typer.Typer.tryExtensionOrConversion(Typer.scala:3527)
	dotty.tools.dotc.typer.Typer.typedSelect(Typer.scala:703)
	dotty.tools.dotc.typer.Typer.typeSelectOnTerm$1(Typer.scala:753)
	dotty.tools.dotc.typer.Typer.typedSelect(Typer.scala:790)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3017)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3111)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3300)
	dotty.tools.dotc.typer.Applications.realApply$1(Applications.scala:941)
	dotty.tools.dotc.typer.Applications.typedApply(Applications.scala:1101)
	dotty.tools.dotc.typer.Applications.typedApply$(Applications.scala:352)
	dotty.tools.dotc.typer.Typer.typedApply(Typer.scala:116)
	dotty.tools.dotc.typer.Typer.typedInfixOp(Typer.scala:2932)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3087)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3112)
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
	dotty.tools.dotc.typer.Applications.realApply$1(Applications.scala:941)
	dotty.tools.dotc.typer.Applications.typedApply(Applications.scala:1101)
	dotty.tools.dotc.typer.Applications.typedApply$(Applications.scala:352)
	dotty.tools.dotc.typer.Typer.typedApply(Typer.scala:116)
	dotty.tools.dotc.typer.Typer.typedInfixOp(Typer.scala:2932)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3087)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3112)
	dotty.tools.dotc.typer.ProtoTypes$FunProto.$anonfun$7(ProtoTypes.scala:495)
	dotty.tools.dotc.typer.ProtoTypes$FunProto.cacheTypedArg(ProtoTypes.scala:418)
	dotty.tools.dotc.typer.ProtoTypes$FunProto.typedArg(ProtoTypes.scala:496)
	dotty.tools.dotc.typer.Applications$ApplyToUntyped.typedArg(Applications.scala:897)
	dotty.tools.dotc.typer.Applications$ApplyToUntyped.typedArg(Applications.scala:897)
	dotty.tools.dotc.typer.Applications$Application.addTyped$1(Applications.scala:589)
	dotty.tools.dotc.typer.Applications$Application.matchArgs(Applications.scala:653)
	dotty.tools.dotc.typer.Applications$Application.init(Applications.scala:492)
	dotty.tools.dotc.typer.Applications$TypedApply.<init>(Applications.scala:779)
	dotty.tools.dotc.typer.Applications$ApplyToUntyped.<init>(Applications.scala:896)
	dotty.tools.dotc.typer.Applications.ApplyTo(Applications.scala:1126)
	dotty.tools.dotc.typer.Applications.ApplyTo$(Applications.scala:352)
	dotty.tools.dotc.typer.Typer.ApplyTo(Typer.scala:116)
	dotty.tools.dotc.typer.Applications.simpleApply$1(Applications.scala:969)
	dotty.tools.dotc.typer.Applications.realApply$1$$anonfun$2(Applications.scala:1052)
	dotty.tools.dotc.typer.Typer.tryEither(Typer.scala:3324)
	dotty.tools.dotc.typer.Applications.realApply$1(Applications.scala:1063)
	dotty.tools.dotc.typer.Applications.typedApply(Applications.scala:1101)
	dotty.tools.dotc.typer.Applications.typedApply$(Applications.scala:352)
	dotty.tools.dotc.typer.Typer.typedApply(Typer.scala:116)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3048)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3112)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3300)
	dotty.tools.dotc.typer.Namer.typedAheadExpr$$anonfun$1(Namer.scala:1653)
	dotty.tools.dotc.typer.Namer.typedAhead(Namer.scala:1643)
	dotty.tools.dotc.typer.Namer.typedAheadExpr(Namer.scala:1653)
	dotty.tools.dotc.typer.Namer.typedAheadRhs$1$$anonfun$1(Namer.scala:1906)
	dotty.tools.dotc.inlines.PrepareInlineable$.dropInlineIfError(PrepareInlineable.scala:243)
	dotty.tools.dotc.typer.Namer.typedAheadRhs$1(Namer.scala:1906)
	dotty.tools.dotc.typer.Namer.rhsType$1(Namer.scala:1914)
	dotty.tools.dotc.typer.Namer.cookedRhsType$1(Namer.scala:1932)
	dotty.tools.dotc.typer.Namer.lhsType$1(Namer.scala:1933)
	dotty.tools.dotc.typer.Namer.inferredResultType(Namer.scala:1944)
	dotty.tools.dotc.typer.Namer.inferredType$1(Namer.scala:1691)
	dotty.tools.dotc.typer.Namer.valOrDefDefSig(Namer.scala:1698)
	dotty.tools.dotc.typer.Namer$Completer.typeSig(Namer.scala:787)
	dotty.tools.dotc.typer.Namer$Completer.completeInCreationContext(Namer.scala:934)
	dotty.tools.dotc.typer.Namer$Completer.complete(Namer.scala:814)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:174)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:187)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:189)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.ensureCompleted(SymDenotations.scala:393)
	dotty.tools.dotc.typer.Typer.retrieveSym(Typer.scala:2989)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3014)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3111)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3210)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3256)
	dotty.tools.dotc.typer.Typer.typedBlockStats(Typer.scala:1159)
	dotty.tools.dotc.typer.Typer.typedBlock(Typer.scala:1163)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3056)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3112)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.$anonfun$14(Typer.scala:1261)
	dotty.tools.dotc.typer.Applications.harmonic(Applications.scala:2338)
	dotty.tools.dotc.typer.Applications.harmonic$(Applications.scala:352)
	dotty.tools.dotc.typer.Typer.harmonic(Typer.scala:116)
	dotty.tools.dotc.typer.Typer.typedIf(Typer.scala:1263)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3057)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3112)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3300)
	dotty.tools.dotc.typer.Typer.typedBlock(Typer.scala:1166)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3056)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3112)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3300)
	dotty.tools.dotc.typer.Typer.$anonfun$57(Typer.scala:2486)
	dotty.tools.dotc.inlines.PrepareInlineable$.dropInlineIfError(PrepareInlineable.scala:243)
	dotty.tools.dotc.typer.Typer.typedDefDef(Typer.scala:2486)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3024)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3111)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3210)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3256)
	dotty.tools.dotc.typer.Typer.typedBlockStats(Typer.scala:1159)
	dotty.tools.dotc.typer.Typer.typedBlock(Typer.scala:1163)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3056)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3112)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3300)
	dotty.tools.dotc.typer.Typer.$anonfun$57(Typer.scala:2486)
	dotty.tools.dotc.inlines.PrepareInlineable$.dropInlineIfError(PrepareInlineable.scala:243)
	dotty.tools.dotc.typer.Typer.typedDefDef(Typer.scala:2486)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3024)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3111)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3210)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3256)
	dotty.tools.dotc.typer.Typer.typedClassDef(Typer.scala:2669)
	dotty.tools.dotc.typer.Typer.typedTypeOrClassDef$1(Typer.scala:3036)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3040)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3111)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3210)
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

java.lang.AssertionError: assertion failed: denotation class Object invalid in run 11. ValidFor: Period(1..5, run = 12)