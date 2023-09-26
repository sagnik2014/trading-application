# trading-application
1: Modified the Application class to impplement the handleSignal method of the interface dynamically as per the signal passed.
2: Created Algo class instance dynamically and also called the required methods of Algo class using Java reflection by reading the method name and method parameters 
   from tradings-config.json file 
   per JIRA signal which is an Integer value
3: I have also tested the application using Postman where in I set the post method request body to "raw" and the content type to "Text" or "Plain Text," 
   and then pass the integer value.
  URL:http://localhost:8080/trading-app/receive-signal

4: Initially I created .json file with signal and respective class name, but then for that I realized I had to write individual implementation classes per signal 
   so I tried to modify the code as stated above to make it more scalable and compact.

5: Right now once the code moves to production and as per new signal/Jira ticket the developement team only have to write the method names, and method parameter per Jira ticket
   in the "tradings-config.json" and no further coding is needed. This make new changes to be easily deployable in very less time.
