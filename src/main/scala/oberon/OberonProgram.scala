package oberon

import oberon.command.Command

class OberonProgram(val cmd: Command) extends Command {
  // receberia uma lista de declarações e um comando
  override
  def run() : Unit = {
    cmd.run()
  }

}
