import org.scalatest.FunSuite

class TestGameOfLife extends FunSuite {
  
  test ("no cell is live if game starts with none") {
    val game = new GameOfLife(List())
    assert(!game.isCellAlive(0, 0))
  }

  test ("cell is dead if game created with another live cell") {
    val game = new GameOfLife(List((1, 1)))
    assert(!game.isCellAlive(0, 0))
  }

  test ("cell is live if game created with this live cell") {
    val game = new GameOfLife(List((0, 0)))
    assert(game.isCellAlive(0, 0))
  }

  test ("a live cell is dead after tick due to no way to live") {
    val game = new GameOfLife(List((0, 0))).tick
    assert(!game.isCellAlive(0, 0))
  }

}
