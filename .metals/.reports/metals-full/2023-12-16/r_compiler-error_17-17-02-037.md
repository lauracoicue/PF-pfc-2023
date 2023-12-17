file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/SolucionesFunc.scala
### java.lang.AssertionError: assertion failed: denotation module class ProcessBuilder$ invalid in run 3. ValidFor: Period(1..2, run = 4)

occurred in the presentation compiler.

action parameters:
offset: 2169
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
        val nSubC = filtrar(subCadena, k).filter(o)
        println("Mejorado : " + nSubC)
        generarSubC(k * 2, nSubC)
      }
    }

    def filtrar(subCadena: Set[Seq[Char]], k: Int): Set[Seq[Char]] = {
      subCadena.flatMap { s1 =>
        subCadena.flatMap { s2 =>
          val s = s1 ++ s2
          val subCDeS = s.sliding(k).toSet
          if (subCDeS.forall(sub => sub.length == k && subCadena.constainsSlice(sub))) Set(s)
          else Set.empty[Seq[Char]]
        }
      }
    }

    val ISubC = alfabeto.map(Seq(_)).toSet
    val subCadena = generarSubC(2, ISubC)
    subCadena.find(_.length == n).getOrElse(S@@eq())
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
	scala.meta.internal.pc.IndexedContext$.fromImportInfo$1$$anonfun$1(IndexedContext.scala:192)
	scala.collection.immutable.List.filter(List.scala:529)
	scala.meta.internal.pc.IndexedContext$.allAccessibleSymbols$1(IndexedContext.scala:168)
	scala.meta.internal.pc.IndexedContext$.fromImportInfo$1(IndexedContext.scala:193)
	scala.meta.internal.pc.IndexedContext$.scala$meta$internal$pc$IndexedContext$$$extractNames(IndexedContext.scala:210)
	scala.meta.internal.pc.IndexedContext$LazyWrapper.<init>(IndexedContext.scala:100)
	scala.meta.internal.pc.IndexedContext$.apply(IndexedContext.scala:88)
	scala.meta.internal.pc.IndexedContext$LazyWrapper.<init>(IndexedContext.scala:99)
	scala.meta.internal.pc.IndexedContext$.apply(IndexedContext.scala:88)
	scala.meta.internal.pc.IndexedContext$LazyWrapper.<init>(IndexedContext.scala:99)
	scala.meta.internal.pc.IndexedContext$.apply(IndexedContext.scala:88)
	scala.meta.internal.pc.IndexedContext$LazyWrapper.<init>(IndexedContext.scala:99)
	scala.meta.internal.pc.IndexedContext$.apply(IndexedContext.scala:88)
	scala.meta.internal.pc.HoverProvider$.hover(HoverProvider.scala:39)
	scala.meta.internal.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:342)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: denotation module class ProcessBuilder$ invalid in run 3. ValidFor: Period(1..2, run = 4)