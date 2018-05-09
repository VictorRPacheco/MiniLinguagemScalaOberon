package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._
import oberon.expression.IntValue
import oberon.expression.DifExpression
import oberon.command.IfElse

class TestIfThenElse extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an if-then-else command"

  it should "the environment must have a y = 5, if (10 != 10): y = 7 else: y = 0" in {
    var initial_assignment = new Assignment("y", IntValue(5))
    initial_assignment.run()

    var ifAssignment = new Assignment("y", IntValue(7))
    var elseAssignment = new Assignment("y", IntValue(0))
    var ifExpr = new DifExpression(IntValue(10), IntValue(10))

    var _ifthen = new IfElse(ifExpr, ifAssignment, elseAssignment)
    _ifthen.run()

    lookup("y") should be (Some(IntValue(0)))

  }

}