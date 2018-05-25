package oberon.thread

import scala.collection.mutable.Stack
import scala.collection.mutable.Map
import oberon.expression._
import oberon.command._
import oberon.callable._

trait Thread

class ProcedureThread(procedure: Procedure, ret: (String, Expression)) extends Thread
class FunctionThread(function: Function, ret: Expression) extends Thread
