$(document).scroll(function(){
	$('.navbar-inverse').toggleClass('scrolled', $(this).
	scrollTop() > $('.navbar-inverse').height());
	
  });

  