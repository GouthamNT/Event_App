function init() {
    var down_vote = $('table#input-attribute tbody tr td span[id^=down-vote]'),
        up_vote = $('table#input-attribute tbody tr td span[id^=up-vote]'),
        rating_content;
    
    down_vote.on('click',function(event) {
        var index = $(event.currentTarget).attr("index");
        var rating =  $('table#input-attribute tbody tr td span#rating_'+index);
        var current = rating.text();
        if(current > 1) {
            rating.text(--current);
            rating_content = rating.text();
        } else {
            rating.text(current);
        }
    });
    
    up_vote.on('click',function(event) {
        var index = $(event.currentTarget).attr("index");
        var rating =  $('table#input-attribute tbody tr td span#rating_'+index);
        var current = rating.text();
        if(current < 10) {
            rating.text(++current);
            rating_content = rating.text();
        } else {
            rating.text(current);
        }
    });
    
    $('button#confirm').on('click',function(event){
       var index = $(event.currentTarget).attr("index"),
           data,
           row = $('#input-attribute tbody tr');
        
        var session = row.attr('data-session_'+index),
            rating = row.find('td span#rating_'+index).text();
        $('.error').remove();
        if(rating < 1) {
            $(this).after("<div>").next().text("Must be greater than 0").addClass("error").attr("style","display:block");
        }
        data = {
            sessionid:session,
            rating:rating
        };
        $.ajax({
            url:"RatingController",
            method:"post",
            data:data,
            success:function() {
                row.find('td #confirm[index='+index+']').remove();
                row.find('td span#up-vote_'+index).remove();
                row.find('td span#down-vote_'+index).remove();
            },
            error:function(){
                
            }
        });
    });
}

init();