package com.cascadeofinsights.skeleton

import com.cascadeofinsights.skeleton.ElevatorControlSystem
import org.scalatest.FunSpec
import org.scalatest.GivenWhenThen

import scala.collection.mutable.Stack

class StackSpec extends FunSpec with GivenWhenThen {

  describe("An Elevator Control System") {

    it("should init") {

      Given("one elevator")
      val system = new ElevatorControlSystem(1)

      When("status is called")
      val result = system.status()

      Then("we should have one elevator")
      assert(result.head._1 === 0)
    }

    it("start moving") {

      Given("one elevator")
      val system = new ElevatorControlSystem(1)

      When("One Pickup request")
      system.pickup(Pickup(3, Direction.Up))
      system.step()
      system.step()

      Then("we should be on the third floor")
      val currentFloor = system.status.head._2.currentFloor
      assert(currentFloor === 3)
    }

  }
}