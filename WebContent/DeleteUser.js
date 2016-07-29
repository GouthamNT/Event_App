var deleteUser = (function() {    
    var searchForm = $('form#search'),
        searchInput = $('input#searchUser'),
        searchButton = $('#searchButton');
    
    return {
        initialize: function() {
            deleteUser.activateSearch();
            deleteUser.addSearchEvent();
        },
        
        activateSearch: function() {
            searchInput.keyup(highlightEvent);
            
            function highlightEvent() {
                if(searchInput.val() == '') {
                    searchButton.removeClass('highlight');
                } else {
                    searchButton.addClass('highlight');
                }
                
            };
        },
        
        addSearchEvent:function() {
            searchButton.on('click',searchButtonEvent);
            
            function searchButtonEvent(){
                var keyword = searchInput.val();
                var dynamicField = '<input type=hidden name=searchKeyword value=' + keyword +'>';
                searchForm.append(dynamicField);
                searchForm.submit();
            };
        }
        
    };
    
})();

(function() {
    deleteUser.initialize();
})();