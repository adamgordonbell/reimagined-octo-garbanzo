package com.cascadeofinsights.skeleton

import com.cascadeofinsights.skeleton.Direction.Direction

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
