package com.cascadeofinsights.skeleton

import com.cascadeofinsights.skeleton.Direction.Direction

import scala.collection.mutable
import scala.util.Random

trait ElevatorControl {
  //The status of all elevators
  def status(): List[(Int, ElevatorState)]

  //update the state of the elevators
  def update(id: Int, state: ElevatorState)

  //recieve a pick up request
  def pickup(pickup: Pickup)

  //step forward the simulaton
  def step()
}

class ElevatorControlSystem(count: Int) extends ElevatorControl {

  var elevators: mutable.Buffer[(Int, ElevatorState)] = List.range(0, count).map((_, ElevatorState())).toBuffer

  var pickups: List[Pickup] = List.empty

  def status: List[(Int, ElevatorState)] = elevators.toList

  def update(id: Int, state: ElevatorState): Unit = elevators(id) = (id, state)

  def pickup(pickup: Pickup): Unit = update(0, getElevator(0).addPickup(pickup))

  def step(): Unit = {
    elevators = elevators.map { case (i, state) => (i, state.step()) }
  }

  private def getElevator(id: Int): ElevatorState = {
    elevators.filter(_._1 == id).head._2
  }

}

case class ElevatorState(
    currentFloor: Int = 1,
    pendingPickups: List[Pickup] = List.empty,
    pendingDropOffs: Set[Int] = Set.empty,
    direction: Direction = Direction.Up
) {
  def addPickup(pickup: Pickup): ElevatorState = {
    ElevatorState(currentFloor, pickup :: pendingPickups, pendingDropOffs, direction)
  }

  def step(): ElevatorState = {
    null
  }
}

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