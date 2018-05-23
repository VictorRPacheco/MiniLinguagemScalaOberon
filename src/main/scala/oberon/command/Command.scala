package oberon.command

import oberon.Environment._

import oberon.expression._
import oberon.callable._
import oberon.expression.BoolValue
import scala.io.StdIn.{readInt,readBoolean}

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
    println(stack)

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

class ProdedureCallCommand(val p: Procedure) extends Command {
  override
  def run() : Unit = {
    p.args.foreach(e => e.eval())
    p.cmds.run()
  }
}
