package example

object Hello {
  def main(args: Array[String]): Unit = {
    println(myFirstFunction(1,2))
    println(mySecondFunction(1,2))
    println(myThirdFunction(1,2,myFirstFunction))
    println(myThirdFunction(2,1,myFourthFunction))
    println(stringAppender("hello,","world!"))
    println(stringCombiner("hello,","world!",stringAppender))
    println(generic(1,2,myFirstFunction))
    println(generic(2,1,myFourthFunction))
    println(generic("hello,","world!", stringAppender))
    println(multCreator(1,1.*)(1))
    println(multCreator(2,1.*)(1))
    println(multCreator(1,2.*)(1))
    println(multCreator(2,2.*)(1))
    println(createSpecificAppender("world!")("hello,"))
    val stringFunc: String => String = createSpecificAppender("world!")
    println(stringFunc("Hello,"))
    println(stringFunc("Go away,"))
  }
  
  def myFirstFunction(x: Int, y: Int) = {
    x+y
  }
  
  def mySecondFunction(x:Int,y:Int): Int = {
    x+y
  }
  
  def myThirdFunction(x: Int, y: Int, f: (Int, Int) => Int) = {
    f(x,y)
  }
  
  def myFourthFunction(x: Int, y: Int): Int = {
    x-y
  }
  
  def multCreator(x: Int, f: Int => Int): Int => Int = {
    def inner(y: Int): Int = {
      f(y)
    }
    inner
  }
  
  def stringAppender(x: String, y: String): String ={
    x + y
  }
  
  def stringCombiner(x:String, y: String, f: (String, String) => String): String = {
    f(x,y)
  }
  
  def generic[A](x:A,y:A, f: (A,A) => A): A ={
    f(x,y)
  }
  
  def createSpecificAppender(x: String): String => String = {
    def combiner(y: String): String ={
      y+x
    }
    combiner
  }
}

