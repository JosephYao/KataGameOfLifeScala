class GameOfLife(val liveCells: List[(Int, Int)]) {

  def tick: GameOfLife = {
    val directions = List((-1, 0), (1, 0), (0, -1), (0, 1),
      (-1, -1), (1, -1), (-1, 1), (1, 1))

    val cellsHaveLiveNeighbours = directions.flatMap(
      direction => liveCells.map(cell => (cell._1 + direction._1, cell._2 + direction._2)))

    def liveRuleWithTwoLiveNeighbours(cellWithLiveNeighbours: ((Int, Int), List[(Int, Int)])) =
      cellWithLiveNeighbours._2.length == 2 && liveCells.contains(cellWithLiveNeighbours._1)
    def liveRuleWithThreeLiveNeighbours(cellWithLiveNeighbours: ((Int, Int), List[(Int, Int)])) =
      cellWithLiveNeighbours._2.length == 3

    val rules = List(liveRuleWithTwoLiveNeighbours(_), liveRuleWithThreeLiveNeighbours(_))

    val liveCellsAfterTick = rules.flatMap(
      rule => cellsHaveLiveNeighbours.groupBy(cell => cell).filter(rule)).
      map(_._1)

    return new GameOfLife(liveCellsAfterTick)
  }

  def isCellAlive(x: Int, y: Int) = liveCells.contains((x, y))

}
