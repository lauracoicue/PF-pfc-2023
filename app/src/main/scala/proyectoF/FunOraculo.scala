package proyectoF
import scala.util.Random

class FunOraculo {
  val Random = new Random
  val a = new SolucionesFunc

  def generarCadena(n:Int): Seq[Char] = {
    Seq.fill(n)(a.alfabeto(Random.nextInt(a.alfabeto.size)))
  }

  def generarOraculo(cadena: Seq[Char]): a.Oraculo = {
    subcadena => cadena.containsSlice(subcadena)
  }
}
