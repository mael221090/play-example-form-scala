package controllers

import models.GradeLevel
import models.GradePointAverage
import models.Hobby
import models.Major
import models.Student
import play.api.data._
import play.api.data.Forms._

import views.formdata.StudentFormData
import play.api.mvc._

object Application extends Controller {
  val studentForm = Form(
    mapping(
      "name" -> text,
      "password" -> text,
      "level" -> text,
      "gpa" -> text,
      "hobbies" -> list(text),
      "majors" -> list(text)
    )(StudentFormData.apply)(StudentFormData.unapply)
  )

  def getIndex(id: Long) = Action { implicit request =>
    val studentData = if (id == 0) new StudentFormData() else models.Student.makeStudentFormData(id)

    val formData = studentForm.fill(studentData)

    Ok(views.html.index(
      formData,
      Hobby.makeHobbyMap(studentData),
      GradeLevel.getNameList,
      GradePointAverage.makeGPAMap(studentData),
      Major.makeMajorMap(studentData)
    ))
  }

  def postIndex() = Action { implicit request =>
    val formData: Form[StudentFormData] = studentForm.bindFromRequest()

    if (formData.hasErrors) {
      BadRequest(views.html.index(formData,
                                  Hobby.makeHobbyMap(null),
                                  GradeLevel.getNameList,
                                  GradePointAverage.makeGPAMap(null),
                                  Major.makeMajorMap(null)
      )
        (Flash(Map("error" -> "Please correct errors above.")))
      )
    }
    else {
      val student = Student.makeInstance(formData.get)
      Ok(views.html.index(formData,
                          Hobby.makeHobbyMap(formData.get),
                          GradeLevel.getNameList,
                          GradePointAverage.makeGPAMap(formData.get),
                          Major.makeMajorMap(formData.get)
      )
        (Flash(Map("success" -> ("Student instance created/edited: " + student))))
      )
    }
  }
}
