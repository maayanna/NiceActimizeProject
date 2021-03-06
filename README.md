# NiceActimizeProject

This project is a program that counts the number of DAU (Daily Active Users) from a file stream.
My first idea was to write a program that pass through the file stream's lines and fill a hashmap that contains
as a key the date of the user's activity and as a value a hashset containing all the users' id of that same day.
In fact, in order to check all different users I preferred to use a set, where I cannot have users duplications.

As we know, the hashmap performs its actions in O(1) expected time, using the hashcode() and equals() functions.
Same for the hashset. So when running line by line, looking for the date as the key and add the user to the set, in the
best case will only take O(1) by line. 
At the end we have atime complexity of O(#lines). Even when we pass then on the hashmap to print the number of 
users by day, the size of the key set will be the number of days we have and
the number of days is <= number of lines (in case every line is a different date).

In the worst case, we have collisions in our hashmap and every date has the same hashcode, so that for every line
we need to check every date. We will have a time complexity of O(numbers of lines x number of different dates).

About the space complexity we need to store every date, with all the hashsets for every different date.
And in the case we have only different users by day, we will store every line.
So O(number of dates + number of lines).

When I started tp write the program, I didn't know if for sure I was receiving the dates in an ordered way. 
But if It was in an ordered way I would not have store a hashmap and work only with one set. I explain my idea, 
The idea is to check at every line if the new date is the same as the old.
If yes : I continue to work with the same hashset and add my userId (different user).
If no : I print the old date and the size of the hashset and then use this same reference to a new hashset for the 
new date and add it my userId. Of course also at the end of the for loop on my ines, I need to print the last date.
In time complexity, I still run on every line in O(number of lines).
And we approved the space complexity depending on the case. 
Best case : we have multiply days, and so we store in a maximum numbers of different users on the days.
Worst case : We have only entries of one day, and we store then O(number of lines).

In the same idea we could have sort our lines by date and then use the same algorithm but the time complexity 
would have been O(n log n) with n as the numbers of lines.
This is an idea to improve the space complexity.

But as I explained earlier, I supposed I could receive the line not sorted, and I chose to work with the best time 
complexity with a less effective space complexity.

More over, I worked with static methods because I don't think we need to create an instance of the class, 
it is more like a behaviour and like that we do not need to create an instance for every file and to use memory space.