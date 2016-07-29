(function (){
    $('li button').on('click',function(){
        $('li button').removeClass('active');
        $(this).addClass('active');
    }); 

    function adminHome() {
    	var template = getTemplate.adminHome();
        
        $('#content-data').html(Mustache.to_html(template));
    }
    
    adminHome();
    
    $('#Home').on('click',adminHome);
})();