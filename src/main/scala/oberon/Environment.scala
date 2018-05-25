package oberon

import scala.collection.mutable.Stack
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import oberon.expression.Value
import oberon.expression.Expression
import oberon.callable._
import oberon.thread._

import scala.collection.mutable

object Environment {
  var symbolsTable = new Stack[Map[String, Value]] ()
  var callableTable = new Stack[Map[String, Callable]] ()
  var executionStack = new Stack[Map[String, Thread]] ()

  def push() {
    symbolsTable.push(new HashMap[String, Value]())
  }

  def pushTable(){
    callableTable.push(new HashMap[String, Callable]())
  }

  def pop() {
    symbolsTable.pop()
  }

  def map(id: String, value: Value) {
    if(symbolsTable.isEmpty) {
      push()
    }
    symbolsTable.top += (id -> value)
  }

  def mapExecutionStack(id: String, t: Thread) = {
    if(executionStack.isEmpty) {
      executionStack.push(new HashMap[String, Thread]())
    }
    executionStack.top += (id -> t)
  }

  def lookup(id: String) : Option[Value] =
    if(symbolsTable.isEmpty) None else Some(symbolsTable.top(id))

  def mapTable(id: String, c: Callable) = {
    if(callableTable.isEmpty) {
      callableTable.push(new HashMap[String, Callable]())
    }
    callableTable.top += (id -> c)

    // declaring arguments in procedure/function
    for (arg <- c.args) {
      map(arg._1, arg._2.eval())
    }
  }

  def lookupTable(id: String) : Option[Callable] =
    if(callableTable.isEmpty) None else Some(callableTable.top(id))

  def clearSymbolsTable() : Unit = { symbolsTable.clear() }
  def clearDeclarations() : Unit = { callableTable.clear() }
  def clearExecutionStack() : Unit = { executionStack.clear() }

}
