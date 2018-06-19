package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.callable.Variable
import oberon.expression.IntValue
import oberon.expression._
import oberon.command._

class TestFor extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a for command"


  // soma := 0;
  // for (x := 1; x <= 10; x := x + 1)
  //    soma := soma + x;
  // end
  // print(soma);

  it should "lookup(soma) must be equal to 55 after a loop summing up 1 to 10" in {
    val soma = new VariableDefinition(new Variable("soma", "Integer", IntValue(0)))     // soma := 0;
    val initCommandFor = new VariableDefinition(new Variable("x", "Integer", IntValue(1)))           // x := 1;

    val cond = new SmallerEqExpression(new VarRef("x"), IntValue(10))                                      // x<=10
    val lastCommandFor = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))          // X + 1
    val commandFor = new Assignment("soma",new AddExpression(new VarRef("soma"), new VarRef("x"))) // soma = soma+x

    val f1 = new For(initCommandFor, cond, lastCommandFor, commandFor)    // for(soma := 0; cond x <= 10; X+1; soma = soma + x)
    soma.run()
    f1.run()

    val res = lookup("soma")
    res match {
      case Some(v) => v.asInstanceOf[Variable].dataValue.eval() should be (IntValue(55))
      case _       => println("Erros")
    }
  }
}