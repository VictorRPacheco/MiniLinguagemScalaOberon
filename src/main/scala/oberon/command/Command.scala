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
    var v = expression.eval()
    executionStack.pop()
    return v
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
      val newWhile = new While(cond, new BlockCommand(List(command, lastCommand)))
      newWhile.run()
      val v = cond.eval.asInstanceOf[BoolValue]
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
    mapCallable(id, callable)
  }
}

class VariableDefinition(val variable: Variable) extends Command {
  override
  def run() : Unit = {
    map(variable.id, variable)
  }
}

class ProcedureCall(val id: String, val args: List[Expression], val ret: Variable) extends Command {
  override
  def run() : Unit = {

    // get the procedure declaration
    var procDeclaration = new CallableRef(id).eval().asInstanceOf[Procedure]

    // Create new localVariables HashMap
    var localVariables = new HashMap[String, Variable]

    // Create a new thread for executing the procedure
    var procThread = new ProcedureThread(procDeclaration.id, procDeclaration, localVariables)

    // Instantiate the formal parameters
    var newProcArgs = procDeclaration.args
    var n = 0
    // For all parameters
    for (v <- newProcArgs) {
      if(args.size != procDeclaration.args.size) println("Error de argumentos")
      // v._1 = variable name
      // v._2 = variable value type
      procThread.addVariable(v._1, new Variable(v._1 , v._2, args(n).eval()))
      n+=1
    }

    // Adding the thread to the execution stack
    push()
    executionStack.top += (procThread.id -> procThread)

    var retVariable = new VariableDefinition(ret)
    retVariable.run()

    for (c <- procDeclaration.blockCmds.cmds){
      c.run()
    }

    var retCommand = new VariableDefinition(lookup(ret.id).get)
    executionStack.pop()
    retCommand.run()

  }
}
