(function () {
	var assign = $('#assign'),
	studentlist_selected = $('#student-selected'),
	index,
	row = "<tr></tr>",
	cell = "<td></td>",
	button = "<button></button>",
	options;

	function initindex() {
		index = 0;
	}

	function updateindex(index) {
		this.index = index;
	}
    
    function initialize() {
        $('#content-data').remove();
        $('#Main-content').append('<div id=content-data>');
        var table_exist = $('#student-table'),
        selectoptions = $('select#studentlist option');
        if(table_exist.length > 0 || selectoptions.length > 0) {
            table_exist.remove();
            selectoptions.remove();
            $('#button.temp').remove();
            $('p#sub-heading').remove();
            $('p#error').remove();
            if($('div#success').length > 0) {
                $('div#success').remove();
            }
            initindex();
        }
        addTable();
    }
    
    function addTable() {
        $('#button').remove();
        var template = '<table id=input-attribute>' 
                + '<tr>'
                +   '<td>Student List</td>'
                +   '<td>:</td>'
                +   '<td><select id=studentlist name=students multiple>'
                +   '</select></td>'
                +   '</tr>'
                +   '</table>'
                + '<div id=button><button type="button" class="normal" id=assign>Assign</button></div>'
                +   '<div id=student-selected></div>';
        
        $('#content-data').html(Mustache.to_html(template));
        assign = $('button#assign');
        studentlist_selected = $('#student-selected');
        initializeAssignEvent();
    }
    
    function populate() {
        
        initialize();
        
		$.ajax({
			url:"StudentController",
			method:"GET",
			datatype:"JSON",
			success: function(data) {
				var optiontemplate = '<option value={{name}}-{{username}}>{{name}}-{{username}}</option>';
				console.log(data[0]);
				if(Array.isArray(data)) {
					for(var i in data) {
						console.log(data[i]);
						var options = Mustache.to_html(optiontemplate,data[i]);
						$('select#studentlist').append(options);
					}
				} else {
					$('select#studentlist').remove();
                    $('select#studentlist').remove();
					$('#Main-content').append($('p').attr('id','error').text(data.error));
				}
			},
			error: function(xhr,status,error) {
				modal.showError(xhr.responseText);
			}
		});
	}
    
    $('button#AssignBatch').on('click',populate);
	initindex();
    function initializeAssignEvent() {
	assign.on('click',function() {
		var studenttable = $('#student-table tbody');
		var select = $('#studentlist'),
		list = select.val(),
		theadtemplate = "<thead><tr><td>No</td><td>Student Name</td><td>Remove Student</td></tr></thead>";
		template = "<tr id=row_{{index}}><td>{{no}}</td><td id=name_{{index}}>{{name}}</td><td><button id=remove_{{index}} class=small index={{index}}>remove</button></td></tr>",
		button = "<div id=button class=temp><button type=button id=confirm class=normal>Confirm</button></div>"; 
        if(list.length > 0) {
            if(studenttable.length == 0){
                $(studentlist_selected).append($("<p>").attr("id","sub-heading").append($("<h4>").text("Students Assigned")));
                $(studentlist_selected).append($("<table>").attr("id","student-table"));
                studenttable = $(studentlist_selected).find('table#student-table');
                var thead = Mustache.to_html(theadtemplate);
                $(studenttable).append(thead);
            }
            for(var i in list) {
                var data = {
                        no:index+1,
                        name:list[i],
                        index:index
                };
                var row = Mustache.to_html(template,data);
                $(studenttable).append(row);
                studenttable = $(studentlist_selected).find('table#student-table');
                removeButtonEvent(index);
                index++;
            }
            if(($('button#confirm')).length == 0) {
                createConfirmButton();
            }
        }
		function createConfirmButton() {
			var confirm = Mustache.to_html(button);
			$('#Main-content').append(confirm);
			confirmEvent();
		}
		removeSelected(list);

		function confirmEvent() {
			$('button#confirm').on('click',function(){
				var namelist = new Array(),
				datalist = $('table#student-table tbody tr td[id^=name]');
				for(var i = 0; i < datalist.length; i++) {
					namelist.push($(datalist[i]).text());
				}
				$.ajax({
					url:"StudentBatch",
					method:"POST",
                    dataType:'json',
					data:{
                        action:"assign",
                        studentname:namelist
                    },
					success:function(success){
                        var successtemplate = '<div id=success><p>Students assigned to {{batch}}</p><div>',
                            successhtml = Mustache.to_html(successtemplate,success);
                        populate();
                        $('body').append(successhtml);
                        modal.showSuccess('Students allocated to Batch successfully');
					},
					error:function() {
                        modal.showError('Problem Occured! Try again later');
					}
				});
			});
		}

		function removeSelected(list) {
			var j = 0,
			flag = true;
			while(flag) {
				options = $('select#studentlist').find('option');
				for(var i in options) {
					var temp = $(options[i]).val();
					console.log(temp);
					if(temp == list[j]) {
						$(options[i]).remove();
						j++;
						if(j == list.length) {
							flag = false;
						}
						break;
					}
				}
			}
		}

		function removeButtonEvent(index) {
			var remove = $('button#remove_'+index);

			remove.on('click',function(event){
				var index = $(event.currentTarget).attr("index"),
				value = $('table tbody tr td#name_'+index).text();
				addBackToSelect(value);
				var sno = sno = $('table#student-table tbody tr');
				$('table tbody tr#row_'+index).remove();
				adjustSNO(sno);
			});

			function addBackToSelect(value) {
				$(select).append($('<option>').val(value).text(value));
			}

			function adjustSNO(sno) {
				var no = $('table#student-table tbody tr td:first-child');
				$.each(sno, function(key , value) {
					$(value).text(++i);
				});
                if(sno.length == 1) {
                    initindex();
                }
			}
		}

	});
    }
})();