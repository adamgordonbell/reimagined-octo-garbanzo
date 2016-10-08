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
    //Assign to the first idle elevator
    val firstIdle = elevators.find(_._2.isIdle)
    firstIdle match {
      case Some(elevator) => update(elevator._1, getElevator(elevator._1).addPickup(pickup))
      //otherwise pick the first
      case _ => update(0, getElevator(0).addPickup(pickup))
    }

  }

  def step(): Unit = {
    elevators = elevators.map { case (i, state) => (i, state.step()) }
  }

  private def getElevator(id: Int): ElevatorState = {
    elevators.filter(_._1 == id).head._2
  }

}

