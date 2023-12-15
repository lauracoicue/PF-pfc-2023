file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/ProyectoF.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.

action parameters:
offset: 1019
uri: file:///C:/Users/usuario/OneDrive/Escritorio/PROGRAMACION%20FUNCIONAL/PF-pfc-2023/app/src/main/scala/proyectoF/ProyectoF.scala
text:
```scala
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

    val benchmark = new Benchmark();
    val a = new FunOraculo

    for {
      i <- 1 to 10
      n = math.pow(2, i).toInt
      m1 = a.generarCadena(n)
      m2 = a.generarOraculo(m1)
    } yield {
      val (t1, t2, aceleracion) = benchmark.compararAlgoritmos(
        new SolucionesF().reconstruirCadenaMejorado,
        new Strassen().reconstruirCadenaTurbo
      )(n, @@m2);
      println(s"Tiempo secuencial: $t1");
      println(s"Tiempo paralelo: $t2");
      println(s"Aceleración: $aceleracion");


    }
  }
 }

```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:131)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:186)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:94)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:63)
	scala.meta.internal.pc.MetalsSignatures$.signatures(MetalsSignatures.scala:17)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:51)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:388)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 0