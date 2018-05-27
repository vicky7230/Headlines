![alt text](https://github.com/vicky7230/Headlines/blob/master/app/src/main/res/mipmap-xhdpi/ic_launcher.png "Logo")

# Headlines
![License](https://img.shields.io/badge/LICENSE-Apache%20License%202.0-blue.svg)

Simple Headlines app to showcase MVVM(Model-View-ViewModel) architecture in android.

<p align="center">
  <img src="https://github.com/vicky7230/Headlines/blob/master/graphics/1.png" width="280">
  <img src="https://github.com/vicky7230/Headlines/blob/master/graphics/2.png" width="280">
  <img src="https://github.com/vicky7230/Headlines/blob/master/graphics/3.png" width="280">
</p>

## The Model-View-ViewModel Pattern
The main components in the MVVM pattern are:
* The View — that informs the ViewModel about the user’s actions
* The ViewModel — exposes streams of data relevant to the View
* The DataModel — abstracts the data source. The ViewModel works with the DataModel to get and save the data.

### MVVM pattern was created to simplify the event driven programming of user interfaces.
In MVVM, ViewModel exposes streams of events to which the Views can bind to. Because of this, the ViewModel does not need to hold a reference to the View anymore. This also means that all the interfaces that the MVP pattern requires, are now dropped. View has a reference to ViewModel but ViewModel has no information about the View. The consumer of the data should know about the producer, but the producer — the ViewModel — doesn’t know, and doesn’t care, who consumes the data.

<img src="https://github.com/vicky7230/Headlines/blob/master/graphics/mvvm.png">

## Offline functionality implementation

The app fetches the aticles from the server and saves them in Database locally when it is opened for the first time. When the user opens it next time, the app loads articles from the Database first and then makes a network request in the background, once the network request is complete, the articles on the screen are swapped with the fresh articles from the server. Doing this gives the app a snappy user experience.

<img src="https://github.com/vicky7230/Headlines/blob/master/graphics/offline.png">

In the app there are two sources of data :
1. a Local Database(implemented through Room)
2. a Server(implemented through Retrofit)

So the results(data) that come out of the Database are very quick because the Database is locally available on your device. But the problem with having the results purely in the local Database is that they tend to be stale. The newest form of data is available from the Server. So typically what we also want to do is execute a network request and that network request, typically network requests take a longer time to complete, but once it completes we want to take those results and swap it out with the Local Database results. Doing this gives our app a snapy experience. So the user opens the app and immediately sees the results from the local database and the network request happens in the background and as the network request completes we swap the results. So if we want to do it in rxjava, how do we do it.

### Concat comes to rescue

<img src="https://github.com/vicky7230/Headlines/blob/master/graphics/concat.png">

The Concat operator concatenates the output of multiple Observables so that they act like a single Observable, with all of the items emitted by the first Observable being emitted before any of the items emitted by the second Observable (and so forth, if there are more than two).

Concat waits to subscribe to each additional Observable that you pass to it until the previous Observable completes.

So we can create two observables observables to load data from :
1. one will load data from the Database
2. the other will fetch the data from the server

```
    fun getArticlesFromDatabase(): Observable<Headlines?> {
           return Observable.defer { Observable.just(dataManager.selectArticles()) }
                   .subscribeOn(Schedulers.computation())
       }

   fun getArticlesFromNetwork(): Observable<Headlines?> {
           return dataManager.getArticles()
                   .subscribeOn(Schedulers.io())
       }
```

And them concat these observables and observe them :

```
    Observable.concat(
                   newsViewModel.getArticlesFromDatabase(),
                   newsViewModel.getArticlesFromNetwork()
           )
                   .observeOn(AndroidSchedulers.mainThread(), true)
                   .subscribe(object : Observer<Headlines?> {
                       override fun onComplete() {
                           refresh_layout.isRefreshing = false
                       }

                       override fun onSubscribe(d: Disposable) {
                           refresh_layout.isRefreshing = true
                           compositeDisposable.add(d)
                       }

                       override fun onNext(t: Headlines) {
                           articlesAdapter.addItems(t.articles as MutableList<Article>?)
                       }

                       override fun onError(e: Throwable) {
                           refresh_layout.isRefreshing = false
                           Timber.e(e.message)
                       }
                   })
```

Study the repository to understand the implementation completely.