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

  test ("a live cell is still live after tick given two live neighbours on left and right") {
    val game = new GameOfLife(List((0, 0), (1, 0), (-1, 0))).tick
    assert(game.isCellAlive(0, 0))
  }

  test ("other cells are dead after tick given not enough live neighbours") {
    val game = new GameOfLife(List((0, 0), (1, 0), (-1, 0))).tick
    assert(!game.isCellAlive(1, 0))
    assert(!game.isCellAlive(-1, 0))
  }

  test ("a live cell is still live after tick given two live neighbours on top and bottom") {
    val game = new GameOfLife(List((0, 0), (0, 1), (0, -1))).tick
    assert(game.isCellAlive(0, 0))
  }

  test ("a live cell is still live after tick given two live neighbours on right-top and left-top") {
    val game = new GameOfLife(List((0, 0), (1, 1), (-1, 1))).tick
    assert(game.isCellAlive(0, 0))
  }

  test ("a live cell is still live after tick given two live neighbours on right-bottom and left-bottom") {
    val game = new GameOfLife(List((0, 0), (1, -1), (-1, -1))).tick
    assert(game.isCellAlive(0, 0))
  }

  test ("a live cell is still live after tick given three live neighbours") {
    val game = new GameOfLife(List((0, 0), (1, 0), (-1, 0), (0, 1))).tick
    assert(game.isCellAlive(0, 0))
  }

  test ("side effects of scenario that a live cell is still live after tick given three live neighbours") {
    val game = new GameOfLife(List((0, 0), (1, 0), (-1, 0), (0, 1))).tick
    assert(game.isCellAlive(1, 0))
    assert(game.isCellAlive(-1, 0))
    assert(game.isCellAlive(0, 1))
  }

  test ("a dead cell will be live after tick given three live neighbours") {
    val game = new GameOfLife(List((1, 0), (-1, 0), (0, 1))).tick
    assert(game.isCellAlive(0, 0))
  }

  test ("a dead cell will not be live after tick given two live neighbours") {
    val game = new GameOfLife(List((1, 0), (-1, 0))).tick
    assert(!game.isCellAlive(0, 0))
  }

}
