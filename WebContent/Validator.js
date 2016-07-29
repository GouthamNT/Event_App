var validator = (function() {
	var inputfield,
        content,
        flag;
	return {
		validateField: function (input) {
            flag = true;
            validator.removeError();
            validator.removeErrorHighlight();
			$.each(input,function(index, value){
				inputfield = $(value);
				var type = inputfield.attr("data-type");
                if(type != "hidden") {
                    content = inputfield.val();
                    var blank = validator.isNotBlank();
                    if(blank) {
                        if(type === 'letter') {
                            validator.validateText();
                        } else if (type === 'number') {
                            validator.validateNumber();
                        }
                    }
                }
            });
            return flag;
		},

		validateText: function () {
            var pattern = /[^A-z]/g;
            var result = content.match(pattern);
            var fieldname = inputfield.attr("name");
            if(result == null) {
                result = "";
            }
			if(result.length > 0) {
				inputfield.addClass("error-highlight");
                if(fieldname != undefined) {
				    inputfield.after("<div>").next().text(fieldname+" can contain only aphabets").addClass("error");
                } else {
                    inputfield.after("<div>").next().text("Must be alphabetic").addClass("error").attr("style","display:block");
                }
                flag = false;
			} else {

			}
		},
        
        validateNumber: function() {
			if(!($.isNumeric(content))) {
				inputfield.addClass("error-highlight");
                var fieldname = inputfield.attr("name");
                if(fieldname != undefined) {
				    inputfield.after("<div>").next().text(fieldname+" can contain only numbers").addClass("error");
                } else {
                    inputfield.after("<div>").next().text("Must be numberic").addClass("error").attr("style","display:block");
                }
                flag = false;
			} else {

			}
        },
        
        isNotBlank: function() {
			if(content.length === 0) {
				inputfield.addClass("error-highlight");
                var fieldname = inputfield.attr("name");
                if(fieldname != undefined) {
				    inputfield.after("<div>").next().text(fieldname+" is Mandatory").addClass("error");
                } else {
                    inputfield.after("<div>").next().text("Must have a value").addClass("error").attr("style","display:block");
                }
                flag = false;
                return false;
			} else {
                return true;
			}
        },
        
        validateSelect: function(select) {
        	var value = $(select).val();
        	if(value === 'default') {
        		$(select).addClass('error-highlight');
        		$(select).after("<div>").next().text("Select an Option").addClass("error");
        	}
        },
        
        removeError: function() {
            $('.error').remove();
        },
        
        removeErrorHighlight: function() {
            $('.error-highlight').removeClass();
        },
        checkUnsavedInputs: function() {
        	var input = $('input');
        	var textarea = $('textarea');
        	var select = $('select');
        	if(input.length > 0 || textarea.length > 0 || select.length > 0) {
        		return true;
        	} else {
        		return false;
        	}
        }
    }
})();