package oberon

import scala.collection.mutable.Stack
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap

import oberon.expression.Value
import oberon.expression.Expression
import oberon.callable.Callable
import oberon.function.Function

object Environment {
  var stack = new Stack[Map[String, Value]] ()
  var executionStack = new Stack[Map[String, Callable]]

  def push() {
    stack.push(new HashMap[String, Value]())
  }

  def pop() {
    stack.pop()
  }

  def mapExecStack(id: String, procedure: Callable) = {
    if(executionStack.isEmpty) {
      executionStack.push(new HashMap[String, Callable]())
    }
    executionStack.top += (id -> procedure)
  }

  def map(id: String, value: Value) {
    if(stack.isEmpty) {
      push()
    }
    stack.top += (id -> value) 
  }

  def lookup(id: String) : Option[Value] =
    if(stack.isEmpty) None else Some(stack.top(id))

  def lookupExecStack(id: String) : Option[Callable] =
    if(executionStack.isEmpty) None else Some(executionStack.top(id))

  def clear() : Unit = { stack.clear() }
  def clearExecutionStack() : Unit = { executionStack.clear() }

}
