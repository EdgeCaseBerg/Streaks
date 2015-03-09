package controllers

import play.api._
import play.api.mvc._

class Streaks extends Controller {
	
	def index = Action {
		val habits = scala.List.empty
		Ok(views.html.index("Streaks - Home", habits))
	}

}