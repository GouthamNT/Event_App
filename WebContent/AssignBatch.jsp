<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <link rel="stylesheet" href="EventCreation.css">
        <script src="jquery-1.12.3.js"></script>
        <script src="mustache.js"></script>
    </head>
    <body>
        <div id=Main-content>
            <p id=heading>Assign Batch</p>
             <button type="button" id=AssignBatch>Test</button>
            <table id=input-attribute>
                
                <tr>
                    <td>Student List</td>
                    <td>:</td>
                    <td><select id=studentlist name=students multiple>
                        
                        </select></td>
                </tr>
            </table>
            <div id=button><button type="button" class="normal" id=assign>Assign</button></div>
            <div id=student-selected>
            
            </div>
        </div>
        <script src="AssignBatch.js"></script>
    </body>
</html>