package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.callable._
import oberon.command._
import java.lang.RuntimeException
import oberon.expression.Value


class TestFunctionExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a function expression"

  before {
    clear()
  }

  // function soma (x, y: int) {
  //    return x + y
  // }
  //
  // soma(2, 5)

  it should "lookup should return value 7" in {

    val somaXY = new Return(new AddExpression(new VarRef("x"), new VarRef("y")))
    val cmds = new BlockCommand(List(somaXY))

    var function = new Function("soma", TInt(), List(("x", TInt()), ("y", TInt())), cmds)
    val functionDeclaration = new CallableDeclaration(function.id, function)
    functionDeclaration.run()

    val functionCall = new FunctionExpression("soma", List(IntValue(2), IntValue(5)))

    functionCall.eval() should be(IntValue(7))

  }

  // int factorial(n: int) {
  //    if (n > 1) return n * factorial(n - 1);
  //    else return 1
  it should "lookup should return 24 when we call factorial(24)" in {

    clear()
    // n - 1
    var nminus1 = new MinusExpression(new VarRef("n"), IntValue(1))
    // factorial(n - 1)
    var recursiveFactorial = new FunctionExpression("factorial", List(nminus1))
    // return n * factorial(n-1)
    var ntimesn = new Return(new MultExpression(new VarRef("n"), recursiveFactorial))

    // if (n > 1)
    var cond = new BiggerExpression(new VarRef("n"), IntValue(1))
    // if (n > 1) return n * factorial(n - 1)
    var ifsuccess = ntimesn
    // else return 1
    var elsesuccess = new Return(IntValue(1))

    var debug = new Print(IntValue(5))
    // creating ifelse
    var ifelse = new IfElse(cond, ifsuccess, elsesuccess)
    var cmds = new BlockCommand(List(ifelse, debug))

    // creating function declaration
    var factorial = new Function("factorial", TInt(), List(("n", TInt())), cmds)
    var factorialDeclaration = new CallableDeclaration(factorial.id, factorial)
    factorialDeclaration.run()

    var funcCall = new FunctionExpression("factorial", List(IntValue(6)))
    funcCall.eval() should be (IntValue(720))
    funcCall = new FunctionExpression("factorial", List(IntValue(3)))
    funcCall.eval() should be (IntValue(6))
    funcCall = new FunctionExpression("factorial", List(IntValue(1)))
    funcCall.eval() should be (IntValue(1))

  }

  // try declaring two functions with the same name should return an error
  // int factorial(n: int) {
  //    if (n > 1) return n * factorial(n - 1);
  //    else return 1
  // int factorial(n: int) {
  //    if (n > 1) return n * factorial(n - 1);
  //    else return 1
  it should "throw an exception when trying to declare a function with the same name" in {
    clear()
    // n - 1
    var nminus1 = new MinusExpression(new VarRef("n"), IntValue(1))
    // factorial(n - 1)
    var recursiveFactorial = new FunctionExpression("factorial", List(nminus1))
    // return n * factorial(n-1)
    var ntimesn = new Return(new MultExpression(new VarRef("n"), recursiveFactorial))

    // if (n > 1)
    var cond = new BiggerExpression(new VarRef("n"), IntValue(1))
    // if (n > 1) return n * factorial(n - 1)
    var ifsuccess = ntimesn
    // else return 1
    var elsesuccess = new Return(IntValue(1))

    var debug = new Print(IntValue(5))
    // creating ifelse
    var ifelse = new IfElse(cond, ifsuccess, elsesuccess)
    var cmds = new BlockCommand(List(ifelse, debug))

    // creating function declaration
    var factorial = new Function("factorial", TInt(), List(("n", TInt())), cmds)
    var factorialDeclaration = new CallableDeclaration(factorial.id, factorial)
    factorialDeclaration.run()
    var dumbFactorial = new Function("factorial", TInt(), List(("n", TInt())), cmds)
    var dumbFactorialDeclaration = new CallableDeclaration(dumbFactorial.id, dumbFactorial)
    val thrown = intercept[Exception] {
      dumbFactorialDeclaration.run()
    }

    thrown.getMessage should be ("Function already declared")
  }

  // testing use of global variable instead of local variable
  // x = false
  // bool valueEquals(bool: x, y) {
  //    if (x == y) return true
  //    else return false
  it should "use the global variable instead of local" in {
    clear()

    // declaring global variable x
    // x = false
    var globalX = new VariableDefinition(new Variable("x", TBool(), BoolValue(false)))
    globalX.run()

    // building function cause we didn't make a parser (oops)
    // if (x == y) return true
    // else return false
    var ifCond = new EqExpression(new VarRef("x"), new VarRef("y"))
    var ifSuccess = new Return(BoolValue(true))
    var elseSuccess = new Return(BoolValue(false))
    var ifElse = new IfElse(ifCond, ifSuccess, elseSuccess)
    var cmds = new BlockCommand(List(ifElse))

    // declaring function
    var newFunction = new Function("valueEquals", TBool(), List(("x", TBool()), ("y", TBool())), cmds)
    var newFunctionDeclaration = new CallableDeclaration(newFunction.id, newFunction)
    newFunctionDeclaration.run()

    // calling function with different parameters
    // x = true, y = false
    // it should return true since we declared a global variable equal to false
    var funcCall = new FunctionExpression("valueEquals", List(BoolValue(true), BoolValue(false)))
    funcCall.eval() should be (BoolValue(true))

  }
}