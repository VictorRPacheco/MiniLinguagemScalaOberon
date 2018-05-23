package oberon.callable

import oberon.Environment._
import oberon.command
import oberon.command.BlockCommand
import oberon.expression.Expression
import oberon.expression._

/*
 * Definição de um Callable: Procedure, Function, Variable
 * Qualquer coisa que pode ser chamada ou referenciada
 */

trait Callable

class Procedure(val id: String, val args: List[Expression], val ret: Expression, val cmds: BlockCommand) extends Callable

class Function(val id: String, val args: List[Expression], val retType: String, val cmds: BlockCommand) extends Callable
