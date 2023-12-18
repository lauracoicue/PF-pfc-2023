package proyectoF

import common.{parallel, task}

import common._
class SolucionesFuncPar {

  val trie = new Arbol
  val funcSecuencial = new SolucionesFunc
  val alfabeto = Seq('a', 'c', 'g', 't')
  type Oraculo = Seq[Char] => Boolean

  /**
   * Reconstruir cadena de forma Ingenua paralela
   * @param umbral: Int Determina si se aplica la funcion secuencial o paralela 
   * @param n : Int cantidad de caracteres de la cadena
   * @param o : Oraculo, indica su la subcadena hace parte de la cadena
   * @return Seq[Char] Cadena resultante
   */
  def reconstruirCadenaIngenuoPar(umbral: Int)(n: Int, o: Oraculo): Seq[Char] = {
    if (umbral<=n) {
      funcSecuencial.reconstruirCadenaIngenuo(n, o)
    }
    else {
    def generarCadena(n: Int, cadena: Seq[Char] = Seq()): Seq[Seq[Char]] = {
          if (n == 0) {
            Seq(cadena)
          } else {
            val m = alfabeto.size
            val z = m / 2
            val sub1 = alfabeto.take(z)
            val sub2 = alfabeto.drop(z)
            val (p1, p2) = parallel(
              sub1.flatMap(s => generarCadena(n - 1, cadena :+ s)),
              sub2.flatMap(s => generarCadena(n - 1, cadena :+ s))
            )
            p1 ++ p2

          }
      }
      generarCadena(n).find(o).getOrElse(Seq())
    }

  }

  /**
   * Reconstruir cadena de forma Mejorada paralela
   * @param umbral: Int Determina si se aplica la funcion secuencial o paralela
   * @param n : Int cantidad de caracteres de la cadena
   * @param o : Oraculo, indica su la subcadena hace parte de la cadena
   * @return Seq[Char] Cadena resultante
   */
      def reconstruirCadenaMejoradoPar(umbral:Int)(n: Int, o: Oraculo): Seq[Char] = {
        if(umbral<=n){
          funcSecuencial.reconstruirCadenaMejorado(n, o)
        }
        else {
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
      }


  /**
   * Reconstruir cadena de forma Turbo paralela
   * @param umbral: Int Determina si se aplica la funcion secuencial o paralela
   * @param n : Int cantidad de caracteres de la cadena
   * @param o : Oraculo, indica su la subcadena hace parte de la cadena
   * @return Seq[Char] Cadena resultante
   */
      def reconstruirCadenaTurboPar(umbral:Int)(n: Int, o: Oraculo): Seq[Char] = {
        if(umbral<=n){
          funcSecuencial.reconstruirCadenaTurbo(n, o)
        }
        else {
          def generarSubC(k: Int, subCadena: Seq[Seq[Char]]): Seq[Seq[Char]] = {
            if (k > n) subCadena
            else {
              val (p1, p2) = parallel(subCadena.take(subCadena.length / 2).flatMap(s1 => subCadena.map(s2 => s1 ++ s2).filter(o)),
                subCadena.drop(subCadena.length / 2).flatMap(s1 => subCadena.map(s2 => s1 ++ s2).filter(o)))
              generarSubC(k * 2, p1 ++ p2)
            }
          }

          val ISubC = alfabeto.map(Seq(_))
          val subCadena = generarSubC(2, ISubC)
          subCadena.find(_.length == n).getOrElse(Seq())
        }
      }

  /**
   * Reconstruir cadena de forma Turbo Mejorada paralela
   * @param umbral: Int Determina si se aplica la funcion secuencial o paralela
   * @param n : Int cantidad de caracteres de la cadena
   * @param o : Oraculo, indica su la subcadena hace parte de la cadena
   * @return Seq[Char] Cadena resultante
   */
      def reconstruirCadenaTurboMejoradaPar(umbral:Int)(n: Int, o: Oraculo): Seq[Char] = {
        if(umbral<=n){
          funcSecuencial.reconstruirCadenaTurboMejorada(n, o)
        }
        else {
          def generarSubC(k: Int, subCadena: Seq[Seq[Char]]): Seq[Seq[Char]] = {
            if (k > n) subCadena
            else {
              val nSubC = filtrar(subCadena, k)
              generarSubC(k * 2, nSubC.filter(o))
            }
          }

          def filtrar(subCadena: Seq[Seq[Char]], k: Int): Seq[Seq[Char]] = {
            val (p1, p2) = parallel(subCadena.take(subCadena.length / 2).flatMap { s1 =>
              subCadena.flatMap { s2 =>
                val s = s1 ++ s2
                val subCDeS = s.sliding(k / 2)
                if (k == 2) Seq(s)
                else if (subCDeS.forall(sub => subCadena.contains(sub))) Seq(s)
                else Seq()
              }
            }, subCadena.drop(subCadena.length / 2).flatMap { s1 =>
              subCadena.flatMap { s2 =>
                val s = s1 ++ s2
                val subCDeS = s.sliding(k / 2)
                if (k == 2) Seq(s)
                else if (subCDeS.forall(sub => subCadena.contains(sub))) Seq(s)
                else Seq()
              }
            })
            p1 ++ p2
          }

          val ISubC = alfabeto.map(Seq(_))
          val subCadena = generarSubC(2, ISubC)
          subCadena.find(_.length == n).getOrElse(Seq())
        }
      }


  /**
   * Reconstruir cadena de forma Turbo Acelerada paralela
   * @param umbral: Int Determina si se aplica la funcion secuencial o paralela
   * @param n : Int cantidad de caracteres de la cadena
   * @param o : Oraculo, indica su la subcadena hace parte de la cadena
   * @return Seq[Char] Cadena resultante
   */
  def reconstruirCadenaTurboAceleradaPar(umbral:Int)(n: Int, o: Oraculo): Seq[Char] = {
    if (umbral<=n) {
      funcSecuencial.reconstruirCadenaTurboAcelerada(n, o)
    }
    else {
      def generarSubC(k: Int, subCadena: Seq[Seq[Char]]): Seq[Seq[Char]] = {
        if (k >= n) subCadena
        else {
          val (p1, p2) = parallel(subCadena.take(subCadena.length / 2).flatMap { s1 =>
            subCadena.flatMap { s2 =>
              Seq(s1 ++ s2)
            }
          }, subCadena.drop(subCadena.length / 2).flatMap { s1 =>
            subCadena.flatMap { s2 =>
              Seq(s1 ++ s2)
            }
          })
          val SCactual = filtrar(p1 ++ p2, subCadena, k)
          val SCkFiltrado = SCactual.filter(o)
          generarSubC(k * 2, SCkFiltrado)
        }
      }

      def filtrar(Cactual: Seq[Seq[Char]], Canterior: Seq[Seq[Char]], k: Int): Seq[Seq[Char]] = {
        if (Cactual.head.length > 2) {
          val t = trie.arbolDeSufijos(Canterior)
          val (p1, p2) = parallel(Cactual.take(Cactual.length / 2).filter { s1 => 0 to s1.length - k forall { i => trie.pertenece(s1.slice(i, i + k), t) } },
            Cactual.drop(Cactual.length / 2).filter { s1 => 0 to s1.length - k forall { i => trie.pertenece(s1.slice(i, i + k), t) } })
          p1 ++ p2
        } else Cactual
      }

      val ISubC = alfabeto.map(Seq(_)).filter(o)
      val subCadena = generarSubC(1, ISubC)
      subCadena.head
    }
  }
}
