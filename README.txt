README

	/* TODO: describe the app and its purpose */
	
	/* TODO: explain input files format */
	
	/* TODO: explain the commands and their format */
	
	/* TODO: explain the hierarchy how it's implemented*/

	First Commit: - developed the structures and the hierarchy (a country contains regions; a region contain cities; a city has locations);
				  - reading from file;
				  - display information (simple, general) about location / city / region / country / everything;
				  - implmented Location, City, Region, Country, Vacation, Period, Activity classes ;
				  
	Second Commit:  - top5 fully developed - top 5 locations overall;
										   - top 5 locations in a city / region / country;
							*the locations were sorted in ascending order considering : average money spent in a day for activities / hotelPrice per night / total money spent in a day (hotel + activities);
							*the user choses the sorting criteria;
				    - create class Print to print all the information;
					- create class PrintTopFive for the top5 command;
					- create class CheapActivities to find and display the cheapest activity available and its location.;
					- modify toString method for location (choose to print <or not> the periods available for a location);
					- add exit command for every step of the communication with the user;
					- input is processes in LowerCase;
					- readme added
					
	TODO: 	- read commands from file
			- print output to file
			- search cheapest activity considering a key-word given by user
			- add sorting option : desceding
			- solve bug
			- make the print methods prettier and clearer

	Bug: - when given a period of time, if the first and the last day given by the user match the period limits of the location, the location is not found (dateInRange method)