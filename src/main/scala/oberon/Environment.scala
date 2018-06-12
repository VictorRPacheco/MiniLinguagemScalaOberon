package oberon

import scala.collection.mutable.Stack
import scala.collection.mutable.HashMap

import oberon.expression.Value
import oberon.callable._
import oberon.thread._


object Environment {
  // Execution Stack =>  contains the current function or procedure call, can stack for recursive functions
  var executionStack = new Stack[HashMap[String, Thread]]()
  // Global Variables => variables defined outside of a function or procedure scope
  var globalVariables = new HashMap[String, Variable]()
  // Program Definitions  => definitions of programs (functions, procedures)
  var progDefinitions = new HashMap[String, Callable]()

  // Insert new space for a new Thread
  def push() {
    executionStack.push(new HashMap[String, Thread])
  }

  // Pops a thread that finished executing
  def pop() {
    executionStack.pop()
  }

  // Adds a new variable to the global variables hashmap or the local variables
  def map(id: String, variable: Variable) {
    // No function/program executing yet means we have a global variable
    if (executionStack.isEmpty) {
      if(globalVariables.get(id).isDefined) globalVariables.remove(id)
      globalVariables += (id -> (variable))
    }
    // There's a function or procedure executing, create new local variable
    else {
      var t = executionStack.top.toList.head._2.asInstanceOf[Thread]
      if(t.localVariables.get(id) != null) t.localVariables.remove(id)
      t.addVariable(id, variable)
    }
  }

  def lookup(id: String): Option[Variable] = {
     if(globalVariables.isEmpty || globalVariables.get(id).isEmpty) {
       executionStack.top.toList.head._2.localVariables.get(id)
     }
     else
       globalVariables.get(id)
  }

  def lookupCallable(id: String): Option[Callable] = {
    if(progDefinitions.isEmpty) None
    else progDefinitions.get(id)
  }

  def clear() : Unit = {
    executionStack.clear()
  }

}
