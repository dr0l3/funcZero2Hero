package example

import java.io.File

import cats.effect.IO

object Hello {
  def main(args: Array[String]): Unit = {
    val currentDir = new File(".")
    println(currentDir.getAbsolutePath)
    val files = currentDir.listFiles().toList.mkString(" - ")
    println(files)
    
    val better = IO {
      val dir = new File(".")
      println(dir.getAbsolutePath)
      val contents = dir.listFiles().toList.mkString(" - ")
      println(contents)
    }
    better.unsafeRunSync()
    
    val evenBetter = for {
      dir <- IO {new File(".")}
      _ <- IO {println(dir.getAbsolutePath)}
      contents <- IO {dir.listFiles().toList.mkString(" - ")}
      _ <- IO {println(contents)}
    } yield ()
    
    evenBetter.unsafeRunSync()
  }
}

