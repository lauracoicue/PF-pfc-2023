file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/common/package.scala
### java.lang.AssertionError: assertion failed: failure to resolve inner class:
externalName = java.lang.Thread$UncaughtExceptionHandler,
outerName = java.lang.Thread,
innerName = UncaughtExceptionHandler
owner.fullName = java.lang.Thread
while parsing /modules/java.base/java/util/concurrent/ForkJoinPool.class

occurred in the presentation compiler.

action parameters:
uri: file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/common/package.scala
text:
```scala

import java.util.concurrent._
import scala.util.DynamicVariable

package object common {

  val forkJoinPool = new ForkJoinPool

  abstract class TaskScheduler {
    def schedule[T](body: => T): ForkJoinTask[T]
    def parallel[A, B](taskA: => A, taskB: => B): (A, B) = {
      val right = task {
        taskB
      }
      val left = taskA
      (left, right.join())
    }
  }

  class DefaultTaskScheduler extends TaskScheduler {
    def schedule[T](body: => T): ForkJoinTask[T] = {
      val t = new RecursiveTask[T] {
        def compute = body
      }
      Thread.currentThread match {
        case wt: ForkJoinWorkerThread =>
          t.fork()
        case _ =>
          forkJoinPool.execute(t)
      }
      t
    }
  }

  val scheduler =
    new DynamicVariable[TaskScheduler](new DefaultTaskScheduler)

  def task[T](body: => T): ForkJoinTask[T] = {
    scheduler.value.schedule(body)
  }

  def parallel[A, B](taskA: => A, taskB: => B): (A, B) = {
    scheduler.value.parallel(taskA, taskB)
  }

  def parallel[A, B, C, D](taskA: => A, taskB: => B, taskC: => C, taskD: => D): (A, B, C, D) = {
    val ta = task { taskA }
    val tb = task { taskB }
    val tc = task { taskC }
    val td = taskD
    (ta.join(), tb.join(), tc.join(), td)
  }

}

```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.core.classfile.ClassfileParser$innerClasses$.classSymbol(ClassfileParser.scala:1122)
	dotty.tools.dotc.core.classfile.ClassfileParser.classNameToSymbol(ClassfileParser.scala:116)
	dotty.tools.dotc.core.classfile.ClassfileParser.sig2type$1(ClassfileParser.scala:429)
	dotty.tools.dotc.core.classfile.ClassfileParser.sig2type$1(ClassfileParser.scala:478)
	dotty.tools.dotc.core.classfile.ClassfileParser.dotty$tools$dotc$core$classfile$ClassfileParser$$sigToType(ClassfileParser.scala:550)
	dotty.tools.dotc.core.classfile.ClassfileParser$MemberCompleter.complete(ClassfileParser.scala:312)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:174)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:187)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:189)
	dotty.tools.dotc.core.Denotations$SingleDenotation.computeAsSeenFrom(Denotations.scala:1117)
	dotty.tools.dotc.core.Denotations$SingleDenotation.computeAsSeenFrom(Denotations.scala:1070)
	dotty.tools.dotc.core.Denotations$PreDenotation.asSeenFrom(Denotations.scala:134)
	dotty.tools.dotc.core.Denotations$DenotUnion.computeAsSeenFrom(Denotations.scala:1234)
	dotty.tools.dotc.core.Denotations$PreDenotation.asSeenFrom(Denotations.scala:134)
	dotty.tools.dotc.core.Denotations$DenotUnion.computeAsSeenFrom(Denotations.scala:1234)
	dotty.tools.dotc.core.Denotations$PreDenotation.asSeenFrom(Denotations.scala:134)
	dotty.tools.dotc.core.Denotations$DenotUnion.computeAsSeenFrom(Denotations.scala:1234)
	dotty.tools.dotc.core.Denotations$PreDenotation.asSeenFrom(Denotations.scala:134)
	dotty.tools.dotc.core.Denotations$DenotUnion.computeAsSeenFrom(Denotations.scala:1234)
	dotty.tools.dotc.core.Denotations$PreDenotation.asSeenFrom(Denotations.scala:134)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.findMember(SymDenotations.scala:2145)
	dotty.tools.dotc.core.Types$Type.go$1(Types.scala:695)
	dotty.tools.dotc.core.Types$Type.findMember(Types.scala:874)
	dotty.tools.dotc.typer.TypeAssigner.selectionType(TypeAssigner.scala:155)
	dotty.tools.dotc.typer.TypeAssigner.selectionType$(TypeAssigner.scala:16)
	dotty.tools.dotc.typer.Typer.selectionType(Typer.scala:116)
	dotty.tools.dotc.typer.Typer.typedSelect(Typer.scala:679)
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
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3237)
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

java.lang.AssertionError: assertion failed: failure to resolve inner class:
externalName = java.lang.Thread$UncaughtExceptionHandler,
outerName = java.lang.Thread,
innerName = UncaughtExceptionHandler
owner.fullName = java.lang.Thread
while parsing /modules/java.base/java/util/concurrent/ForkJoinPool.class