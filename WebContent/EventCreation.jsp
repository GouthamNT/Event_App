<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="EventCreation.css">
    <script src="jquery-1.12.3.js"></script>
</head>
<body>
	<div id=Main-content>
		<p id=heading>Enter event Details</p>
		<form action="EventController" method="post">
		<input type=hidden name=action value=create>
			<table id=input-attribute>
				<tr>
					<td>Name</td>
					<td>:</td>
					<td><input type=text name=eventname></td>
				</tr>
				<tr>
					<td>Date</td>
					<td>:</td>
					<td><input type=date name=date></td>
				</tr>
				<tr>
					<td>Time</td>
					<td>:</td>
					<td><input type=time name=time></td>
				</tr>
				<tr>
					<td>Address</td>
					<td>:</td>
					<td><textarea name=address></textarea></td>
				</tr>
				<tr>
					<td>City</td>
					<td>:</td>
					<td><input type=text name=city data-type=letter></td>
				</tr>
				<tr>
					<td>Pin</td>
					<td>:</td>
					<td><input type=text name=pin data-type=number></td>
				</tr>
			</table>
			<div>
				<button type="button" class="normal">Create</button>
			</div>
		</form>
        <script src="Validator.js"></script>
        <script src="FormValidator.js"></script>
	</div>
</body>
</html>