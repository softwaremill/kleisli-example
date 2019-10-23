import com.typesafe.scalalogging.LazyLogging

object App5 extends App with LazyLogging {

  val r = scala.util.Random

  val generate: Unit => Int = _ => r.nextInt(100)
  val process: Int => Double = v => v * math.Pi
  val save: Double => Boolean = _ => true

  //first version of composition
  val generated: Int = generate()
  val processed: Double = process(generated)
  val saved: Boolean = save(processed)

  logger.info(s"Result is: ${saved}")

  //second attempt with function
  val combine_1: Unit => Boolean = _ => save(process(generate()))
  logger.info(s"Result 1 is: ${combine_1()}")
  //this is kind of difficult to read

  //we can use compose or andThen
  val combine_2: Unit => Boolean = save compose process compose generate
  logger.info(s"Result 2 is: ${combine_2()}")
  //this is a bit difficult to read too as we are used to read from left to right

  //andThen version
  val combine_3: Unit => Boolean = generate andThen process andThen save
  logger.info(s"Result 3 is: ${combine_3()}")

  //the problem is will all the above examples that we need to match the inputs with the outputs
  //if the output of some function will be a wrapper around some type (e.g. Future) we will get into trouble

}
