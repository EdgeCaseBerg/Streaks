package controllers

import play.api._
import play.api.mvc._

class Streaks extends Controller {
	
	def index = Action {
		val habits = models.Habit.getHabits()
		Ok(views.html.index("Streaks - Home", habits))
	}

	def createHabit = Action {
		Redirect(routes.Streaks.index)
	}

	def markHabitDone = Action {
		Redirect(routes.Streaks.index)	
	}

}