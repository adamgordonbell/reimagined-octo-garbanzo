package com.cascadeofinsights.skeleton

object SimpleApp extends App {
  val system = new ElevatorControlSystem(1)
  println(system.status)
  system.pickup(Pickup(3, Direction.Down))
  println(system.status)
  system.pickup(Pickup(5, Direction.Down))
  println(system.status)
  system.step()
  println(system.status)
  system.step()
  println(system.status)
  system.step()
  println(system.status)
  system.step()
  println(system.status)
  system.step()
  println(system.status)
}
