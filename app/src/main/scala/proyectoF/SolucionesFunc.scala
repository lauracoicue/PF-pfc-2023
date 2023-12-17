package proyectoF

class SolucionesFunc {
  val alfabeto = Seq('a', 'c', 'g', 't')
  val trie = new Arbol
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
    def generarSubC(k: Int, subCadena: Seq[Seq[Char]]): Seq[Seq[Char]] = {
      if (k > n) subCadena
      else {
        val nSubC = subCadena.flatMap(s1 => alfabeto.map(s2 => s1 ++ Seq(s2))).filter(o)
        generarSubC(k + 1, nSubC)
      }
    }
    val subCadena = generarSubC(1, Seq(Seq()))
    subCadena.find(_.length == n).getOrElse(Seq())
  }

  def reconstruirCadenaTurbo(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Seq[Seq[Char]]): Seq[Seq[Char]] = {
      if (k > n) subCadena 
      else {
        val nSubC = subCadena.flatMap(s1 => subCadena.map(s2 => s1 ++ s2).filter(o))
        generarSubC(k*2, nSubC)
      }
    }
    val ISubC = alfabeto.map(Seq(_))
    val subCadena = generarSubC(2, ISubC)
    subCadena.find(_.length == n).getOrElse(Seq())
  }

  def reconstruirCadenaTurboMejorada(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Seq[Seq[Char]]): Seq[Seq[Char]] = {
      if (k > n) subCadena
      else {
        val nSubC = filtrar(subCadena, k)
        generarSubC(k * 2, nSubC.filter(o))
      }
    }

    def filtrar(subCadena: Seq[Seq[Char]], k: Int): Seq[Seq[Char]] = {
      subCadena.flatMap { s1 =>
        subCadena.flatMap { s2 =>
          val s = s1 ++ s2
          val subCDeS = s.sliding(k/2)
          if (k == 2) Seq(s)
          else if (subCDeS.forall(sub => subCadena.contains(sub))) Seq(s)
          else Seq()
        }
      }
    }

    val ISubC = alfabeto.map(Seq(_))
    val subCadena = generarSubC(2, ISubC)
    subCadena.find(_.length == n).getOrElse(Seq())
  }
/*
  def reconstruirCadenaTurboMejorada(n: Int, o: Oraculo): Seq[Char] ={
      def reconstruirAux(t:Arbol )
  }
*/
}
