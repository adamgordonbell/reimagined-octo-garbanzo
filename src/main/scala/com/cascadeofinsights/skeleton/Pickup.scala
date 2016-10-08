package com.cascadeofinsights.skeleton

import com.cascadeofinsights.skeleton.Direction.Direction

import scala.util.Random

case class Pickup(currentFloor: Int, direction: Direction) {
  val random = new Random()

  // Goal floor is not know until you get in the elevator
  // For now, we just hardcode it to 2 floors up or down
  lazy val goalFloor = direction match {
    case Direction.Up => currentFloor + 2
    case _ => Math.max(currentFloor - 2, 0)
  }
}
