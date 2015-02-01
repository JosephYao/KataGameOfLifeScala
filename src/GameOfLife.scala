class GameOfLife(val liveCells: List[(Int, Int)]) {
  def tick: GameOfLife = new GameOfLife(List())


  def isCellAlive(x: Int, y: Int) = liveCells.contains((x, y))

}
