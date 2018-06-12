package oberon.expression

import oberon.Environment._
import oberon.callable.CallableRef
import oberon.callable.Variable
import oberon.command._
import oberon.thread.FunctionThread

import scala.collection.mutable.HashMap

class FunctionExpression (var id: String, val args: List[(String, Expression)]) extends Expression {
  override
  def eval() : Value = {
    var f = (new CallableRef(id).eval()).asInstanceOf[oberon.callable.Function]
    var localVariables = new(HashMap[String, Variable])
    var fThread = new FunctionThread(f.id, f, localVariables)

    for (variable <- args) {
      var v = new Variable(variable._1, "type", variable._2.eval())
      fThread.addVariable(variable._1, v)
    }

    push()
    executionStack.top += (f.id -> fThread)

      for (c <- f.blockCmds.cmds){
      if(c.isInstanceOf[Return]) {
        return c.asInstanceOf[Return].runReturn()
      }
      else c.run()
    }

    executionStack.pop()
    return IntValue(0)
  }
}
