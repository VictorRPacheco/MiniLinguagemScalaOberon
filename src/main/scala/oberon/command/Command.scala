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
    map(id, expression.eval())
  }

}

class Return(val id: String, val exp: Expression) extends Command {
  override
  def run() : Unit = {
    map(id, exp.eval())
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
    println("Condicao:" + cond)
    println("Environment: ")

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
    readInt()
  }
}

class ReadBool() extends Command {
  override
  def run() : Unit = {
    //readBoolean()
  }
}

class ProcedureCall(val p: Procedure, val args: List[(String, Expression)]) extends Command {
  override
  def run() : Unit = {

    for (variable <- args) {
      map(variable._1, variable._2.eval())
    }

    var t = new ProcedureThread(p, p.ret)
    mapExecutionStack("id", t)

    for (c <- p.blockCmds.cmds) c.run()

    executionStack.pop()
  }
}
