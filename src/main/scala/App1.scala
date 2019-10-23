import com.typesafe.scalalogging.LazyLogging

object App1 extends App with LazyLogging {

  var logAcc: String = ""

  val simple_1: Boolean => Boolean = { x =>
    logAcc = logAcc + s"negate_1 called with ${x}\n"
    x
  }

  val simple_2: Boolean => Boolean = { x =>
    logAcc = logAcc + s"negate_2 called with ${x}\n"
    x
  }

  simple_1(true)
  simple_2(false)
  logger.info("LOG: \n" + logAcc)
}
