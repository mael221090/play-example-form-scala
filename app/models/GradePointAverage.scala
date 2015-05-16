package models

import views.formdata.StudentFormData

object GradePointAverage {

  def makeGPAMap(student: StudentFormData): Map[String, Boolean] = {
    allGPAs.map(gpa =>
      (gpa.name, if (student == null) false else student.gpa != null && student.gpa == gpa.name)
    ).toMap
  }

  val getGPAList: List[String] = List("4.0", "3.0 - 3.9", "2.0 - 2.9", "1.0 - 1.9")

  def findGPA(gpaName: String): GradePointAverage = {
    allGPAs.find(gpaName == _.name).orNull
  }

  def getDefaultGPA: GradePointAverage = findGPA("4.0")

  val allGPAs: List[GradePointAverage] = List(GradePointAverage(1L, "4.0"),
                                              GradePointAverage(2L, "3.0 - 3.9"),
                                              GradePointAverage(3L, "2.0 - 2.9"),
                                              GradePointAverage(4L, "1.0 - 1.9")
  )
}

case class GradePointAverage(id: Long, name: String)