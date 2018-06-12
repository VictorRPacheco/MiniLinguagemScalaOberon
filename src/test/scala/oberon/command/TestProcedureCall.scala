package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.callable._
import oberon.command.VariableDefinition
import oberon.expression.{AddExpression, IntValue, VarRef}

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

    val zReturn = new Variable("z", "Integer", undef)
    val somaXY = new Assignment("z", new AddExpression(new VarRef("x"), new VarRef("y")))
    val cmds = new BlockCommand(List(somaXY))

    var procedure = new Procedure("soma", List(("x", undef), ("y", undef)), cmds, zReturn)
    val somaDeclaracao = new CallableDeclaration(procedure.id, procedure)

    somaDeclaracao.run()

    val chamada = new ProcedureCall("soma" , List(IntValue(2), IntValue(5)), zReturn)

    chamada.run()

    val res = lookup("z")
    res match {
      case Some(v) => v.dataValue should be (IntValue(7))
      case _       => print("Error")
    }

  }
}