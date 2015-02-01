class GameOfLife(val liveCells: List[(Int, Int)]) {

  def isCellAlive(x: Int, y: Int) = liveCells.contains((x, y))

}
