package views.formdata

import models.GradeLevel
import models.GradePointAverage
import models.Hobby
import models.Major
import play.api.data.validation.ValidationError

import scala.collection.mutable.ListBuffer

//remove if not needed

case class StudentFormData(name: String = "",
                           password: String = "",
                           level: String = "",
                           gpa: String = "",
                           hobbies: List[String] = List(),
                           majors: List[String] = List()
                            ) {

  def validate(): ListBuffer[ValidationError] = {
    var errors = new ListBuffer[ValidationError]()

    if (name == null || name.length == 0) {
      errors += new ValidationError("name", "No name was given.")
    }

    if (password == null || password.length == 0) {
      errors += new ValidationError("password", "No password was given.")
    } else if (password.length < 5) {
      errors += new ValidationError("password", "Given password is less than five characters.")
    }

    if (hobbies.size > 0) {
      for (hobby <- hobbies if Hobby.findHobby(hobby) == null) {
        errors += new ValidationError("hobbies", "Unknown hobby: " + hobby + ".")
      }
    }

    if (level == null || level.length == 0) {
      errors += new ValidationError("level", "No grade level was given.")
    } else if (GradeLevel.findLevel(level) == null) {
      errors += new ValidationError("level", "Invalid grade level: " + level + ".")
    }

    if (gpa == null || gpa.length == 0) {
      errors += new ValidationError("gpa", "No gpa was given.")
    } else if (GradePointAverage.findGPA(gpa) == null) {
      errors += new ValidationError("gpa", "Invalid GPA: " + gpa + ".")
    }

    if (majors.size > 0) {
      for (major <- majors if Major.findMajor(major) == null) {
        errors += new ValidationError("majors", "Unknown Major: " + major + ".")
      }
    }

    if (errors.size > 0) errors
    else null
  }
}
