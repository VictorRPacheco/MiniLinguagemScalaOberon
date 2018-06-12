package oberon

import scala.collection.mutable.Stack
import scala.collection.mutable.HashMap

import oberon.callable._
import oberon.thread._

/*
 * Environment object: contains an execution stack, global variables and program declarations (procedures, functions)
 */
object Environment {
  // Execution Stack =>  contains the current function or procedure call, can stack for recursive functions
  var executionStack = new Stack[HashMap[String, Thread]]()
  // Global Variables => variables defined outside of a function or procedure scope
  var globalVariables = new HashMap[String, Variable]()
  // Program Declarations  => declarations of programs (functions, procedures)
  var progDeclarations = new HashMap[String, Callable]()

  // Insert new space for a new Thread
  def push() {
    executionStack.push(new HashMap[String, Thread])
  }

  // Pops a thread that finished executing
  def pop() {
    executionStack.pop()
  }

  def current(): Thread = {
    executionStack.top.toList.head._2.asInstanceOf[Thread]
  }

  // Adds a new variable to the global variables hashmap or the local variables depending on the scope
  def map(id: String, variable: Variable) {
    // No function/program executing yet means we have a global variable
    if (executionStack.isEmpty) {
      // if the global variable is defined update it
      if(globalVariables.get(id).isDefined) globalVariables.remove(id)
      globalVariables += (id -> (variable))
    }
    // There's a function or procedure executing, create new local variable
    else {
      // takes the thread that is currently executing
      var currentThread = current()
      // if the local variable exists update it
      if(currentThread.localVariables.get(id).isDefined) currentThread.localVariables.remove(id)
      currentThread.addVariable(id, variable)
    }
  }

  // Look for a variable in the global variables stack or the local variables stack
  // global scope has priority
  def lookup(id: String): Option[Variable] = {
     // if we can't find a global variable with that name
     if(globalVariables.get(id).isEmpty) {
       // get the variable form the local variables stack from the current stack
       var currentThread = current()
       currentThread.localVariables.get(id)
     }
       // get the global variable
     else
       globalVariables.get(id)
  }

  // lookup a callable declaration, it could be a procedure or a function
  def lookupCallable(id: String): Option[Callable] = {
    if(progDeclarations.isEmpty) None
    else progDeclarations.get(id)
  }

  // clear the execution stack, delete all threads
  def clear() : Unit = {
    executionStack.clear()
  }

}
