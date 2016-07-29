var getTemplate = (function() {
	return {
		modalBg: function() {
			var modalBg = '<div class="modal-bg"></div>';
            
            return modalBg;
        },
        modalContent: function() {
			var modalContent = '<div class="modal-content">'
                + '<div class=horizontal-line></div>'
                +    '<div><p>{{Message}}</p></div>'
             + '</div>';
			
			return modalContent;
		},
        
        adminHome: function() {
        	var homeContent = '<a href=CreateAdmin.jsp class=href-button>Create Admin User</a>'
        		 + '<a href=StudentRegistration.jsp class=href-button>Create Student</a>'
        		 + '<a href=AuthorRegistration.jsp class=href-button>Create Author</a>'
        		 + '<a href=EventCreation.jsp class=href-button>Create Event</a>'
                 + '<a href=DeleteUser.jsp class=href-button>Delete User</a>';
            
            return homeContent;
        }
	};
})();