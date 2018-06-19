package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.callable._
import oberon.expression.{AddExpression, BoolValue, IntValue, VarRef, TInt, TBool}

class TestProcedureCall extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a procedure call"

  before {
    clear()
  }

  // procedure sumPlus5 (x, y: int, z: int) {
  //    z = x + y
  // }
  //
  // sumPlus5(2, 5, z)
  it should "lookup should return value 7" in {

    val undef = oberon.expression.Undefined()

    val zReturn = new Variable("z", TInt(), undef)
    val somaXY = new Assignment("z", new AddExpression(new VarRef("x"), new VarRef("y")))
    val cmds = new BlockCommand(List(somaXY))

    var procedure = new Procedure("soma", TInt(), List(("x", TInt()), ("y", TInt())), cmds, zReturn)
    val somaDeclaracao = new CallableDeclaration(procedure.id, procedure)

    somaDeclaracao.run()

    val chamada = new ProcedureCall("soma" , List(IntValue(2), IntValue(5)), zReturn)

    chamada.run()

    val res = lookup("z")
    res match {
      case Some(v) => v.dataValue.eval() should be (IntValue(7))
      case _       => print("Error")
    }

  }

  // procedure sumPlus5 (x, y: int, z: int) {
  //    z = x + y
  // }
  // sumPlus5(true, false, z)
  it should "intercept an exception when calling with wrong argumment types" in {
    clear()
    val undef = oberon.expression.Undefined()

    val zReturn = new Variable("z", TInt(), undef)
    val somaXY = new Assignment("z", new AddExpression(new VarRef("x"), new VarRef("y")))
    val cmds = new BlockCommand(List(somaXY))

    var procedure = new Procedure("soma", TInt(), List(("x", TInt()), ("y", TInt())), cmds, zReturn)
    val somaDeclaracao = new CallableDeclaration(procedure.id, procedure)

    somaDeclaracao.run()

    val chamada = new ProcedureCall("soma" , List(BoolValue(true), BoolValue(false)), zReturn)

    val thrown = intercept[Exception] {
      chamada.run()
    }

    thrown.getMessage should be ("Invalid argument type")

  }

}