package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


import oberon.Environment._

import oberon.expression.IntValue
import oberon.expression._
import oberon.command._

class TestFor extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a for command"

  before {
    clear()
  }
  // soma := 0;
  // for (x := 1; x <= 10; x := x + 1)
  //    soma := soma + x;
  // end
  // print(soma);
//  it should "lookup(soma) must be equal to 55 after a loop summing up 1 to 10" in {
//    val a1 = new Assignment("soma", IntValue(0))     // soma := 0;
//
//    val initCommandFor = new Assignment("x", IntValue(1))        //    x := 1;
//    val cond = new SmallerEqExpression(new VarRef("x"), IntValue(10))
//    val lastCommandFor = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
//    val commandFor = new Assignment("soma",new AddExpression(new VarRef("soma"), new VarRef("x")))
//
//    val f1 = new For(initCommandFor, cond, lastCommandFor, commandFor)
//
//    a1.run()
//    f1.run()
//
//    val res = lookup("soma")
//    res match {
//      case Some(v) => v.eval() should be (IntValue(55))
//      case _       => 5 should be (1)
//    }
//  }
}
