package controllers

import play.api._
import play.api.mvc._

class Streaks extends Controller {
	
	def index = Action {
		val habits = scala.List[models.Habit](
			new models.Habit("Test", List(1,2,3,5,6,7,8))
			)
		Ok(views.html.index("Streaks - Home", habits))
	}

	def createHabit = Action {
		Redirect(routes.Streaks.index)
	}

	def markHabitDone = Action {
		Redirect(routes.Streaks.index)	
	}

}