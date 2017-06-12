package example

import java.io.{File, PrintWriter}
import java.nio.file.Files

import cats.effect.IO

import scala.io.Source

/**
  * Created by dr0l3 on 6/9/17.
  */
object Files {
	def impure() = {
		val currentDir = new File(".")
		println(currentDir.getAbsolutePath)
		val files = currentDir.listFiles().toList.mkString(" - ")
		println(files)
	}
	
	def pure = IO {
		val dir = new File(".")
		println(dir.getAbsolutePath)
		val contents = dir.listFiles().toList.mkString(" - ")
		println(contents)
	}
	
	def alsoPure: IO[Unit] = {
		for {
			dir <- IO {new File(".")}
			_ <- IO {println(dir.getAbsolutePath)}
			contents <- IO {dir.listFiles().toList.mkString(" - ")}
			_ <- IO {println(contents)}
		} yield ()
	}
	
	def getContents(path: String): IO[List[String]] = {
		for {
			dir <- IO { new File(path)}
			contents <- IO {dir.listFiles.toList.map(_.getCanonicalPath)}
		} yield contents
	}
	
	def print(stuff: String): IO[Unit] = {
		for {
			_ <- IO {println(stuff)}
		} yield ()
	}
	
	def printContents(path:String): IO[Unit] = {
		for {
			dir <- IO {new File(path)}
			contents <- getContents(path)
			_ <- print(s"Contents of the directory: ${dir.getAbsolutePath} \n \n${contents.mkString("\n")} \n")
		} yield ()
	}
	
	def createExampleFileAndPrint(fileName: String): IO[File] = {
		for {
			file <- IO {new File(fileName)}
			writer <- IO {new PrintWriter(file)}
			_ <- IO {writer.write("Hello world")}
			_ <- IO {writer.close()}
		} yield file
	}
	
	def createPrintDelete: IO[Unit] = {
		for {
			_ <- printContents(".")
			file <- createExampleFileAndPrint("hello.txt")
			_ <- printContents(".")
			_ <- IO {println(Source.fromFile(file.getAbsolutePath).getLines.mkString("\n"))}
			_ <- IO {println(file.getAbsolutePath)}
			_ <- IO {file.delete()}
			_ <- printContents(".")
		} yield ()
	}
	
	def listFilesInDirectory(path: String): IO[List[File]] = {
		for {
			filePath <- IO {new File(path)}
			contents <- IO {filePath.listFiles}
			files <- IO {contents.filter(_.isFile).toList}
		} yield files
	}
	
	def listDirsInDir(path:String): IO[List[File]] =  {
		for {
			filePath <- IO {new File(path)}
			contents <- IO {filePath.listFiles}
			files <- IO {contents.filter(_.isDirectory).toList}
		} yield files
	}
	
	def dirPrinter(path:String) : IO[Unit] = {
		def inner(file: File, indent: Int): IO[Unit] = {
			for {
				contents <- IO {file.listFiles()}
				_ <- IO {println("-"*(indent-2) + file.getName)}
				files <- IO {contents.filter(_.isFile)}
				prettyPrinted <- IO {files.map("-"*indent + _.getName)}
				_ <- IO {prettyPrinted.foreach(println)}
				dirs <- IO{contents.filter(_.isDirectory)}
				_ <- IO{ dirs.foreach(inner(_, indent + 2).unsafeRunSync())}
			} yield ()
		}
		inner(new File(path),0)
	}
	
}
