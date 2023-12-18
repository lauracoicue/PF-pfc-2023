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

  //recibe una secuencia s y un trie t, devuelve true si s pertenece a t
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


  //recibe una secuencia s y un trie t, devuelve el trie correspondiente a adicionar s a t
  def adicionar(s: Seq[Char], t: Trie): Trie = { // Prepara la "rama" a ser agregada al arbol correspondiente a la secuencia o resto de secuencia a ser añadida.
    def crearRama(s: Seq[Char]): Trie = {
      s match {
        case cabeza :: cola => cola match {
          case head :: tail => Nodo(cabeza, marcada = false, List(crearRama(cola)))
          case Nil => Hoja(cabeza, marcada = true)
        }
        case Nil => Nodo(' ', marcada = false, List())
      }
    }

    def agregarRama(arbolActual: Trie, prefix: Seq[Char], remaining: Seq[Char]): Trie = {
      (arbolActual, prefix, remaining) match {
        case (Nodo(car, marcada, hijos), _, head :: tail) if cabezas(Nodo(car, marcada, hijos)).contains(head) => // Recorre recursivamente el árbol hasta llegar al camino deseado
          val updatedHijos = hijos.map { hijo =>
            if (raiz(hijo) == head) agregarRama(hijo, prefix :+ head, tail)
            else hijo
          }
          Nodo(car, marcada, updatedHijos)
        case (Hoja(car, marcada), _, head :: tail) => Nodo(car, marcada,List(crearRama(remaining)))
        case (Nodo(car, marcada, hijos), _, head :: tail) => // Agrega el nuevo nodo a la lista de hijos cuando el camino se detiene en un Nodo
          Nodo(car, marcada, hijos :+ crearRama(remaining))
        case (Nodo(car, false, hijos), _, Nil) => Nodo(car, marcada = true, hijos)
        case (_, _, _) => arbolActual
      }
    }

    agregarRama(t, Seq.empty[Char], s)
  }


  //recibe una secuencia de secuencias ss y devuelve un trie correspondiente al arbol de sifijos ss
  def arbolDeSufijos(ss: Seq[Seq[Char]]): Trie = {
    ss.foldLeft(Nodo('_', false, List()): Trie)((t, s) => adicionar(s, t))
  }


}
