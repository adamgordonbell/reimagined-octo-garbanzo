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

  //Here we decide which elevator to assign the pickup to

  def pickup(pickup: Pickup): Unit = {
    def elevatorInDirection(): Option[(Int, ElevatorState)] = {
      elevators.find(_._2.isOnWay(pickup))
    }
    def leastBusy(): (Int, ElevatorState) = {
      elevators.minBy(_._2.pendingPickups.length)
    }
    val firstIdle = elevators.find(_._2.isIdle)
    val elevatorState: (Int, ElevatorState) = elevatorInDirection().getOrElse(firstIdle.getOrElse(leastBusy))
    update(elevatorState._1, elevatorState._2.addPickup(pickup))
  }

  def step(): Unit = {
    elevators = elevators.map { case (i, state) => (i, state.step()) }
  }

  private def getElevator(id: Int): (Int, ElevatorState) = {
    elevators.filter(_._1 == id).head
  }

}

