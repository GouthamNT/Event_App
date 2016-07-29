var courseNav = document.querySelector('#content-header ul li #course'),
    authorNav = document.querySelector('#content-header ul li #author'),
    reviewNav = document.querySelector('#content-header ul li #review'),
    recruiterNav = document.querySelector('#content-header ul li #recruiter'),
    contactNav = document.querySelector('#content-header ul li #contact'),
    
    nav = [courseNav,authorNav,reviewNav,recruiterNav,contactNav];
    
    courseContent = document.querySelector('#about-courses'),
    authorContent = document.querySelector('#about-authors'),
    reviewContent = document.querySelector('#about-review'),
    recruiterContent = document.querySelector('#about-recruiter'),
    contactContent = document.querySelector('#about-contact'),
    
    content = [courseContent,authorContent,reviewContent,recruiterContent,contactContent];


courseNav.addEventListener('click',function () {
    
    navHighlight(nav,courseNav.getAttribute('id'));
    contentDisplay(content,courseContent.getAttribute('id'));
});

authorNav.addEventListener('click',function () {
    
    navHighlight(nav,authorNav.getAttribute('id'));
    contentDisplay(content,authorContent.getAttribute('id'));
});

reviewNav.addEventListener('click',function () {
    navHighlight(nav,reviewNav.getAttribute('id'));
    contentDisplay(content,reviewContent.getAttribute('id'));
});

recruiterNav.addEventListener('click',function () {
    navHighlight(nav,recruiterNav.getAttribute('id'));
    contentDisplay(content,recruiterContent.getAttribute('id'));
});

contactNav.addEventListener('click',function () {
   navHighlight(nav,contactNav.getAttribute('id'));
    contentDisplay(content,contactContent.getAttribute('id'));
});

function contentDisplay(content,id) {
    for(var i = 0; i<content.length; i++) {
        var temp = content[i].classList;
        if(content[i].getAttribute('id') === id) {
            content[i].classList.remove('hide');
        } else {
            if(!(temp.contains('hide'))) {
                temp.add('hide');
            }
        }
    }
}

function navHighlight(nav,id) {
    for(var i = 0; i<nav.length; i++) {
        var temp = nav[i].classList;
        if(nav[i].getAttribute('id') === id) {
            temp.add('active');
        } else {
            temp.remove('active');
        }
    }
}
