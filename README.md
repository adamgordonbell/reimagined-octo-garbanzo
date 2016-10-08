# Elevator Control System

## Problem 
Design and implement an elevator control system. What data structures, interfaces and algorithms will you need? Your elevator control system should be able to handle a few elevators — up to 16.

You can use the language of your choice to implement an elevator control system. In the end, your control system should provide an interface for:

## Build Instructions
 * run >sbt compile to generate a jar file
 * run >sbt test to runs tests
 * run >sbt run to run an example
 
## Usage
 * See tests for sample usage
 
```
  it("should not pickup people going the wrong direction") {

      Given("one elevator")
      val system = new ElevatorControlSystem(1)

      When("two Pickup request")
      system.pickup(Pickup(3, Direction.Down))
      system.pickup(Pickup(5, Direction.Down))
      system.step()
      system.step()
      system.step()

      Then("we should not have a passenger")
      val passengers = system.status.head._2.pendingDropOffs
      assert(passengers === Set.empty)
    }
    ``` 