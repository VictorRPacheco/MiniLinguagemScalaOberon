package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


import oberon.Environment._

import oberon.expression.IntValue
import oberon.expression._
import oberon.command._

class TestWhile extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a while command"

  before {
    clear()
  }
  // soma := 0;
  //    x := 1;
  // while(x <= 10) begin
  //   soma := soma + x;
  //      x := x + 1;
  // end
  // print(soma);
  it should "lookup(soma) must be equal to 55 after a loop summing up 1 to 10" in {
    val a1 = new Assignment("soma", IntValue(0))     // soma := 0;
    val a2 = new Assignment("x", IntValue(1))        //    x := 1;
    val a3 = new Assignment("soma",new AddExpression(new VarRef("soma"), new VarRef("x")))
    val a4 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val cond = new SmallerEqExpression(new VarRef("x"), IntValue(10))
    val w1 = new While(cond, new BlockCommand(List(a3, a4)))

    a1.run()
    a2.run()
    w1.run()

    val res = lookup("soma")
    res match {
      case Some(v) => v.eval() should be (IntValue(55))
      case _       => 5 should be (1)
    }
  }
}
