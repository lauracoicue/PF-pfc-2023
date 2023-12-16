package proyectoF
  import org.scalameter.measure
  import org.scalameter.withWarmer
  import org.scalameter.Warmer

class Benchmark {
  val obj1 = new SolucionesFunc()
  val obj2 = new FunOraculo
  def compararAlgoritmos(Funcion1: (Int,obj1.Oraculo) => Seq[Char], Funcion2: (Int, obj1.Oraculo) => Seq[Char])(m1: Int, m2: obj1.Oraculo): (Double, Double, Double) = {
    val timeF1 = withWarmer(new Warmer.Default) measure {
    Funcion1(m1, m2);
    }
    val timeF2 = withWarmer(new Warmer.Default) measure {
    Funcion2(m1, m2);
    }

    val promedio = timeF1.value / timeF2.value;
    (timeF1.value, timeF2.value, promedio);
}
}
