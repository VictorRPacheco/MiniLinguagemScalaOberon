package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._
import oberon.expression.IntValue
import oberon.expression.BoolValue

class TestPrint extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a print command"

  it should "the environment must have a print(5)" in {
    val integerPrint = new Print(IntValue(5))
    val boolPrint = new Print(BoolValue(false))
    integerPrint.run()
    boolPrint.run()
  }
}
