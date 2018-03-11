# Find 20 first StackOverflow users with a name

For this challenge build an app that connects to the
StackExchange API and displays users as described below

## Description

The following wireframes describe the screens for the app.  
The ascii-drawings are for reference only.  
Just make sure that the required data is displayed.

### Main Screen

Display an input field and Button to search for users by name.  
Display up to 20 users alphabetically and show their reputation and username.  
When tapped, open a new `Activity` to display more information about the
user.

```
+--------------------+
|  AppName           |
|--------------------+
| __________  SEARCH | - input and button  
| ------------------ |
| 123  Username1     |
| ------------------ |
| 390  Username2     | -----*tap* -----> user details screen
| ------------------ |
|   0  Username3     |
| ------------------ |
| 275  Username4     |
| ------------------ |
| 122  Username5     |
+--------------------+
```

### User Details screen

Displays additional information about the user.

```
+--------------------+
| < User             | - up button and page title
|--------------------+
|                    |
|   +-----------+    |
|   |           |    |
|   |   Avatar  |    |
|   |           |    |
|   +-----------+    |
|   User Name        |
|   Reputation       |
|   Badges           |
|                    |
|   Location         |
|   Age              |
|   Creation Date    |
|                    |
+--------------------+
```

## References

Retrofit2 - http://square.github.io/retrofit/  
RxJava - https://github.com/ReactiveX/RxJava  
StackExchange API - https://api.stackexchange.com/docs
