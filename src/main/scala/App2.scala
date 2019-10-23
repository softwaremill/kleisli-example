import com.typesafe.scalalogging.LazyLogging

/**
* this version uses pure functions but the functions are:
 * - difficult to memoize
 * - difficult to work with at the end
 * - uses logic for handling logs inside - breaks single repsonsiblitiy principle
 */
object App2 extends App with LazyLogging {

  val simple_1: (Boolean, String) => (Boolean, String) = { (x: Boolean, log: String) =>
    (!x, log + s"simple_1 called with ${x}\n")
  }

  val simple_2: (Boolean, String) => (Boolean, String) = { (x: Boolean, log: String) =>
    (!x, log + s"simple_2 called with ${x}\n")
  }

  val negate_1_Result = simple_1(true, "")
  val negate_2_Result = simple_2(false, negate_1_Result._2)
  logger.info("LOG: \n" + negate_2_Result._2)
}
