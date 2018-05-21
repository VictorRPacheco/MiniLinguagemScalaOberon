package oberon.callable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.command.{Assignment, BlockCommand, While}
import oberon.expression.{AddExpression, IntValue, SmallerEqExpression, VarRef}

class TestDeclareFunction extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a function declaration"

  before {
    clear()
    clearExecutionStack()
  }

  it should "lookupExecStack(sumPlus5) return the declaration" in {
    val a3 = new Assignment("soma",new AddExpression(new VarRef("soma"), new VarRef("x")))
    val a4 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val w1 = new BlockCommand(List(a3, a4))
    var function = new Function("sumPlus5", "x, y: int", "int", w1)
    mapExecStack("sumPlus5", function)

    val res = lookupExecStack("sumPlus5")
    res match {
      case Some(f) => f should be (function)
      case _       => print("Error")
    }

  }

}
