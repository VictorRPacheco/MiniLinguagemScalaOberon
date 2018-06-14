package oberon.expression

import oberon.Environment._
import oberon.callable.{CallableRef, Variable}
import oberon.command._
import oberon.thread.FunctionThread
import oberon.visitor.Visitor

import scala.collection.mutable.HashMap

class FunctionExpression (var id: String, val args: List[Expression]) extends Expression {
  override
  def eval() : Value = {

    // Get the function declaration
    var functionDeclaration = new CallableRef(id).eval().asInstanceOf[oberon.callable.Function]

    // Create new localVariables HashMap
    var localVariables = new HashMap[String, Variable]

    // Create a new thread for executing the function
    var functionThread = new FunctionThread(functionDeclaration.id, functionDeclaration, localVariables)

    // Instantiate the formal parameters
    var newFunctionArgs = functionDeclaration.args
    var n = 0
    // For all parameters
    for (v <- newFunctionArgs) {
      if(args.size != functionDeclaration.args.size) println("Error de argumentos")
      // v._1 = variable name
      // v._2 = variable value type
      functionThread.addVariable(v._1, new Variable(v._1 , v._2, args(n).eval()))
      n+=1
    }

    // Adding the thread to the execution stack
    push()
    executionStack.top += (functionThread.id -> functionThread)

    // Start running commands
    for (c <- functionDeclaration.blockCmds.cmds){
      if(c.isInstanceOf[Return]) {
        return c.asInstanceOf[Return].runReturn()
      }
      else c.run()
    }

    executionStack.pop()
    return Undefined()
  }

  override def calculateType(): Type = {
    // Get the function declaration
    var functionDeclaration = new CallableRef(id).eval().asInstanceOf[oberon.callable.Function]

    if(functionDeclaration.ret.equals("Integer")){
      return TInt()
    }
    if(functionDeclaration.ret.equals("Boolean")){
      return TBool()
    }
    TUndefined()
  }

  def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}
