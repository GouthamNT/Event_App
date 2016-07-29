(function(){
    $('li#login').on('click',function(){
        $('div#modal-login').removeClass("hide");
        $('div#login-content').removeClass("hide");
        validator.removeError();
        validator.removeErrorHighlight();
    });
    
    $('div#modal-login').on('click',function(){
        $('div#modal-login').addClass("hide");
        $('div#login-content').addClass("hide");
    })
})();

var modal = (function() {
	return {
		showError: function(message) {
			var body = $('body');
            var modalErrBg = getTemplate.modalBg();
            var modalErrMsg = getTemplate.modalContent();
            
            message = {
            	Message:message	
            };
            
            body.append(Mustache.to_html(modalErrBg));
            body.append(Mustache.to_html(modalErrMsg,message))
            modal.removeModal();
		},
		
		showSuccess:function(message) {
			var body = $('body');
            var modalBg = getTemplate.modalBg();
            var modalSuccessMsg = getTemplate.modalContent();
            
            message = {
            	Message:message	
            };
            
            body.append(Mustache.to_html(modalBg));
            body.append(Mustache.to_html(modalSuccessMsg,message))
            modal.removeModal();
		},
		
		removeModal:function() {
			$('.modal-bg').on('click',function(){
				$('.modal-bg').remove();
				$('.modal-content').remove();
			});
		}
	};
})();