import com.typesafe.scalalogging.LazyLogging
/**
  * this version tries to compose the functions in a more general way
  */
object App4 extends App with LazyLogging {


  val simple_1: Boolean => (Boolean, String) = { (x: Boolean) =>
    (!x, s"simple_1 called with ${x}\n")
  }

  val simple_2: Boolean => (Boolean, String) = { (x: Boolean) =>
    (!x, s"simple_2 called with ${x}\n")
  }

  //we will create a function to compose which takes two functions which are composable
  //this is regular function composition
  def compose[A, B, C](f: A => B, g: B => C): A => C = { (x: A) =>
    {
      val p1 = f(x)
      val p2 = g(p1)
      p2
    }
  }

  //but we want the one to work with tuples
  //so in other words we take a function which takes boolean and returns boolean and convert it to the function which takes a boolean and returns boolean with a string,
  //double/double to double/(double,string) etc
  def composeT[A](f: A => (A, String),
                  g: A => (A, String)): A => (A, String) = { (x: A) =>
    {
      val p1 = f(x)
      val p2 = g(p1._1)
      (p2._1, p1._2 + p2._2)
    }
  }

  def composed: Boolean => (Boolean, String) = composeT(simple_1, simple_2)

  logger.info(s"Result: ${composed.apply(true)}")


  //identity function
  //take an A and return a pair, it should do nothing to the log
  def id[A](a: A): (A, String) = (a, "")
}
