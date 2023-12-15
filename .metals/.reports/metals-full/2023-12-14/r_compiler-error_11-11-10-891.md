file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/SolucionesFunc.scala
### dotty.tools.dotc.core.CyclicReference: Cyclic reference involving object Predef

occurred in the presentation compiler.

action parameters:
offset: 1287
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
        alfabeto.flatMap(s => generarCadena(n - 1, cadena :+ s))
      }
    }
    generarCadena(n).find(o).getOrElse(Seq())
  }

  def reconstruirCadenaMejorado(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Seq[Set[Seq[Char]]]): Seq[Set[Seq[Char]]] = {
        if (k > n) subCadena
        else {
          val nSubC = subCadena(k-1).flatMap(s1 => alfabeto.map(s2 => s1 :+ s2)).filter(o)
          generarSubC(k+1, subCadena :+ nSubC)
        }
      }
      val subCadena = generarSubC(1, Seq(Set(Seq())))
      subCadena(n).find(_.length == n).getOrElse(Seq())
  }

  def reconstruirCadenaTurbo(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Seq[Seq[Char]]): Seq[Seq[Char]] = {
      if (k > n) subCadena
      else {
        val nSubC = subCadena(k/2 - 1).flatMap(s1 => alfabeto.map(s2 => s1 ++ s2)).filter(o)
        generarSubC(k*2, subCadena :+ nSubC)
        val resultado = AsubC(k-1).find(_.length == n@@)
        resultado match {
          case Some(seq) => Seq(seq)
          case None => generarSubC(k*2, AsubC)
        }
      }
    }
    generarSubC(1, Seq(alfabeto.map(Seq(_)))).flatten
  }
}

```



#### Error stacktrace:

```

```
#### Short summary: 

dotty.tools.dotc.core.CyclicReference: Cyclic reference involving object Predef