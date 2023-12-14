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
    def generarSecuencias(k: Int, secuencias: Seq[Set[Seq[Char]]]): Seq[Set[Seq[Char]]] = {
        if (k > n) secuencias
        else {
          val nSecuencias = secuencias(k-1).flatMap(s => alfabeto.map(c => s :+ c)).filter(o)
          generarSecuencias(k+1, secuencias :+ nSecuencias)
        }
      }
      val secuencias = generarSecuencias(1, Seq(Set(Seq())))
      secuencias(n).find(_.length == n).getOrElse(Seq())
  }
}
