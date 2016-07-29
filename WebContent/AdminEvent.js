(function() {
    var event_content,
        date_content,
        time_content,
        address_content,
        city_content,
        pin_content,
        action;

    function addEditEvent() {
        var edit = $('#input-attribute tbody tr td button[id^="edit_"]');
        $(edit).on('click',edit,function(event) {
            console.log(event);
            var index = $(event.currentTarget).attr("index");
            if($(event.currentTarget).attr("id") != ("edit_"+index)) {
                return;
            }
            var unsavedFlag = validator.checkUnsavedInputs();
            
            if(!unsavedFlag) {
                makeEventNameEditable(index);
                makeDateEditable(index);
                makeTimeEditable(index);
                makeAddressEditable(index);
                makeCityEditable(index);
                makePinEditable(index);
                changeToSave(index);
                addCancelButton(index);
            } else {
            	modal.showError('Only one data set can be edited at a time');
            }
        });
    }

    addEditEvent();

    function makeEventNameEditable(index) {
        var name = $('#eventname_'+index);
        var content = name.text();
        event_content = content;
        name.text("");
        name.append($('<input>').attr({"id":"eventname_"+index,"type":"text"}));
        name.find("input").val(content);
    }

    function makeDateEditable(index) {
        var date = $('#date_'+index),
            content = date.text();
        date_content = content;
        date.text("");
        date.append($('<input>').attr({"id":"date_"+index,"type":"date"}));
        content = changeDateFormatComputer(content);
        date.find("input").val(content);
    }

    function makeTimeEditable(index) {
        var time = $('#time_'+index),
            content = time.text();
        time_content = content;
        time.text("");
        time.append($('<input>').attr({"id":"time_"+index,"type":"time"}));
        console.log(content);
        /*content = changeDateFormatComputer(content);
        console.log(content);*/
        time.find("input").val(content);
    }

    function makeAddressEditable(index) {
        var address = $('#address_'+index),
            content = address.text();
        address_content = content;
        address.text("");
        address.append($('<textarea>').attr({id:"address_"+index}));
        address.find("textarea").text(content);
    }

    function makeCityEditable(index) {
        var city = $('#city_'+index),
            content = city.text();
        city_content = content;
        city.text("");
        city.append($('<input>').attr({
            id:"city_"+index,
            "type":"text",
            "data-type":"letter"
        }));
        city.find("input").val(content);
    }

    function makePinEditable(index) {
        var pin = $('#pin_'+index),
            content = pin.text();
        pin_content = content;
        pin.text("");
        pin.append($('<input>').attr({
            id:"pin_"+index,
            type:"text",
            "data-type":"number"
        }));
        pin.find("input").val(content);
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
            saveEventname(index);
            saveDate(index);
            saveTime(index);
            saveAddress(index);
            saveCity(index);
            savePin(index);
            changeToEdit(index);
        });
    }

    function addSaveEvent(index) {
        var save = $('#input-attribute tbody tr td button[id="save_'+index+'"]');
        $(save).on('click',save,function(event) {
            if($(event.currentTarget).attr("id") != ("save_"+index)) {
                return;
            }
            var inputlist = $('input');
            inputlist.push($('textarea'));
            var checkvalid = validator.validateField(inputlist);
            if(checkvalid) {
                action = "save";
                updateDatabase(index);
                
            }
        });
    }

    function saveEventname(index) {
        var name = $("input[id='eventname_"+index+"']"),
            content = name.val(),
            td_name = $("td[id='eventname_"+index+"']");
        name.remove();
        if(action === "cancel") {
            td_name.text(event_content);
        } else {
            td_name.text(content);
        }
        console.log(content);
    }

    function saveDate(index) {
        var date = $("input[id='date_"+index+"']"),
            content = date.val(),
            td_date = $("td[id='date_"+index+"']");
        date.remove();
        if(action === "cancel") {
            td_date.text(date_content);
        } else {
            content = changeDateFormatHuman(content);
            td_date.text(content);
        }
        console.log(content);
    }

    function changeDateFormatHuman(date) {
        var date_components = date.split("-"),
            year = date_components[0],
            month = date_components[1],
            day = date_components[2];

        return (day+'-'+month+'-'+year);
    }

    function changeDateFormatComputer(date) {
        var date_components = date.split("-"),
            day = date_components[0],
            month = date_components[1],
            year = date_components[2];

        return (year+'-'+month+'-'+day);
    }

    function saveTime(index) {
        var time = $("input#time_"+index),
            content = time.val(),
            td_time = $("td#time_"+index);
        console.log(content);
        time.remove();
        if(action === "cancel") {
            td_time.text(time_content);
        } else {
            td_time.text(content);
        }
    }

    function saveAddress(index) {
        var address = $("textarea#address_"+index),
            content = address.val(),
            td_address = $("td#address_"+index);
        console.log(content);
        address.remove();
        if(action === "cancel") {
            td_address.text(address_content);
        } else {
            td_address.text(content);
        }
    }

    function saveCity(index) {
        var city = $("input#city_"+index),
            content = city.val(),
            td_city = $("td#city_"+index);
        console.log(content);
        city.remove();
        if(action === "cancel") {
            td_city.text(city_content);
        } else {
            td_city.text(content);
        }
    }

    function savePin(index) {
        var pin = $("input#pin_"+index),
            content = pin.val(),
            td_pin = $("td#pin_"+index);
        console.log(content);
        pin.remove();
        if(action === "cancel") {
            td_pin.text(pin_content);
        } else {
            td_pin.text(content);
        }
    }
    
    function updateDatabase (index) {
        var data,
            row = $('#input-attribute tbody tr');

        var eventname = row.find('td#eventname_'+index+' input').val(),
            eventid = $(row[index]).attr('data-event_'+index),
            date = row.find('td#date_'+index+' input').val(),
            time = row.find('td#time_'+index+' input').val(),
            address = row.find('td#address_'+index).text(),
            city = row.find('td#city_'+index+' input').val(),
            pin = row.find('td#pin_'+index+' input').val();
        
        data = {
            action:"update",
            eventname:eventname,
            eventid:eventid,
            date:date,
            time:time,
            address:address,
            city:city,
            pin:pin
        }
        
        $.ajax({
            url:"UpdateEvent",
            method:"POST",
            data:data,
            datatype:'json',
            success:function(){
                modal.showSuccess('Event Updated Successfully');
                saveEventname(index);
                saveDate(index);
                saveTime(index);
                saveAddress(index);
                saveCity(index);
                savePin(index);
                changeToEdit(index);
            },
            error:function() {
                modal.showError("Problem Occurred! Try again later");
            }
        });
    }
    
    function viewSessionEvent(key) {
        $('button#view_'+key).on('click',function(){
            var eventid = $(this).attr('data-event-id'),
                eventname = $(this).attr('data-eventname'),
                field = '<input type=hidden name=event value="' + eventid + '">'
            + '<input type=hidden name=eventname value="' + eventname + '">';
            $('form#ViewSession').append(field);
            $('form#ViewSession').submit();
        });
    }
    
    function deleteEvent(key) {
        $('button#delete_'+key).on('click',function(){
            var eventid = $(this).attr('data-event-id'),
                eventDetails = $(this);
            $.ajax({
                url:"DeleteEvent",
                method:"POST",
                data: {
                    event:eventid
                },
                success:function(){
                    $(eventDetails).parent().parent().remove();
                    modal.showSuccess('Event Deleted Successfully');
                },
                error:function() {
                    modal.showError("Problem Occurred! Try again later");
                }
            });
        });
    }
    
    function addSessionEvent(key) {
        $('button#add_'+key).on('click',function(){
            var eventid = $(this).attr('data-event-id'),
                eventname = $(this).attr('data-eventname'),
                field = '<input type=hidden name=event value="' + eventid + '">'
            + '<input type=hidden name=eventname value="' + eventname + '">';
            $('form#AddSession').append(field);
            $('form#AddSession').submit();
        });
    }

    function changeToEdit(index) {
        var save = $('#save_'+index);
        $(save).off();
        save.attr("id","edit_"+index);
        save.text("Edit");
        $('#cancel_'+index).remove();
        addEditEvent();
    }

    $('#AdminEvent').on('click',function() {
        $.ajax ({
            url:"EventController",
            method:"GET",
            data: {
                fetch:"event"
            },
            datatype:"JSON",
            success: function(data) {
                console.log(data);
                addTable();
                for(var i in data) {
                    data[i].index = i;
                    addEventData(data[i]);
                    addEvents(i);
                }
            },
            error:function(xhr,status,error) {
                modal.showError("Problem Occurred! Try again later");
            }
        });
        
        function addEvents(key) {
            viewSessionEvent(key);
            deleteEvent(key);
            addSessionEvent(key);
        }
        
        

        function addTable() {
            var table = $('#content-data'),
                headingtemplate = "<form id=AddSession action=AddSession></form><form id=ViewSession action=ViewSession></form><table id=input-attribute><thead><tr><td>Name</td><td>Date</td><td>Time</td><td>Address</td><td>City</td><td>Pin</td><td>Batch</td><td>Edit Event</td><td>Delete Event</td><td>Session Details</td></tr></thead><tbody></tbody></table>";
            var heading = Mustache.to_html(headingtemplate);
            table.html(heading);
        }

        function addEventData(event) {
            var table = $('table#input-attribute tbody'),
                rowtemplate = "<tr data-event_{{index}}={{eventid}}><td id=eventname_{{index}}>{{name}}</td>"
                        + "<td id=date_{{index}}>{{date}}</td>"
                        + "<td id=time_{{index}}>{{time}}</td>"
                        + "<td id=address_{{index}}>{{address}}</td>"
                        + "<td id=city_{{index}}>{{city}}</td>"
                        + "<td id=pin_{{index}}>{{pin}}</td>"
                        + "<td>{{batch}}</td>"
                        + "<td id=action_{{index}}><button id=edit_{{index}} type=button class='small' index={{index}} >Edit</button></td>"
                        + "<td><button id=delete_{{index}} type=button class='small' data-event-id={{eventid}}>Delete</button></td>"
                        + "<td><span><button id=view_{{index}} type=button class='small' name=button data-event-id={{eventid}} data-eventname={{name}}>View</button></span>"
                        + "<span><button id=add_{{index}} type=button class='small' name=button data-event-id={{eventid}} data-eventname={{name}} style='margin-left:4px'>Add</button></span></td></tr>";

            event.date = changeDateFormatHuman(event.date);
            var row = Mustache.to_html(rowtemplate,event);
            table.append(row);
            addEditEvent();
        }

    });
})();
    