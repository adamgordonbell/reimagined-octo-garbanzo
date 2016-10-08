package com.cascadeofinsights.skeleton

/**
 * Created by adam on 10/8/16.
 */
trait ElevatorControlSystem {
  //The status of all elevators
  def status(): Seq[ElevatorState]

  //update the state of the elevators
  def update(state: ElevatorState)

  //recieve a pick up request
  def pickup(floor: Int, direction: Int)

  //step forward the simulaton
  def step()
}

case class ElevatorState(id: Int, floorNumber: Int, goalFloor: Int)