# BNetTestTask

# Preview

<img src="https://user-images.githubusercontent.com/52213479/138871172-829363e4-e260-4cfe-8c51-4a3f88dfe893.gif" height="500" />

# Logic

All entries are shown inside ```RecyclerView``` in ```EntryListActivity```.


After very first run of the app ```Repository``` will send request ```new_session``` to the server using ```Retrofit``` and gets session for user.

For adding entry ```AddEntryActivity``` calls ```ViewModel's``` addEntry() method which calls ```Repository's``` addEntry(session, body) inside ```Coroutine``` **since request should be sent on a background thread and not block UI thread.** ```Repository``` sends request to the server using ```Retrofit```, passes user's session and entry's body.

```EntryListActivity``` is used for displaying all user's entries by sending another request get_entries through ```ViewModel``` and ```Repository```.

```EntryDetailActivity``` shows detail of clicked entry in ```RecyclerView```.

