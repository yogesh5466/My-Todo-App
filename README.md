# My-Todo-App
Todo App in Android using MVVM Architecture.


The opening screen(StartActivity.java) has welcome text using time of the day, AddTodo button and Recyclerview for showing complete Todo List.
On clicking AddTodo button the app navigates to Addnote.java. Using this activity we add the data to the local sqlite database notes.
On long click of note in MainActivity.java we get a dialog in which edit and delete button is there. Using edit we can edit a todo and using delete button we can delete a todo item.
We use Recyclerview to display the complete todo list in MainActivity.java.
We use Checkbox to denote done and not done.

