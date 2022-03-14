Feature: Create activity
Scenario: Registre activities and expected time

	Given that there is a Project manager or a developer that wants to create an activity
	When a new project is created you need activities
	And they need to say expected time
	Then the system refers to a list of available developers

Scenario: There has been created more than 50 activities for a single project

	Given that there is a Project manager or a developer that wants to create an activity
	When a new project is created you need activities
	And they need to say expected time
	And the system keeps track of already created activities of the project
	Then the system asks the creater of the project if that many activites is needed

Scenario: There has not been given an estimated time for activity

	Given that there is a Project manager or a developer that wants to create an activity
	When a new project is created you need activities
	And they need to say expected time
	And the system keeps track of already created activities of the project
	Then the system asks the the creater of the activity about estimated time



Feature: Registre work hours
Scenario: Registre activities and expected time

	Given that there is a developer that needs to report time spent on project
	When a week day is finished
	And they should be able to change the registrated time
	And the project manager should be able to see the registrated time
	Then the system does nothing


Scenario: A developer has not registrated work hours

	Given that there is a developer that needs to report time spent on project
	When a week day is finished
	And they should be able to change the registrated time
	And the project manager should be able to see the registrated time
	Then the system sends a message to the developer that he needs to registre working hours for the day

Scenario: A developer is changing the registrated work hours

	Given that there is a developer that needs to report time spent on project
	When a week day is finished
	And they should be able to change the registrated time
	And the project manager should be able to see the registrated time
	Then the system asks the developer if he on purpose is changing the registrated hours





	
