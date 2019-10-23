import com.typesafe.scalalogging.LazyLogging

/**
 * this version uses pure functions but the functions are:
 * - difficult to work with at the end - we need to modify the process of composing functions
 */
object App3 extends App with LazyLogging {

  val simple_1: Boolean => (Boolean, String) = { (x: Boolean) =>
    (!x, s"simple_1 called with ${x}\n")
  }

  val simple_2: Boolean => (Boolean, String) = { (x: Boolean) =>
    (!x, s"simple_2 called with ${x}\n")
  }

  val negate_1_Result = simple_1(true)
  val negate_2_Result = simple_2(false)
  logger.info("LOG: \n" + negate_1_Result._2 + negate_2_Result._2)
}
