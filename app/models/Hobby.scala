package models

import views.formdata.StudentFormData


object Hobby {
  val allHobbies: List[Hobby] = List(new Hobby(1L, "Surfing"),
                                     new Hobby(2L, "Biking"),
                                     new Hobby(3L, "Paddling"),
                                     new Hobby(4L, "Running")
  )

  def makeHobbyMap(student: StudentFormData): Map[String, Boolean] = {
    allHobbies.map( hobby =>
      hobby.name -> (student != null && student.hobbies.contains(hobby.name))
    ).toMap
  }

  def findHobby(hobbyName: String): Hobby = {
    allHobbies.find(hobbyName == _.name).orNull
  }
}

case class Hobby(id: Long, name: String)
