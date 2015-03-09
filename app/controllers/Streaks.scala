package controllers

import play.api._
import play.api.mvc._

class Streaks extends Controller {
	
	def index = Action {
		Ok(views.html.index("Streaks - Home"))
	}

}