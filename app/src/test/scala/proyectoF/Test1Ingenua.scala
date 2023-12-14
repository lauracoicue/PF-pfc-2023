package proyectoF

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Test1Ingenua extends AnyFunSuite{
    val a = new SolucionesFunc
    val b = new FunOraculo

    test("Test 1"){
        val n = 2
        val cadena = b.generarCadena(n)
        val oraculo = b.generarOraculo(cadena)
        val resultado: Seq[Char] = a.reconstruirCadenaIngenuo(n, oraculo) 
        assert(resultado == cadena)
    }

    test("Test 2"){
        val n = 4
        val cadena = b.generarCadena(n)
        val oraculo = b.generarOraculo(cadena)
        val resultado: Seq[Char] = a.reconstruirCadenaIngenuo(n, oraculo) 
        assert(resultado == cadena)
    }
  
    test("Test 3"){
        val n = 8
        val cadena = b.generarCadena(n)
        val oraculo = b.generarOraculo(cadena)
        val resultado: Seq[Char] = a.reconstruirCadenaIngenuo(n, oraculo) 
        assert(resultado == cadena)
    }
}

