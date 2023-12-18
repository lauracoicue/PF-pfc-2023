file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/Arbol.scala
### java.lang.AssertionError: assertion failed: denotation class WithPureFuns invalid in run 3. ValidFor: Period(1..5, run = 4)

occurred in the presentation compiler.

action parameters:
uri: file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/Arbol.scala
text:
```scala
package proyectoF

class Arbol {
  abstract class Trie

  case class Nodo(car: Char, marcada: Boolean,
                  hijos: List[Trie]) extends Trie

  case class Hoja(car: Char, marcada: Boolean) extends Trie

  def raiz(t: Trie): Char = {
    t match {
      case Nodo(c, _, _) => c
      case Hoja(c, _) => c
    }
  }

  def cabezas(t: Trie): Seq[Char] = {
    t match {
      case Nodo(_, _, lt) => lt.map(t => raiz(t))
      case Hoja(c, _) => Seq[Char](c)
    }
  }

  //recibe una secuencia s y un trie t, devuelve true si s pertenece a t
  def pertenece(s: Seq[Char], t: Trie): Boolean = {
    if (s.isEmpty) {
      t match {
        case Nodo(_, marcada, _) => marcada
        case Hoja(_, marcada) => marcada
      }
    } else {
      t match {
        case Nodo(_, _, hijos) => hijos.exists(h => raiz(h) == s.head && pertenece(s.tail, h))
        case Hoja(_, _) => false
      }
    }
  }


  //recibe una secuencia s y un trie t, devuelve el trie correspondiente a adicionar s a t
  def adicionar(s: Seq[Char], t: Trie): Trie = {
    if (s.isEmpty) {
      t match {
        case Nodo(c, _, hijos) => Nodo(c, true, hijos)
        case Hoja(c, _) => Hoja(c, true)
      }
    } else {
      t match {
        case Nodo(c, marcada, hijos) => {
          val h = hijos.find(h => raiz(h) == s.head)
          if (h.isEmpty) {
            Nodo(c, marcada, hijos :+ adicionar(s, Hoja(s.head, false)))
          } else {
            Nodo(c, marcada, hijos.map(h => if (raiz(h) == s.head) adicionar(s.tail, h) else h))
          }
        }
        case Hoja(c, marcada) => Nodo(c, marcada, List(adicionar(s, Hoja(s.head, false))))
      }
    }
  }


  //recibe una secuencia de secuencias ss y devuelve un trie correspondiente al arbol de sifijos ss
  def arbolDeSufijos(ss: Set[Seq[Char]]): Trie = {
    ss.foldLeft(Hoja(' ', false): Trie)((t, s) => adicionar(s, t))
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
	dotty.tools.dotc.core.Symbols$Symbol.name(Symbols.scala:260)
	dotty.tools.dotc.core.tasty.TreeUnpickler$$anon$3.hasSymbol(TreeUnpickler.scala:750)
	dotty.tools.dotc.core.tasty.TreeUnpickler.dotty$tools$dotc$core$tasty$TreeUnpickler$TreeReader$$_$createMemberSymbol$$anonfun$1(TreeUnpickler.scala:649)
	scala.collection.immutable.List.exists(List.scala:395)
	dotty.tools.dotc.core.tasty.TreeUnpickler$TreeReader.createMemberSymbol(TreeUnpickler.scala:649)
	dotty.tools.dotc.core.tasty.TreeUnpickler$TreeReader.createSymbol(TreeUnpickler.scala:560)
	dotty.tools.dotc.core.tasty.TreeUnpickler$TreeReader.symbolAtCurrent(TreeUnpickler.scala:282)
	dotty.tools.dotc.core.tasty.TreeUnpickler$TreeReader.indexStats(TreeUnpickler.scala:764)
	dotty.tools.dotc.core.tasty.TreeUnpickler$TreeReader.indexStats$$anonfun$1$$anonfun$1(TreeUnpickler.scala:774)
	dotty.tools.dotc.core.tasty.TreeUnpickler$TreeReader.processPackage(TreeUnpickler.scala:794)
	dotty.tools.dotc.core.tasty.TreeUnpickler$TreeReader.processPackage(TreeUnpickler.scala:790)
	dotty.tools.dotc.core.tasty.TreeUnpickler$TreeReader.indexStats(TreeUnpickler.scala:774)
	dotty.tools.dotc.core.tasty.TreeUnpickler.enter(TreeUnpickler.scala:107)
	dotty.tools.dotc.core.tasty.DottyUnpickler.enter(DottyUnpickler.scala:57)
	dotty.tools.dotc.core.classfile.ClassfileParser.unpickleTASTY$1(ClassfileParser.scala:923)
	dotty.tools.dotc.core.classfile.ClassfileParser.unpickleOrParseInnerClasses(ClassfileParser.scala:991)
	dotty.tools.dotc.core.classfile.ClassfileParser.parseClass(ClassfileParser.scala:189)
	dotty.tools.dotc.core.classfile.ClassfileParser.$anonfun$1(ClassfileParser.scala:87)
	dotty.tools.dotc.core.classfile.ClassfileParser.run(ClassfileParser.scala:82)
	dotty.tools.dotc.core.ClassfileLoader.load(SymbolLoaders.scala:412)
	dotty.tools.dotc.core.ClassfileLoader.doComplete(SymbolLoaders.scala:407)
	dotty.tools.dotc.core.SymbolLoader$$anon$1.doComplete(SymbolLoaders.scala:325)
	dotty.tools.dotc.core.SymbolLoader.complete(SymbolLoaders.scala:341)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:174)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:187)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:189)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.computeMembersNamed(SymDenotations.scala:2120)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.membersNamed(SymDenotations.scala:2090)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.membersNamedNoShadowingBasedOnFlags(SymDenotations.scala:2113)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.nonPrivateMembersNamed(SymDenotations.scala:2103)
	dotty.tools.dotc.core.SymDenotations$PackageClassDenotation.recur$5(SymDenotations.scala:2472)
	dotty.tools.dotc.core.SymDenotations$PackageClassDenotation.computeMembersNamed(SymDenotations.scala:2539)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.membersNamed(SymDenotations.scala:2090)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.findMember(SymDenotations.scala:2141)
	dotty.tools.dotc.core.Types$Type.go$1(Types.scala:695)
	dotty.tools.dotc.core.Types$Type.findMember(Types.scala:874)
	dotty.tools.dotc.core.Types$Type.memberBasedOnFlags(Types.scala:678)
	dotty.tools.dotc.core.Types$Type.member(Types.scala:662)
	dotty.tools.dotc.core.Denotations$.select$1(Denotations.scala:1306)
	dotty.tools.dotc.core.Denotations$.recurSimple$1(Denotations.scala:1336)
	dotty.tools.dotc.core.Denotations$.recur$1(Denotations.scala:1338)
	dotty.tools.dotc.core.Denotations$.staticRef$$anonfun$1(Denotations.scala:1343)
	dotty.tools.dotc.util.GenericHashMap.getOrElseUpdate(GenericHashMap.scala:134)
	dotty.tools.dotc.core.Denotations$.staticRef(Denotations.scala:1343)
	dotty.tools.dotc.core.Symbols$.requiredClass(Symbols.scala:898)
	dotty.tools.dotc.core.classfile.ClassfileParser.lookupTopLevel$1(ClassfileParser.scala:118)
	dotty.tools.dotc.core.classfile.ClassfileParser.classNameToSymbol(ClassfileParser.scala:127)
	dotty.tools.dotc.core.classfile.ClassfileParser.sig2type$1(ClassfileParser.scala:429)
	dotty.tools.dotc.core.classfile.ClassfileParser.dotty$tools$dotc$core$classfile$ClassfileParser$$sigToType(ClassfileParser.scala:550)
	dotty.tools.dotc.core.classfile.ClassfileParser$ConstantPool.getType(ClassfileParser.scala:1287)
	dotty.tools.dotc.core.classfile.ClassfileParser.unpickleOrParseInnerClasses(ClassfileParser.scala:1016)
	dotty.tools.dotc.core.classfile.ClassfileParser.parseClass(ClassfileParser.scala:189)
	dotty.tools.dotc.core.classfile.ClassfileParser.$anonfun$1(ClassfileParser.scala:87)
	dotty.tools.dotc.core.classfile.ClassfileParser.run(ClassfileParser.scala:82)
	dotty.tools.dotc.core.ClassfileLoader.load(SymbolLoaders.scala:412)
	dotty.tools.dotc.core.ClassfileLoader.doComplete(SymbolLoaders.scala:407)
	dotty.tools.dotc.core.SymbolLoader$$anon$1.doComplete(SymbolLoaders.scala:325)
	dotty.tools.dotc.core.SymbolLoader.complete(SymbolLoaders.scala:341)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:174)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:187)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:189)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.computeMembersNamed(SymDenotations.scala:2120)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.membersNamed(SymDenotations.scala:2090)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.membersNamedNoShadowingBasedOnFlags(SymDenotations.scala:2113)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.nonPrivateMembersNamed(SymDenotations.scala:2103)
	dotty.tools.dotc.core.SymDenotations$PackageClassDenotation.recur$5(SymDenotations.scala:2472)
	dotty.tools.dotc.core.SymDenotations$PackageClassDenotation.computeMembersNamed(SymDenotations.scala:2539)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.membersNamed(SymDenotations.scala:2090)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.findMember(SymDenotations.scala:2141)
	dotty.tools.dotc.core.Types$Type.go$1(Types.scala:695)
	dotty.tools.dotc.core.Types$Type.findMember(Types.scala:874)
	dotty.tools.dotc.core.Types$Type.memberBasedOnFlags(Types.scala:678)
	dotty.tools.dotc.core.Types$Type.member(Types.scala:662)
	dotty.tools.dotc.core.Denotations$.select$1(Denotations.scala:1306)
	dotty.tools.dotc.core.Denotations$.recurSimple$1(Denotations.scala:1336)
	dotty.tools.dotc.core.Denotations$.recur$1(Denotations.scala:1338)
	dotty.tools.dotc.core.Denotations$.staticRef$$anonfun$1(Denotations.scala:1343)
	dotty.tools.dotc.util.GenericHashMap.getOrElseUpdate(GenericHashMap.scala:134)
	dotty.tools.dotc.core.Denotations$.staticRef(Denotations.scala:1343)
	dotty.tools.dotc.core.Symbols$.requiredClass(Symbols.scala:898)
	dotty.tools.dotc.core.classfile.ClassfileParser.lookupTopLevel$1(ClassfileParser.scala:118)
	dotty.tools.dotc.core.classfile.ClassfileParser.classNameToSymbol(ClassfileParser.scala:127)
	dotty.tools.dotc.core.classfile.ClassfileParser.sig2type$1(ClassfileParser.scala:429)
	dotty.tools.dotc.core.classfile.ClassfileParser.dotty$tools$dotc$core$classfile$ClassfileParser$$sigToType(ClassfileParser.scala:550)
	dotty.tools.dotc.core.classfile.ClassfileParser$ConstantPool.getType(ClassfileParser.scala:1287)
	dotty.tools.dotc.core.classfile.ClassfileParser.unpickleOrParseInnerClasses(ClassfileParser.scala:1016)
	dotty.tools.dotc.core.classfile.ClassfileParser.parseClass(ClassfileParser.scala:189)
	dotty.tools.dotc.core.classfile.ClassfileParser.$anonfun$1(ClassfileParser.scala:87)
	dotty.tools.dotc.core.classfile.ClassfileParser.run(ClassfileParser.scala:82)
	dotty.tools.dotc.core.ClassfileLoader.load(SymbolLoaders.scala:412)
	dotty.tools.dotc.core.ClassfileLoader.doComplete(SymbolLoaders.scala:407)
	dotty.tools.dotc.core.SymbolLoader.complete(SymbolLoaders.scala:341)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:174)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeOnce(SymDenotations.scala:385)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.unforcedDecls(SymDenotations.scala:409)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.typeParamsFromDecls(SymDenotations.scala:1857)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.typeParams(SymDenotations.scala:1870)
	dotty.tools.dotc.core.TypeApplications$.typeParams$extension(TypeApplications.scala:183)
	dotty.tools.dotc.core.TypeApplications$.typeParamSymbols$extension(TypeApplications.scala:220)
	dotty.tools.dotc.typer.Typer.adaptType$1(Typer.scala:4180)
	dotty.tools.dotc.typer.Typer.adapt1(Typer.scala:4267)
	dotty.tools.dotc.typer.Typer.adapt(Typer.scala:3587)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.typedType(Typer.scala:3303)
	dotty.tools.dotc.typer.Namer.typedAheadType$$anonfun$1(Namer.scala:1650)
	dotty.tools.dotc.typer.Namer.typedAhead(Namer.scala:1643)
	dotty.tools.dotc.typer.Namer.typedAheadType(Namer.scala:1650)
	dotty.tools.dotc.typer.Namer.defDefSig(Namer.scala:1786)
	dotty.tools.dotc.typer.Namer$Completer.typeSig(Namer.scala:791)
	dotty.tools.dotc.typer.Namer$Completer.completeInCreationContext(Namer.scala:934)
	dotty.tools.dotc.typer.Namer$Completer.complete(Namer.scala:814)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:174)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:187)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:189)
	dotty.tools.dotc.typer.Namer$ClassCompleter.completeConstructor(Namer.scala:1456)
	dotty.tools.dotc.typer.Namer$ClassCompleter.completeInCreationContext(Namer.scala:1586)
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
	scala.meta.internal.pc.SemanticdbTextDocumentProvider.textDocument(SemanticdbTextDocumentProvider.scala:33)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticdbTextDocument$$anonfun$1(ScalaPresentationCompiler.scala:191)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: denotation class WithPureFuns invalid in run 3. ValidFor: Period(1..5, run = 4)