# My-Todo-App
Todo App in Android using MVVM Architecture.


![MVVM Arcitecture](https://krify.co/wp-content/uploads/2019/06/MVVM-for-Android-App-Development.png)

The opening screen(StartActivity.java) has welcome text using time of the day, AddTodo button and Recyclerview for showing complete Todo List.
On clicking AddTodo button the app navigates to Addnote.java and send data back to StartActivity. The data is then added to database.
On long click of note in MainActivity.java we get a dialog in which edit and delete button is there. Using edit we can edit a todo and using delete button we can delete a todo item.
We use Recyclerview to display the complete todo list in MainActivity.java.
We use Checkbox to denote done and not done.

All the endpoints and database actions are provided in Repository and the Activity send data with the help of viewmodel. The activity observes livedata and updates recyclerview. MVVM provides seperation of concerns and activity/fragment only updates UI, it does not have any business logic or database actions in it. Activity should be as dumb as posible.
