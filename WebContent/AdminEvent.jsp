<!DOCTYPE html>
<html>
    <head>
        <title>Create Session</title>
        <link rel="stylesheet" href="EventCreation.css">
        <script src="jquery-1.12.3.js"></script>
        <script src="mustache.js"></script>
    </head>
    <body>
        <div id=Main-content>
            <p id=heading>List of Events</p>
            <button type="button" id=AdminEvent>test</button>
            <form action=SessionController>
            <table id=input-attribute>
                <thead>
                    <tr>
                        <td>Name</td>
                        <td>Date</td>
                        <td>Time</td>
                        <td>Address</td>
                        <td>City</td>
                        <td>Pin</td>
                        <td>Edit Event</td>
                        <td>Delete Event</td>
                        <td>Session Details</td>
                    </tr>
                </thead>
                <tbody>
                <tr data-event_0=EVT-001>
                    <td id=eventname_0>Test</td>
                    <td id=date_0>10-01-2016</td>
                    <td id=time_0>10:30</td>
                    <td id=address_0>Test</td>
                    <td id=city_0>Chennai</td>
                    <td id=pin_0>600063</td>
                    <td id=action_0><button id=edit_0 type=button index=0 class="small">Edit</button></td>
                    <td><button type=button class="small">Delete</button></td>
                    <td><span><button type=button class="small" name=button>View</button></span>
                    <span><button type=button class="small" name=button>Add</button></span></td>
                </tr>
                <tr data-event_1=EVT-002>
                   <td id=eventname_1>Test</td>
                    <td id=date_1>dd-MM-yyyy</td>
                    <td id=time_1>10:30 PM</td>
                    <td id=address_1>Test</td>
                    <td id=city_1>Chennai</td>
                    <td id=pin_1>600063</td>
                    <td id=action_1><button id=edit_1 type=button index=1 class="small">Edit</button></td>
                    <td><button type=button class="small">Delete</button></td>
                    <td><span><button type=button class="small" name=button>View</button></span>
                    <span><button type=button class="small" name=button>Add</button></span></td>
                </tr>
                </tbody>
            </table>
            </form>
        <script src="Validator.js"></script>
        <script src="AdminEvent.js"></script>
        <script src="Template.js"></script>
        <script src="Modal.js"></script>
        </div>
    </body>
</html>