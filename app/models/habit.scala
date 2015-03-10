package models

class Habit(val name: String, val daysDone: List[Int]) {
	def computeStreak(): Int = {
		/* iterate over daysDone and look for gaps and such */
		val start = 0
		val end = 0
		for( i <- 0 until daysDone.length) {
			
		}
	}
}