# GCD_Minesweeper_SF
Created with Android Studio

Assignment 02: building a custom view to implement the game of minesweeper

In this assignment you will be tasked with building a custom view that will render and
implement the rules of minesweeper. Assuming you are not already familiar with minesweeper the
premise of the game is simple. You are given a two dimensional grid representing a minefield.
However all of the cells are covered so you cannot see what is underneath. To sweep the minefield you
must uncover cells one after the other until all non-mined cells are uncovered. Non-mined cells will
include a number indicating how many adjacent cells contain a mine relative to that cell. If there are no
adjacent mines a number will not be displayed.

Players should be able to use a combination of a logical reasoning and a little bit of guesswork
to determine what cells contain mines. If a cell contains a mine they should be able to mark it as such.
Any time a player clicks on a marked cell (usually by accident) it will not uncover. Unless the user
explicitly unmarks it.

You will be tasked with building a fully working game of minesweeper runing on a 10x10 board
with 20 mines. Players should be able to reset the game, switch between marking and uncovering
modes and have some information displayed to them about how many mines they have marked and
how many mines there are in total.

