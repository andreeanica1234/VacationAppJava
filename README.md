# Vacation Application Java

Application implemented in Java to help the users choose the destination of their next vacation. 

## Input Files
The program reads a file given as an argument that contains the total number of locations and the name of the files containing the detailed locations.

Format of input file (given as an argument to the program):
```
<n> , n - number of locations
<FileName - location 1>
...
<FileName - location n>
```

Format of location input file:
```
<location name>
<location id>
<country> <region> <city> 
<location price / night>
<m> , m - number of activities
<Name of activity 1>
<Price of activity 1>
...
<Name of activity m>
<Price of activity m>
<p> , p - number of periods available for this location
<FirstDay - period 1> <LastDay - period 1> <Number of days - period 1>
...
<FirstDay - period p> <LastDay - period p> <Number of days - period p>
```

## Commands 
(See exampleOutput.txt for a concrete example)

The communication takes place through the Console.
The user can search through the available informations using and following the 'menu' displayed by the program in the form of questions.
The response options are written within the angle brakets ('<', '>') after the question.
At any given time, the command 'exit' will terminate the program. 

## Available information
The user can see and search for: 
- the hierarchy (the countries with their regions, their cities and their locations; no details shown for the locations)
- a certain location with all its info 
- the locations available in a city / region / country
- cheapest activity and its price and location(s)
- top5 locations
- top5 locations in a city / region / country

  - the locations are sorted in ascending order considering : average money spent in a day for activities / hotelPrice per night / total money spent in a day (hotel + activities)
  - the user choses the sorting criteria
  
## Implementation
The program was developed in Eclipse IDE. Version: Neon.1a Release (4.6.1)

The main class (Vacation) contains an ArrayList of countries and an ArrayList of locations. Every country contains regions, every region contains cities and every city contains an array with the index of every location that is found in that city.

An object of type Location has the next fields: name, id, city, region, country, price of the hotel / night, average price of the activities, array of available activities, array of available periods.


