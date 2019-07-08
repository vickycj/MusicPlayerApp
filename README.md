# Music Player List

Architecture Design Pattern - MVVM (Model View ViewModel) 

Tech Stack
-----------

- Programming language    - **Kotlin**
- NetWork API             - **RetroFit**
- Dependancy Injection    - **Dagger 2 for Kotlin**
- Offline Storing         - **Room ORM (Arch components)**
- Streams                 - **RxAndroid and LiveData**
- Architecture Components - **ViewModel, LiveData, Android JetPAck Components**
- Testing Framework       - **Junit, Mockito , Expresso, AndroidJunit4**


Theory
------
Search box is connected with RxTexView, since kotlin comes with inbuilt subscription for editexts. It observes the changes in the textview and transaltes it
to the api call with optimal debounce. Thanks to flatmap/switchmap, once the user re triggers the text, it stops the api call and changes the value and calls again.




