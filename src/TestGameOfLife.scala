import org.scalatest.FunSuite

class TestGameOfLife extends FunSuite {
  
  test ("no live cell when game just start") {
    val game = new Game

    assert(!game.isAlive(0, 0))
  }

  test ("two cells can be both set as live cells") {
    val game = new Game((0, 0), (1, 1))

    assert(game.isAlive(0, 0))
    assert(game.isAlive(1, 1))
  }

  test ("a live cell without any neighbour will dead after tick") {
    val game = new Game((0, 0))
      .tick

    assert(!game.isAlive(0, 0))
  }

  test ("a live cell with two live neighbour on the right and left will live after tick") {
    val game = new Game((0, 0), (1, 0), (-1, 0))
      .tick

    assert(game.isAlive(0, 0))
    assert(!game.isAlive(1, 0))
    assert(!game.isAlive(-1, 0))
  }

  test ("a live cell with two live neighbour on the top and bottom will live after tick") {
    val game = new Game((0, 0), (0, 1), (0, -1))
      .tick

    assert(game.isAlive(0, 0))
  }

  test ("a live cell with two live neighbour on the top-right and bottom-left will live after tick") {
    val game = new Game((0, 0), (1, 1), (-1, -1))
      .tick

    assert(game.isAlive(0, 0))
  }

  test ("a live cell with two live neighbour on the top-left and bottom-right will live after tick") {
    val game = new Game((0, 0), (1, -1), (-1, 1))
      .tick

    assert(game.isAlive(0, 0))
  }

  test ("three cells will live after tick given they are neighbours each other") {
    val game = new Game((0, 0), (1, 0), (0, 1))
      .tick

    assert(game.isAlive(0, 0))
    assert(game.isAlive(1, 0))
    assert(game.isAlive(0, 1))
  }

  test ("a live cell with three live neighbour will live after tick") {
    val game = new Game((0, 0), (1, 0), (-1, 0), (0, 1))
      .tick

    assert(game.isAlive(0, 0))
  }
  
  test ("a dead cell with three live neighbour will revive after tick") {
    val game = new Game((1, 0), (-1, 0), (0, 1))
      .tick

    assert(game.isAlive(0, 0))
  }

  test ("a dead cell with two live neighbour will still dead after tick") {
    val game = new Game((1, 0), (0, 1))
      .tick

    assert(!game.isAlive(0, 0))
  }
}
