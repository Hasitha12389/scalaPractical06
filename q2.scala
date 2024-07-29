import scala.io.StdIn._

def getStudentInfo(name: String,marks: Int,totalMarks: Int): (String,Int,Int,Double,Char)={
   val percentage = (marks.toDouble / totalMarks) * 100
   val grade = percentage match {
    case p if p >= 90 => 'A'
    case p if p >= 75 => 'B'
    case p if p >= 50 => 'C'
    case _ => 'D'
   }
   (name,marks,totalMarks,percentage,grade)
}

def printStudentRecord(student: (String,Int,Int,Double,Char)): Unit={
    val name = student._1
    val marks = student._2
    val possibleMark = student._3
    val percentage = student._4
    val grade = student._5
    
    println("\n---------Student Report-------------")
    println(s"\nStudent name : $name")
    println(s"Marks : $marks")
    println(s"Total Possible marks: $possibleMark")
    println(s"Percentage : $percentage%")
    println(s"Grade : $grade\n")
}

def validateInput(name: String,marks:Int, totalMarks:Int):(Boolean,Option[String]) = { 
   if(name.isEmpty){
   (false, Some("Name cannot be empty."))   
   }
   else if(marks < 0 || totalMarks <=0){
      (false, Some("Marks cannot exceed total possible marks."))
   }
   else if(marks > totalMarks) {
      (false, Some("Marks cannot exceed total possible marks."))
   }
   else {
      (true, None)
   }
}

def getStudentInfoWithRetry(): (String, Int, Int, Double, Char) = {
    var isValid = false
    var name = ""
    var marks = 0
    var totalMarks = 0

    while (!isValid) {
      name = readLine("\nEnter student's name: ")

      printf("Enter student's marks: ")
      marks = readInt()

      printf("Enter total possible marks: ")
      totalMarks = readInt()

      val validation = validateInput(name, marks, totalMarks)    // validation is tuple with (bool value,String)
      isValid = validation._1
      if (!isValid) {
        println(s"\nInvalid input!!!:\n   ${validation._2.get}")
      }
    }

    getStudentInfo(name, marks, totalMarks)
  }


def main(args: Array[String]): Unit={
   val studentInfo = getStudentInfoWithRetry()   // tuple
   printStudentRecord(studentInfo)
}