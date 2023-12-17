file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/SolucionesFunc.scala
### java.lang.AssertionError: assertion failed: phase SetRootTree has already been used once; cannot be reused

occurred in the presentation compiler.

action parameters:
offset: 2670
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
    subCadena.find(_.length == n).getO@@rElse(Seq())
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
	scala.meta.internal.pc.HoverProvider$.hover(HoverProvider.scala:34)
	scala.meta.internal.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:342)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: phase SetRootTree has already been used once; cannot be reused