package proyectoF
import scala.util.Random

class FunOraculo {
  val Random = new Random
  val a = new SolucionesFunc

  /**
   * Generar cadenas de forma aleatoria
   * @param n: Int Cantidad de caracteres de la cadena
   * @return Seq[Char] Cadena resultante
   */
  def generarCadena(n:Int): Seq[Char] = {
    Seq.fill(n)(a.alfabeto(Random.nextInt(a.alfabeto.size)))
  }

  /**
   * Comprobación en el oraculo
   * @param cadena: Seq[Char] Cadena aleatoria 
   * @return Oraculo: Boolean Indica si la subcadena hace parte de la cadena
   */
  def generarOraculo(cadena: Seq[Char]): a.Oraculo = {
    subcadena => cadena.containsSlice(subcadena)
  }
}
