# Idagio_test


***The activity leak problem*** .   
There are two branches that trace the task :

The problem simulation:  
https://github.com/linkinamine/Idagio_test/tree/leak_Error .    
The problem resolution:  
https://github.com/linkinamine/Idagio_test/tree/leakFix .   

The code may cause a leak in case the activity is destroyed before the thread is finished , the activity cannot be garbage collected.
Holding a reference to the activity, the thread prevents the GC from ever claiming the activity, this leads to leak.
Using Leak Canary we can catch the leak :
![Leak Canary Snapshot](https://scontent-frx5-1.xx.fbcdn.net/v/t1.15752-9/s2048x2048/31357884_10211320749438265_6278678467999432704_n.png?_nc_cat=0&oh=168499e6a7ab31c6ae0e1ded17123b3f&oe=5B9B2902)

The leak can be solved using WeakReference, meaning If the activity is being garbage collected and is only referenced weakly, we don’t hold onto it.  
  
  ***The dependency injection*** .  
  
  The branch : https://github.com/linkinamine/Idagio_test/tree/dependency_injection .  
  The unit tests: https://github.com/linkinamine/Idagio_test/tree/dependency_injection_test .   
  
  The example consists of a Car that depends on an Engine and a Driver, the dependency injection is provided by Dagger2 and 
  we can perform the actions that are : start and stop
  Both of these actions are unit tested

 ***The service that outlive the activity*** .  
  
  The branch : https://github.com/linkinamine/Idagio_test/tree/background_service .   
  The unit tests: https://github.com/linkinamine/Idagio_test/tree/background_service_test .    
  
  The service is basically an always running service , can be stopped with the app, however even whith the destruction of the activity, the service remains.  
I chose a foreground service so I can create a sample notification ( can be a music player for instance) the notification allows us to interact with the service even after closing the activity.
