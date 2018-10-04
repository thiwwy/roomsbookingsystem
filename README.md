# Rooms booking system

### Some notes

- This API has been developed using spring boot.
- Based on the description of the exercise I decided to use the rooms as a container for meetings thinking about the fact that
for each room is desired to display the list of meetings.
- Through the API is not possible to manage rooms or employees but only meetings.
- There is not any validation related to Employees IDs provided, it might be added in future.
- Has been assumed that the system uses numeric ID unique on the specific entity. I.E. The ID 1 might be assigned to a room and a meeting.
- Agenda has been modeled as an object to offer more flexibility in case we don't want to consider an agenda a simple string.
- The swagger specification has been manually produced. Is it possible to automatically generate the specification. There are different opinions about
the fact that the contract should be provided before the development starts and not generated using the implementation. Is a tradeoff between maintainability of the contracts and have applications driven
by a contract and not the other way around.
- The swagger specification is not complete due to lack of time and expertise in the syntax I preferred to focus on other aspects of the implementation.
- The API is using an hashmap as a storage. Reason for that is that the data model to support the developed endpoints is very simple and doesn't require a relational database. Also this is only for demo purposes.
- The storage choice is not future proof but the API relies on a RoomService interface so it is possible to plug another storage type providing another implementation of RoomService.
- The unit tests are not covering all the cases and are focused mostly on testing the RoomServiceHashMap which is where most of the logic sits.
- Currently the booking works with timestamps since has been not defined the duration of a timeslot, for this reason is possible to book a room at any second. This is obviously unrealistic.
- Regarding the endpoints developed:
    - GET /rooms provides the IDs of all the rooms available.
    - GET /rooms/{id} provides the details of the selected room.
    - POST /rooms/{id}/meetings create a new meeting associated to the given room
    - PUT /rooms/{id}/meetings/{id} updates an existing meeting
    - GET /rooms/{id}/meetings has been not created but it might provide the list of meetings associated to a specific room
    - DELETE /rooms/{id}/meetings/{id} delete the selected meeting
This is only a partial implementation since some endpoints are missing. Also a different approach might be to associate to each meeting a room.
In that case the structure of the endpoints will drastically change.

