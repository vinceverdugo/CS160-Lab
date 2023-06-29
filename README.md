# CS160-Lab
- This Java program is used to create and store coffee orders

#How to run the application
- This application will run and prompt the user with a menu to make selections on the activity they would like to perform
- The first option to create a new order will continue to prompt the user to make selections for the type of coffee, and any add ons
- Any of the other options will perform their tasks before prompting the user again with the menu

  
#CoffeeOrder
- This class is where methods will be performed to add individual coffee's to coffee orders. Containss information for total costs and receipts for orders

#Coffee <<Interface>>
- This interface introduces the printCoffee, getCost, and getIngredients methods that are used throughout the program

#BlackCoffee
- This class implements Coffee and is the base for creating a drink.

#Espresso
- Like BlackCoffee, this class implements Coffee and is the base for creating a drink.

#CoffeeDecorator
- This class implements Coffee, and is the parent class for the decorator classes

#WithHotWater
- When this class is called it will add Hot Water to the coffee order

#WithMilk
- When this class is called it will add Milk to the coffee order

#WithSugar
- When this class is called it will add Sugar to the coffee order

#WithWhippedCream
- When this class is called it will add Hot Water to the coffee order

#WithFlavor
- When this class is called it will add the user will be prompted to select which enum Syrup value flavor they would like to be added to their order
