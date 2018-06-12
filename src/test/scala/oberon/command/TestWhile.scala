package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


import oberon.Environment._

import oberon.expression.IntValue
import oberon.expression._
import oberon.callable._

class TestWhile extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a while command"

  // soma := 0;
  //    x := 1;
  // while(x <= 10) begin
  //   soma := soma + x;
  //      x := x + 1;
  // end
  // print(soma);
  it should "lookup(soma) must be equal to 55 after a loop summing up 1 to 10" in {

    val soma = new VariableDefinition(new Variable("soma", "Integer", IntValue(0)))
    val x = new VariableDefinition(new Variable("x", "Integer", IntValue(1)))

    soma.run()
    x.run()

    val a1 = new Assignment("soma", new AddExpression (new VarRef("soma"), new VarRef("x")))
    val a2 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val cond = new SmallerEqExpression(new VarRef("x"), IntValue(10))
    val w1 = new While(cond, new BlockCommand(List(a1, a2)))

    w1.run()

    val res = lookup("soma")
    res match {
      case Some(v) => v.asInstanceOf[Variable].dataValue.eval() should be (IntValue(55))
      case _       => println("Erros")
    }
  }
}
