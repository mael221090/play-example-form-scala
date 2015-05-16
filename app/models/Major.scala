package models

import views.formdata.StudentFormData

object Major {

  def makeMajorMap(student: StudentFormData): Map[String, Boolean] = {
    allMajors.map(major =>
      major.name -> (if (student == null) false else student.majors.contains(major.name))
    ).toMap
  }

  def findMajor(majorName: String): Major = {
    allMajors.find(majorName == _.name).orNull
  }

  val allMajors: List[Major] = List[Major](Major(1L, "Chemistry"),
                                           Major(2L, "Biology"),
                                           Major(3L, "Physics"),
                                           Major(4L, "Mathematics")
  )
}

case class Major(id: Long,name: String)
