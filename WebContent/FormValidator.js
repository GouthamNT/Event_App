(function() {
    var inputfield = $('input'),
        textareafield = $('textarea'),
    	select = $('select');
    
    if(textareafield.length > 0) {
        inputfield.push(textareafield);
    }

    $('button').on('click',function(){
        var flag = validator.validateField(inputfield);
        var selectFlag = validator.validateSelect(select);
        if(flag) {
            $('form').submit();
        }
    });
})();