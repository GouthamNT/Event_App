(function () {
    var title_content,
        author_content,
        author_contentid,
        duration_content,
        description_content,
        action;

    function editEvent() {
        var edit = $('#input-attribute tbody tr td button[id^="edit_"]');
        $(edit).on('click',edit,function(event) {
            console.log(event);
            var index = $(event.currentTarget).attr("index");
            if($(event.currentTarget).attr("id") != ("edit_"+index)) {
                return;
            }
            var unsavedFlag = validator.checkUnsavedInputs();
            
            if(!unsavedFlag) {
                makeTitleEditable(index);
                makeAuthorEditable(index);
                makeDurationEditable(index);
                makeDescriptionEditable(index);
                changeToSave(index);
            } else {
            	modal.showError('Only one data set can be edited at a time');
            }
        });
    }
    
    function deleteSession() {
        var del = $('#input-attribute tbody tr td button[id^="delete_"]'),
        	sessionid,
            field;
        $(del).on('click',function(){
        	var index = $(this).attr("index");
            sessionid = $(this).parent().parent().attr('data-session_'+index),
            sessionDetails = $(this);
            $.ajax({
                url:"DeleteSession",
                method:"POST",
                data: {
                    session:sessionid
                },
                success:function(){
                    $(sessionDetails).parent().parent().remove();
                    modal.showSuccess('Session Deleted Successfully');
                },
                error:function() {
                    modal.showError("Problem Occurred! Try again later");
                }
            });
        });
    }
    
    editEvent();
    deleteSession();

    function makeTitleEditable(index) {
        var title = $('#title_'+index);
        var content = title.text();
        title_content = content;        
        title.text("");
        title.append($('<input>').attr({"id":"title_"+index,"type":"text"}));
        title.find("input").val(content);
    }
    
    function makeAuthorEditable(index) {
        var author = $('#author_'+index),
            content = author.text(),
            contentid = author.attr('data-id'),
            select_template = '<select id=author_'+index+'></select>',
            option_template = '<option value={{authorid}}>{{authorname}}</option>';
        author_content = content;
        author_contentid = contentid;
        author.text("");
        author.append(Mustache.to_html(select_template));
        var authordata = {
            authorid:contentid,
            authorname:content
        };
        var option = author.find("select#author_"+index);
        
        $.ajax({
            url:'AuthorOptions',
            method:'GET',
            datatype:'json',
            data: {
            	action:"getoption"
            },
            success: function(authorlist) {
                if(authorlist.length > 0) {
                    for(var i in authorlist) {
                        authordata = {
                            authorid:authorlist[i].id,
                            authorname:authorlist[i].name
                        };
                        if(authordata.authorid == contentid) {
                            $(option.append(Mustache.to_html(option_template,authordata)).find('option')[i]).attr('selected','selected');
                        } else {
                            option.append(Mustache.to_html(option_template,authordata));
                        }
                    }
                }
            }
        });
        
    }
    
    function makeDurationEditable(index) {
        var duration = $('#duration_'+index),
            content = duration.text();
        duration_content = content;
        duration.text("");
        duration.append($('<input>').attr({"id":"duration_"+index,"type":"text"}));
        duration.find("input").val(content);
    }


    function makeDescriptionEditable(index) {
        var description = $('#description_'+index),
            content = description.text();
        description_content = content;
        description.text("");
        description.append($('<textarea>').attr({id:"description_"+index}));
        description.find("textarea").text(content);
    }

    function changeToSave(index) {
        var edit = $('#edit_'+index);
        edit.attr("id","save_"+index);
        edit.text("Save");
        addSaveEvent(index);
        addCancelButton(index);
    }
    
    function addCancelButton(index) {
        var action_td = $('#input-attribute tbody tr td[id="action_'+index+'"]'),
            edit_template = '<button type=button id=cancel_'+ index +' class=small index='+ index +' style="margin-left:4px">Cancel</button>';
        action_td.append(Mustache.to_html(edit_template));
        action_td.find('button[id^=cancel]').on('click',function(){
            action = "cancel";
            saveTitle(index);
            saveAuthor(index);
            saveDuration(index);
            saveDescription(index);
            changeToEdit(index);
        });
    }

    function addSaveEvent(index) { 
        var save = $('#input-attribute tbody tr td button[id="save_'+index+'"]'),
            sessionid;
        
        $(save).on('click',save,function(event) {
            if($(event.currentTarget).attr("id") != ("save_"+index)) {
                return;
            }
            sessionid = $(this).parent().parent().attr('data-session_'+index);
            action = "save";
            var inputlist = $('input');
            inputlist.push($('textarea'));
            var checkvalid = validator.validateField(inputlist);
            if(checkvalid) {
                updateDatabase(index);
            }
        });
        
        function updateDatabase (index) {
            var data;

            var title = $('input#title_'+index).val(),
                author = $('select#author_'+index).val(),
                duration = $('input#duration_'+index).val(),
                description = $('textarea#description_'+index).val();

            data = {
                action:"update",
                session:sessionid,
                title:title,
                authorid:author,
                duration:duration,
                description:description
            }

            $.ajax({
                url:"EditSession",
                method:"POST",
                data:data,
                datatype:'json',
                success:function(){
                    saveTitle(index);
                    saveAuthor(index);
                    saveDuration(index);
                    saveDescription(index);
                    changeToEdit(index);
                },
                error:function() {
                    modal.showError("Problem Occured!! Try again later")
                }
            });
        }
    }

    function saveTitle(index) {
        var title = $("input[id='title_"+index+"']"),
            content = title.val(),
            td_title = $("td[id='title_"+index+"']");
        title.remove();
        if(action === "cancel") {
            td_title.text(title_content);
        } else {
            td_title.text(content);
        }
        console.log(content);
    }

    function saveDuration(index) {
        var duration = $("input#duration_"+index),
            content = duration.val(),
            td_duration = $("td#duration_"+index);
        console.log(content);
        duration.remove();
        if(action === "cancel") {
            td_duration.text(duration_content);
        } else {
            td_duration.text(content);
        }
    }
    
    function saveAuthor(index) {
        var author = $("select#author_"+index),
            contentid = author.val(),
            content = $("option[value="+contentid+"]").text(),
            td_author = $("td#author_"+index);
        console.log(content);
        author.remove();
        if(action === "cancel") {
            td_author.attr('data-id',author_contentid);
            td_author.text(author_content);
        } else {
            td_author.attr('data-id',contentid);
            td_author.text(content);
        }
    }

    function saveDescription(index) {
        var description = $("textarea#description_"+index),
            content = description.val(),
            td_description = $("td#description_"+index);
        console.log(content);
        description.remove();
        if(action === "cancel") {
            td_description.text(description_content);
        } else {
            td_description.text(content);
        }
    }

    function changeToEdit(index) {
        var save = $('#save_'+index);
        $(save).off();
        save.attr("id","edit_"+index);
        save.text("Edit");
        $('#cancel_'+index).remove();
        editEvent(index);
    }

})();
    