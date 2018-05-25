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

trait Callable {
  def id: String
  def args: List[(String, Expression)]
  def blockCmds: BlockCommand
}

case class Procedure(
                      id: String,
                      args: List[(String, Expression)],
                      blockCmds: BlockCommand,
                      ret: (String, Expression)) extends Callable

case class Function(
                      id: String,
                      args: List[(String, Expression)],
                      blockCmds: BlockCommand,
                      ret: Expression ) extends Callable
