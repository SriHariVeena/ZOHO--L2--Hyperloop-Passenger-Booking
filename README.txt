
class Passenger->To store the Passenger details(name,age,source,destination)
class Path->To store Path details
class sortBY->It implements Comparator interface,the function compare is used to store the path with length as priority.
class sorttByAGE->implements Comparator interface with age as priority.
class sortBYQUEUE->implements Comparator interface with position as priority.
class Graph->In class,
Attributes:
adj list to store the values,
minsize to maintain a minimum distance,
paths to give a minimum distance between source and destinaton,
queue to maintain the position,
agequeue to maintain age.
Functions:
addEdge - to append the values in the adjacency List 
Deque - To pop the people from the pod
Enqueue - To add the passenger in the queue
print - print the values in the queue
printAllPaths - To print the paths.
printPaths - To Print the minimum paths.
solve - Recursive function,which helps to find the different paths.
Problem statement:
1. INIT Command - Initializes the system with
a. Number of interconnecting routes (N) and the Starting station.
b. Next N lines denotes connection between two interconnections.
Without initializing - All other commands should throw proper error.
2. ADD_PASSENGER command adds passenger to the line.
a. ADD_PASSENGER X adds X number of passengers to the line. X lines
following the ADD command denotes the passenger's name, age and
destination
b. NAME AGE DEST
3. START_POD command starts pod with a passenger having highest age to his
destination following the minimum interconnection points. Print the passenger
name and route.
a. START_POD X starts X number of passengers of highest age. (X lines are
printed with name and route )
b. NAME ROUTE
4. PRINT_Q command prints the number of passengers and their details who are
remaining in the queue.
a. COUNT
b. NAME AGE
Logic:
Whenever a pod is started, the pod should pickup
● the oldest person from the passenger queue and
● take the route which has minimum interconnections


