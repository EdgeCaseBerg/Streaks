package models

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
			println(s"$topStreak and $streak")
		}
		/* Don't forget to count an ongoing streak */
		if (streak > topStreak) {
			topStreak = streak +1
		}
		topStreak
	}
}