# Elevator Control System

## Problem 
Design and implement an elevator control system. What data structures, interfaces and algorithms will you need? Your elevator control system should be able to handle a few elevators — up to 16.

You can use the language of your choice to implement an elevator control system. In the end, your control system should provide an interface for:

## Build Instructions
 * run >sbt compile to generate a jar file
 ```
 sbt compile
 ```
 * run >sbt test to runs tests
  ```
  sbt test
  ```
 * run >sbt run to run an example
  ```
  sbt run
  ```
## Example run:
```
[info] Running com.cascadeofinsights.skeleton.Main 
List((0,ElevatorState(1,List(Pickup(5,Down), Pickup(3,Down)),Set(),Up)))
List((0,ElevatorState(2,List(Pickup(5,Down), Pickup(3,Down)),Set(),Up)))
List((0,ElevatorState(3,List(Pickup(5,Down), Pickup(3,Down)),Set(),Up)))
List((0,ElevatorState(4,List(Pickup(5,Down), Pickup(3,Down)),Set(),Up)))
List((0,ElevatorState(5,List(Pickup(3,Down)),Set(3),Down)))
List((0,ElevatorState(4,List(Pickup(3,Down)),Set(3),Down)))
List((0,ElevatorState(3,List(),Set(1),Down)))
List((0,ElevatorState(2,List(),Set(1),Down)))
List((0,ElevatorState(1,List(),Set(),Down)))
List((0,ElevatorState(1,List(),Set(),Down)))

```
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
    
## The scheduling problem
Currently a pickup is assigned  this way
 * If there is an elevator on the way, use it
 * else if there is an idle elevator use it
 * else use the elevator with the least pending pickups