# Factory Method and Abstract Factory Patterns - Logistics Application

## Overview
This Java application demonstrates the Factory Method and Abstract Factory design patterns in a logistics system that supports road and sea delivery with Windows and macOS UI components.

## Implementation Details

### Part A: Factory Method Pattern (Transport)
- **Product**: `Transport` interface with `deliver(String cargo, String destination)` method
- **Concrete Products**: 
  - `Truck` class implementing road delivery
  - `Ship` class implementing sea delivery
- **Creator**: Abstract `Logistics` class with:
  - Abstract factory method `createTransport()`
  - Shared method `planDelivery(String cargo, String destination)` that uses the factory method
- **Concrete Creators**:
  - `RoadLogistics` overriding `createTransport()` to return a `Truck`
  - `SeaLogistics` overriding `createTransport()` to return a `Ship`

### Part B: Abstract Factory Pattern (UI Components)
- **Product Interfaces**:
  - `Button` interface with `paint()` method
  - `Checkbox` interface with `paint()` method
- **Abstract Factory**: `GUIFactory` interface with:
  - `createButton()` method returning `Button`
  - `createCheckbox()` method returning `Checkbox`
- **Concrete Factories**:
  - `WindowsFactory` creating `WindowsButton` and `WindowsCheckbox`
  - `MacOSFactory` creating `MacOSButton` and `MacOSCheckbox`
- **Client**: `DeliveryApplication` class that:
  - Receives `Logistics` and `GUIFactory` objects via constructor injection
  - Renders UI components and processes delivery

### Main Application
- `Main` class parses command-line arguments for:
  - Delivery mode: `ROAD` or `SEA`
  - UI platform: `WINDOWS` or `MACOS`
- Validates inputs and creates appropriate concrete implementations
- Instantiates and runs `DeliveryApplication`

## UML Diagrams (Text Representation)

### Factory Method Pattern (Transport)
```
+-----------------------------------------+       +------------------+
|   <<interface>>                         |       |   Transport      |
|     Transport                           +<------|     (interface)  |
| +deliver(cargo:String, dest:String):void|       |                  |
+-----------------------------------------+       +------------------+
        ^                                           ^
        |                                           |
+-------------------+                             +-------------------+
|     Truck         |                             |     Ship          |
| -deliver(...):void|                             | -deliver(...):void|
+-------------------+                             +-------------------+
        ^                                               ^
        |                                               |
+----------------------------------------------+       +-----------------------------+
|    Logistics                                 |<----- |RoadLogistics                |
| (abstract)                                   |       | -createTransport():Transport|
| +createTransport():Transport                 |       |                             |
| +planDelivery(cargo:String, dest:String):void|       +-----------------------------+       
+----------------------------------------------+             ^
        ^                                                    |
        |                                                    |
+----------------------------------------------+       +-----------------------------+
|    Logistics                                 |<----- |SeaLogistics                 |
| (abstract)                                   |       | -createTransport():Transport|
| +createTransport():Transport                 |       +-----------------------------+
| +planDelivery(cargo:String, dest:String):void|      
+----------------------------------------------+
```

### Abstract Factory Pattern (UI Components)
```
+----------------+       +------------------+
|   <<interface>>|       |     Button       |
|     Button     +<------|     (interface)  |
| +paint():void  |       |                  |
+----------------+       +------------------+
        ^                         ^
        |                         |
+----------------+       +------------------+       +------------------+       +------------------+
|WindowsButton   |       |MacOSButton       |       |WindowsCheckbox   |       |MacOSCheckbox     |
| -paint():void  |       | -paint():void    |       | -paint():void    |       | -paint():void    |
+----------------+       +------------------+       +------------------+       +------------------+
        ^                         ^                         ^                         ^
        |                         |                         |                         |
+---------------------------+  +------------------+       +------------------+       +------------------+
|   <<interface>>           |  |   GUIFactory     |       |   <<interface>>  |       |   Checkbox       |
|     GUIFactory            +<-|     (interface)  |       |     Checkbox     +<------|     (interface)  |
| +createButton():Button    |  |                  |       | +paint():void    |       |                  |
| +createCheckbox():Checkbox|  |                  |       +------------------+       +------------------+
+---------------------------+  +------------------+
        ^                            ^
        |                            |
+---------------------------+       +---------------------------+
| WindowsFactory            |       |  MacOSFactory             |
| -createButton():Button    |       | -createButton():Button    |
| -createCheckbox():Checkbox|       | -createCheckbox():Checkbox|
+---------------------------+       +---------------------------+
```

### Client Usage
```
+-----------------------+
| DeliveryApplication   |
| -logistics:Logistics  |
| -guiFactory:GUIFactory|
| +run():void           |<-----------------------------------
+-----------------------+                                   |
        ^                                                   |
        |                                                   |
+----------------------------------------------+       +-----------------------------+
|    Logistics                                 |<----- |RoadLogistics                |
| (abstract)                                   |       | -createTransport():Transport|
| +createTransport():Transport                 |       +-----------------------------+
| +planDelivery(cargo:String, dest:String):void|            ^
+----------------------------------------------+            |
        ^                                                   |
        |                                                   |
+----------------------------------------------+       +-----------------------------+
|    Logistics                                 |<----- |SeaLogistics                 |
| (abstract)                                   |       | -createTransport():Transport|
| +createTransport():Transport                 |       +-----------------------------+
| +planDelivery(cargo:String, dest:String):void|         ^
+----------------------------------------------+         |
        ^                                                |
        |                                                |
+---------------------------+       +---------------------+
|   <<interface>>           |       |   GUIFactory        |
|     GUIFactory            +<------|     (interface)     |
| +createButton():Button    |       |                     |
| +createCheckbox():Checkbox|       |                     |
+---------------------------+       +---------------------+
        ^                               ^
        |                               |
+---------------------------+       +---------------------------+
| WindowsFactory            |       |  MacOSFactory             |
| -createButton():Button    |       | -createButton():Button    |
| -createCheckbox():Checkbox|       | -createCheckbox():Checkbox|
+---------------------------+       +---------------------------+
```

## Clean Code Practices Applied

1. **Meaningful Names**: 
   - Class and interface names clearly indicate their purpose (e.g., `RoadLogistics`, `WindowsButton`)
   - Method names describe actions (`deliver`, `paint`, `createTransport`)

2. **Small Methods**: 
   - Each method has a single responsibility (e.g., `deliver()` only handles delivery logic)
   - Factory methods focus solely on object creation
   - The `planDelivery()` method orchestrates the workflow without implementation details

3. **Avoid Duplicated Logic**: 
   - Delivery workflow is centralized in `Logistics.planDelivery()`
   - UI component creation is centralized in the respective factories
   - No repeated conditional logic for transport or UI selection

4. **Data Abstraction**: 
   - Clients interact with abstractions (`Transport`, `Button`, `Checkbox`)
   - Concrete implementations are hidden behind interfaces
   - No exposure of internal state through getters/setters

5. **Objects and Encapsulation**: 
   - Behavior is expressed through methods (`deliver()`, `paint()`)
   - Internal state is kept private where needed (though minimal in this example)
   - No unnecessary accessors that break encapsulation

## Design Reflection

### Adding One More Transport (e.g., AirTransport)
- **Changes needed**:
  - Create `Airplane` class implementing `Transport`
  - Create `AirLogistics` class extending `Logistics` and overriding `createTransport()` to return `Airplane`
- **Unchanged**:
  - `Transport` interface
  - `Logistics.planDelivery()` method
  - `DeliveryApplication` and `Main` classes
  - All UI-related code

### Adding One More UI Family (e.g., Linux)
- **Changes needed**:
  - Create `LinuxButton` and `LinuxCheckbox` classes implementing respective interfaces
  - Create `LinuxFactory` class implementing `GUIFactory`
- **Unchanged**:
  - `Button` and `Checkbox` interfaces
  - `GUIFactory` interface
  - `DeliveryApplication` class
  - All transport-related code

### Adding One More UI Product Type (e.g., TextField)
- **Changes needed**:
  - Create `TextField` interface with appropriate methods (e.g., `renderText(String text)`)
  - Modify `GUIFactory` interface to add `createTextField()` method
  - Update all factory implementations (`WindowsFactory`, `MacOSFactory`, and any new ones) to implement the new method
  - Update `DeliveryApplication` to use the new text field
- **Unchanged**:
  - Existing `Button` and `Checkbox` implementations
  - Transport-related code
  - Core application structure

This demonstrates the power of these patterns: they allow for extension with minimal changes to existing code, adhering to the Open/Closed Principle.

## How to Run
Compile and run using Java 17+:

```bash
# Compile
javac -d build src/**/*.java

# Run examples:
java -cp build application.Main ROAD WINDOWS
java -cp build application.Main SEA WINDOWS
java -cp build application.Main ROAD MACOS
java -cp build application.Main SEA MACOS
```

Invalid inputs will produce clear error messages.