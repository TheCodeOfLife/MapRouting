# MapRouting

##Attempts at Optimization:

######Note: I assumed the given "usa.txt" was our primary subject, and based all observations as such.

1. My first idea involved cutting down on the time involved with using the built-in Priority-Queue used in my basic implementation. I realized that a MinHeap would be more advantageous in terms of retrieving the minimum item, so I replaced the queue with the heap. This cut down on quite a lot of time, by about a constant factor of 3/4 or so.
2. My second improvement involved the check of if the node we had just finished relaxing was the destination we desired. This cut down on an even smaller amount of runtime, only by about 5ms per run. Either way, still an improvement!
3. Lastly, I tried to incorporate the A* (A-Star) map routing shortest path into my program using the euclidian geometry and distance relative to the source node, but unfortunately I was not able to implement this correctly. I think this also had to do with my dataset, usa.txt, because there were some instances in which there were a few "islands" that could not be accessed from the other parts of the set, which was frustrating to try to bug-fix. 

Either way, my overall runtime came down to about a factor of 2/3 or 3/4 of the original, unoptimized version. Data below:

Original, unoptimized time, using usa.txt from source 0 to destination 5000: 95-105ms

Modified to use minheap instead of priority queue, same source/dest:75-83ms

Modified to check if destination was last node relaxed/finished earlier: 70-74ms
