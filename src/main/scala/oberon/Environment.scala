package oberon

import scala.collection.mutable.Stack
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap

import oberon.expression.Value
import oberon.expression.Expression
import oberon.callable.Callable

object Environment {
  var stack = new Stack[Map[String, Value]] ()
  var symbolsTable = new Stack[Map[String, Callable]]

  def push() {
    stack.push(new HashMap[String, Value]())
  }

  def pop() {
    stack.pop()
  }

  def mapTable(id: String, procedure: Callable) = {
    if(symbolsTable.isEmpty) {
      symbolsTable.push(new HashMap[String, Callable]())
    }
    symbolsTable.top += (id -> procedure)
  }

  def map(id: String, value: Value) {
    if(stack.isEmpty) {
      push()
    }
    stack.top += (id -> value)
  }

  def lookup(id: String) : Option[Value] =
    if(stack.isEmpty) None else Some(stack.top(id))

  def lookupTable(id: String) : Option[Callable] =
    if(symbolsTable.isEmpty) None else Some(symbolsTable.top(id))

  def clear() : Unit = { stack.clear() }
  def clearExecutionStack() : Unit = { symbolsTable.clear() }

}
