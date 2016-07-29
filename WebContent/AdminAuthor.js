(function () {
    var index = 0;
    

    $('#AuthorList').on('click',populate) 
    
    function populate() {
        
        $.ajax({
            url:"AuthorController",
            method:"GET",
            datatype:"json",
            data:{
            	action:"viewauthor"
            },
            success:function(data) {
                addTable();
                $.each(data,function(key,data){
                    data.index = index;
                    insertRow(data);
                    submitform(index);
                    index++;
                });
            }
        });
    }
    
    function addTable() {
        var table = $('#content-data'),
            headingtemplate = "<table id=input-attribute><thead><tr><td>Name</td>"
                +   "<td>Age</td>"
                +   "<td>Experience</td>"
                +   "<td>E-Mail ID</td>"
                +   "<td>Address</td>"
                +   "<td>Rating</td>"
                +   "<td>Edit</td>"
                +   "</tr></thead><tbody></tbody></table>";
		var heading = Mustache.to_html(headingtemplate);
		table.html(heading);
    }
    
    function insertRow(data) {
        var table = $('table#input-attribute tbody'),
			rowtemplate = "<tr data-author_{{index}}={{id}}><td id=name_{{index}}>{{name}}</td>"
				+ "<td id=age_{{index}}>{{age}}</td>"
				+ "<td id=experience_{{index}}>{{exp}}</td>"
                + "<td id=email_{{index}}>{{email}}</td>"
                + "<td id=address_{{index}}>{{address}}</td>"
                + "<td id=rating_{{index}}>{{rating}}</td>"
				+ "<td id=action_{{index}}><form action=EditAuthor><button id=edit_{{index}} type=button class='small' index={{index}}>Edit</button></form></td>"
                + "</tr>";
		var row = Mustache.to_html(rowtemplate,data);
        table.append(row);
    }
    
    function submitform(index) {
        $('#edit_'+index).on('click', function() {
            var id = $(this).attr('index');
            var tr = $('table#input-attribute tbody tr'),
                authorid;
            $.each(tr, function(key,value) {
                if(key == id) {
                    authorid = $(value).attr('data-author_'+id);
                }
            });          
            var hidden = '<input type=hidden name=authorid value="'+authorid+'">';
            $('form').append(hidden);
            $('form').submit();
        });
    }
    
})();
    