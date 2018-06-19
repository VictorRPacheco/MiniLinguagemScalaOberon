package oberon.callable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.command._
import oberon.expression.{TInt, IntValue}

class TestDeclareProcedure extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a procedure declaration"

  before {
    clear()
  }

  // procedure sumPlus5 (x, y: int, z: int) {
  //    z = x + y
  // }
  it should "lookup should return the function declaration" in {
    val undef = oberon.expression.Undefined()

    val printInt = new Print(IntValue(5))
    val c = new BlockCommand(List(printInt))
    val z = new Variable("z", TInt(), undef)
    var procedure = new Procedure("soma", TInt(),  List(("x", TInt()), ("y", TInt())), c, z)
    val somaDeclaracao = new CallableDeclaration(procedure.id, procedure)

    somaDeclaracao.run()

    val res = lookupCallable("soma")
    res match {
      case Some(p) => p should be (procedure)
      case _       => print("Error")
    }
  }
}
