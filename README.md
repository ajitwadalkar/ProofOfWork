To run the project from Linux terminal use following command
```
javac pow.java  
java pow $COLUMNS
```
//$COLUMS will give nice look to the borders, but can be ignored on other systems, As far as I checked that will not throw any error, but incase there is any error, please put 150 instead of $COLUMNS.


This will print the current data in the files and will give you options to choose the function as follows.
```
-------------------------------------------------------------------------------------------------------------------------------------------------
Current Data in all files:
input.txt : M12511182
target.txt : 0000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
solution.txt : 3664205142
-------------------------------------------------------------------------------------------------------------------------------------------------

Following are the functionalities in the code, choose anyone:
1.Generate Target
2.Generate Solution
3.Verify
4.Performance test
5.Read current data in files
6.Exit
Please choose the value in between 1,2,3,4,5 or 6 

```
Here you will need to input the number corresponding to the function.
once the function is executed it will print the updated status of all the files.
once that part is done, the program will ask you if you want to re-run the program, you can select 1 to rerun it or 2 to terminate the program.
```
-------------------------------------------------------------------------------------------------------------------------------------------------
Do you want to re-run the program?
Please choose from the following.
1. Yes
2. No
-------------------------------------------------------------------------------------------------------------------------------------------------
```
if you select 1 it will reprint the function menu and if you press 2 the program will terminate.
