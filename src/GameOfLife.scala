class GameOfLife(val liveCells: List[(Int, Int)]) {

  def tick: GameOfLife = {
    val cellsHaveLiveRightNeighbour = liveCells.map(cell => (cell._1 - 1, cell._2))
    val cellsHaveLiveLeftNeighbour = liveCells.map(cell => (cell._1 + 1, cell._2))

    val liveCellsAfterTick =
      (cellsHaveLiveRightNeighbour ::: cellsHaveLiveLeftNeighbour).
        groupBy(cell => cell).
        filter(_._2.length == 2).
        keys.toList

    return new GameOfLife(liveCellsAfterTick)
  }

  def isCellAlive(x: Int, y: Int) = liveCells.contains((x, y))

}
