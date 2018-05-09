package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._
import oberon.command.Print
import oberon.expression.IntValue

class TestPrint extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a print command"

  it should "the environment must have a print(5)" in {
    val _print = new Print(IntValue(5))
    _print.run()
  }
}
