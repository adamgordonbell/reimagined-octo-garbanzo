package com.cascadeofinsights.skeleton

import com.cascadeofinsights.skeleton.Direction.Direction

import scala.collection.Set

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
    def remainingPickups(floor: Int): List[Pickup] = pendingPickups.filter(_.currentFloor != floor)
    def joiningPickups(floor: Int): List[Pickup] = pendingPickups.filter(_.currentFloor == floor)
    def newDropOffs(floor: Int): List[Int] = joiningPickups(floor).map(_.goalFloor)
    def remainingOriginalDropsOffs(floor: Int): Set[Int] = pendingDropOffs - floor
    def dropOffs(floor: Int): Set[Int] = (remainingOriginalDropsOffs(floor).toList ++ newDropOffs(floor)).toSet

    //If we have nothing to do, then we are done
    if (pendingDropOffs.isEmpty && pendingPickups.isEmpty) {
      this
    } else {
      //Increment floor
      val floor = direction match {
        case Direction.Up => currentFloor + 1
        case _ => currentFloor - 1
      }
      ElevatorState(currentFloor = floor, pendingPickups = remainingPickups(currentFloor), pendingDropOffs = dropOffs(currentFloor), direction)
    }

  }
}
