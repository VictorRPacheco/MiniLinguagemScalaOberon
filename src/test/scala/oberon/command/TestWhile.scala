package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._
import oberon.expression.IntValue
import oberon.expression.DifExpression

class TestWhile extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a while command"

  it should "the environment must have a x = 1, while (x != 5) { x = 5 } command" in {

    var initialAssignment = new Assignment("x", IntValue(1))
    initialAssignment.run()
/*
    var whileExpr = new DifExpression(lookup("x").getOrElse(0).asInstanceOf[IntValue], IntValue(5))
    var whileAssignment = new Assignment("x", IntValue(5))
    var _while = new While(whileExpr, whileAssignment)
    _while.run()*/cd

    lookup("x") should be (Some(IntValue(5)))
  }
}
