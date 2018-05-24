![alt text](https://github.com/vicky7230/Headlines/blob/master/app/src/main/res/mipmap-xhdpi/ic_launcher.png "Logo")

# Headlines
![License](https://img.shields.io/badge/LICENSE-Apache%20License%202.0-blue.svg)

Simple Headlines app to showcase MVVM(Model-View-ViewModel) architecture in android.

## The Model-View-ViewModel Pattern
The main components in the MVVM pattern are:
..*The View — that informs the ViewModel about the user’s actions
..*The ViewModel — exposes streams of data relevant to the View
..*The DataModel — abstracts the data source. The ViewModel works with the DataModel to get and save the data.

### MVVM pattern was created to simplify the event driven programming of user interfaces.
In MVVM, ViewModel exposes streams of events to which the Views can bind to. Because of this, the ViewModel does not need to hold a reference to the View anymore. This also means that all the interfaces that the MVP pattern requires, are now dropped. View has a reference to ViewModel but ViewModel has no information about the View. The consumer of the data should know about the producer, but the producer — the ViewModel — doesn’t know, and doesn’t care, who consumes the data.



