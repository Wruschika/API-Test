
Feature: Add/update/delete/retrive user using User API

Scenario: Verify user is added using api
	Given Add user API and Payload
	When user calls "api" with post request
	Then API call success with status code 201 


	Scenario: Verify list of user
	Given Get user API
	When user calls "api" with get request
	Then API call success with status code equal 200 
	
	
	Scenario: Verify user is getting updated
	Given update user API and id
	When user calls "api" with put request
	Then API call success with status code as 200
	
	Scenario: Verify user is getting deleted
	Given delete user API and id
	When user calls "api" with delete request
	Then API call success with status code is 204