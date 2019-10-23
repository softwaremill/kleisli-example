import cats.data.Kleisli
import cats.effect.IO
import com.typesafe.scalalogging.LazyLogging
import cats.implicits._

object App7 extends App with LazyLogging {

  val r = scala.util.Random

  val generate: Unit => IO[Int] = _ => IO.pure(r.nextInt(100))
  val process: Int => IO[Double] = num => IO.pure(num * math.Pi)
  val save: Double => IO[Boolean] = num => IO.pure(true)

  val kleisliCombine_1: Kleisli[IO, Unit, Boolean] = {
    val generateK:Kleisli[IO, Unit, Int] = Kleisli(generate)
    val processK:Kleisli[IO, Int, Double] = Kleisli(process)
    val saveK:Kleisli[IO, Double, Boolean] = Kleisli(save)
    generateK andThen processK andThen saveK
  }
  logger.info(s"Kleilis example 1: ${kleisliCombine_1.run().unsafeRunSync()}")
  //this way we are back in our simple function composition

  val kleisliCombine_2: Kleisli[IO, Unit, Boolean] = Kleisli(generate) >>> Kleisli(process) >>> Kleisli(save)
  logger.info(s"Kleilis example 2: ${kleisliCombine_2.run().unsafeRunSync()}")

  //but we can get much cleaner
  val kleisliCombine_3: Kleisli[IO, Unit, Boolean] = Kleisli(generate) andThen process andThen save
  logger.info(s"Kleilis example 3: ${kleisliCombine_3.run().unsafeRunSync()}")

}
