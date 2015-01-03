class Game (liveCells: (Int, Int)*) {
  def tick: Game = {
    def cellsHaveLiveNeighbourOnOneSide(side: (Int, Int)) =
      liveCells.map(cell => (cell._1 - side._1, cell._2 - side._2))

    def cellsHaveLiveNeighbourOnAllSides = {
      val RIGHT = 1; val LEFT = -1; val TOP = 1; val BOTTOM = -1
      List(
        (RIGHT, 0), (LEFT, 0),
        (0, TOP), (0, BOTTOM),
        (RIGHT, TOP), (LEFT, BOTTOM),
        (LEFT, TOP), (RIGHT, BOTTOM))
        .flatMap(cellsHaveLiveNeighbourOnOneSide)
    }

    def isAliveAfterTick(cellWithLiveNeighbours: ((Int, Int), List[(Int, Int)])) =
      cellWithLiveNeighbours._2.length == 2 && liveCells.contains(cellWithLiveNeighbours._1) ||
        cellWithLiveNeighbours._2.length == 3

    val nextTickLiveCell = cellsHaveLiveNeighbourOnAllSides
      .groupBy(cell => cell)
      .filter(isAliveAfterTick)
      .keys.toArray
    new Game(nextTickLiveCell: _*)
  }

  def isAlive(x: Int, y: Int) = liveCells.contains((x, y))

}
