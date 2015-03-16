package models

import slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.StaticQuery.interpolation
import scala.slick.jdbc.GetResult

class Habit(val name: String, val daysDone: List[Int]) {
	def computeStreak(): Int = {
		/* iterate over daysDone and look for gaps and such */
		val sorted = daysDone.sortWith(_ < _)
		var streak = 0
		var topStreak = 0
		for( i <- 0 until daysDone.length-1) {
			if (sorted(i) - sorted(i+1) == -1) {
				streak += 1
			} else {
				if (streak > topStreak) {
					topStreak = streak + 1 //+1 to count the last day in streak before it was broken
				}
				streak = 0
			}
		}
		/* Don't forget to count an ongoing streak */
		if (streak > topStreak) {
			topStreak = streak +1
		}
		topStreak
	}
}

//86400
object Habit {
	val db = Database.forURL("jdbc:mysql://localhost/streaks_db",driver="com.mysql.jdbc.Driver", user="scala", password="functional")

	def getHabits() : List[Habit] = {
		val query = sql"select id, description from habits".as[(Int,String)]
		val people : List[Habit] = db.withSession{ implicit session =>
  			query.list.map( x => new Habit(x._2, List()) )
		}

		// get the days

		people
	}
}