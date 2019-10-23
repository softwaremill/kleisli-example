import com.typesafe.scalalogging.LazyLogging

/**
 * this version uses pure functions but the functions are:
 * - difficult to work with at the end - we need to modify the process of composing functions
 */
object App3 extends App with LazyLogging {

  val negate_1: Boolean => (Boolean, String) = { (x: Boolean) =>
    (!x, s"negate_1 called with ${x}\n")
  }

  val negate_2: Boolean => (Boolean, String) = { (x: Boolean) =>
    (!x, s"negate_2 called with ${x}\n")
  }

  val negate_1_Result = negate_1(true)
  val negate_2_Result = negate_2(false)
  logger.info("LOG: \n" + negate_1_Result._2 + negate_2_Result._2)
}
