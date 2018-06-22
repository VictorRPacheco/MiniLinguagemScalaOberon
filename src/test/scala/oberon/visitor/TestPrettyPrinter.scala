package oberon

import oberon.callable._
import oberon.command.{BlockCommand, CallableDeclaration, Return, VariableDefinition}
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.expression._
import oberon.visitor.PrettyPrinter
import oberon.Environment._

class TestPrettyPrinter extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a pretty printer"

  it should "print \"(5 + 10)\" when we call accept in such an expression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val add   = new AddExpression(val5, val10)

    val pp = new PrettyPrinter()

    add.accept(pp)
    pp.str should be ("(5 + 10)")
  }

  it should "print false when we call accept in such a value" in {
    clear()
    val boolValue = BoolValue(false)
    val variableDefinition = new VariableDefinition(new Variable ("bol", TBool(), boolValue))
    val pp = new PrettyPrinter()
    boolValue.accept(pp)

    pp.str should be("false")

    val newPP = new PrettyPrinter()
    variableDefinition.accept(newPP)
    newPP.str should be ("Boolean bol = BoolValue(false)")



  }

  it should "print our function when we call accept in such a value" in {

    val somaXY = new Return(new AddExpression(new VarRef("x"), new VarRef("y")))
    val cmds = new BlockCommand(List(somaXY))

    var function = new Function("soma", TInt(), List(("x", TInt()), ("y", TInt())), cmds)
    val functionDeclaration = new CallableDeclaration(function.id, function)
    functionDeclaration.run()

    val functionCall = new FunctionExpression("soma", List(IntValue(2), IntValue(5)))
    val pp = new PrettyPrinter()
    functionCall.accept(pp)
    pp.str should be ("soma(2, 5)")

  }

}