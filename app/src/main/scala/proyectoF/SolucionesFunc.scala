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
          val nSubC = subCadena(k-1).flatMap(s => alfabeto.map(c => s :+ c)).filter(o)
          generarSubC(k+1, subCadena :+ nSubC)
        }
      }
      val subCadena = generarSubC(1, Seq(Set(Seq())))
      subCadena(n).find(_.length == n).getOrElse(Seq())
  }
}
