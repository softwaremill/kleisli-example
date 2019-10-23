import cats.effect.IO
import com.typesafe.scalalogging.LazyLogging

object App6 extends App with LazyLogging {

  val r = scala.util.Random

  val generate: Unit => IO[Int] = _ => IO.pure(r.nextInt(100))
  val process: Int => IO[Double] = num => IO.pure(num * math.Pi)
  val save: Double => IO[Boolean] = number => IO.pure(true)

  //this stuff cannot be composed like we did before, we can try to flatmap it
  val flatMappedVersion: Unit => Boolean = _ => {
    val comboFlatMap: Unit => IO[Boolean] = _ => {
      generate().flatMap { number =>
        process(number).flatMap { processed =>
          save(processed)
        }
      }
    }
    comboFlatMap().unsafeRunSync()
  }

  logger.info(s"FlatMap that sh**t: ${flatMappedVersion()}")

  //using for comprehension
  val forComp: Unit => Boolean = _ => {
    val comboForComp: Unit => IO[Boolean] = _ => {
      for {
        number <- generate()
        processed <- process(number)
        result <- save(processed)
      } yield result
    }
    comboForComp().unsafeRunSync()
  }
  logger.info(s"For comprehension version: ${forComp()}")
}
