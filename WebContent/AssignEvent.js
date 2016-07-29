(function () {
	var batch_content,
	action;

	function addEditEvent() {
		var edit = $('#input-attribute tbody tr td button[id^="edit_"]');
		$(edit).on('click', edit, function (event) {
			console.log(event);
			var index = $(event.currentTarget).attr("index");
			if ($(event.currentTarget).attr("id") != ("edit_"+index)) {
				return;
			}
			var checkUnsavedFlag = validator.checkUnsavedInputs();
			
			if(!checkUnsavedFlag) {
				makeBatchEditable(index);
				changeToSave(index);
				addCancelButton(index);
			} else {
				modal.showError('Only one data set can be edited at a time');
			}
		});
	}

	addEditEvent();

	function makeBatchEditable(index) {
		var batch = $('#batch_'+index),
		content = batch.text(),
		select_template = '<select id=batch_'+index+'></select>',
		option_template = '<option value={{batchid}}>{{batchid}}</option>';
		batch_content = content;
		batch.text("");
		batch.append(Mustache.to_html(select_template));
		var batchdata = {batchid:content};
		var option = batch.find("select#batch_"+index);
		$(option.append(Mustache.to_html(option_template,batchdata))).attr('selected');

		$.ajax({
			url:'FetchBatch',
			method:'GET',
			datatype:'json',
			success: function(batchlist) {
				if(batchlist.length > 0) {
					for(var i in batchlist) {
						if(batch_content != batchlist[i].batch) {
							batchdata = {batchid:batchlist[i].batch};
							option.append(Mustache.to_html(option_template,batchdata));

						}
					}
				}
			},
			error:function() {
				modal.showError('Problem Occured! Try again later');
			}
		});
	}

	function changeToSave(index) {
		var edit = $('#edit_'+index);
		edit.attr("id","save_"+index);
		edit.text("Save");
		addSaveEvent(index);
	}

	function addCancelButton(index) {
		var action_td = $('#input-attribute tbody tr td[id="action_'+index+'"]'),
		edit_template = '<button type=button id=cancel_'+ index +' class=small index='+ index +' style="margin-left:4px">Cancel</button>';
		action_td.append(Mustache.to_html(edit_template));
		action_td.find('button[id^=cancel]').on('click',function(){
			action = "cancel";
			saveBatchname(index);
			changeToEdit(index);
		});
	}

	function addSaveEvent(index) {
		var save = $('#input-attribute tbody tr td button[id="save_'+index+'"]'),
		status;
		$(save).on('click',save,function(event) {
			if($(event.currentTarget).attr("id") != ("save_"+index)) {
				return;
			}
			action = "save";
			updateDatabase(index);

		});
	}

	function saveBatchname(index) {
		var name = $("select[id='batch_"+index+"']"),
		content = name.val(),
		td_name = $("td[id='batch_"+index+"']");
		name.remove();
		if(action === "cancel") {
			td_name.text(batch_content);
		} else {
			td_name.text(content);
		}
		console.log(content);
	}

	function updateDatabase (index) {
		var data,
		row = $('#input-attribute tbody tr');

		var eventid = row.find('td#eventid_'+index).text(),
		batchid = row.find('select#batch_'+index).val();

		data = {
				eventid:eventid,
				batchid:batchid,    
		};

		$.ajax({
			url:"AssignEvent",
			method:"POST",
			data: data,
			datatype:'json',
			async:'false',
			success:function(data){
				if(data.length > 0) {
					addBatchError();
				}
				else {
					saveBatchname(index);
					changeToEdit(index);
				}
				modal.showSuccess('Event assigned to Batch successfully');
			},
			error:function() {
				modal.showError('Problem Occured! Try again later');
			}
		});
	}

	function addBatchError() {
		$('#Main-content p#error').text("Two Events can't have 2 events in same date");
	}

	function changeToEdit(index) {
		var save = $('#save_'+index);
		$(save).off();
		save.attr("id","edit_"+index);
		save.text("Edit");
		$('#cancel_'+index).remove();
		addEditEvent();
	}

	$('#AssignEvent').on('click',function() {
		$.ajax ({
			url:"FetchBatchMapping",
			method:"GET",
			datatype:"JSON",
			success: function(data) {
				console.log(data);
				addTable();
				for(var i in data) {
					data[i].index = i;
					addEventBatchData(data[i]);
					addEditEvent(i);
				}
			},
			error:function() {
				modal.showError('Problem Occured! Try again later');
			}
		});

		function addTable() {
            var table = $('#content-data'),
                headingtemplate = "<table id=input-attribute><thead><tr><td>Event ID</td><td>Event Name</td><td>Batch Assigned</td><td>Assign Batch</td></tr></thead><tbody></tbody></table>";
			var heading = Mustache.to_html(headingtemplate);
			table.html(heading);
		}

		function addEventBatchData(event) {
			var table = $('table#input-attribute tbody'),
			rowtemplate = "<tr><td id=eventid_{{index}}>{{eventid}}</td>"
				+ "<td id=eventname_{{index}}>{{name}}</td>"
				+ "<td id=batch_{{index}}>{{batch}}</td>"
				+ "<td id=action_{{index}}><button id=edit_{{index}} type=button class='small' index={{index}}>Edit</button></td>"
				+ "<td></tr>";
			var row = Mustache.to_html(rowtemplate,event);
			table.append(row);
		}

	});
})();
