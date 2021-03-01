## Film Query Application

My first SQL project ever! A part of my work for Skill Distillery.  This app uses prepared statements to safely query an sql database.

### Overview

This app pulls data from an sql database as you query for different films! It pulls from multiple tables and unites related data using foreign to primary key mapping.


## Class Structure

This app is structured using separation of concerns as well as the MVC design pattern.

The UI functionality it found in the com.skilldistillery.app directory.

The interaction with the database is all done within the com.skilldistillery.database directory.

Finally - the entities, whose state data are pulled in from the database, are modeled inside of the com.skilldistillery.entities directory.

### How to use

This app's main can be found using the path:
  FilmQuery/src/com.skilldistillery.filmquery.app.FilmQueryApp

It requires no command line arguments.  Once run the menu will display in the console or terminal.

### What I learned

This week I learned SQL - and for this project I learned how to debug an SQL application!  One of the errors I ran into was for 'no parameters'. I figured out that this was because I wrote '?' instead of ? in  my prepared statement.
