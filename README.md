# ChatApp

## About

ChatApp is a simple Java program that lets a user register and log in.

The program checks the user's username, password and cellphone number before allowing registration. It then uses the saved details to check the login.

## Files

* `Login.java` - Handles registration, validation and login.
* `ChatApp.java` - Runs the program and gets information from the user.
* `ChatAppTest.java` - Tests the different functions in the program.

## Rules

### Username

* Must contain `_`
* Must be 5 characters or less

Example: `kyl_1`


### Password

The password must have:

* At least 8 characters
* A capital letter
* A number
* A special character

Example: `Ch&&sec@ke99!`

### Cellphone Number

The number must start with `+27` and contain 9 digits after it.

Example: `+27838968976`

## How It Works

1. Enter your first and last name.
2. Create a valid username.
3. Create a valid password.
4. Enter your cellphone number.
5. The details are stored.
6. Log in using your username and password.
7. The program shows whether the login was successful.

## Testing

