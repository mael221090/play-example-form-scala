package models


object GradeLevel {

  val getNameList: List[String] = List("Freshman", "Sophomore", "Junior", "Senior", "Other")

  def findLevel(levelName: String): GradeLevel = {
    allLevels.find(levelName == _.name).orNull
  }

  def getDefaultLevel: GradeLevel = findLevel("Freshman")

  val allLevels: List[GradeLevel] = List[GradeLevel](GradeLevel(1L, "Freshman"),
                                                     GradeLevel(2L, "Sophomore"),
                                                     GradeLevel(3L, "Junior"),
                                                     GradeLevel(4L, "Senior"),
                                                     GradeLevel(5L, "Other")
  )
}

case class GradeLevel(id: Long, name: String)
