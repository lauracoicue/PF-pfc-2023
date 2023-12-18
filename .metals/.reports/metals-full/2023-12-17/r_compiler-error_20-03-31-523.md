file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/Arbol.scala
### java.lang.AssertionError: assertion failed: NoType

occurred in the presentation compiler.

action parameters:
offset: 1230
uri: file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/Arbol.scala
text:
```scala
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
  def adicionar(s: Seq[Char], t: Trie): Trie = {
    if (s.isEmpty) t
    else {
      t match {
        case Nodo(c, marcada, hijos) => {
          val h = hijos.find{
            c@@
          }
          if (h.isEmpty) {
            Nodo(c, marcada, hijos :+ adicionar(s, Hoja(s.head, false)))
          } else {
            Nodo(c, marcada, hijos.map(h => if (raiz(h) == s.head) adicionar(s.tail, h) else h))
          }
        }
        case Hoja(c, marcada) => Nodo(c, marcada, List(adicionar(s, Hoja(s.head, false))))
      }
    }
  }

  /*def adicionar(s: Seq[Char], t: Trie): Trie = {
    if (s.isEmpty) {
      t match {
        case Nodo(c, _, hijos) => Nodo(c, true, hijos)
        case Hoja(c, _) => Hoja(c, true)
      }
    } else {
      t match {
        case Nodo(c, marcada, hijos) => {
          val h = hijos.find(h => raiz(h) == s.head)
          if (h.isEmpty) {
            Nodo(c, marcada, hijos :+ adicionar(s, Hoja(s.head, false)))
          } else {
            Nodo(c, marcada, hijos.map(h => if (raiz(h) == s.head) adicionar(s.tail, h) else h))
          }
        }
        case Hoja(c, marcada) => Nodo(c, marcada, List(adicionar(s, Hoja(s.head, false))))
      }
    }
  }*/

  //recibe una secuencia de secuencias ss y devuelve un trie correspondiente al arbol de sifijos ss
  def arbolDeSufijos(ss: Set[Seq[Char]]): Trie = {
    ss.foldLeft(Hoja(' ', false): Trie)((t, s) => adicionar(s, t))
  }


}

```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.core.Types$TypeBounds.<init>(Types.scala:5141)
	dotty.tools.dotc.core.Types$AliasingBounds.<init>(Types.scala:5220)
	dotty.tools.dotc.core.Types$TypeAlias.<init>(Types.scala:5242)
	dotty.tools.dotc.core.Types$TypeAlias$.apply(Types.scala:5279)
	dotty.tools.dotc.core.Types$Type.bounds(Types.scala:1732)
	scala.meta.internal.pc.completions.CaseKeywordCompletion$.contribute(MatchCaseCompletions.scala:156)
	scala.meta.internal.pc.completions.Completions.advancedCompletions(Completions.scala:442)
	scala.meta.internal.pc.completions.Completions.completions(Completions.scala:183)
	scala.meta.internal.pc.completions.CompletionProvider.completions(CompletionProvider.scala:86)
	scala.meta.internal.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:136)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: NoType