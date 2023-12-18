package proyectoF

class Arbol {
  abstract class Trie

  case class Nodo(car: Char, marcada: Boolean,
                  hijos: List[Trie]) extends Trie

  case class Hoja(car: Char, marcada: Boolean) extends Trie

  def raiz(t: Trie): Char = {
    t match {
      case Nodo(c, _, _) => c
      case Hoja(c, _) => c
    }
  }

  def cabezas(t: Trie): Seq[Char] = {
    t match {
      case Nodo(_, _, lt) => lt.map(t => raiz(t))
      case Hoja(c, _) => Seq[Char](c)
    }
  }

  /**
   * Funcion que determina si una secuencia de caracteres pertenece a un Trie
   * @param s: Seq[Char] secuencia de caracteres
   * @param t: Trie
   * @return Boolean Devuelve true si la secuencia de caracteres pertenece al Trie
   */
  def pertenece(s: Seq[Char], t: Trie): Boolean = {
    if (s.isEmpty) {
      t match {
        case Nodo(_, marcada, _) => marcada
        case Hoja(_, marcada) => marcada
      }
    } else {
      t match {
        case Nodo(_, _, hijos) => hijos.exists(h => raiz(h) == s.head && pertenece(s.tail, h))
        case Hoja(_, _) => false
      }
    }
  }


  def adicionar(s: Seq[Char], t: Trie): Trie = {
    def nueva(s: Seq[Char]): Trie = {
      s match {
        case cabeza :: cola => cola match {
          case head :: tail => Nodo(cabeza, marcada = false, List(nueva(cola)))
          case Nil => Hoja(cabeza, marcada = true)
        }
        case Nil => Nodo(' ', marcada = false, List())
      }
    }

    def adicionaraux(Aactual: Trie, Aanterior: Seq[Char], Afaltante: Seq[Char]): Trie = {
      (Aactual, Aanterior, Afaltante)
      match {
        case (Nodo(car, marcada, hijos), _, head :: tail) => Nodo(car, marcada, hijos :+ nueva(Afaltante))
        case (Nodo(car, false, hijos), _, Nil) => Nodo(car, marcada = true, hijos)
        case (Nodo(car, marcada, hijos), _, head :: tail)
          if cabezas(Nodo(car, marcada, hijos)).contains(head) =>
            val updatedHijos = hijos.map { hijo =>
              if (raiz(hijo) == head){
                adicionaraux(hijo, Aanterior :+ head, tail)
              }
              else hijo
            }
            Nodo(car, marcada, updatedHijos)
        case (Hoja(car, marcada), _, head :: tail) => Nodo(car, marcada,List(nueva(Afaltante)))
        case (_, _, _) => Aactual
      }
    }

    adicionaraux(t, Seq.empty[Char], s)
  }

  /**
   * Arbol de sufijos
   * @param ss: Seq[Seq[Char]] secuencia de secuencias de caracteres
   * @return Trie Devuelve el Trie correcpondiente al arbol de sufijos
   */

  def arbolDeSufijos(ss: Seq[Seq[Char]]): Trie = {
    ss.foldLeft(Nodo('_', false, List()): Trie)((t, s) => adicionar(s, t))
  }


}
