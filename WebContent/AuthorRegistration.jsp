<!DOCTYPE html>
<html>
    <head>
        <title>Author Registration</title>
        <link rel="stylesheet" href="EventCreation.css">
        <script src="jquery-1.12.3.js"></script>
        <script src="mustache.js"></script>
    </head>
    <body>
        <div id=Main-content>
            <p id=heading>Enter the Author Details</p>
            <form action="AuthorController" method="post">
            <input type=hidden name=action value=create>
            <table id=input-attribute>
                <tr>
                    <td>Name</td>
                    <td>:</td>
                    <td><input type=text name=name data-type=letter></td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td>:</td>
                    <td><input type=text name=age data-type=number></td>
                </tr>
                <tr>
                    <td>Experience (in years)</td>
                    <td>:</td>
                    <td><input type=text name=experience data-type=number></td>
                </tr>
                <tr>
                    <td>E-mail ID</td>
                    <td>:</td>
                    <td><input type=text name=email data-type=email></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>:</td>
                    <td><textarea name=address></textarea></td>
                </tr>
            </table>
        <div id=button><button type="button" class="normal" id=create>Create</button></div>
        </form>
            <script src="Validator.js"></script>
            <script src="FormValidator.js"></script>
        </div>
    </body>
</html>