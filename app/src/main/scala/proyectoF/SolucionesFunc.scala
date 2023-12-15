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
    def generarSubC(k: Int, subCadena: Set[Seq[Char]]): Set[Seq[Char]] = {
      if (k > n) subCadena
      else {
        println("Mejorado" + subCadena.size)
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
        println("Turbo" + subCadena.size)
        val nSubC = subCadena.flatMap(s1 => subCadena.map(s2 => s1 ++ s2).filter(o))
        generarSubC(k*2, nSubC)
      }
    }
    val ISubC = alfabeto.map(Seq(_)).toSet 
    val subCadena = generarSubC(2, ISubC)
    subCadena.find(_.length == n).getOrElse(Seq())
}



  /*def reconstruirCadenaTurbo(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Set[Seq[Char]]): Set[Seq[Char]] = {
      if (k > n) subCadena // Si superamos la longitud n, detenemos la generación
      else {
        val newSubC = for {
          s1 <- subCadena
          s2 <- subCadena
          combined = s1 ++ s2
          if combined.length <= n && o(combined)
        } yield combined
        generarSubC(k * 2, newSubC)
      }
    }

    val subCadenaInicial = alfabeto.map(Seq(_)).toSet // Conjunto inicial de subcadenas de longitud 1
    val subCadena = generarSubC(2, subCadenaInicial)
    subCadena.find(_.length == n).getOrElse(Seq())
  }*/




  /*def reconstruirCadenaTurbo(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Seq[Set[Seq[Char]]]): Seq[Set[Seq[Char]]] = {
      if (subCadena.last.exists(_.length == n)) subCadena
      else {
        println(subCadena.length)
        val nSubC = subCadena.last.flatMap(s1 => alfabeto.map(s2 => s1 ++ Seq(s2))).filter(o)
        println(nSubC)
        generarSubC(k * 2, subCadena :+ nSubC)
     }
    }
    val subCadena = generarSubC(1, Seq(Set(Seq())))
    subCadena.find(_.length == n).getOrElse(Seq())
  }

  def reconstruirCadenaTurbo(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Seq[Set[Seq[Char]]]): Seq[Set[Seq[Char]]] = {
      if (subCadena.last.exists(_.length == n)) subCadena // Si ya se encontró una cadena de longitud n, se detiene
      else {
        val newSubC = subCadena.last.flatMap(s1 => alfabeto.map(s2 => s1 :+ s2)).filter(o)
        generarSubC(k * 2, subCadena :+ newSubC)
      }
    }

    val subCadena = generarSubC(1, Seq(Set(Seq())))
    subCadena.last.find(_.length == n).getOrElse(Seq())
}*/
}
