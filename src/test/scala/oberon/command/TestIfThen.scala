package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._
import oberon.expression.IntValue
import oberon.expression.DifExpression
import oberon.command.IfThen

class TestIfThen extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an if-then command"

  it should "the environment must have a y = 5, if (10 != 1): y = 7 " in {
    var initial_assignment = new Assignment("y", IntValue(5))
    initial_assignment.run()

    var ifAssignment = new Assignment("y", IntValue(7))
    var ifExpr = new DifExpression(IntValue(10), IntValue(1))

    var _ifthen = new IfThen(ifExpr, ifAssignment)
    _ifthen.run()

    lookup("y") should be (Some(IntValue(7)))

  }

}