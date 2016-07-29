<!DOCTYPE html>
<html>
<head>
<title>Create Session</title>
<link rel="stylesheet" href="EventCreation.css">
<link rel="stylesheet" href="Index.css">
<script src="jquery-1.12.3.js"></script>
<script src="mustache.js"></script>
</head>
<body>
	<div id=Main-content>
		<div id=content-header>
			<ul>
				<li><button id=Home class="active">HOME</button></li>
				<li><button id=AdminEvent>EVENTS</button></li>
				<li><button id=AssignBatch>ASSIGN BATCH</button></li>
				<li><button id=AssignEvent>ASSIGN EVENT</button></li>
				<li><button id=AuthorList>AUTHORS</button></li>
			</ul>
		</div>
		<div id=content-data></div>


		<script src="Template.js"></script>
        <script src="Modal.js"></script>
		<script src="Validator.js"></script>
		<script src="AdminEvent.js"></script>
		<script src="AssignBatch.js"></script>
		<script src="AssignEvent.js"></script>
		<script src="AdminAuthor.js"></script>
		<script src="AdminIndex.js"></script>
	</div>
</body>
</html>