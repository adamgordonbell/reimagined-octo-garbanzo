package com.cascadeofinsights.skeleton

import com.cascadeofinsights.skeleton.ElevatorControlSystem
import org.scalatest.FunSpec
import org.scalatest.GivenWhenThen

import scala.collection.mutable.Stack

class StackSpec extends FunSpec with GivenWhenThen {

  describe("An Elevator Control System") {

    it("something") {

      Given("one elevator")
      val x = new ElevatorControlSystem(1)

      When("status is called")
      val result = x.status()

      Then("we should have one elevator")
      assert(result.head._1 === 0)
    }

    it("should throw NoSuchElementException if an empty stack is popped") {

      Given("an empty stack")
      val emptyStack = new Stack[String]

      When("pop is invoked on the stack")
      When("NoSuchElementException should be thrown")
      intercept[NoSuchElementException] {
        emptyStack.pop()
      }

      And("the stack should still be empty")
      assert(emptyStack.isEmpty)
    }
  }
}