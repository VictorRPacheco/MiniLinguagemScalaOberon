package oberon.command

import oberon.Environment._
import oberon.expression._
import oberon.callable._
import oberon.expression.BoolValue
import oberon.thread._

import scala.collection.mutable.{HashMap, Map, Stack}
import scala.io.StdIn.{readBoolean, readInt}

trait Command {
  def run() : Unit
}

class BlockCommand(val cmds: List[Command]) extends Command {
  override
  def run() : Unit = {
    cmds.foreach(c => c.run())
  }
}

class Assignment(val id: String, val expression: Expression) extends Command {
  override
  def run() : Unit = {
    lookup(id) match {
      case Some(v) => {
        var newVariable = new Variable(v.id, v.dataType, expression.eval())
        map(id, newVariable)
      }
      case _ => { println("Error")}
    }
  }
}

class Return(val expression: Expression) extends Command {
  def run(){}

  def runReturn(): Value = {
    expression.eval()
  }

}

class IfElse(val cond: Expression, val ifCommand: Command, val elseCommand: Command) extends Command {
  override
  def run() : Unit = {
    val v = cond.eval.asInstanceOf[BoolValue]

    v match {
      case BoolValue(true)  => { ifCommand.run(); }
      case BoolValue(false) => { elseCommand.run(); }
    }
  }
}

class IfThen(val cond: Expression, val command: Command) extends Command {
  override
  def run() : Unit = {
    val v = cond.eval.asInstanceOf[BoolValue]

    v match {
      case BoolValue(true)  => { command.run(); }
      case _ => { }
    }
  }
}


class For(val initCommand: Command, val cond: Expression, val lastCommand: Command, val command: Command) extends Command {
  override
  def run() : Unit = {
      initCommand.run()
      val v = cond.eval.asInstanceOf[BoolValue]
      v match {
        case BoolValue(true) => { command.run(); lastCommand.run(); this.run(); }
        case _               => { }
      }

  }

}

class While(val cond: Expression, val command: Command) extends Command {
  override
  def run() : Unit = {
    val v = cond.eval.asInstanceOf[BoolValue]

    v match {
      case BoolValue(true) => { command.run(); this.run(); }
      case _               => { }
    }
  }
}

class Print(val exp: Expression) extends Command {
  override
  def run() : Unit = {
    exp.getClass.getDeclaredFields foreach { f =>
      f.setAccessible(true)
      print(f.get(exp))
    }
  }
}

class ReadInt() extends Command {
  override
  def run() : Unit = {
    //readInt()
  }
}

class ReadBool() extends Command {
  override
  def run() : Unit = {
    //readBoolean()
  }
}

class CallableDeclaration(id: String, val callable: Callable) extends Command {
  override
  def run() : Unit = {
    progDefinitions += (id -> callable)
  }
}

class VariableDefinition(val variable: Variable) extends Command {
  override
  def run() : Unit = {
    map(variable.id, variable)
  }
}

class ProcedureCall(val procedureName: String, val args: List[Expression], val ret: Variable) extends Command {
  override
  def run() : Unit = {
    var localVariables = new(HashMap[String, Variable])
    var p = new CallableRef(procedureName).eval()
    var pThread = new ProcedureThread(p.id, p.asInstanceOf[Procedure], localVariables)
    var n = 0
    for (variable <- args) {
      var v = new Variable(p.asInstanceOf[Procedure].args(n)._1, "type", args(n).eval())
      pThread.addVariable(p.asInstanceOf[Procedure].args(n)._1, v)
      n+=1
    }

    push()
    executionStack.top += (p.id -> pThread)

    var retVariable = new VariableDefinition(ret)
    retVariable.run()

    for (c <- p.asInstanceOf[Procedure].blockCmds.cmds){
      c.run()
    }

    var retCommand = new VariableDefinition(lookup(ret.id).get)
    executionStack.pop()
    retCommand.run()

  }
}
