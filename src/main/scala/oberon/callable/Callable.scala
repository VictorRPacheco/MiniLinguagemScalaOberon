package oberon.callable

import oberon.command.BlockCommand
import oberon.expression.Value
import oberon.expression.Type

/*
 * Definition of Callabe: Any structure that can be called or referenced
 * - Procedure: Subprogram that does not return a value directly (Call = command)
 * - Function: Subprogram that returns a value directly (Call = expression)
 * - Variable: Can be referenced anytime
 *
 */

trait Callable {
  def id: String
}

case class Procedure(id: String, procType: Type, args: List[(String, Type)], blockCmds: BlockCommand, ret: Variable) extends Callable

case class Function(id: String, funcType: Type, args: List[(String, Type)], blockCmds: BlockCommand) extends Callable

case class Variable(id: String, dataType: Type, dataValue: Value) extends Callable

case class Undefined(id: String) extends Callable