<!DOCTYPE html>
<html>
    <head>
        <title>Session Details</title>
        <link rel="stylesheet" href="EventCreation.css">
        <script src="jquery-1.12.3.js"></script>
        <script src="mustache.js"></script>
    </head>
    <body>
        <div id=Main-content>
            <p id=heading>List of Session</p>
            <button type="button" id=test>test</button>
            <p id=error></p>
            <form action=SessionController>
            <table id=input-attribute>
                <thead>
                    <tr>
                        <td>Event ID</td>
                        <td>Event Name</td>
                        <td>Batch Assigned</td>
                        <td>Assign Batch</td>
                    </tr>
                </thead>
                <tbody>
                <tr>
                    <td id=eventid_0>Test</td>
                    <td id=eventname_0>Anup</td>
                    <td id=batch_0>2 Hrs</td>
                    <td id=action_0><button id=edit_0 type=button index=0 class="small">Edit</button></td>
                </tr>
                <tr>
                    <td id=eventid_1>Test2</td>
                    <td id=eventname_1>Anup2</td>
                    <td id=batch_1>2 Hrs2</td>
                    <td id=action_1><button id=edit_1 type=button index=1 class="small">Edit</button></td>
                </tr>
                </tbody>
            </table>
            </form>
        <script src="AssignEvent.js"></script>
        </div>
    </body>
</html>