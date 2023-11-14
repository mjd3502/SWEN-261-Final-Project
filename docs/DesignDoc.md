---
geometry: margin=1in
---
# PROJECT Design Documentation

> _The following template provides the headings for your Design
> Documentation.  As you edit each section make sure you remove these
> commentary 'blockquotes'; the lines that start with a > character
> and appear in the generated PDF in italics._

## Team Information
* Team name: Sultans of Scrum

* Team members:
  * Garrett Geyer     |  GitHub Code Review PR
  * Michael DiBiase   |  Configuration Coordinator
  * Carla Lopez       |  Design Coordinator
  * Rachel Adkins     |  Team Coordinator
  * Cheyenne Zhang    |  Quality Coordinator

## Executive Summary

This is a summary of the project.

### Purpose
>  _**[Sprint 2 & 4]** Provide a very brief statement about the project and the most
> important user group and user goals._

The manager of the Ufund system can add, remove, and edit needs stored in their cupboard.
The Helper can browse through a list of needs and select individuals needs to support.

### Glossary and Acronyms
> _**[Sprint 2 & 4]** Provide a table of terms and acronyms._

| Term | Definition |
|------|------------|
| SPA | Single Page |
| Helper | A user using the application with intent to give support|


## Requirements

This section describes the features of the application.

> The MIA Foundation U Fund website focuses on providing both helpers of the organizations and managers an 
optimal way of collaborating for an important cause such us supporting animals in need.
> In our website the Administrator of the Funding website will be able to:
> Log in to the "Admin dashboard" to see the list of needs of their organization
>Add,remove, and edit the needs which are either goods or volunteer opportunities
> The helper will be able to:
>Log in to their "Helper" Dashboard to access the cupboard of the organization
> Search for a need by name
> A personalized funding basket where they can add/delete the needs they want to checkout

### Definition of MVP
>  _**[Sprint 2 & 4]** Provide a simple description of the Minimum Viable Product._

Each user logs in with their username, the Ufund manager can log in with the username 'admin'.
The manager can add, remove and change needs stored in their cupboard and a helper can view that cupboard and select needs to add to their funding basket, and checkout, funding their selected needs. 


### MVP Features
>  A user logs in with a username, going to their page
>  A U-fund Manager logs in with username 'admin'
>  A Helper can see a list of needs
>  A Helper can search through the list of needs
>  A Helper can add/remove a need from their Funding Basket
>  A Helper can Check-out and fund all needs in their Funding Basket
>  A U-fund Manager can add, remove, and edit the list of needs
>  A U-fund manager cannot see each user's funding basket

### Enhancements
> _**[Sprint 4]** Describe what enhancements you have implemented for the project._


## Application Domain

This section describes the application domain.

![Domain Model](domain-model-placeholder.png)

> _**[Sprint 2 & 4]** Provide a high-level overview of the domain for this application. You
> can discuss the more important domain entities and their relationship
> to each other._


## Architecture and Design

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture. 
**NOTE**: detailed diagrams are required in later sections of this document. (_When requested, replace this diagram with your **own** rendition and representations of sample classes of your system_.) 

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

The web application, is built using the Model–View–ViewModel (MVVM) architecture pattern. 

The Model stores the application data objects including any functionality to provide persistance. 

The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The ViewModel provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.

Both the ViewModel and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts with the web application.

> _Provide a summary of the application's user interface.  Describe, from the user's perspective, the flow of the pages in the web application._

> Our web application's user interface offers a clear and structured  for the user roles of Manager and Helper. All users can log in and access their personal "dashboard". If the user decides to have the role of "Helper", their personal page or dahsboard will display a list of available needs in the organization. Furthermore, they will be able to view and manage their Funding Basket ,which includes adding and removing needs. The Helper view also contains a feature which allows users to search needs by a specific name type and access a detail description of said need. 

> On the other, the 'admin" or " U-fund manager, logs in to an administrative dashboard where they can oversee and and modify the needs in their cupboard. 
> Both user roles have the opportunity to log out of the site whenever it is convenient.


### View Tier
> _**[Sprint 4]** Provide a summary of the View Tier UI of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

> _**[Sprint 4]** You must  provide at least **2 sequence diagrams** as is relevant to a particular aspects 
> of the design that you are describing.  (**For example**, in a shopping experience application you might create a 
> sequence diagram of a customer searching for an item and adding to their cart.)
> As these can span multiple tiers, be sure to include an relevant HTTP requests from the client-side to the server-side 
> to help illustrate the end-to-end flow._

> _**[Sprint 4]** To adequately show your system, you will need to present the **class diagrams** where relevant in your design. Some additional tips:_
 >* _Class diagrams only apply to the **ViewModel** and **Model** Tier_
>* _A single class diagram of the entire system will not be effective. You may start with one, but will be need to break it down into smaller sections to account for requirements of each of the Tier static models below._
 >* _Correct labeling of relationships with proper notation for the relationship type, multiplicities, and navigation information will be important._
 >* _Include other details such as attributes and method signatures that you think are needed to support the level of detail in your discussion._

### ViewModel Tier
> _**[Sprint 4]** Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

> _At appropriate places as part of this narrative provide **one** or more updated and **properly labeled**
> static models (UML class diagrams) with some details such as critical attributes and methods._
> 
![Replace with your ViewModel Tier class diagram 1, etc.](model-placeholder.png)

### Model Tier
> _**[Sprint 2, 3 & 4]** Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

> The model tier of our application contains 5 main entities:
- Need
- Funding Basket
- User
- Pet
- Favorite Pets

Each on of them has a specific function within the application.
The Need entity represents a good/volunteer opportunity that admin users can create and modify and that helper users can checkout to support the organization
Funding Basket entity represent a unique basket that associated to a a particular username.
User entity represents a helper user that logs in to the application. User are identified by a unique username. Furthermore, the Pet entity models a pet that a user can adpot through our organization. 
Favorite Pets entity reprensets the wishlist of potential adopted pets for a user.
The model tier forms the backbone of our application's architecture with the Need, Funding Basket and User entities playing distinct but interconnected roles. 


> _At appropriate places as part of this narrative provide **one** or more updated and **properly labeled**
> static models (UML class diagrams) with some details such as critical attributes and methods._
> 
![Replace with your Model Tier class diagram 1, etc.](model-placeholder.png)

## OO Design Principles
> _**[Sprint 2, 3 & 4]** Will eventually address upto **4 key OO Principles** in your final design. Follow guidance in augmenting those completed in previous Sprints as indicated to you by instructor. Be sure to include any diagrams (or clearly refer to ones elsewhere in your Tier sections above) to support your claims._

The MIA Foundation U Fund website has been thoughtfully designed to align with two fundamental Object-Oriented (OO) design principles: High Cohesion and the Single Responsibility Principle (SRP). These principles are reflected in both the frontend (Angular) and backend (Java) components and modules. 


  High Cohesion is evident in the design, where related responsibilities are grouped together within modules. For instance, in the Angular frontend, the navigation bar component has a specific purpose: facilitating user navigation. Initially, this navigation was included in various modules, but the team recognized that it deserved a module of its own. With this implementatin ,the navbar can be implemented in different components of the applicatin avoiding repetition.

  The Single Responsibility Principle (SRP) is well implemented, ensuring that each class or module has a single, well-defined responsibility. In the frontend, different components are dedicated to specific views or tasks. For example, the navigation bar component focuses solely on navigation, avoiding mixed responsibilities. This design principle is also evident in the backend, where user management and funding basket functionalities are separated into different controllers. Users are isolated in their primary task of logging in, while the Funding Basket Controller exclusively handles operations related to the basket, such as adding and removing needs.

  <Principle 3>

  
  <Principle 4>

By following these OO design principles, the MIA Foundation U Fund website has a maintainable and extensible code, enhancing the overall quality of the project.


> _**[Sprint 3 & 4]** OO Design Principles should span across **all tiers.**_

## Static Code Analysis/Future Design Improvements
> _**[Sprint 4]** With the results from the Static Code Analysis exercise, 
> **Identify 3-4** areas within your code that have been flagged by the Static Code 
> Analysis Tool (SonarQube) and provide your analysis and recommendations.  
> Include any relevant screenshot(s) with each area._

> _**[Sprint 4]** Discuss **future** refactoring and other design improvements your team would explore if the team had additional time._

## Testing
> _This section will provide information about the testing performed
> and the results of the testing._
### Acceptance Testing
> _**[Sprint 2 & 4]** Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._
19 user stories will be finished.

### Unit Testing and Code Coverage
> _**[Sprint 4]** Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets._

>_**[Sprint 2 & 4]** **Include images of your code coverage report.** If there are any anomalies, discuss
> those._


## Use of Postman API Platform for Sprint 1 RestAPI Demo
> The Postman API Platform will be used to manage and document the Restful API's of our application. Postman is a development tool which helps build, test and modify API's. Postman offers a usr-friendly graphical interface that makes it easier for the team of developers, as well as the product owner, to interact with API's. The team "Sultans of Scrum" has a "workspace" in which the Restful API's of our applciation will be stored. The workspace facilitates collaboration in the team by allowing us to share our collection of API's and maintain a record of them.

### Use of Sweet Alert Library
> The team of developers has chosen to incorporate the SweetAlert JavaScript library to enhance the user experience by offering visually appealing and user-friendly pop-up dialogs for web development. This choice not only elevates the user experience but also improves the presentation of important information and messages to users. 

### Use of Tailwind CSS
> Our decision to incorporate Tailwind CSS into our development stack was driven bu its effiency and flexibility to create UI components. The framework allowed us to rapidly build our front end without the need for extensive custom styling

> As a team with varying levels of experience using the framework, we found Tailwin'ds documentations and tutorials as very helpful resources. Following these tutorials enabled us to quickly grasp the fundamentals of Tailwind CSS and leverage its features effectively


