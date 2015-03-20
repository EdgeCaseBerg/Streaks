package models

import slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.StaticQuery.interpolation
import scala.slick.jdbc.GetResult

class Habit(val id : Int, val name: String = "", val daysDone: List[Int] = List()) {
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

object Habit {
	val db = Database.forURL("jdbc:mysql://localhost/streaks_db",driver="com.mysql.jdbc.Driver", user="scala", password="functional")

	def getHabits() : List[Habit] = {
		val query = sql"select id, description from habits".as[(Int,String)]
		val daysQuery = sql"select habit_id, epoch_day from days".as[(Int,Int)]

		val people : List[Habit] = db.withSession{ implicit session =>
			//this is gross and could be made nicer with some Set's and reduce's
  			val habits = query.list.map( x => new Habit(x._1, x._2, List()) )
  			val days = daysQuery.list.groupBy(_._1)
  			habits.map( h => new Habit(h.id, h.name, days.getOrElse(h.id,List[(Int,Int)]()).map(_._2)))
		}
		people
	}

	def markToday(habit : Habit) : Boolean = {
		val today = java.util.Calendar.getInstance().getTimeInMillis() / 86400000

		val checkIfExists = sql"select id from days where habit_id = ${habit.id} and epoch_day = ${today}".as[Int]
		val insertIt = sqlu"insert into days (habit_id, epoch_day) values (${habit.id},$today)"
		db.withSession { implicit session => 
			val exists = !checkIfExists.list.isEmpty

			if (!exists) {
				insertIt.firstOption match {
					case Some(num) if num == 1 => true
					case None => false
				}
			} else {
				true
			}
		}		
	}

	def saveHabit(habit : Habit) : Boolean = {
		val desc = habit.name
		val make = sqlu"insert into habits (description) values ($desc)"
		db.withSession { implicit session =>
			make.firstOption match {
				case Some(num) if num == 1 => true
				case _ => false
			}
		}
	}
}