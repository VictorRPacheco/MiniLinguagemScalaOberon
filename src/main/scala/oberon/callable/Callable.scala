package oberon.callable

import oberon.Environment._
import oberon.command
import oberon.command.BlockCommand
import oberon.expression.Value

/*
 * Definição de um procedure: procedure name(argument(s): type1, argument(s): type 2, ... )  {};
 */

sealed trait ReturnType

final case class Unit() extends ReturnType
final case class Value() extends ReturnType

trait Callable {
  def call(): ReturnType
}

class Procedure(val id: String, val args: String, val ret: String, val cmds: BlockCommand) extends Callable {
  override
  def call() : ReturnType = {

     _
  }
}

class Function(val id: String, val args: String, val retType: String, val cmds: BlockCommand) extends Callable {
  override
  def call(): ReturnType = {

    Value()
  }
}