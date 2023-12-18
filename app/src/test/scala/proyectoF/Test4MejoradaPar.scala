package proyectoF

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Test4MejoradaPar extends AnyFunSuite{
  val a = new SolucionesFuncPar
  val b = new FunOraculo

  test("Test 1"){
    val n = 4
    val cadena = b.generarCadena(n)
    val oraculo = b.generarOraculo(cadena)
    val resultado: Seq[Char] = a.reconstruirCadenaMejoradoPar(4)(n, oraculo)
    assert(resultado == cadena)
  }

  test("Test 2"){
    val n = 8
    val cadena = b.generarCadena(n)
    val oraculo = b.generarOraculo(cadena)
    val resultado: Seq[Char] = a.reconstruirCadenaMejoradoPar(4)(n, oraculo)
    assert(resultado == cadena)
  }

  test("Test 3"){
    val n = 16
    val cadena = b.generarCadena(n)
    val oraculo = b.generarOraculo(cadena)
    val resultado: Seq[Char] = a.reconstruirCadenaMejoradoPar(4)(n, oraculo)
    assert(resultado == cadena)
  }

  test("Test 4"){
    val n = 512
    val cadena = b.generarCadena(n)
    val oraculo = b.generarOraculo(cadena)
    val resultado: Seq[Char] = a.reconstruirCadenaMejoradoPar(4)(n, oraculo)
    assert(resultado == cadena)
  }
}
