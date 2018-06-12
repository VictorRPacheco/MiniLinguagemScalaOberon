package oberon.thread

import oberon.expression._
import oberon.callable._
import scala.collection.mutable.HashMap

trait Thread {
  def id: String
  def localVariables: HashMap[String, Variable]
  def addVariable(id: String, variable: Variable) = {
    localVariables += (id -> (variable))
  }
}

case class ProcedureThread(
                            id: String,
                            procedure: Procedure,
                            localVariables: HashMap[String, Variable],
                          ) extends Thread


case class FunctionThread(
                           id: String,
                           function: Function,
                           localVariables: HashMap[String, Variable]
                         ) extends Thread
