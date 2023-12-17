package proyectoF

import common.{parallel, task}

class SolucionesFuncPar {
  val alfabeto = Seq('a', 'c', 'g', 't')
  type Oraculo = Seq[Char] => Boolean

  def reconstruirCadenaIngenuoPar(n: Int, o: Oraculo): Seq[Char] = {
    def generarCadena(n: Int, cadena: Seq[Char] = Seq()): Seq[Seq[Char]] = {
      if (n == 0) {
        Seq(cadena)
      } else {
        val m = alfabeto.size
        val z = m / 2
        val sub1 = alfabeto.take(z)
        val sub2 = alfabeto.drop(z)
        val (p1, p2) = parallel(sub1.flatMap(s => generarCadena(n - 1, cadena :+ s)), sub2.flatMap(s => generarCadena(n - 1, cadena :+ s)))
        p1 ++ p2
      }
    }

    generarCadena(n).find(o).getOrElse(Seq())

  }

  def reconstruirCadenaMejoradoPar(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Set[Seq[Char]]): Set[Seq[Char]] = {
      if (k > n) subCadena
      else {
        val m = subCadena.size
        val z = m / 2
        val sub1 = subCadena.take(z)
        val sub2 = subCadena.drop(z)

        val (p1, p2) = parallel(sub1.flatMap(s1 => alfabeto.map(s2 => s1 ++ Seq(s2))).filter(o),
          sub2.flatMap(s1 => alfabeto.map(s2 => s1 ++ Seq(s2))).filter(o))

        generarSubC(k + 1, p1 ++ p2)
      }
    }

    val subCadena = generarSubC(1, Set(Seq()))
    subCadena.find(_.length == n).getOrElse(Seq())
  }

  def reconstruirCadenaTurboPar(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Set[Seq[Char]]): Set[Seq[Char]] = {
      if (k > n) subCadena
      else {
        val nSubC = subCadena.flatMap(s1 => subCadena.map(s2 => s1 ++ s2).filter(o))
        val m = nSubC.size
        val z = m / 2
        val (p1, p2) = parallel(nSubC.take(z), nSubC.drop(z))
        generarSubC(k * 2, (p1 ++ p2))
      }
    }

    val ISubC = alfabeto.map(Seq(_)).toSet
    val subCadena = generarSubC(2, ISubC)
    subCadena.find(_.length == n).getOrElse(Seq())

  }


  def reconstruirCadenaTurboMejoradaPar(n: Int, o: Oraculo): Seq[Char] = {
    def generarSubC(k: Int, subCadena: Seq[Seq[Char]]): Seq[Seq[Char]] = {
      if (k > n) subCadena
      else {
        val nSubC = filtrar(subCadena, k)
        val m = nSubC.size
        val z = m / 2
        val a = nSubC.take(z)
        val b = nSubC.drop(z)
        val (p1, p2) = parallel(a, b)
        generarSubC(k * 2, (p1++p2).filter(o))
      }
    }

    def filtrar(subCadena: Seq[Seq[Char]], k: Int): Seq[Seq[Char]] = {
      subCadena.flatMap { s1 =>
        subCadena.flatMap { s2 =>
          val s = s1 ++ s2
          val subCDeS = s.sliding(k / 2)
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
}
