package example

import cats.effect.IO
import example.Files._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Hello {
	def main(args: Array[String]): Unit = {
		impure()
		
		val better = pure
		better.unsafeRunSync()
		
		val evenBetter = alsoPure
		evenBetter.unsafeRunSync()
		
		listDirsInDir(".").unsafeRunSync().foreach(println)
		println("-"*20)
		listFilesInDirectory(".").unsafeRunSync().foreach(println)
		
		val otherProgram = createPrintDelete
		val res = otherProgram.runAsync(displayResult).unsafeToFuture()
		Await.result(res,Duration(5,"sec"))
		
		dirPrinter(".").runAsync(displayResult).unsafeRunSync()
	}
	
	def displayResult(res: Either[Throwable, Unit]): IO[Unit] = res match {
		case Left(error) => IO {println("Error occured during computation. Stacktrce: "); error.printStackTrace(); println(s"Error during computation: ${error.toString} ${error.getMessage}")}
		case Right(_) => IO {println("Done")}
	}
}

