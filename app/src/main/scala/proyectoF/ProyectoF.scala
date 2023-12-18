/**
  * Proyecto Final - Reconstrucción de cadenas
  * Autores: <Laura Tatiana Coicue Poquiguegue - 2276652-3743 
  *           Laura Sofía Peñaloza López - 2259485-3743
  *           Esmeralda Rivas Guzmán - 2259580-3743>
  * Profesor: Carlos A Delgado
  */
package proyectoF

import org.scalameter.measure
import org.scalameter.withWarmer
import org.scalameter.Warmer

object ProyectoF{

  def saludo() = "Proyecto Final 2023-II"

  def main(args: Array[String]): Unit = {

    println(saludo())
    println(
      withWarmer(new Warmer.Default) measure {
        (1 to 100000000).toArray
      }
    )

    val a = new SolucionesFunc
    val b = new FunOraculo
    val c = new SolucionesFuncPar
    val benchmark = new Benchmark();
/*
    //INGENUA
    for {
      i <- 1 to 3
      m1 = math.pow(2, i).toInt
      val cadena = b.generarCadena(m1)
      m2 = b.generarOraculo(cadena)
    } yield {
      println(s"****************************************")
      println(s"Algoritmo ingenuo con cadenas de tamaño ${i}");
      val (t1, t2, aceleracion) = benchmark.compararAlgoritmos(
        new SolucionesFuncPar().reconstruirCadenaIngenuoPar(1),
        new SolucionesFunc().reconstruirCadenaIngenuo
      )(m1, m2);
      println(s"Paralelizada=   $t1");
      println(s"Normal=   $t2");
      println(s"Aceleración=   $aceleracion");

    }

    //MEJORADA
    for {
      i <- 1 to 9
      m1 = math.pow(2, i).toInt
      val cadena = b.generarCadena(m1)
      m2 = b.generarOraculo(cadena)
    } yield {
      println(s"****************************************")
      println(s"Algoritmo mejorada de ${math.pow(2, i).toInt}x${math.pow(2, i).toInt}");
      val (t1, t2, aceleracion) = benchmark.compararAlgoritmos(
        new SolucionesFuncPar().reconstruirCadenaTurboMejoradaPar(8),
        new SolucionesFunc().reconstruirCadenaTurboMejorada
      )(m1, m2);
      println(s"Paralelizada=   $t1");
      println(s"Normal=   $t2");
      println(s"Aceleración=   $aceleracion");

    }

    //TURBO
    for {
      i <- 1 to 9
      m1 = math.pow(2, i).toInt
      val cadena = b.generarCadena(m1)
      m2 = b.generarOraculo(cadena)
    } yield {
      println(s"****************************************")
      println(s"Algotirmo Turbo ${i}")
      val (t1, t2, aceleracion) = benchmark.compararAlgoritmos(
        new SolucionesFuncPar().reconstruirCadenaTurboPar(8),
        new SolucionesFunc().reconstruirCadenaTurbo
      )(m1, m2);
      println(s"Paralelizada=   $t1");
      println(s"Normal=   $t2");
      println(s"Aceleración=   $aceleracion");

    }
*/
/*
    //TURBOMEJORADA
    for {
      i <- 1 to 9
      m1 = math.pow(2, i).toInt
      val cadena = b.generarCadena(m1)
      m2 = b.generarOraculo(cadena)
    } yield {
      println(s"****************************************")
      println(s"Algoritmo turbo mejorado ${math.pow(2, i).toInt}");
      val (t1, t2, aceleracion) = benchmark.compararAlgoritmos(
        new SolucionesFuncPar().reconstruirCadenaTurboMejoradaPar(8),
        new SolucionesFunc().reconstruirCadenaTurboMejorada()
      )(m1, m2);
      println(s"Paralelizada=   $t1");
      println(s"Normal=   $t2");
      println(s"Aceleración=   $aceleracion");

    }
*/
    //TURBOACELERADA
    for {
      i <- 1 to 9
      m1 = math.pow(2, i).toInt
      val cadena = b.generarCadena(m1)
      m2 = b.generarOraculo(cadena)
    } yield {
      println(s"****************************************")
      println(s"Algoritmo turbo mejorado ${math.pow(2, i).toInt}");
      val (t1, t2, aceleracion) = benchmark.compararAlgoritmos(
        new SolucionesFuncPar().reconstruirCadenaTurboAcelerada(8),
        new SolucionesFunc().reconstruirCadenaTurboAcelerada
      )(m1, m2);
      println(s"Paralelizada=   $t1");
      println(s"Normal=   $t2");
      println(s"Aceleración=   $aceleracion");

    }

  }

    /*val n = 4
    val cadena = b.generarCadena(n)
    val oraculo = b.generarOraculo(cadena)
    val resultado = a.reconstruirCadenaTurboMejorado(n, oraculo) 
    val resultado1 = a.reconstruirCadenaTurbo(n, oraculo)
    println(cadena)
    println(resultado)
    println(resultado1)*/


 }
