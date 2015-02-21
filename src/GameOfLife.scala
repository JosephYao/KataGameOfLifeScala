class GameOfLife(val liveCells: List[(Int, Int)]) {

  def tick: GameOfLife = {
    val directions = List((-1, 0), (1, 0), (0, -1), (0, 1),
      (-1, -1), (1, -1), (-1, 1), (1, 1))

    val cellsHaveLiveNeighbours = directions.flatMap(
      direction => liveCells.map(cell => (cell._1 + direction._1, cell._2 + direction._2)))

    val liveCellsAfterTick = cellsHaveLiveNeighbours.groupBy(cell => cell).
        filter(cellWithLiveNeighbours =>
                cellWithLiveNeighbours._2.length == 2 && liveCells.contains(cellWithLiveNeighbours._1) ||
                cellWithLiveNeighbours._2.length == 3).
        keys.toList

    return new GameOfLife(liveCellsAfterTick)
  }

  def isCellAlive(x: Int, y: Int) = liveCells.contains((x, y))

}
