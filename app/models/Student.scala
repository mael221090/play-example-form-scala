package models

import views.formdata.StudentFormData
import scala.collection.mutable.ListBuffer

object Student {

  def makeStudentFormData(id: Long): StudentFormData = {
    allStudents.filter(_.id == id).map(student =>
      StudentFormData(student.name, student.password, student.level.name, student.gpa.name,
        student.hobbies.map(_.name), student.majors.map(_.name))
    ).head

    for (student <- allStudents if student.id == id) {
      StudentFormData(student.name, student.password, student.level.name, student.gpa.name,
        student.hobbies.map(_.name), student.majors.map(_.name))
    }
    throw new RuntimeException("Couldn't find student")
  }

  def makeInstance(formData: StudentFormData): Student = {
    new Student(name = formData.name,
      password = formData.password,
      level = GradeLevel.findLevel(formData.level),
      gpa = GradePointAverage.findGPA(formData.gpa),
      hobbies = formData.hobbies.map(hobby => Hobby.findHobby(hobby)),
      majors = formData.majors.map(major => Major.findMajor(major))
    )
  }

  val allStudents: List[Student] = List[Student](
    Student(1L, "Joe Good", "mypassword", GradeLevel.findLevel("Freshman"), GradePointAverage.findGPA("4.0")),
    Student(2L, "Alice Good", "mypassword", GradeLevel.findLevel("Sophomore"), GradePointAverage.findGPA("4.0")),
    Student(3L, "Frank Bad", "pass", GradeLevel.findLevel("Freshman"), GradePointAverage.findGPA("4.0"),
      List(Hobby.findHobby("Biking"), Hobby.findHobby("Surfing")), List(Major.findMajor("Chemistry"), Major.findMajor("Physics"))
    )
  )

  def getById(id: Long): Student = {
    allStudents.filter(_.id == id).head
  }
}

case class Student(id: Long = 0, name: String, password: String, level: GradeLevel, gpa: GradePointAverage,
                   hobbies: List[Hobby] = List(), majors: List[Major] = List()
                    )