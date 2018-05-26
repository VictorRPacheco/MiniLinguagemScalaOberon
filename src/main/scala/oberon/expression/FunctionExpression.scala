package oberon.expression

import oberon.Environment._
import oberon.callable._
import oberon.command._
import oberon.expression._
import oberon.thread.FunctionThread

class FunctionExpression (var f: Callable, val args: List[(String, Expression)]) extends Expression {
  override
  def eval() : Value = {

    for (variable <- args) {
      map(variable._1, variable._2.eval())
    }

    var t = new FunctionThread(f.asInstanceOf[Function], f.asInstanceOf[Function].ret)
    mapExecutionStack("id", t)

    for (c <- f.blockCmds.cmds) {
      if (!c.equals(f.blockCmds.cmds.tail)) c.run()
      else {
        c.run()
        executionStack.pop()
      }
    }

    var ret = lookup(f.blockCmds.cmds.last.asInstanceOf[Return].id).get
    return ret
  }
}
