package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

class Streaks extends Controller {

	case class MarkForm(id: Int)
	val markForm = Form(
    	mapping(
      	"id" -> number
    	)(MarkForm.apply)(MarkForm.unapply)
  	)
	
	def index = Action {
		val habits = models.Habit.getHabits()
		Ok(views.html.index("Streaks - Home", habits))
	}

	def createHabit = Action {
		Redirect(routes.Streaks.index)
	}

	def markHabitDone = Action { implicit request =>
		val habitId = markForm.bindFromRequest.get
		models.Habit.markToday(new models.Habit(habitId.id))	
    	Redirect(routes.Streaks.index)	
	}

}