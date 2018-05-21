package oberon.callable

import oberon.Environment._
import oberon.command
import oberon.command.BlockCommand

/*
 * Definição de um procedure: procedure name(argument(s): type1, argument(s): type 2, ... )  {};
 */

trait Callable {
  def call(): Unit = {}
}

class Procedure(val id: String, val args: String, val ret: String, val cmds: BlockCommand) extends Callable {
  override
  def call() : Unit = {}
}

class Function(val id: String, val args: String, val retType: String, val cmds: BlockCommand) extends Callable {
  override
  def call(): Unit = {}
}