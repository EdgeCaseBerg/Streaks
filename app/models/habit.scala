package models

class Habit(val name: String, val daysDone: List[Int]) {
	def computeStreak(): Int = {
		/* iterate over daysDone and look for gaps and such */
		-1
	}
}