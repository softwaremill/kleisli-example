import com.typesafe.scalalogging.LazyLogging

/**
* this version uses pure functions but the functions are:
 * - difficult to memoize
 * - difficult to work with at the end
 * - uses logic for handling logs inside - breaks single repsonsiblitiy principle
 */
object App2 extends App with LazyLogging {

  val negate_1: (Boolean, String) => (Boolean, String) = { (x: Boolean, log: String) =>
    (!x, log + s"negate_1 called with ${x}\n")
  }

  val negate_2: (Boolean, String) => (Boolean, String) = { (x: Boolean, log: String) =>
    (!x, log + s"negate_2 called with ${x}\n")
  }

  val negate_1_Result = negate_1(true, "")
  val negate_2_Result = negate_2(false, negate_1_Result._2)
  logger.info("LOG: \n" + negate_2_Result._2)
}
