# W10_C13_GROUP1 ASSIGNMENT 2 MOVIE BOOKING SYSTEM REPOSITORY

Welcome to our repository! It consists of the artefacts that were inlovlved in the application development of the Movie Booking program through agile and scrum practices

<h1 align="center"><project-name></h1>

<p align="center"><project-description></p>

## Project Links

- [Repository Clone Link](https://github.sydney.edu.au/SOFT2412-2021S2/W10G1A2.git> "Movie Booking System Repo")

- [Live Repository Page](<https://github.sydney.edu.au/SOFT2412-2021S2/W10G1A2> "Live View")

- [Bugs/Issues/Milestones Page](https://github.sydney.edu.au/SOFT2412-2021S2/W10G1A2/milestones "Issues/Milestones Page")

## Screenshots - Sample

Customer Booking Main Phase            |  Manager Main Phase
:-------------------------:|:-------------------------:
![Screen Shot 2021-11-03 at 12 20 11 pm](https://media.github.sydney.edu.au/user/10756/files/ebca9980-3ca0-11ec-9557-53fa1f9aa273) | ![Screen Shot 2021-11-03 at 12 21 15 pm](https://media.github.sydney.edu.au/user/10756/files/fedd6980-3ca0-11ec-8e57-c4ff3491dc1a)


## Prerequisites 

- Gradle - [Installing Gradle](https://gradle.org/install/> "ATM Repo").
- JAVA 11 - [Installing JAVA 11 ORACLE JDK](https://www.oracle.com/java/technologies/downloads/#java11/> "JAVA 11") 
- Java supported IDE - recommended Vscode and Ecplise
- See - [Installing Gradle using Vscode](https://code.visualstudio.com/docs/java/java-build/> "ATM Repo")
- JAVA Swing and AWT - [Installing JAVA Swing and JFC](https://docs.oracle.com/javase/tutorial/uiswing/start/about.html)


## Using the Program 
Once the [master](https://github.sydney.edu.au/SOFT2412-2021S2/W10G1A2.git> "Master branch") branch has been cloned. We can straight away start building the program. Note: Please check the prerequisites for the program have been installed.
Clone command: 
### `git clone https://github.sydney.edu.au/SOFT2412-2021S2/W10G1A2.git `
Next ensure your are in the master branch by running:
### `git branch -a`
You should be able to see a star next the master text. If you happen to be in another branch, run the command:
### `git checkout master`
Once the master branch has been cloned to your local directory, you can run: (Note: cd command works for going to into a folder Windows cmd and Linux bash supports this)
### `cd W10G1A2`
Next run the following command: 
### `gradle clean build`,
The above command will clean previous build binary/classes files and create build files for running the program

You are now one step away from using the Movie Booking System. Run the following commands: 
### `gradle run`,
This command compiles the gradle build files according to the build.gradle specifics.

And that's it! You are able to navigate the application as either a staff member, manager, guest user or a customer, each having access to different functionalities. For example, a guest is able to only browse movies, whereas a customer has access to all the functions available to guests, as well as being able to book tickets.

Guest Page           |  Customer Page
:-------------------------:|:-------------------------:
![Screen Shot 2021-11-03 at 12 34 46 pm](https://media.github.sydney.edu.au/user/10756/files/e1a99a80-3ca2-11ec-8aed-ff707452a5dc) | ![Screen Shot 2021-11-03 at 12 35 11 pm](https://media.github.sydney.edu.au/user/10756/files/eec68980-3ca2-11ec-981a-760b828e1a15)

## Program Features and Specifications

Corresponding to the Specifications outlined by Fancy Cinemas, the ticket booking management system holds two interfaces; one for guests and customers, and the other for staff and managers. Upon starting, the application allows the user to select whether they would like to access the system as a guest/customer or as a member of staff, as seen below:

<img width="500" alt="Screen Shot 2021-11-03 at 3 04 29 pm" src="https://media.github.sydney.edu.au/user/10756/files/643c5500-3cb7-11ec-9b21-bbea5ecf26e3">

To access the booking functionality, users must login or register as a customer. You will be able to browse and select from the move selection across the locations: Paramatta, Usyd, Cantebury, Chatswood, and Bondi. Guests and customer can filter by the attributes: movie title, location, session time, and whether the showing is today or not.

When booking, customers have a 2 minute time frame and can either use giftcards, which have a default amount of $50 to begin with, or a credit card which is saved in our database. Credit cards which are not recognised by our system are rejected, which applies to giftcards as well. Customers have the option of using a split payment method, where the remaining balance of the gift card is exhausted before the credit card it used. If a customer is to exceed this time frame, the booking transaction will automatically be cancelled.

The staff and manager interface allows users to modify, add or remove movies, add or remove movie showings (can only be added for the upcoming week), as well as adding giftcards to the database. When adding a movie, all fields seen below require a valid input. To remove a movie, only the title is required, and to modify, the title as well as the field requiring modification is required.

<img width="1434" alt="Screen Shot 2021-11-03 at 3 25 38 pm" src="https://media.github.sydney.edu.au/user/10756/files/681da680-3cba-11ec-8a3a-5df063e3c6d5">

To add and remove showings, all fields below require valid inputs and the showing must not overlap with an existing show at the same location and room type, when adding.

<img width="1290" alt="Screen Shot 2021-11-03 at 3 29 51 pm" src="https://media.github.sydney.edu.au/user/10756/files/e9753900-3cba-11ec-8895-f6159a050ef7">


 ## Built Using

- Vscode
- Jenkins
- Gradle
- Java 11
- Jave Swing and AWT

## Contributors

**Rahul Talla**

- [Profile](https://github.sydney.edu.au/rtal2306 "Rahul Talla")
- [Email](mailto:rahultalla29@uni.sydney.edu.au?subject=Hi "Hi!")
- 500551998

**Akasha Nedeem**

- [Profile](https://github.sydney.edu.au/anad8554 "Akasha Nadeem")
- [Email](mailto:anad8554@uni.sydney.edu.au?subject=Hi "Hi!")
- 500459605

**Abhishek Verma**

- [Profile](https://github.sydney.edu.au/aver4541 "Abhishek Verma")
- [Email](mailto:aver4541@uni.sydney.edu.au?subject=Hi "Hi!")
- 500476613

**Aryan Pokharna**

- [Profile](https://github.sydney.edu.au/apok9341 "Aryan Pokharna")
- [Email](mailto:apok9341@uni.sydney.edu.au?subject=Hi "Hi!")
- 500503791

**Negeen Daudi**

- [Profile](https://github.sydney.edu.au/ndau3314 "Negeen Daudi")
- [Email](mailto:ndau3314@uni.sydney.edu.au?subject=Hi "Hi!")
- 500508121

## 🤝 Support

Contributions, issues, and feature requests are welcome at the above contacts.

Give us a ⭐️ if you like this project!
