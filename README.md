# BSPQ22-E1
![GitHubActions](https://github.com/SPQE21-22/BSPQ22-E1/actions/workflows/maven.yaml/badge.svg)

# [Documentation](https://spqe21-22.github.io/BSPQ22-E1/)

### A project from Deusto Computer Engineering, Software Process and Quality.

## Authors:

- [Alex Egaña](https://github.com/Aeganarte)
- [Eneko Eguiguren](https://github.com/Eneko9)
- [Rubén García](https://github.com/rubengp39)
- [Tyler De Mier](https://github.com/tylerdemier)
- [Aida Gomezbueno](https://github.com/aidagomezbueno)

## Purpose:

The main objetive of our application is to manage a library.

Users will be able to pick a book, book a room, buy and eat in the cafeteria and see his/her profile information.

Administrators will be able to add new books, fine users and manage cafeteria supplies and menus.

## Working Process [(Youtrack)](https://spqdeusto.myjetbrains.com/youtrack/projects/def40ce8-436c-408c-bc32-c3343ec45451):

We worked using SCRUM, an agile methodology where we defined user stories (needs) and we gave them a priority and an estimation time. We divided the project in three different sprints as shown below.

### Sprint 1 (March 20 - April 4)

#### Tasks:
- Setup Maven Environment.
- Server Side Architecture.
- Basic GUI.
- DB Connection.
- Book Management.
- Picking up a book.
- Creating an account.
- Show book availability.

#### Estimation: 

![image](https://user-images.githubusercontent.com/62309228/169716298-a9656b9c-9a24-4fa3-8ef9-4d5ed4d30fe6.png)

#### Burndown Chart:

![image](https://user-images.githubusercontent.com/62309228/169716334-f2f0c52f-2704-44e8-8a7d-7205e3cd2ab7.png)

#### Conclusions:

We started with some configuration problems in diffent OS but we managed to figure it out. We divided the work in a balanced way but we were in a hurry at the final days. We underestimated the time it would cost us starting with the project.

### Sprint 2 (April 5 - May 2)

#### Tasks:
- Server Refination and depuration.
- Updating DB.
- Unit tests.
- Performance tests.
- Book a room.
- Reserve a book.
- Study room assignment.
- Deleting account.
- Show room availability.

#### Estimation:

![image](https://user-images.githubusercontent.com/62309228/169716527-29a7c8ec-02c7-454d-8291-ab2d0740c05b.png)

#### Burndown Chart:

![image](https://user-images.githubusercontent.com/62309228/169716547-bdbca804-3199-433a-a64c-44dbc9604b83.png)

#### Conclusions:

This time we were working since the first days but, we left all the task in "to verify" and the burndown does not update until we moved the task to finish. Most of these tasks refer to tests so they were moved to finish when all the implementation was done. We learned that smaller tasks would give us a better approach of the work. We had some issues because real time was not updating for some of us.

### Sprint 3 (May 3 - May 19)

#### Tasks:
- Internationalization.
- JavaDocs Documentation.
- Doxygen Implementation.
- GitHub Pages.
- GitHub Actions.
- New Server Unit Tests.
- New DB Unit Tests.
- Managing cafeteria supplies.
- Managing study rooms.
- Online order management.
- Checking online menu.
- Keeping DB inventory updated.
- Access to user history.

#### Estimation: 

![image](https://user-images.githubusercontent.com/62309228/169716955-742c9716-7d2e-4bf0-884d-a034b13ba9e2.png)

#### Burndown Chart:

![image](https://user-images.githubusercontent.com/62309228/169716980-5541328d-cb1c-4341-9c41-803232a52ca0.png)

#### Conclusions:

This time, as learned from previous sprints, we divided the work in more tasks so we could end them faster. We worked together to create new functionalities in the server and improved some GUIs. We also created tests for these new functionalities. Finally we documentated all the code and created this beautiful Readme.

## Technologies:

- Project developed using Java Maven.
- MySQL as the DB.
- JUnit, Contiperf and VisualVM for tests.
- Javadocs, Doxygen and GitHub Pages for documentation.
- GitHub and Youtrack as VCS (Version Control Systems). 

## How To Run:

First, compile the whole code:
1. mvn compile

Then, in three separate cmd windows, run:

1. mvn jetty:run
2. mvn exec:java -Pmanager
3. mvn exec:java -Pclient

## GUI:

A glance at some of our windows.

| Login Window: | Register Window: |
| ------------- | ------------- |
| ![image](https://user-images.githubusercontent.com/62309228/169717547-fb8ad379-6bef-43fe-b3b5-8a4e42b8ad6b.png) | ![image](https://user-images.githubusercontent.com/62309228/169717569-9db9d6a9-bbc0-4a5e-8c08-a93e73b7af14.png) |


#### Main Window:

![image](https://user-images.githubusercontent.com/62309228/169717583-c7463235-1ddb-410e-8230-f44d724b3df3.png)

#### User Information:

![image](https://user-images.githubusercontent.com/62309228/169717606-bb06864a-70e0-4342-ab42-55a745e877cd.png)

#### Admin Cafeteria Window:

![image](https://user-images.githubusercontent.com/62309228/169717647-d09fdb6d-42f8-4ea6-9b2b-8ddb2257e2fe.png)


#### 
## Final conclusions:

Overall we worked great as a team, we found the strengths of each one and helped each other when needed. A project like this has given us a view of how software development works in real life. We used a lot of interesting developing tools that will be very useful in our career.
