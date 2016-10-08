package com.cascadeofinsights.skeleton

object Main extends App {
  val system = new ElevatorControlSystem(1)
  system.pickup(Pickup(3, Direction.Down))
  system.pickup(Pickup(5, Direction.Down))
  println(system.status)

  printStep
  printStep
  printStep
  printStep
  printStep
  printStep
  printStep
  printStep
  printStep

  def printStep: Unit = {
    system.step()
    println(system.status)
  }
}
