package com.cascadeofinsights.skeleton

import com.cascadeofinsights.skeleton.Direction.Direction

import scala.util.Random

trait ElevatorControl {
  //The status of all elevators
  def status(): Map[Int, ElevatorState]

  //update the state of the elevators
  def update(id: Int, state: ElevatorState)

  //recieve a pick up request
  def pickup(pickup: Pickup)

  //step forward the simulaton
  def step()
}

class ElevatorControlSystem(count: Int) extends ElevatorControl {

  val elevators: Map[Int, ElevatorState] = List.range(0, count).map((_, ElevatorState(1, Set.empty, Direction.Up))).toMap

  var pickups: List[Pickup] = List.empty

  def status = elevators

  def update(id: Int, state: ElevatorState) = null

  def pickup(pickup: Pickup) = pickups = pickup :: pickups

  def step = null

}

case class ElevatorState(currentFloor: Int, goalFloor: Set[Int], direction: Direction)

object Direction extends Enumeration {
  type Direction = Value
  val Up, Down = Value
}

case class Pickup(currentFloor: Int, direction: Direction) {
  val random = new Random()

  // Goal floor is not know until you get in the elevator
  // For now, we just hardcode it to 2 floors up or down
  lazy val goalFloor = direction match {
    case Direction.Up => currentFloor + 2
    case _ => Math.max(currentFloor - 2, 0)
  }
}