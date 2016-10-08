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

  def isIdle: Boolean = pendingDropOffs.isEmpty && pendingPickups.isEmpty

  def step(): ElevatorState = {
    //pickups left after reaching this floor
    def remainingPickups(floor: Int): List[Pickup] = pendingPickups.filter(_.currentFloor != floor)

    //pickups that will join on this floor and thereby become drop offs
    def joiningPickups(floor: Int): List[Pickup] = {
      pendingPickups.filter(x => x.currentFloor == floor && (x.direction == direction || !arePickupsBeyondThisFloor(floor)))
    }

    //Are we as high up or low down as we need to go and will therefore switch directions
    def arePickupsBeyondThisFloor(floor: Int) = {
      val count = direction match {
        case Direction.Up => pendingPickups.filter(_.currentFloor > floor).length
        case _ => pendingPickups.filter(_.currentFloor < floor).length
      }
      count > 0
    }
    //new drop off floors, after reaching this floor
    def newDropOffs(floor: Int): List[Int] = joiningPickups(floor).map(_.goalFloor)

    //renaming people to drop off, who joined before this floor, but didn't leave
    def remainingOriginalDropsOffs(floor: Int): Set[Int] = pendingDropOffs - floor

    //floors to visit after this one to drop everyone off
    def dropOffs(floor: Int): Set[Int] = (remainingOriginalDropsOffs(floor).toList ++ newDropOffs(floor)).toSet

    //We chose direction based on the direction the pickup person choose
    //This pushes direction choice to pick up choice
    def directionFollowingPickup(floor: Int): Direction = {
      joiningPickups(floor).headOption match {
        case Some(p) => p.direction
        case _ => direction
      }
    }

    //If we have nothing to do, then we are done
    if (isIdle) {
      this
    } else {
      //else increment floor
      val floor = direction match {
        case Direction.Up => currentFloor + 1
        case _ => currentFloor - 1
      }
      ElevatorState(
        currentFloor = floor,
        pendingPickups = remainingPickups(currentFloor),
        pendingDropOffs = dropOffs(currentFloor),
        directionFollowingPickup(floor)
      )
    }

  }
}
