#### Project Collaborators: Adam Nixon, Zachary Burgess <br>

## User Guide <br>
- The application's runtime configuration must include Java and JavaFX.
- For now, the user can login by directly pressing the login button without using the textboxes, but there is a user in the user base with credentials "user" and "pass"
- This system has two views; the Login view and the Main view. The Login view allows a user to Login to a preexisting account or close the application. Once logged in, the user is taken to the Main view. Here, the user is shown a search bar, a list of available items, a cart, and a checkout window. Within the main view, the user can add zero to many of each item to their cart. Once items are added to the cart, the checkout window updates respectively. Furthermore, the user has the ability to remove specific items from the cart, or remove the cart entirely. Additionally, the user can place an order. Placing an order will write to a csv file to keep track of placed orders, and update the store's inventory file, inventory.csv.  In the app, the inventory is updated as soon as the purchase is made.

- Login View: <br>
![image](https://user-images.githubusercontent.com/113068231/205457973-5e915696-4fe8-4b0a-9904-823438bcecc5.png)

- Main View: <br>
![image](https://user-images.githubusercontent.com/113068231/205458030-42894e19-2856-43c9-8464-d4e53455db00.png)


## Notable Design Choices/Known Bugs <br>
- There are no known bugs
- Notable Design Choices:

    - Classes that are primarily GUI elements are in the classes that use them
    - GUI item cards are made by the InventoryController's CardBuilder


## Debugging Collaborators <br>
- None

#### Cumulative Hours Spent on Development: 25-30
