
//header top link and categories switch to slide toggle

$(".mob-catgbox-head").click(function(){
		$(".list-cont").slideToggle();
	});	
	
$(".catg-box-inner h1").click(function(){
		$(".catg-box-inner .list-cont").slideToggle();
	});		
	
$("#linkButton").click(function(){
		$(".top-links ul").slideToggle();
	});	

$(window).resize(function() {
  var width =$(window).width();
  if(width<=400)
  {
  	$(".top-links ul").hide();
  }
  if(width>400)
  {
  	$(".top-links ul").show();
	$(".catg-box .list-cont").show();
  }
  
  if(width<=991)
  {
	  $(".catg-box .list-cont").hide();
  }
  
  if(width>991)
  {
	 $(".catg-box .list-cont").show(); 
  }
  
});

$("#linkButton").click(function(){
	$(this).find("i").toggleClass("deg-90-tarnsform");
});

$(".mob-catgbox-head").click(function()
{
	$(this).find("i").toggleClass("deg-90-tarnsform");
});
$(".catg-box-inner").click(function()
{
	$(this).find("i").toggleClass("deg-90-tarnsform");	
});


//mega-menu

$(function() {
  var headings = $(".overlapblackbg, .slideLeft");
  var emptyJ = $(".wsmenucontent");
  /**
   * @return {undefined}
   */
  var backdrop = function() {
    $(headings).removeClass ("menuclose").addClass ("menuopen");
  };
  /**
   * @return {undefined}
   */
  var _element = function() {
    $(headings).removeClass ("menuopen").addClass ("menuclose");
  };
  $("#navToggle").click (function() {
    if (emptyJ.hasClass ("menuopen")) {
      $(_element);
    } else {
      $(backdrop);
    }
  });
  emptyJ.click (function() {
    if (emptyJ.hasClass ("menuopen")) {
      $(_element);
    }
  });
  $("#navToggle,.overlapblackbg").on ("click", function() {
    $(".wsmenucontainer").toggleClass ("mrginleft");
  });
  $(".wsmenu-list li").has (".wsmenu-submenu, .wsmenu-submenu-sub, .wsmenu-submenu-sub-sub").prepend ("<span class='wsmenu-click'><i class='wsmenu-arrow fa fa-angle-down'></i></span>");
  $(".wsmenu-list li").has (".megamenu").prepend ("<span class='wsmenu-click'><i class='wsmenu-arrow fa fa-angle-down'></i></span>");
  $(".wsmenu-mobile").click (function() {
    $(".wsmenu-list").slideToggle ("slow");
  });
  $(".wsmenu-click").click (function() {
    $(this).siblings (".wsmenu-submenu").slideToggle ("slow");
    $(this).children (".wsmenu-arrow").toggleClass ("wsmenu-rotate");
    $(this).siblings (".wsmenu-submenu-sub").slideToggle ("slow");
    $(this).siblings (".wsmenu-submenu-sub-sub").slideToggle ("slow");
    $(this).siblings (".megamenu").slideToggle ("slow");
  });
});


//tooltip
	
$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip(); 
}); 
	
//masonry js
$(window).load(function(){ $('#masonry-container').masonry({
	itemSelector : '.grid-item'
}); });


// custom select box 
 
 $('.select-wrap').on('click','.placeholder',function(){  var parent = $(this).closest('.select-wrap');  if ( ! parent.hasClass('is-open')){    parent.addClass('is-open');    $('.select-wrap.is-open').not(parent).removeClass('is-open');  }else{    parent.removeClass('is-open');  }}).on('click','ul>li',function(){  var parent = $(this).closest('.select-wrap');  parent.removeClass('is-open').find('.placeholder').text( $(this).text() );  parent.find('input[type=hidden]').attr('value', $(this).attr('data-value') );});
 
 
// + and - qty	
	
$(".qtyplus").on("click", function() {
	
var item_no = $(this).attr('item_no');
var $button = $(this);
var oldValue = $(".qtyfield"+item_no).val();

  var newVal = parseFloat(oldValue) + 1;
$(".qtyfield"+item_no).val(newVal);

});

$(".qtyminus").on("click", function() {
var item_no = $(this).attr('item_no');
var $button = $(this);
var oldValue = $(".qtyfield"+item_no).val();
if(oldValue == 1)
return;
  var newVal = parseFloat(oldValue) - 1;
$(".qtyfield"+item_no).val(newVal);

}); 

	