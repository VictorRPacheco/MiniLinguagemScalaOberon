package oberon.command

import oberon.Environment._
import oberon.expression._
import oberon.callable._
import oberon.expression.BoolValue
import oberon.thread._
import oberon.visitor.Visitable
import oberon.visitor.Visitor
import scala.collection.mutable.HashMap

trait Command extends Visitable {
  def run() : Unit
}

class BlockCommand(val cmds: List[Command]) extends Command {
  override
  def run() : Unit = {
    cmds.foreach(c => c.run())
  }
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class Assignment(val id: String, val expression: Expression) extends Command {
  override
  def run() : Unit = {
    // check type of assignment expression
    if (expression.typeCheck().equals(false)) {
      throw new RuntimeException("Assignment expression with invalid type checking")
    }

    lookup(id) match {
      case Some(v) => {
        var newVariable = new Variable(v.id, v.dataType, expression.eval())
        map(id, newVariable)
      }
      case _ => { throw new RuntimeException("Trying to assign to a variable that wasn't declared")}
    }
  }
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class Return(val expression: Expression) extends Command {
  def run(): Unit = {
    // check type of return expression
    if (expression.typeCheck().equals(false)) {
      throw new RuntimeException("Type checking failed at return")
    }

    expression.eval()
  }

  // return for function
  def runReturn(): Value = {

    var v = expression.eval()

    // pop current thread since reached return
    executionStack.pop()
    // return value
    return v
  }

  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }

}

class IfElse(val cond: Expression, val ifCommand: Command, val elseCommand: Command) extends Command {
  override
  def run() : Unit = {

    // perform type checking on condition value
    if (cond.typeCheck().equals(false)) {
      throw new RuntimeException("Type checking failed at IfElse")
    }

    val v = cond.eval.asInstanceOf[BoolValue]

    v match {
      case BoolValue(true)  => { ifCommand.run(); }
      case BoolValue(false) => { elseCommand.run(); }
    }
  }
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class IfThen(val cond: Expression, val command: Command) extends Command {
  override
  def run() : Unit = {

    // perform type checking on condition value
    if (cond.typeCheck().equals(false)) {
      throw new RuntimeException("Type checking failed at IfThen")
    }

    val v = cond.eval.asInstanceOf[BoolValue]

    v match {
      case BoolValue(true)  => { command.run(); }
      case _ => { }
    }
  }
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}


class For(val initCommand: Command, val cond: Expression, val lastCommand: Command, val command: Command) extends Command {
  override
  def run() : Unit = {
      // run first command
      initCommand.run()

      // run as a normal while, with the last command attached to the end
      val newWhile = new While(cond, new BlockCommand(List(command, lastCommand)))
      newWhile.run()

  }
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }

}

class While(val cond: Expression, val command: Command) extends Command {
  override
  def run() : Unit = {

    // perform type checking on condition value
    if (cond.typeCheck().equals(false)) {
      throw new RuntimeException("Type checking failed at For")
    }

    val v = cond.eval.asInstanceOf[BoolValue]

    v match {
      case BoolValue(true) => { command.run(); this.run(); }
      case _               => { }
    }
  }
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class Print(val expression: Expression) extends Command {
  override
  def run() : Unit = {
    // perform type checking on expression to be printed
    if (expression.typeCheck().equals(false)) {
      throw new RuntimeException("Type checking failed at Print")
    }

    expression.getClass.getDeclaredFields foreach { f =>
      f.setAccessible(true)
      print(f.get(expression))
    }
  }
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class ReadInt() extends Command {
  override
  def run() : Unit = {
    //readInt()
  }
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class ReadBool() extends Command {
  override
  def run() : Unit = {
    //readBoolean()
  }
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class CallableDeclaration(id: String, val callable: Callable) extends Command {
  override
  def run() : Unit = {
    mapCallable(id, callable)
  }
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class VariableDefinition(val variable: Variable) extends Command {
  override
  def run() : Unit = {
    map(variable.id, variable)
  }
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class ProcedureCall(val id: String, val args: List[Expression], val ret: Variable) extends Command {
  override
  def run() : Unit = {

    // get the procedure declaration
    var procDeclaration = new CallableRef(id).eval().asInstanceOf[Procedure]

    // Create new localVariables HashMap
    var localVariables = new HashMap[String, Variable]

    // Create a new thread for executing the procedure
    var procThread = new ProcedureThread(procDeclaration.id, procDeclaration, localVariables)

    // Instantiate the formal parameters
    var newProcArgs = procDeclaration.args
    var n = 0
    // For all parameters
    for (v <- newProcArgs) {
      // check number of arguments matches the number of arguments declared in the procedure
      if(args.size != procDeclaration.args.size) throw new RuntimeException("Invalid arguments to procedure")
      // v._1 = variable name
      // v._2 = variable value type

      // perform typecheck on arguments
      if (args(n).calculateType() != procDeclaration.procType) throw new RuntimeException("Invalid argument type")

      // add variable to the current procedure thread
      //                      id                 name, type, value
      procThread.addVariable(v._1, new Variable(v._1 , v._2, args(n).eval()))
      n+=1
    }

    // Adding the thread to the execution stack
    push()
    executionStack.top += (procThread.id -> procThread)

    // Define the return variable
    var retVariable = new VariableDefinition(ret)
    retVariable.run()

    // Run commands
    for (c <- procDeclaration.blockCmds.cmds){
      c.run()
    }

    // save return variable value
    var retValue = new VarRef(ret.id).eval()

    // remove the current thread
    executionStack.pop()

    // declare the return variable like a global variable
    var newReturnVariable = new Variable(ret.id, ret.dataType, retValue)
    var newReturn = new VariableDefinition(newReturnVariable)
    newReturn.run()

  }

  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}
